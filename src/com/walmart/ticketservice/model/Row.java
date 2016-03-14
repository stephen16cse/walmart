package com.walmart.ticketservice.model;

import java.util.List;

/**
 * This model holds row ID and List of seats in each row.
 *
 */
public class Row {
	
	int row_id;
	int available_SeatsInRow;  // This is not utilized in this sample, but will be beneficial in real time app.
	List <Seat> seatList;
	
	/**
	 * @return the row_id
	 */
	public int getRow_id() {
		return row_id;
	}
	/**
	 * @param row_id the row_id to set
	 */
	public void setRow_id(int row_id) {
		this.row_id = row_id;
	}
	/**
	 * @return the available_SeatsInRow
	 */
	public int getAvailable_SeatsInRow() {
		return available_SeatsInRow;
	}
	/**
	 * @param available_SeatsInRow the available_SeatsInRow to set
	 */
	public void setAvailable_SeatsInRow(int available_SeatsInRow) {
		this.available_SeatsInRow = available_SeatsInRow;
	}
	/**
	 * @return the seatList
	 */
	public List<Seat> getSeatList() {
		return seatList;
	}
	/**
	 * @param seatList the seatList to set
	 */
	public void setSeatList(List<Seat> seatList) {
		this.seatList = seatList;
	}
	
	

}
