package com.walmart.ticketservice.model;

import java.util.Date;

/**
 * This model is used to hold seat Id, status, hold time, customerEmail, reservedTime at any given time to access seat status.
 *
 */
public class Seat {
	
	int seatId;
	int rowId;		// Its not used in this sample, but would be beneficial in real time app.
	String seatStatus = "AVL"; // Possible Options are : AVL / HOLD / RECERVED
	String customerEmail;
	String confirmationNumber;
	Date holdTime;
	Date reservedTime;
	
	/**
	 * @return the seatId
	 */
	public int getSeatId() {
		return seatId;
	}
	/**
	 * @param seatId the seatId to set
	 */
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	/**
	 * @return the seatStatus
	 */
	public String getSeatStatus() {
		return seatStatus;
	}
	/**
	 * @param seatStatus the seatStatus to set
	 */
	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}
	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	/**
	 * @return the confirmationNumber
	 */
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	/**
	 * @param confirmationNumber the confirmationNumber to set
	 */
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	/**
	 * @return the holdTime
	 */
	public Date getHoldTime() {
		return holdTime;
	}
	/**
	 * @param holdTime the holdTime to set
	 */
	public void setHoldTime(Date holdTime) {
		this.holdTime = holdTime;
	}
	/**
	 * @return the reservedTime
	 */
	public Date getReservedTime() {
		return reservedTime;
	}
	/**
	 * @param reservedTime the reservedTime to set
	 */
	public void setReservedTime(Date reservedTime) {
		this.reservedTime = reservedTime;
	}
	
	

}
