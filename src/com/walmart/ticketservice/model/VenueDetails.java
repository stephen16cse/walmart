package com.walmart.ticketservice.model;

import java.util.List;

/**
 * This model will hold individual Venue Details with list of Rown in it.
 *
 */
public class VenueDetails {
	
	int levelId;
	String levelName;
	int price;
	int total_no_of_rows; // This can be predefined.
	int total_no_of_seats; // This can be predefined.
	List <Row> rowsList;  // This is the holds list of Rows in a Venue, mainly used in this sample.
	int available_SeatsIn_Venue;   // This variable is not used in this sample, But well worth if we run multiThreaded to hold the value for easy access at any time.
	
	/**
	 * @return the levelId
	 */
	public int getLevelId() {
		return levelId;
	}
	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}
	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the total_no_of_rows
	 */
	public int getTotal_no_of_rows() {
		return total_no_of_rows;
	}
	/**
	 * @param total_no_of_rows the total_no_of_rows to set
	 */
	public void setTotal_no_of_rows(int total_no_of_rows) {
		this.total_no_of_rows = total_no_of_rows;
	}
	/**
	 * @return the total_no_of_seats
	 */
	public int getTotal_no_of_seats() {
		return total_no_of_seats;
	}
	/**
	 * @param total_no_of_seats the total_no_of_seats to set
	 */
	public void setTotal_no_of_seats(int total_no_of_seats) {
		this.total_no_of_seats = total_no_of_seats;
	}
	/**
	 * @return the rowsList
	 */
	public List<Row> getRowsList() {
		return rowsList;
	}
	/**
	 * @param rowsList the rowsList to set
	 */
	public void setRowsList(List<Row> rowsList) {
		this.rowsList = rowsList;
	}
	/**
	 * @return the available_SeatsIn_Venue
	 */
	public int getAvailable_SeatsIn_Venue() {
		return available_SeatsIn_Venue;
	}
	/**
	 * @param available_SeatsIn_Venue the available_SeatsIn_Venue to set
	 */
	public void setAvailable_SeatsIn_Venue(int available_SeatsIn_Venue) {
		this.available_SeatsIn_Venue = available_SeatsIn_Venue;
	}
	
	
	
	
}
