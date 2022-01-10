package il.cshaifasweng.OCSFMediatorExample.client.cellFactory;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AppointmentCellFactory implements Callback<ListView<Appointment>, ListCell<Appointment>> {
    @Override
    public ListCell<Appointment> call(ListView<Appointment> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Appointment appointment, boolean empty) {
                super.updateItem(appointment, empty);
                if (empty || appointment == null) {
                    setText(null);
                } else {
                    setText(appointment.toString());
                }
            }
        };
    }
}
