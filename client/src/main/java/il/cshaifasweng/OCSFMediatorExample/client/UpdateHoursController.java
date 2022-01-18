package il.cshaifasweng.OCSFMediatorExample.client;
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

        SevicesHoursList.getItems().add("Covid Test Hours");
        SevicesHoursList.getItems().add("Covid Vaccine Hours");
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
        if(selectedService.equals("Covid Test Hours")){
            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, App.getManager().getClinic().getName());
//            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, "clinic1");
            App.getClient().sendRequest(requestUpdateActiveHours);
        }else if (selectedService.equals("Covid Vaccine Hours")){
            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, App.getManager().getClinic().getName());
//            UpdateCovidVaccineHoursRequest requestUpdateActiveHours = new UpdateCovidVaccineHoursRequest(hours, "clinic1");
            App.getClient().sendRequest(requestUpdateActiveHours);
        }//************ADD OTHER SERVICES*******
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
            }//************ADD OTHER SERVICES*******
        });

    }

    public void onMouseClick(MouseEvent mouseEvent) {
        String selectedService = SevicesHoursList.getSelectionModel().getSelectedItem();
        if (selectedService == null)
            return;
        GetClinicRequest requestClinic = new GetClinicRequest(App.getManager().getClinic().getName());
        //GetClinicRequest requestClinic = new GetClinicRequest("clinic1");
        App.getClient().sendRequest(requestClinic);
    }

    public void stop() throws Exception {
        EventBus.getDefault().unregister(this);
    }
}
