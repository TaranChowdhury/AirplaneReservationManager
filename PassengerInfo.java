package AirplaneReservation;

public class PassengerInfo {
	public String name;
	public String classOS;
	public String groupName;
	public int row;
	public int col;

	/*
	 * Accessor for row
	 */
	public int getRow() {
		return row;
	}

	/*
	 * Mutator for row
	 * 
	 * @param row row where passenger is seated
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/*
	 * Accessor for col
	 */
	public int getCol() {
		return col;
	}

	/*
	 * Mutator for col
	 * 
	 * @param col col where passenger is seated
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/*
	 * Accessor for name of passenger
	 */
	public String getName() {
		return name;
	}

	/*
	 * Mutator for name
	 * 
	 * @param name name of passenger
	 */
	public void setName(String Name) {
		this.name = Name;
	}

	/*
	 * Accessor for class of service
	 */
	public String getClassOS() {
		return classOS;
	}

	/*
	 * Mutator for class of service
	 * 
	 * @param classOS passenger's class of service
	 */
	public void setClassOS(String classOS) {
		this.classOS = classOS;
	}

	/*
	 * Accessor for name of group reservation
	 */
	public String getGroupName() {
		return groupName;
	}

	/*
	 * Mutator for group name
	 * 
	 * @param groupName name of group under which booking is made.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}

