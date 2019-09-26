package JiraKanban.Utils.GUI;

import javafx.scene.control.Alert;

public class JiraAlert extends Alert {

    public JiraAlert(String header, String message) {
        super(AlertType.ERROR);
        setTitle("Jira Error");
        setHeaderText(header);
        setContentText(message);

        showAndWait();
    }
}
