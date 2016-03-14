package com.walmart.ticketservice.util;

import java.util.Random;
import java.util.UUID;

/**
 * Just an Util class for random Id confirmation creation.
 *
 */
public class TicketUtil {

	/**
	 * Creates a random Integer number for seatHold ID.
	 * @return int value
	 */
	public static int randomHoldId() {
	    Random r = new Random( System.currentTimeMillis() );
	    return 10000 + r.nextInt(200000);
	}

	/**
	 * Creates confirmation Alpha Numeric - used after successful reservation.
	 * @return String as confirmation.
	 */
	public static String createConfirmation() {
		String uuid = UUID.randomUUID().toString();
	    return uuid.substring(1,6);
	}
}
