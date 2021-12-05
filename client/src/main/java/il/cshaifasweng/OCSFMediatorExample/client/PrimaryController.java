package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PrimaryController {

	@FXML
	private ListView<String> clinicList;

	@FXML
	private TextField operatingHours;

	@FXML
	public void initialize() {
		clinicList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		clinicList.getItems().addAll("Clinic 1", "Clinic 2"); // TODO: Add clinics from database here (can change ListView<String> to ListView<Clinic>)
		clinicList.getSelectionModel().selectedItemProperty().addListener((observableValue, newValue, oldValue) -> operatingHours.clear());
	}

	@FXML
	void onRetrieve(ActionEvent event) {
		String selectedClinic = clinicList.getSelectionModel().getSelectedItem();
		if (selectedClinic == null)
			return;
		String workingHours = "9:30 - 12:40"; // TODO: Retrieve working hours from selectedClinic
		operatingHours.setText(workingHours);
	}

	@FXML
	void onUpdate(ActionEvent event) {
		String selectedClinic = clinicList.getSelectionModel().getSelectedItem();
		if (selectedClinic == null)
			return;
		String newWorkingHours = operatingHours.getText();
		if (newWorkingHours.isEmpty())
			return;
		// TODO: Update selected clinic with newWorkingHours
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Working Hours Updated!", ButtonType.OK);
		alert.setHeaderText("Success");
		alert.show();
	}


}
