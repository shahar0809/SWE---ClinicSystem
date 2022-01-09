package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
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
        if(selectedService.equals("Covid Test Hours")){
            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, App.getManager().getClinic().getName());
//            UpdateCovidTestHoursRequest requestUpdateActiveHours = new UpdateCovidTestHoursRequest(hours, "clinic1");
            App.getClient().sendRequest(requestUpdateActiveHours);
        }else if (selectedService.equals("Service Name#2")){
            //***
            System.out.println("hello");//***
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
    public void getTestHours(GetClinicResponse response) {

        Platform.runLater(()->{
            String Service = SevicesHoursList.getSelectionModel().getSelectedItem();
            if(Service.equals("Covid Test Hours")){
                String workingHours = response.clinic.getCovidTestStartHour().toString() + " - " + response.clinic.getCovidTestEndHour().toString();
                operatingServicesHours.setText(workingHours);
            }else if (Service.equals("Service Name#2")){
                System.out.println("hello");//***
            }
        });

    }

    public void onMouseClick(MouseEvent mouseEvent) {
        String selectedService = SevicesHoursList.getSelectionModel().getSelectedItem();
        if (selectedService == null)
            return;
        GetClinicRequest requestClinic = new GetClinicRequest(App.getManager().getClinic().getName());

//        GetClinicRequest requestClinic = new GetClinicRequest("clinic1");
        App.getClient().sendRequest(requestClinic);
    }
}
