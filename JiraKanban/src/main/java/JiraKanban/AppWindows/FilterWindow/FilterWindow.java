package JiraKanban.AppWindows.FilterWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FilterWindow {
    private static Stage stage;

    public FilterWindow(Stage stage) throws IOException {
        String fxmlFile = "/fxml/filterWindow.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = (Parent) loader.load();
//        Controllers.getEditWindowController().setStage(stage);
        FilterWindow.stage = stage;
        stage.setTitle("Настройки");
//        stage.getIcons().add(new Image("/images/main.png"));
        Scene scene = new Scene(root, 780, 400);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}
