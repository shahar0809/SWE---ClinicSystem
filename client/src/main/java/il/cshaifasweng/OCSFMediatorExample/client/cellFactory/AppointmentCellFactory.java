package il.cshaifasweng.OCSFMediatorExample.client.cellFactory;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AppointmentCellFactory implements Callback<ListView<Appointment>, ListCell<Appointment>> {
    @Override
    public ListCell<Appointment> call(ListView<Appointment> param) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Appointment item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                }
            }
        };
    }
}
