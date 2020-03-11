package model;

public class Employee {

	private String name;
	private String surname;
	private String roomNumber;
	private String startTime;
	private String endTime;
	private String workingTime;

	public Employee(String name, String surname, String roomNumber, String startTime, String endTime) {
		this.name = name;
		this.surname = surname;
		this.roomNumber = roomNumber;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Employee(String name, String surname, String roomNumber, String startTime, String endTime,
			String workingTime) {
		this(name, surname, roomNumber, startTime, endTime);
		this.workingTime = workingTime;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
