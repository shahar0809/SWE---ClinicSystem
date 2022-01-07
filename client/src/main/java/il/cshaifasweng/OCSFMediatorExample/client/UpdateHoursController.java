package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.utils.Hours;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.time.LocalTime;

public class UpdateHoursController {

    @FXML
    private ListView<String> SevicesHoursList;

    @FXML
    private TextField operatingServicesHours;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        SevicesHoursList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        SevicesHoursList.getItems().add("Covid Test Hours");
        SevicesHoursList.getItems().add("Service Name#2");
        SevicesHoursList.getItems().add("Service Name#3");
        SevicesHoursList.getItems().add("Service Name#4");


        SevicesHoursList.getSelectionModel().selectedItemProperty().addListener((observableValue, newValue, oldValue) -> operatingServicesHours.clear());
    }

    @FXML
    void onUpdate(ActionEvent event) {
        String selectedService = SevicesHoursList.getSelectionModel().getSelectedItem();
        if (selectedService == null)
            return;

        String newWorkingHours = operatingServicesHours.getText();
        if (newWorkingHours.isEmpty())
            return;
        Hours hours = new Hours(LocalTime.parse(newWorkingHours.substring(0, newWorkingHours.indexOf(" "))), LocalTime.parse(newWorkingHours.substring(newWorkingHours.indexOf(" ") + 3)));

        if(selectedService == "Covid Test Hours"){
//            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, App.getManager().getClinic().getName());
            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, "clinic1");
            App.getClient().sendRequest(requestUpdateActiveHours);
        }else if (selectedService == "Service Name#2"){
            //***
        }
    }

    @Subscribe
    public void updateTestHours(UpdateCovidTestHoursResponse response) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Working Hours Updated!", ButtonType.OK);
        alert.setHeaderText("Success");
        alert.show();
    }

    @Subscribe
    public void getTestHours(GetCovidTestHoursResponse response) {
        String workingHours = response.testHours.openingHours.toString() + " - " + response.testHours.closingHours.toString();
        operatingServicesHours.setText(workingHours);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        String selectedService = SevicesHoursList.getSelectionModel().getSelectedItem();
        if (selectedService == null)
            return;
        if(selectedService == "Covid Test Hours"){
//            GetCovidTestHoursRequest requestHours = new GetCovidTestHoursRequest(App.getManager().getClinic().getName());
            GetCovidTestHoursRequest requestHours = new GetCovidTestHoursRequest("clinic1");
            App.getClient().sendRequest(requestHours);
        }else if (selectedService == "Service Name#2"){
            //***
        }
    }
}
