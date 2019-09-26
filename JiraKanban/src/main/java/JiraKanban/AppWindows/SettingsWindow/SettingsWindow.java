package JiraKanban.AppWindows.SettingsWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsWindow {

    public SettingsWindow(Stage stage) throws IOException {
        String fxmlFile = "/fxml/settingsWindow.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = (Parent) loader.load();
//        Controllers.getEditWindowController().setStage(stage);

        stage.setTitle("Настройки");
//        stage.getIcons().add(new Image("/images/main.png"));
        Scene scene = new Scene(root, 500, 350);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.show();
    }
}
