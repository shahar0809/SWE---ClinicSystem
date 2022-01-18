package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicManager;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    private static Scene scene;
    private static SimpleClient client;
    private static User activeUser;
    private static ClinicManager manager;


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        stage.sizeToScene();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static SimpleClient getClient() {
        return client;
    }

    public static User getActiveUser() { return activeUser; }
    public static void setActiveUser(User user) { activeUser = user; }

    public static ClinicManager getManager() { return manager; }
    public static void setManager(ClinicManager user) { manager = user; }
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        client = SimpleClient.getClient();
        client.openConnection();
//        scene = new Scene(loadFXML("RegisterLogin"));
       // scene = new Scene(loadFXML("UpdateHours"));

//        scene = new Scene(loadFXML("PrimaryManager"));
        scene = new Scene(loadFXML("ChooseComputer"));

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}