/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.HospitalManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;

public class ManagerPrimaryController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mainWindow"
    private Pane mainWindow; // Value injected by FXMLLoader

    @FXML // fx:id="toChnageShours"
    private Tab toChnageShours; // Value injected by FXMLLoader

    @FXML // fx:id="toMainWindow"
    private Tab toMainWindow; // Value injected by FXMLLoader

    @FXML // fx:id="updateHoursServicesWindow"
    private Pane updateHoursServicesWindow; // Value injected by FXMLLoader

    @FXML
    private Pane ReportsWindow;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mainWindow != null : "fx:id=\"mainWindow\" was not injected: check your FXML file 'primary.fxml'.";
        assert toChnageShours != null : "fx:id=\"toChnageShours\" was not injected: check your FXML file 'primary.fxml'.";
        assert toMainWindow != null : "fx:id=\"toMainWindow\" was not injected: check your FXML file 'primary.fxml'.";
        assert updateHoursServicesWindow != null : "fx:id=\"updateHoursServicesWindow\" was not injected: check your FXML file 'primary.fxml'.";

        Parent parent = null;
        try {
            parent = App.loadFXML("ClinicEditor");
            mainWindow.getChildren().clear();
            mainWindow.getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            parent = App.loadFXML("UpdateHours");
            updateHoursServicesWindow.getChildren().clear();
            updateHoursServicesWindow.getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            parent = App.loadFXML("...");
//            ReportsWindow.getChildren().clear();
//            ReportsWindow.getChildren().add(parent);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        toChnageShours.setDisable(App.getActiveUser() instanceof HospitalManager);

        try {
            parent = App.loadFXML("ReportsView");
            ReportsWindow.getChildren().clear();
            ReportsWindow.getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

