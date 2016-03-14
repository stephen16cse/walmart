**********************************
*  Walmart Technologies Coding Assignment
*  TicketService Homework
*
*  Stephen     03/14/2016
**********************************

As per the requirement, build 3 functions for Ticket Service.

	1. Number of Available Tickets
	2. Find and Hold Seats
	3. Reserve Tickets.
	
	
Assumptions or Considerations:
		
	This Sample module is built with the mind set of not to complicate too much with design or code and kept everything simple and clear for running 3 methods to get desired output.
	
	Initial data is loaded from Util package with InitialData class, just by iterating few for loops to load data into Java models to proceed.
	
	Data models are defines in a package called model.
	
	TicketProcessor is the one does the actual logic for all 3 methods.
	
	Not created any UserDefined Exception class to handle, like said above kept things simple as possible. 
	
	Logging is done with very minimal, assuming intention is only to get the logic written correctly.
	
	Given class level and method level comments in detail as needed, also given some line level comments for easy understanding.
	
	Only 2 Junit classes are written to test one Positive and Negative scenarios for all 3 methods, bez of limited time.
	
	Last but not least, methods can be tested not sequentially, but independently, meaning second run method will not consider first ran method results.
	
	
Steps to Build and Run project:
	
	Import Project into any Developer tools like Eclipse which supports Java 8.
	
	Define Class Path to compile from Java 8 from src and test package of all Java classes.
	
	Go to test Package and run TicketServicePositiveTests with different methods.
	
	Go to test package and run TicketServiceNegativeTests with different methods.
	
	Output will be printed in console, error will be printed in console as Info logs.