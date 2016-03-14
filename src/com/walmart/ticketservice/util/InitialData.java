package com.walmart.ticketservice.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.walmart.ticketservice.model.Row;
import com.walmart.ticketservice.model.Seat;
import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.VenueDetails;

/**
 * 
 */

/**
 * This class is just for to load Initial data for processing, same is used for Junit as well
 *
 */
public class InitialData {
	
	/**
	 * This method loads list of Venue list by adding rows and seats lists in it.
	 * It is used for findAndHOld and NumAvailableSeats method.
	 * @return List <VenueDetails> is returned with filled data, In real time can be fetched for any source.
	 */
	public static List <VenueDetails> loadVenueList() {
		
		List <VenueDetails> venueList = new ArrayList<>();
		List <Row> rowList = new ArrayList<>();
		List <Seat> seatList = new ArrayList<>();
		
		VenueDetails venue;
		Row row;
		Seat seat;
		
		for(int k = 1; k <= 3; k++){
			rowList.clear();
			for(int j = 1; j <= 5; j++) {
				
				seatList.clear();
				for(int i = 1; i <= 10; i++) {
					seat = new Seat();
					seat.setSeatId(i);
					seat.setSeatStatus("AVL");
					seatList.add(seat);
				}
				row = new Row();
				row.setRow_id(j);
				row.setSeatList(seatList);;
				rowList.add(row);
			}	
			venue = new VenueDetails();
			venue.setLevelId(k);
			venue.setPrice(k*100);
			venue.setRowsList(rowList);
			venueList.add(venue);
			
			
		}
		return venueList;
		
	}
	
	/**
	 * This method is to load couple of Seat objects list into SeatHold object - This is used for reserve tickets method.
	 * @param seatHoldId
	 * @param customerEmail
	 * @return
	 */
	public static SeatHold loadHoldData(int seatHoldId, String customerEmail){
		
		List <Seat> seatHoldList = new ArrayList<>();
	
		Seat seat1, seat2;
		
		seat1 = new Seat();
		seat1.setSeatId(12);
		seat1.setSeatStatus("HOLD");
		seat1.setHoldTime(new Date());
		seat1.setCustomerEmail("abc@gmail.com");
		
		seat2 = new Seat();
		seat2.setSeatId(45);
		seat2.setSeatStatus("HOLD");
		seat2.setHoldTime(new Date());
		seat2.setCustomerEmail("another@email.com");
				
		seatHoldList.add(seat1);
		seatHoldList.add(seat2);
		
		SeatHold seatHold = new SeatHold();
		seatHold.setSeatHoldId(seatHoldId);
		seatHold.setHoldSeatsList(seatHoldList);
		
		return seatHold;
		
	}

}
