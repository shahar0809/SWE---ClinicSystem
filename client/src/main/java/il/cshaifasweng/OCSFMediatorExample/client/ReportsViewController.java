package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.ClinicManager;
import il.cshaifasweng.OCSFMediatorExample.entities.HospitalManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.requests.GetAllClinicsRequest;
import il.cshaifasweng.OCSFMediatorExample.requests.GetClinicReportsRequest;
import il.cshaifasweng.OCSFMediatorExample.response.GetAllClinicsResponse;
import il.cshaifasweng.OCSFMediatorExample.response.GetClinicReportsResponse;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ReportsViewController extends BaseController {

    public static void autoResizeColumns( TableView<?> table )
    {
        //Set the right policy
        table.setColumnResizePolicy( TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.getColumns().stream().forEach( (column) ->
        {
            //Minimal width = columnheader
            Text t = new Text( column.getText() );
            double max = t.getLayoutBounds().getWidth();
            for ( int i = 0; i < table.getItems().size(); i++ )
            {
                //cell must not be empty
                if ( column.getCellData( i ) != null )
                {
                    t = new Text( column.getCellData( i ).toString() );
                    double calcwidth = t.getLayoutBounds().getWidth();
                    //remember new max-width
                    if ( calcwidth > max )
                    {
                        max = calcwidth;
                    }
                }
            }
            //set the new max-widht with some extra space
            column.setPrefWidth( max + 10.0d );
        } );
    }

    @FXML
    private ListView<String> clinicsList;

    @FXML
    private TableView<Report> reportsTable;

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        clinicsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        reportsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        reportsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("name"));
        reportsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("result"));
        GetAllClinicsRequest requestAllClinic = new GetAllClinicsRequest();
        App.getClient().sendRequest(requestAllClinic);
    }

    @Subscribe
    public void updateListOfClinic(GetAllClinicsResponse response) {
        clinicsList.getItems().clear();
        if (App.getActiveUser() instanceof HospitalManager) {
            for (Clinic clinic : response.clinics) {
                clinicsList.getItems().add(clinic.getName());
            }
        } else if (App.getActiveUser() instanceof ClinicManager) {
            clinicsList.getItems().add(((ClinicManager)App.getActiveUser()).getClinic().getName());
        }
    }

    @Subscribe
    public void updateReports(GetClinicReportsResponse response) {
        reportsTable.setItems(FXCollections.observableList(response.reports));
        autoResizeColumns(reportsTable);
    }

    @FXML
    public void onMouseClick(MouseEvent mouseEvent) {
        String selectedClinic = clinicsList.getSelectionModel().getSelectedItem();
        if (selectedClinic == null)
            return;
        GetClinicReportsRequest request = new GetClinicReportsRequest(selectedClinic);
        App.getClient().sendRequest(request);
    }


}
