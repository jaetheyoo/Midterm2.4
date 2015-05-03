package customerInfo.view;

import customerInfo.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class CustomerController {
	@FXML
	private Button saveButton;
	@FXML
	private Button clearButton;
	@FXML
	private TextField firstName;
	@FXML
	private TextField middleInitial;
	@FXML
	private TextField lastName;
	@FXML
	private RadioButton male;
	@FXML
	private RadioButton female;
	@FXML
	private TextField address;
	@FXML
	private TextField city;
	@FXML
	private ComboBox<String> state;
	@FXML
	private TextField zip;
	@FXML
	private TextField errors;

	Customer myCustomer;

	private ObservableList<String> states = FXCollections.observableArrayList(
			"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI",
			"ID", "IL", "IN", "IA", "Ks", "KY", "LA", "ME", "MD", "MA", "MI",
			"MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
			"ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT",
			"VT", "VA", "WA", "WV", "WI", "WY");

	private String getGender() {
		if (male.selectedProperty().get()) {
			return "Male";
		} else if (female.selectedProperty().get()) {
			return "female";
		}
		return "";
	}

	@FXML
	private void initialize() {
		state.setItems(states);
		firstName.setText(null);
		middleInitial.setText(null);
		lastName.setText(null);
		address.setText(null);
		city.setText(null);
		state.setValue(null);
		zip.setText(null);
		male.setSelected(false);
		female.setSelected(false);
		errors.setText("No errors");
	}

	@FXML
	private void handleMale() {
		if (female.isSelected()) {
			female.setSelected(false);
		}
	}

	@FXML
	private void handleFemale() {
		if (male.isSelected()) {
			male.setSelected(false);
		}
	}
	
	@FXML
	private void handleZip() {
		if (zip.getText()==null) {
			errors.setText("No errors");
		}
		else if (Pattern.matches("^\\d+$", zip.getText())) {
			errors.setText("No errors");
			}
		else {
			errors.setText("Zip must numeric");
			zip.setText(null);
		}
	}

	@FXML
	private void handleSave() {
		if (firstName.getText()==null) {
			errors.setText("No First Name");
		}
		else if (firstName.getText().length()>50){
			errors.setText("First Name is too long");
		}
		else if (middleInitial.getText()==null) {
			errors.setText("No middleInitial");
		}
		else if (middleInitial.getText().length()>1){
			errors.setText("Middle Initial too long");
		}
		else if (lastName.getText()==null) {
			errors.setText("No last Name");
		}
		else if (lastName.getText().length()>50){
			errors.setText("Last Name too long");
		}
		else if (address.getText()==null) {
			errors.setText("No address");
		}
		else if (address.getText().length()>50){
			errors.setText("Address too long");
		}
		else if (city.getText()==null) {
			errors.setText("No city");
		}
		else if (city.getText().length()>25){
			errors.setText("City too long");
		}
		else if (zip.getText()==null) {
			errors.setText("No Zip");
		}
		else if (Pattern.matches("^\\d+$", zip.getText())) {
			if (zip.getText().length() < 5
					|| zip.getText().length() > 9) {
				errors.setText("Zip must be 5-9 digits");
			}
			else {
				errors.setText("saved");
				customerInfo.model.CustomerSave.setCustomer(new Customer(
						firstName.getText(), middleInitial.getText(), lastName
								.getText(), getGender(), address.getText(),
						city.getText(), state.getValue(), zip.getText()));
			}
		}
		else {
			errors.setText("Zip must be numeric");
		}
	}

	@FXML
	private void handleClear() {
		initialize();
	}
}
