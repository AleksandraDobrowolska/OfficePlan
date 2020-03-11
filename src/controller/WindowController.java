package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Employee;

public class WindowController {

	public Employee employee;
	private Main main;
	private Stage primaryStage;

	private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

	public void setMain(Main main, Stage primaryStage) {
		this.main = main;
		this.primaryStage = primaryStage;

		tableView.setItems(employeeList);
	}

	@FXML
	private Label nameLabel;
	@FXML
	private TextField nameTextField;
	@FXML
	private Label surnameLabel;
	@FXML
	private TextField surnameTextField;
	@FXML
	private Label roomNumberLabel;
	@FXML
	private TextField roomNumberTextField;
	@FXML
	private Label startTimeLabel;
	@FXML
	private TextField startTimeTextField;
	@FXML
	private Label endTimeLabel;
	@FXML
	private TextField endTimeTextField;
	@FXML
	private TableView<Employee> tableView;
	@FXML
	private TableColumn<Employee, String> nameColumn;
	@FXML
	private TableColumn<Employee, String> surnameColumn;
	@FXML
	private TableColumn<Employee, String> roomColumn;
	@FXML
	private Button loadButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button addButton;
	@FXML
	private Button reportButton;
	@FXML
	private Label roomNo1Label;
	@FXML
	private Rectangle roomNo1;
	@FXML
	private Label roomNo2Label;
	@FXML
	private Rectangle roomNo2;
	@FXML
	private Label roomNo3Label;
	@FXML
	private Rectangle roomNo3;
	@FXML
	private Label roomNo4Label;
	@FXML
	private Rectangle roomNo4;
	@FXML
	private Label noRoomWithThisNumberLabel;

	@FXML
	public void handleLoadButton() {
		System.out.println("loadButton pressed");

		employeeList.clear();
		BufferedReader employeeFileToRead = null;

		try {
			employeeFileToRead = new BufferedReader(new FileReader("res/employees.txt"));
			String employee = null;
			do {
				employee = employeeFileToRead.readLine();
				if (employee != null) {
					String[] employees = employee.split(" ");
					String name = employees[0];
					String surname = employees[1];
					String room = employees[2];
					String startTime = employees[3];
					String endTime = employees[4];

					employeeList.add(new Employee(name, surname, room, startTime, endTime));
				}
			} while (employee != null);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void handleSaveButton() {
		System.out.println("saveButton pressed");
		BufferedWriter employeeListToSave = null;
		try {
			employeeListToSave = new BufferedWriter(new FileWriter("res/employees.txt"));

			for (Iterator iterator = employeeList.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				employeeListToSave
						.write(employee.getName() + " " + employee.getSurname() + " " + employee.getRoomNumber() + " "
								+ employee.getStartTime() + " " + employee.getEndTime() + "\n");
			}
			employeeListToSave.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleAddButton() {
		System.out.println("addButton pressed");
		String name = nameTextField.getText();
		String surname = surnameTextField.getText();
		String roomNumber = roomNumberTextField.getText();
		String startTime = startTimeTextField.getText();
		String endTime = endTimeTextField.getText();

		employeeList.add(new Employee(name, surname, roomNumber, startTime, endTime));

		nameTextField.clear();
		surnameTextField.clear();
		roomNumberTextField.clear();
		startTimeTextField.clear();
		endTimeTextField.clear();
	}

	@FXML
	public void handleReportButton() {
		System.out.println("reportButton pressed");

		int size = employeeList.size();
		int workingTime;
		Employee[] employeeArr = new Employee[size];

		while (size > 0) {
			int endTime = Integer.parseInt(employeeList.get(size - 1).getEndTime());
			int startTime = Integer.parseInt(employeeList.get(size - 1).getStartTime());
			workingTime = endTime - startTime;
			employeeList.get(size - 1).setWorkingTime("" + workingTime);
			size--;
		}

		for (int i = 0; i < employeeList.size(); i++) {
			employeeArr[i] = employeeList.get(i);
		}

		for (int i = 0; i < employeeList.size() - 1; i++) {
			for (int j = 0; j < employeeList.size() - i - 1; j++) {
				if (Integer.parseInt(employeeArr[j].getWorkingTime()) > Integer
						.parseInt(employeeArr[j + 1].getWorkingTime())) {

					Employee temp = employeeArr[j];
					employeeArr[j] = employeeArr[j + 1];
					employeeArr[j + 1] = temp;
				}
			}
		}

		List<Employee> reportList = Arrays.asList(employeeArr);
		BufferedWriter employeeReportList = null;

		try {
			employeeReportList = new BufferedWriter(new FileWriter("res/report.txt"));

			for (Iterator iterator = reportList.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				employeeReportList.write(employee.getName() + " " + employee.getSurname() + " "
						+ employee.getRoomNumber() + " " + employee.getStartTime() + " " + employee.getEndTime() + " "
						+ employee.getWorkingTime() + "\n");
			}
			employeeReportList.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initialize() {
		nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
		roomColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("roomNumber"));
		tableView.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
			System.out.println(newVal.getName() + " " + newVal.getSurname());
			nameTextField.setText(newVal.getName());
			surnameTextField.setText(newVal.getSurname());
			roomNumberTextField.setText(newVal.getRoomNumber());
			startTimeTextField.setText(newVal.getStartTime());
			endTimeTextField.setText(newVal.getEndTime());

			switch (roomNumberTextField.getText()) {
			case "1": {
				roomNo1Label.setVisible(true);
				roomNo2Label.setVisible(false);
				roomNo3Label.setVisible(false);
				roomNo4Label.setVisible(false);
				noRoomWithThisNumberLabel.setVisible(false);
				break;
			}
			case "2": {
				roomNo1Label.setVisible(false);
				roomNo2Label.setVisible(true);
				roomNo3Label.setVisible(false);
				roomNo4Label.setVisible(false);
				noRoomWithThisNumberLabel.setVisible(false);
				break;
			}
			case "3": {
				roomNo1Label.setVisible(false);
				roomNo2Label.setVisible(false);
				roomNo3Label.setVisible(true);
				roomNo4Label.setVisible(false);
				noRoomWithThisNumberLabel.setVisible(false);
				break;
			}
			case "4": {
				roomNo1Label.setVisible(false);
				roomNo2Label.setVisible(false);
				roomNo3Label.setVisible(false);
				roomNo4Label.setVisible(true);
				noRoomWithThisNumberLabel.setVisible(false);
				break;
			}
			default: {
				roomNo1Label.setVisible(false);
				roomNo2Label.setVisible(false);
				roomNo3Label.setVisible(false);
				roomNo4Label.setVisible(false);
				noRoomWithThisNumberLabel.setVisible(true);
			}
			}
		});
	}

}
