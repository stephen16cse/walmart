package com.walmart.ticketservice.services;

import java.util.Optional;
import java.util.logging.Logger;

import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.processor.TicketProcessor;


/**
 * This class is implementing all the given methods from Interface (TicketService).
 * This class jsut act as a Deligate to Processor class and does basic input validation before passing.
 */
public class TicketServiceImpl implements TicketService {
	
	private static final Logger log = Logger.getLogger( TicketServiceImpl.class.getName() );
	
	/* 
	 * This method just checks the input for null and pass it to Processor class for further action.
	 * Returns the available Seats to the user for the givel level id.
	 * This method catches any exception and prints in Log, but in real time we can report to user in any form.
	 * 
	 */
	@Override
	public int numSeatsAvailable(final Optional<Integer> venueLevel) {
		int availableSeats = 0;
		try {
			if(null != venueLevel) {
				availableSeats = TicketProcessor.checkAvailableSeats(venueLevel.get(), 0); // Second value passed in this method is to limit the redundant code at
			}
			else {
				availableSeats = TicketProcessor.checkAvailableSeats(0, 0); // Passing Zero if nothing is passed, rest is handled in Processor.
			}
		} catch(Exception ex) {
			log.info(ex.getMessage());
		}
		return availableSeats;

		
	}

	/* 
	 *  This method is used to find and HOLD seats for a customer.
	 *  This method just sends all inputs to Processor class for further processing after basic input validation.
	 *  This method catches any exception and prints in Log, but in real time we can report to user in any form.
	 */
	@Override
	public SeatHold findAndHoldSeats(final int numSeats, final Optional<Integer> minLevel,
			final Optional<Integer> maxLevel, final String customerEmail) {
		
		
		SeatHold seatHold = null;
		try {
			seatHold = TicketProcessor.findAndHold(numSeats, (null != minLevel ? minLevel.get() : 0), (null != maxLevel ? maxLevel.get() : 0), customerEmail);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	
		return seatHold;
	}

	/* 
	 * This method reserve Seats for the given seat Hold ID and customer Email.
	 * Just delegats input to Processor class for reserving tickets after basic validation.
	 * This method catches any exception and prints in Log, but in real time we can report to user in any form.
	 */
	@Override
	public String reserveSeats(final int seatHoldId, final String customerEmail) {
		
		String confirmation = null;
		if(seatHoldId > 0 && null != customerEmail) {
			
			try {
				confirmation = TicketProcessor.reserveTickets(seatHoldId, customerEmail);
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		} else {
			log.info("Please provive valid input");
		}
		
		
		
		return confirmation;
	}
}
