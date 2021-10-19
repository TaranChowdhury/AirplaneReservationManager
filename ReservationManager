package AirplaneReservation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationManager {
	private PassengerInfo[][] econ = new PassengerInfo[20][6];
	private PassengerInfo[][] first = new PassengerInfo[2][4];
	private String dbFilePath;

	PassengerInfo pi = new PassengerInfo();
	ReservationArchive ra;

	/*
	 * @Constructor handles command line argument txt file. initializes
	 * ReservationArchive with the output/input file path.
	 * 
	 * @param dbFilePath file path to the command line argument file.
	 */
	public ReservationManager(String dbFilePath) {
		this.dbFilePath = dbFilePath;
		this.ra = new ReservationArchive(this.dbFilePath);
	}

	/*
	 * returns seat where the passenger is seated.
	 * 
	 * @param row row of 2d array
	 * 
	 * @param col column of 2d array
	 * 
	 * @returns result seat number based on row and column
	 */
	public String getSeat(int row, int col) {
		String result = "";
		switch (col) {
		case 0:
			result = "" + row + "" + 'A';
			break;
		case 1:
			result = "" + row + "" + 'B';
			break;
		case 2:
			result = "" + row + "" + 'C';
			break;
		case 3:
			result = "" + row + "" + 'D';
			break;
		case 4:
			result = "" + row + "" + 'E';
			break;
		case 5:
			result = "" + row + "" + 'F';
			break;
		}
		return result;
	}

	/*
	 * reserves seat for individual passenger. assigns seat based on class of
	 * service.
	 * 
	 * @param namevname of passenger
	 * 
	 * @param classOS class of service the passenger prefers
	 * 
	 * @param seatPref seat preference of passenger window, center or aisle
	 */
	public void addPassenger(String name, String classOS, String seatPref) {
		PassengerInfo pi = new PassengerInfo();
		pi.setName(name);
		pi.setClassOS(classOS);
		pi.setGroupName(name);
		boolean seatFound = false;

		int colStart = 0;
		int colLimit = 0;
		int addCol = 0;

		if (classOS.toUpperCase().equals("ECONOMY")) {
			switch (seatPref.toUpperCase()) {
			case "W":
				colStart = 0;
				colLimit = econ[0].length;
				addCol = 5;
				break;
			case "C":
				colStart = 1;
				colLimit = econ[0].length;
				addCol = 3;
				break;
			case "A":
				colStart = 2;
				colLimit = 4;
				addCol = 1;
				break;
			default:
				System.out.println("Incorrect input!!!");
			}
			String seatNum = "";
			for (int row = 0; row < econ.length; row++) {
				for (int col = colStart; col < colLimit; col += addCol) {
					if (econ[row][col] == null) {
						seatNum = getSeat(row + 10, col);
						pi.setRow(row);
						pi.setCol(col);
						econ[row][col] = pi;
						seatFound = true;
						break;
					}
				}
				if (seatFound == true) {
					System.out.println("Preferred seat assigned for " + name + ". Seat: " + seatNum);
					ra.add(pi);
					return;
				}
			}
			System.out.println("preferred seat is not available.");
		}

		else if (classOS.toUpperCase().equals("FIRST")) {
			switch (seatPref.toUpperCase()) {
			case "W":
				colStart = 0;
				colLimit = first[0].length;
				addCol = 3;
				break;
			case "C":
				System.out.println(
						"Error... first class does not have center seats. choose between window and aisle seats");
				return;
			case "A":
				colStart = 1;
				colLimit = 3;
				addCol = 1;
				break;
			default:
				System.out.println("Incorrect input!!!");
			}
			String seatNum = "";
			for (int row = 0; row < first.length; row++) {
				for (int col = colStart; col < colLimit; col += addCol) {
					if (first[row][col] == null) {
						seatNum = getSeat(row + 1, col);
						pi.setRow(row);
						pi.setCol(col);
						first[row][col] = pi;
						seatFound = true;
						break;
					}
				}
				if (seatFound == true) {
					System.out.println("Preferred seat assigned for " + name + ". Seat: " + seatNum);
					ra.add(pi);
					return;
				}
			}
			System.out.println("preferred seat is not available.");
		}
	}

	/*
	 * reserves seats for a group of people. uses recursive call to add people in
	 * group.
	 * 
	 * @param groupName name of group to be added.
	 * 
	 * @param passengerNames name of passengers in the group separated with commas
	 * 
	 * @param classOS class of service the group wants to reserve
	 */
	public void addGroup(String groupName, String passengerNames, String classOS) {
		String[] names = passengerNames.split(",");
		try {
			List<String> seatsAssigned = new ArrayList<String>();
			this.assignGroupSeats(0, classOS, groupName, names, seatsAssigned);
			System.out.println(Arrays.toString(seatsAssigned.toArray()));

		} catch (SeatsFullException e) {
			e.printStackTrace();
			cancelReservation(groupName);
		}
	}

	/*
	 * assigns seats for group members. checks for adjacent empty seats and adds
	 * passengers of the group to the seats
	 * 
	 * @param n counter for empty seats
	 * 
	 * @param classOS class of service the group chose to book
	 * 
	 * @param groupName name of the group that wants to make a reservation
	 * 
	 * @param names array of names of people in the group
	 * 
	 * @param seats allocated seats.
	 */
	public void assignGroupSeats(int n, String classOS, String groupName, String[] names, List<String> seats)
			throws SeatsFullException {
		PassengerInfo[][] currentDB = (classOS.toLowerCase() == "first") ? this.first : this.econ;
		int maxGrpSize = (names.length - n > 6) ? 6 : names.length - n;
		boolean seatsAllocated = false;
		while (maxGrpSize > 0) {
			for (int i = 0; i < currentDB.length; i++) {
				PassengerInfo[] row = currentDB[i];
				for (int j = 0; j < row.length; j++) {
					if (row.length - j < maxGrpSize) {
						break;
					}
					if (currentDB[i][j] == null) {
						boolean seatsAvailable = true;
						for (int k = j; k < j + maxGrpSize; k++) {
							if (currentDB[i][k] != null) {
								seatsAvailable = false;
								break;
							}
						}
						if (seatsAvailable) {
							seatsAllocated = true;
							for (int k = j; k < j + maxGrpSize; k++) {
								String seat = String.valueOf((classOS == "FIRST") ? 1 : 10 + i)
										+ String.valueOf((char) (k + 65));
								seats.add(seat);
								PassengerInfo pi = new PassengerInfo();
								pi.setName(names[n]);
								pi.setClassOS(classOS);
								pi.setGroupName(groupName);
								currentDB[i][k] = pi;
								n++;
							}
							break;
						}
					}
					if (seatsAllocated)
						break;
				}
				if (seatsAllocated)
					break;
			}
			if (seatsAllocated)
				break;
			maxGrpSize--;
		}
		if (maxGrpSize == 0) {
			throw new SeatsFullException();
		}
		if (names.length - n > 0) {
			assignGroupSeats(n, classOS, groupName, names, seats);
		}
	}

	/*
	 * cancel individual or group reservation looks for specified name of booking
	 * and cancels all instance of that booking
	 * 
	 * @param groupName name under which the booking was made.
	 */
	public void cancelReservation(String groupName) {
		boolean is_Cancelled = false;
		for (int row = 0; row < first.length; row++) {
			for (int col = 0; col < first[0].length; col++) {
				if (first[row][col] != null && first[row][col].getGroupName().equals(groupName)) {
					first[row][col] = null;
					is_Cancelled = true;
				}
			}
		}
		if (is_Cancelled) {
			System.out.println("Reservation cancelled for: " + groupName);
			ra.remove(pi);
			return;
		} else {
			for (int row = 0; row < econ.length; row++) {
				for (int col = 0; col < econ[0].length; col++) {
					if (econ[row][col] != null && econ[row][col].getGroupName().equals(groupName)) {
						econ[row][col] = null;
						is_Cancelled = true;
					}
				}
			}
			if (is_Cancelled) {
				System.out.println("Reservation cancelled for: " + groupName);
				ra.remove(pi);
				return;
			}
		}
		System.out.println("Reservation for " + groupName + " doesn't exist!");
	}

	/*
	 * printing availability chart prints the empty seats of a specified class
	 * 
	 * @param classOS specified class of service for checking availability.
	 */
	public void printAvailChart(String classOS) {
		char alphabet = 'A';
		boolean is_Available = false;
		System.out.println("Availability List: \n");

		switch (classOS.toUpperCase()) {
		case "FIRST":
			System.out.println("First:");
			for (int row = 0; row < first.length; row++) {
				System.out.print(row + 1 + ": ");
				for (int col = 0; col < first[0].length; col++) {
					if (first[row][col] == null) {
						System.out.print(alphabet + ",");
						is_Available = true;
					}
					alphabet++;
				}
				System.out.println();
				alphabet = 'A';
			}
			break;
		case "ECONOMY":
			System.out.println("Economy:");
			for (int row = 0; row < econ.length; row++) {
				System.out.print(row + 10 + ": ");
				for (int col = 0; col < econ[0].length; col++) {
					if (econ[row][col] == null) {
						System.out.print(alphabet + ",");
						is_Available = true;
					}
					alphabet++;
				}
				System.out.println();
				alphabet = 'A';
			}
			break;
		default:
			System.out.println("Incorrect input!!!");
		}
		if (is_Available == false) {
			System.out.println("\nSorry, no seats Available....Better luck next time!");
		}
	}

	/*
	 * prints the manifest list prints the passengers with allocated seats who have
	 * made a reservation
	 * 
	 * @param specified classOS class of service to print manifest list
	 */
	public void printManifest(String classOS) {
		char alphabet = 'A';
		boolean is_Empty = true;
		System.out.println("Manifest List: \n");

		switch (classOS.toUpperCase()) {
		case "FIRST": // manifest for first class
			System.out.println("First:");
			for (int row = 0; row < first.length; row++) {
				for (int col = 0; col < first[0].length; col++) {
					if (first[row][col] != null) {
						System.out.println((row + 1) + "" + alphabet + ": " + first[row][col].getName());
						is_Empty = false;
					}
					alphabet++;
				}
				alphabet = 'A';
			}
			break;
		case "ECONOMY":
			System.out.println("Economy:");
			for (int row = 0; row < econ.length; row++) {
				for (int col = 0; col < econ[0].length; col++) {
					if (econ[row][col] != null) {
						System.out.println((row + 10) + "" + alphabet + ": " + econ[row][col].getName());
						is_Empty = false;
					}
					alphabet++;
				}
				alphabet = 'A';
			}
			break;
		default:
			System.out.println("Incorrect input!!!");
		}
		if (is_Empty) {
			System.out.println("There are no reservations yet!");
		}
	}

	/*
	 * called at the end of program execution. overwrites output/input file with
	 * updates reservations.
	 */
	public void quit() throws FileNotFoundException {
		ra.write();
	}

	/*
	 * initializes program with reservations from previous run. reservations from
	 * previous run are read from output file and put into ArrayList each
	 * reservation is then assigned to previously assigned seat.
	 */
	public void initialize() {
		ArrayList<PassengerInfo> passList = ra.read();
		for (PassengerInfo pi : passList) {
			if (pi.getClassOS().toUpperCase().equals("ECONOMY")) {
				econ[pi.getRow()][pi.getCol()] = pi;
			} else if (pi.getClassOS().toUpperCase().equals("FIRST")) {
				first[pi.getRow()][pi.getCol()] = pi;
			}
		}
	}
}

