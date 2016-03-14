package com.walmart.ticketservice.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.walmart.ticketservice.model.Row;
import com.walmart.ticketservice.model.Seat;
import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.VenueDetails;
import com.walmart.ticketservice.util.InitialData;
import com.walmart.ticketservice.util.TicketUtil;

/**
 * This class is declared at top with venueMap as concurrent Map, but not used in this sample implementation. 
 * but in real time can be used to maintain List of Hold seats and Available seats in MultiThreaded, Map can be a shared resource with caution.
 * List is used as Data structure to hold Seats to retrieve quick access, Map is used to jump directly to the level Id.
 *
 */
public class TicketProcessor {

	private Map<Integer, VenueDetails> venueMap = new ConcurrentHashMap<>(); // This is not used in this sample, But will be beneficial if want to run multiThreaded real time app.

	/**
	 * This method will return Number of Available seats at any given time by optionally provided Venue level.
	 * But in this sample assumed that data will be fed and filled in all the Model created, InitialData class can be modified to give different data set.
	 * In real time number of seats variable can be maintained in Venue level and Row level to get quick access, but in this sample not implemented.
	 * 
	 * As mentioned above, in real time we dont need to iterate every time to get available seats unlike below iteration. 
	 * but can be easily done by maintaining a variable having latest count at any given time in MultiThreaded app.
	 * Also single variable can be maintained at class level for easy access to multiple threads, Here we are not considering all those.
	 * 
	 * @param minLevelId Integer min Level Id is accepted
	 * @param maxLevelId Integer max Level Id is accepted
	 * @return Number of seats available for a given venue level as optional
	 */
	public static int checkAvailableSeats(Integer minLevelId, Integer maxLevelId) throws Exception {  // signature is not declared final, bez values are needed change.
		
		// Below line will load initial data by running some loop to load data into models List, before calculating available seats.
		List< VenueDetails> venueList = InitialData.loadVenueList();  
		int availableSeats = 0;
		
		// Below If else are written to access List on their Array location starting from 0.
		// Basically if input comes as level Id 1 to 3, then to access list in Array below logic changing to 0 to 2.
		if(minLevelId == 0 && maxLevelId == 0){
			maxLevelId = venueList.size();
		} else if (maxLevelId == 0) {
			maxLevelId = minLevelId;
			minLevelId = minLevelId -1;
		} else {
			minLevelId = minLevelId-1;
		}
		
		// Here Loop is running from requested Level Id as Min to Max to get total available tickets.
		// If Max value sent as 0 then loop will run only once for the given min level, else will run from 0 to list size.
		for(int i=minLevelId; i<maxLevelId; i++)
		for(Row row : venueList.get(i).getRowsList()) { // Iteration Row List then Seat list in each Row
			for(Seat seat : row.getSeatList()) {
				// Here available seats are counted based on the status of each Seat - Possible values are : AVL / HOLD / RECERVED
				if(seat.getSeatStatus().equals("AVL")) { // AVL can be referred from Constants class or Interface if time available.
					availableSeats++;
				}
			}
		}
		// Returning available seats calculated from the above iteration, But in real time we can place this value in class level as Thread safe.
		return availableSeats;  
	}
	
	
	/**
	 * This method is the core of this application to find available seats for the requested number of seats and HOLD them for 2 minutes (assumption)
	 * 
	 * @param numSeats - Number of requested seats for HOLD if available.
	 * @param minLevel - minimum level id requested to check.
	 * @param maxLevel - maximum level id requested to check.
	 * @param customerEmail - Given email is used to put against HOLD seat to future reference.
	 * @return - SeatHold Object containing seatHOldId and list of Seat object put on HOLD.
	 * @throws Exception - To Keep it simple UserDefined Exceptions are not created, just leveraged Java Exception class is used to throw.
	 */
	public static SeatHold findAndHold(final int numSeats, int minLevel,
			int maxLevel, final String customerEmail) throws Exception {
		
		int seatCount = 0;
		SeatHold seatHold = null;
		List <Seat> holdSeatList = new ArrayList<>();
		List <Row> rowsList;
		// Unfortunately we needed to call the previous method to get available seats before we proceed for HOLD,
		// but in real time as mentioned above, if class level variable holds the available seats for any given time, 
		// that would be really helpful in multi threaded app to reduce time consuming.
		int availableSeats = checkAvailableSeats(minLevel, maxLevel); 
		
		List< VenueDetails> venueList = InitialData.loadVenueList(); // Here loading all models to fill seats and current status, Here if we can use concurrent Map to refer real time.
		
		if(availableSeats >= numSeats) { // Checking before proceeding further, Total available tickets are good enough than request number of tickets.
			
			
			// Below If else are written to access List on their Array location starting from 0.
			// Basically if input comes as level Id 1 to 3, then to access list in Array below logic changing to 0 to 2.
			if(minLevel == 0 && maxLevel == 0){
				maxLevel = venueList.size();
			} else if (maxLevel == 0) {
				maxLevel = minLevel;
				minLevel = minLevel -1;
			} else {
				minLevel = minLevel-1;
			}
			
			// below loop variable is created to break out of all loops once required seats are put on HOLD and set in list to send back.
			outerLoop :
			for(int i = minLevel; i<maxLevel; i++) {
				rowsList = venueList.get(i).getRowsList();
				
				for(int k = 0; k < rowsList.size(); k++) {
					for(int j = 0; j < rowsList.get(k).getSeatList().size(); j++) { // Iterates until seats object to check status AVL to change if yes.
						
						Seat seat = rowsList.get(k).getSeatList().get(j);
						if(seat.getSeatStatus().equals("AVL")) {
							
							// When AVL ticket status is found, blocking the seat with HOLD status and holdTime on customer Email.
							seat.setSeatStatus("HOLD");
							seat.setHoldTime(new Date());
							seat.setCustomerEmail(customerEmail);
							seatCount++;  // This variable is to count till the number of requested seats for HOLD.

							holdSeatList.add(seat);
							
							if(seatCount == numSeats) {
								break outerLoop;  // Once all requested seats are put on HOLD, break all loops for further processing.
							}
						} else {
							//This could happen in very rare case in MultiThreaded, between the time of AVL status checking and available seats count.
							throw new Exception("No Seats are Available in the requested Levels");
						}
						
				}
			}
		}	
			
			seatHold = new SeatHold();
			int randomId = TicketUtil.randomHoldId();
			seatHold.setSeatHoldId(randomId);
			seatHold.setHoldSeatsList(holdSeatList);  // SeatHold Object is set with list of seats put on HOLD and hold ID.
			
			expireHoldSeats(holdSeatList);  // Creating new Thread to hold the current SeatHold until 2 min, then release back to AVL.
			
	} else {
		//When number of seats available less then requested Send back INFO message to user with available seats count.
		throw new Exception("Number of Available Seats are less then requested Number ... Try again with available seats:" + availableSeats);
	}
		
		return seatHold; //returning SeatHold Object is set with list of seats put on HOLD and hold ID.
	}
	
	
		/**
		 * This is the important method to Keep the ticket on HOLD for given time and then will release back to AVL if not utilized.
		 * @param seatHoldList - This holds the list of seats put on Hold in Main Thread.
		 * This method return nothing as its going to run as a separate new thread and no need to return anything to user.
		 */
		public static void expireHoldSeats(final List <Seat> holdSeatList) {
			
			// Creating new Runnable Thread from the Main Thread and put it on sleep for given time
			new Thread(new Runnable()
		    {
		        @Override
		        public void run() {
		        	try {
						Thread.sleep(120000);  // Newly initiated thread will be on Sleep for 2 minutes, THis can be made as configurable if needed.
						for(Seat seat : holdSeatList) {
							if (seat.getSeatStatus().equals("HOLD")) { // Only if seats are in HOLD then will be released back to AVL.
								seat.setSeatStatus("AVL");
								seat.setCustomerEmail("");
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		            
		        }
		    }).start();
		}
		
		
		/**
		 * This method is to reserve the Tickets which were put on HOLD before, if not expired yet.
		 * @param seatHoldId - Uniquely identified integer received during Ticket HOLD.
		 * @param customerEmail - Customer Email given while Ticket put on HOLD.
		 * @return Confirmation Alpha Numeric after seats are reserved successfully.
		 * @throws Exception - Throws if no matching Email and holdID found.
		 */
		public static String reserveTickets(final int seatHoldId, final String customerEmail) throws Exception {
			
			SeatHold seatHold = InitialData.loadHoldData(seatHoldId, customerEmail);  // Assumption taken that some Seats are put on hold, Load required data from any source to fill data models, In real time we can use Concurrent Map as shared resource to fetch List of HOLD seats in the app.
			String confirmation = null;
			if(null != seatHold && seatHold.getSeatHoldId() == seatHoldId)  // Checking seatHOldID matching with input, In real time we can iterate from List of SeatHold to match given input.
				if(seatHold.getHoldSeatsList().size() > 0) {
					for(Seat seat : seatHold.getHoldSeatsList()) { // Iterating list of seats put on HOLD 
						if(seat.getCustomerEmail().equals(customerEmail) && seat.getSeatStatus().equals("HOLD")) {   // CHecking HOLD status and customer email matching before reserving correct seats.
							seat.setSeatStatus("RECERVED");
							seat.setReservedTime(new Date());
							confirmation = TicketUtil.createConfirmation(); // Creating 5 Digit Alpha numeric confirmation.
							seat.setConfirmationNumber(confirmation);
							} else {
								throw new Exception("No matching HoldID found, Try with Different holdId or customerEmail");
							}
					}
				} else {
					throw new Exception("No matching HoldID found, Try with Different holdId or customerEmail");
				}
			return confirmation;
		}
		
	
}
