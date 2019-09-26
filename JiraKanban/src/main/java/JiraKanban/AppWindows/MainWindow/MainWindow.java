package JiraKanban.AppWindows.MainWindow;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Основное окно
 */
public class MainWindow extends Application {
    private static Stage primaryStage;
    private static HostServices hostServices;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainWindow.primaryStage = primaryStage;
        MainWindow.hostServices = getHostServices();
        String fxmlFile = "/fxml/mainWindow.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        primaryStage.setTitle("Jira Kanban Desk");
        Scene scene = new Scene(root, 1620, 900);
//        primaryStage.getIcons().add(new Image("/images/main.png"));
        primaryStage.setScene(scene);
    }

    public static Stage getPrimaryStage(){
        return primaryStage;
    }

    public static HostServices getHostService(){
        return hostServices;
    }
}

