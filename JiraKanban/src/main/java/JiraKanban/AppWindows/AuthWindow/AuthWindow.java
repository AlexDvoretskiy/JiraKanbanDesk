package JiraKanban.AppWindows.AuthWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Properties;

public class AuthWindow extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        AuthWindow.stage = stage;
        String fxmlFile = "/fxml/authWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Authorization");
        Scene scene = new Scene(root, 400, 370);
//        stage.getIcons().add(new Image("/images/main.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage(){
        return stage;
    }
}

