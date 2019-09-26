package JiraKanban.AppWindows.SettingsWindow;

import JiraKanban.AppWindows.SettingsWindow.SettingsComponents.AuthSettingsComponent;
import JiraKanban.AppWindows.SettingsWindow.SettingsComponents.UserGroupComponent;
import JiraKanban.Utils.GUI.ScaledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsController {

    @FXML
    VBox settingButtons;

    @FXML
    Pane centralPane;

    @FXML
    public void initialize(){
        initButtons();

        AuthSettingsComponent authSettingsComponent = new AuthSettingsComponent();
        centralPane.getChildren().add(authSettingsComponent);
    }

    public void initButtons(){
        ScaledButton authSettingsButton = new ScaledButton("images/ButtonsImg/settingsWindowImg/authSettingsImg.png","Настройки авторизации");

        authSettingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                centralPane.getChildren().clear();
                AuthSettingsComponent authSettingsComponent = new AuthSettingsComponent();
                centralPane.getChildren().add(authSettingsComponent);
            }
        });

        ScaledButton userGroupButton = new ScaledButton("images/ButtonsImg/settingsWindowImg/userGroupButtonImg.png", "Настройки группы");

        userGroupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                centralPane.getChildren().clear();
                UserGroupComponent userGroupComponent = new UserGroupComponent();
                centralPane.getChildren().add(userGroupComponent);
            }
        });

        settingButtons.getChildren().add(authSettingsButton);
        settingButtons.getChildren().add(userGroupButton);
    }
}
