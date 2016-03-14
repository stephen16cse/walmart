package com.walmart.ticketservice.model;

import java.util.List;

/**
 * This model holds seatHold Id and list of Seats object put on Hold.
 *
 */
public class SeatHold {

	int seatHoldId;
	List <Seat> holdSeatsList;
	
	/**
	 * @return the seatHoldId
	 */
	public int getSeatHoldId() {
		return seatHoldId;
	}
	/**
	 * @param seatHoldId the seatHoldId to set
	 */
	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
	/**
	 * @return the holdSeatsList
	 */
	public List<Seat> getHoldSeatsList() {
		return holdSeatsList;
	}
	/**
	 * @param holdSeatsList the holdSeatsList to set
	 */
	public void setHoldSeatsList(List<Seat> holdSeatsList) {
		this.holdSeatsList = holdSeatsList;
	}
	
	

}
