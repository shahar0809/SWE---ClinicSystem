package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.ClinicManager;
import il.cshaifasweng.OCSFMediatorExample.requests.*;
import il.cshaifasweng.OCSFMediatorExample.response.*;
import il.cshaifasweng.OCSFMediatorExample.utils.Hours;
import javafx.application.Platform;
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

        SevicesHoursList.getItems().add("Clinic Hours");
        SevicesHoursList.getItems().add("Covid Test Hours");
        SevicesHoursList.getItems().add("Covid Vaccine Hours");
        SevicesHoursList.getItems().add("Flu Vaccine Hours");

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
        Hours hours;
        try {
            hours = new Hours(LocalTime.parse(newWorkingHours.substring(0, newWorkingHours.indexOf(" "))), LocalTime.parse(newWorkingHours.substring(newWorkingHours.indexOf(" ") + 3)));
        } catch (Exception e) {
            return;
        }
        if(selectedService.equals("Covid Test Hours")){
            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, ((ClinicManager)App.getActiveUser()).getClinic().getName());
//            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, "clinic1");
            App.getClient().sendRequest(requestUpdateActiveHours);
        }else if (selectedService.equals("Covid Vaccine Hours")){
            UpdateCovidVaccineHoursRequest requestUpdateActiveHours = new UpdateCovidVaccineHoursRequest(hours, ((ClinicManager)App.getActiveUser()).getClinic().getName());
//            UpdateCovidVaccineHoursRequest requestUpdateActiveHours = new UpdateCovidVaccineHoursRequest(hours, "clinic1");
            App.getClient().sendRequest(requestUpdateActiveHours);
        }else if (selectedService.equals("Clinic Hours")){
            UpdateClinicHoursRequest requestUpdateActiveHours = new UpdateClinicHoursRequest(hours, ((ClinicManager)App.getActiveUser()).getClinic().getName());
            App.getClient().sendRequest(requestUpdateActiveHours);
        }else if (selectedService.equals("Flu Vaccine Hours")){
            UpdateFluVaccineHoursRequest requestUpdateActiveHours = new UpdateFluVaccineHoursRequest(hours, ((ClinicManager)App.getActiveUser()).getClinic().getName());
            App.getClient().sendRequest(requestUpdateActiveHours);
        }

    }

    @Subscribe
    public void updateTestHours(UpdateCovidTestHoursResponse response) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Covid Test Hours Updated!", ButtonType.OK);
            alert.setHeaderText("Success");
            alert.show();
        });
    }

    @Subscribe
    public void updateVaccineHours(UpdateCovidVaccineHoursResponse response) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Covid Vaccine Hours Updated!", ButtonType.OK);
            alert.setHeaderText("Success");
            alert.show();
        });

    }

    @Subscribe
    public void updateFVaccineHours(UpdateFluVaccineHoursResponse response) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Flu Vaccine Hours Updated!", ButtonType.OK);
            alert.setHeaderText("Success");
            alert.show();
        });

    }

    @Subscribe
    public void updateClinicHours(UpdateClinicHoursResponse response) {
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Clinic Hours Updated!", ButtonType.OK);
            alert.setHeaderText("Success");
            alert.show();
        });
    }

    @Subscribe
    public void getHours(GetClinicResponse response) {

        Platform.runLater(()->{
            String Service = SevicesHoursList.getSelectionModel().getSelectedItem();
            if (Service == null)
                return;

            if(Service.equals("Covid Test Hours")){
                String workingHours = response.clinic.getCovidTestStartHour().toString() + " - " + response.clinic.getCovidTestEndHour().toString();
                operatingServicesHours.setText(workingHours);
            }else if (Service.equals("Covid Vaccine Hours")){
                String workingHours = response.clinic.getCovidVaccineStartHour().toString() + " - " + response.clinic.getCovidVaccineEndHour().toString();
                operatingServicesHours.setText(workingHours);
            }else if (Service.equals("Clinic Hours")){
                String workingHours = response.clinic.getOpeningHours().toString() + " - " + response.clinic.getClosingHours().toString();
                operatingServicesHours.setText(workingHours);
            }else if (Service.equals("Flu Vaccine Hours")){
                String workingHours = response.clinic.getFluVaccineStartHour().toString() + " - " + response.clinic.getFluVaccineEndHour().toString();
                operatingServicesHours.setText(workingHours);
            }
        });

    }

    public void onMouseClick(MouseEvent mouseEvent) {
        String selectedService = SevicesHoursList.getSelectionModel().getSelectedItem();
        if (selectedService == null)
            return;
        GetClinicRequest requestClinic = new GetClinicRequest(((ClinicManager)App.getActiveUser()).getClinic().getName());
        App.getClient().sendRequest(requestClinic);
    }

    public void stop() throws Exception {
        EventBus.getDefault().unregister(this);
    }
}
