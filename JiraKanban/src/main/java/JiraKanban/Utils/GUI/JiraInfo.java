package JiraKanban.Utils.GUI;

import javafx.scene.control.Alert;

public class JiraInfo extends Alert {

    public JiraInfo(String header, String message) {
        super(AlertType.INFORMATION);
        setTitle("Jira Information");
        setHeaderText(header);
        setContentText(message);

        showAndWait();
    }
}
