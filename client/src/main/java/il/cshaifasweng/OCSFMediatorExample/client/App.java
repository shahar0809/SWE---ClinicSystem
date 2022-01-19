package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ClinicManager;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import il.cshaifasweng.OCSFMediatorExample.response.TokenExpiredResponse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    private static Scene scene;
    private static SimpleClient client;
    private static User activeUser;
    private static ClinicManager manager;
    private static Map<String, Parent> parents = new HashMap<>();
    private static Map<String, BaseController> controllers = new HashMap<>();

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadAndStartFXML(fxml));
        stage.sizeToScene();
    }

    public static Parent loadAndStartFXML(String fxml) throws IOException {
        if (!parents.containsKey(fxml)) {
            Parent parent = loadFXML(fxml);
            parents.put(fxml, parent);
        }
        controllers.get(fxml).start();
        return parents.get(fxml);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent parent = fxmlLoader.load();
        controllers.put(fxml, fxmlLoader.getController());
        return parent;
    }

    public static void main(String[] args) {
        launch();
    }

    public static SimpleClient getClient() {
        return client;
    }

    public static User getActiveUser() { return activeUser; }
    public static void setActiveUser(User user) { activeUser = user; }

    @Override
    public void start(Stage stage) throws IOException {
        EventBus.getDefault().register(this);
        App.stage = stage;
        client = SimpleClient.getClient();
        client.openConnection();
        scene = new Scene(loadAndStartFXML("RegisterLogin"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        EventBus.getDefault().unregister(this);
        super.stop();
    }

    @Subscribe
    public void onTokenExpired(TokenExpiredResponse response) {
        Alert alert = new Alert(AlertType.WARNING, "User was logged in from another location!", ButtonType.CLOSE);
        alert.setHeaderText("Token Expired");
        alert.show();
        alert.setOnHidden(e -> Platform.exit());
    }
}