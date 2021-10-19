package AirplaneReservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationArchive {
	private ArrayList<PassengerInfo> reservations = new ArrayList<PassengerInfo>();
	private String dbFilePath;
	PassengerInfo pi;

	/*
	 * @constructor initializes database file path from command line argument
	 */
	public ReservationArchive(String dbFilePath) {
		this.dbFilePath = dbFilePath;
	}

	/*
	 * adding passenger object to arraylist
	 * 
	 * @param pi passenger to be added
	 */
	public void add(PassengerInfo pi) {
		reservations.add(pi);
	}

	/*
	 * removing passenger object form arraylist
	 * 
	 * @param pi passenger to be removed
	 */
	public void remove(PassengerInfo pi) {
		reservations.remove(pi);
	}

	/*
	 * reads previous run output file. re creates passenger objects from previous
	 * run and puts them in arraylist passes arraylist to reservation manager so
	 * that previously made reservations can be reinitialized.
	 * 
	 * @returns passList arraylist with passenger objects of previously made
	 * reservations
	 */
	public ArrayList<PassengerInfo> read() {
		ArrayList<PassengerInfo> passList = new ArrayList<PassengerInfo>();
		try {
			File file = new File(this.dbFilePath);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] objInfo = data.split(",");
				pi = new PassengerInfo();
				pi.setName(objInfo[0]);
				pi.setGroupName(objInfo[1]);
				pi.setClassOS(objInfo[2]);
				pi.setRow(Integer.parseInt(objInfo[3]));
				pi.setCol(Integer.parseInt(objInfo[4]));
				passList.add(pi);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return passList;
	}

	/*
	 * overwrites output file with updated reservations and checks if output file
	 * exists if not creates one.
	 */
	public void write() throws FileNotFoundException {

		try {
			FileWriter myWriter = new FileWriter(this.dbFilePath);
			for (int i = 0; i < reservations.size(); i++) {
				myWriter.write(reservations.get(i).getName() + "," + reservations.get(i).getGroupName() + ","
						+ reservations.get(i).getClassOS() + "," + reservations.get(i).getRow() + ","
						+ reservations.get(i).getCol() + "\n");
			}

			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Good Bye");
	}

}

