import org.junit.Test;

import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.services.TicketService;
import com.walmart.ticketservice.services.TicketServiceImpl;

/**
 * This is a Junit Class to run some Negative flow to test all 3 methods.
 * Uncomment @Test line above any method to run that method, make sure all other @Test are commented. 
 * As per InitialData class, 150 is the total number of seats for all 3 levels.
 * SeatHold ID is set as 123 in InitialData class for positive testing.
 * INFO log message will be printed in Console logs.
 */
public class TicketServiceNegativeTests {



    /**
     * This method is to test numSeatsAvailable method in Service class by passing null as level id.
     * Output is printed simply in Sysout, not checking against any assert method here in this sample.
     */
	//@Test
    public void testAvailableSeats() {
		
     TicketService ticketService = new TicketServiceImpl();
     
     int numSeats = ticketService.numSeatsAvailable(null);
     
     System.out.println("Number of Seats Available: "+ numSeats);

    }
	
	/**
	 * This method is to test findAndHoldSeats method in Service class by some input.
     * Output is printed simply in Sysout, not checking against any assert method here in this sample.
	 */
	//@Test
    public void testSeatHold() {
		
     TicketService ticketService = new TicketServiceImpl();
     
     SeatHold seatHold = ticketService.findAndHoldSeats(151, null, null, "abc@gmail.com");
     
     System.out.println("Seat Hold Id: "+ seatHold.getSeatHoldId());

    }
	
    /**
	 * This method is to test reserveSeats method in Service class by some email id other than abc@gmail.com
     * Output is printed simply in Sysout, not checking against any assert method here in this sample.
     */
	@Test
    public void testReserveSeat() {
		
     TicketService ticketService = new TicketServiceImpl();
     
     String confirmation = ticketService.reserveSeats(124, "different@gmail.com");  // You can pass other than 123 or other email to test.
     
     System.out.println("Confirmation Number is : "+ confirmation);

    }

}
