package AirplaneReservation;

public class SeatsFullException extends Exception {

	/*
	 * overrides exception super class to create a custom exception.
	 * 
	 * @constructor
	 */
	public SeatsFullException() {
		super("Not enough seats!");
	}

}

