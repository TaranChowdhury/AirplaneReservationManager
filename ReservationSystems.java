package AirplaneReservation;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReservationSystems {

	/*
	 * Main method
	 * handles all of the user - system interaction.
	 *handles txt input/output file as command line argument 
	 *passes user input to appropriate classes to make the program run.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		ReservationManager rm = new ReservationManager(args[0]);
		Scanner input = new Scanner(System.in);

		rm.initialize();
		System.out.println("Welcome to BlueBird Airlines reservation System.");
		System.out.println("Please select one of the following options: \n");
		System.out.println(
				"Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
		String function = input.nextLine();

		while (!function.toUpperCase().equals("Q")) {
			switch (function.toUpperCase()) {
			case "P":
				System.out.print("\nPassenger Name: ");
				String name = input.nextLine();
				System.out.print("Service Class: ");
				String classOS = input.nextLine();
				System.out.print("Seat Preference([W]indow, [C]enter or [A]isle): ");
				String seatPref = input.nextLine();
				rm.addPassenger(name, classOS, seatPref);
				System.out.println(
						"\nAdd [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				function = input.nextLine();
				break;
			case "G":
				System.out.print("\nGroup Name: ");
				String gName = input.nextLine();
				System.out.print("Passenger Names (seperate with comas): ");
				String names = input.nextLine();
				System.out.print("Service Class: ");
				String gClassOS = input.nextLine();
				rm.addGroup(gName, names, gClassOS);
				System.out.println(
						"\nAdd [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				function = input.nextLine();
				break;
			case "C":
				System.out.println("\nCancel [I]ndividual or [G]roup? ");
				String cancelRes = input.nextLine();
				if (cancelRes.toUpperCase().equals("I")) {
					System.out.print("Name: ");
					String cancelName = input.nextLine();
					rm.cancelReservation(cancelName);
				} else if (cancelRes.toUpperCase().equals("G")) {
					System.out.print("Group Name: ");
					String groupName = input.nextLine();
					rm.cancelReservation(groupName);
				} else {
					System.out.println("Invalid Character!!!");
				}
				System.out.println(
						"\nAdd [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				function = input.nextLine();
				break;
			case "A":
				System.out.print("\nEnter Service class: ");
				String availClassOS = input.nextLine();
				rm.printAvailChart(availClassOS);
				System.out.println(
						"\nAdd [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				function = input.nextLine();
				break;
			case "M":
				System.out.print("\nEnter Service class: ");
				String manifestClassOS = input.nextLine();
				rm.printManifest(manifestClassOS);
				System.out.println(
						"\nAdd [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				function = input.nextLine();
				break;
			default:
				System.out.println("\nInvalid input, please select a valid option from the following: ");
				System.out.println(
						"Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
				function = input.nextLine();
			}
		}
		if (function.toUpperCase().equals("Q")) {
			rm.quit();
		}
	}
}

