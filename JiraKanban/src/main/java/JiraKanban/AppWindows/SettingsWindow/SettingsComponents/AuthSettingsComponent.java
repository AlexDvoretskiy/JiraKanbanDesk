package JiraKanban.AppWindows.SettingsWindow.SettingsComponents;

import JiraKanban.JiraService.JiraService;
import JiraKanban.Utils.Configurations.ConfigParameters;
import JiraKanban.Utils.Configurations.UserConfiguration;
import JiraKanban.Utils.GUI.ScaledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AuthSettingsComponent extends VBox {

    public AuthSettingsComponent(){
        Label headerLabel = new Label("Настройки авторизации");
        headerLabel.setPadding(new Insets(0, 0, 15, 0));

        GridPane authPane = new GridPane();
        authPane.setHgap(5);
        authPane.setVgap(10);
        Label loginLabel = new Label("Логин: ");
        Label passLabel = new Label("Пароль: ");

        TextField loginField = new TextField();
        loginField.setText(JiraService.getLogin());
        PasswordField passField = new PasswordField();
        passField.setText(JiraService.getPassword());

        authPane.add(loginLabel, 0, 0);
        authPane.add(loginField, 1, 0);
        authPane.add(passLabel, 0, 1);
        authPane.add(passField, 1, 1);

        authPane.setMargin(this, new Insets(5, 10, 5, 10));

        VBox buttons = new VBox();
        buttons.setPadding(new Insets(10, 0, 0, 0));
        SaveLoginAndPassButton saveButton = new SaveLoginAndPassButton();
        DeleteLoginAndPassButton deleteButton = new DeleteLoginAndPassButton();
        ShutDownButton shutDownButton = new ShutDownButton();

        buttons.getChildren().add(saveButton);
        buttons.getChildren().add(deleteButton);
        buttons.getChildren().add(shutDownButton);

        this.getChildren().add(headerLabel);
        this.getChildren().add(authPane);
        this.getChildren().add(buttons);
    }
}

class SaveLoginAndPassButton extends ScaledButton {
    private String imgPath = "images/ButtonsImg/acceptButtonImg.png";

    public SaveLoginAndPassButton (){
        super();

        ImageView imageView= new ImageView(new Image(imgPath));
        setGraphic(imageView);
        setText("Сохранить текущий логин и пароль");

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserConfiguration.setConfig(ConfigParameters.LOGIN_CLASSPATH, JiraService.getLogin());
                UserConfiguration.setConfig(ConfigParameters.PASSWORD_CLASSPATH, JiraService.getPassword());
            }
        });
    }
}

class DeleteLoginAndPassButton extends ScaledButton {
    private String imgPath = "images/ButtonsImg/cancelButtonImg.png";

    public DeleteLoginAndPassButton(){
        super();

        ImageView imageView= new ImageView(new Image(imgPath));
        setGraphic(imageView);
        setText("Удалить текущий логин и пароль");

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserConfiguration.setConfig(ConfigParameters.LOGIN_CLASSPATH, ConfigParameters.LOGIN_DEFAULT_VALUE);
                UserConfiguration.setConfig(ConfigParameters.PASSWORD_CLASSPATH, ConfigParameters.PASSWORD_DEFAULT_VALUE);
            }
        });
    }
}

class ShutDownButton extends ScaledButton {
    private String imgPath = "images/ButtonsImg/settingsWindowImg/shutDownButtonImg.png";

    public ShutDownButton(){
        super();

        ImageView imageView= new ImageView(new Image(imgPath));
        setGraphic(imageView);
        setText("Выйти и закрыть");
        setPadding(new Insets(30, 0 , 5 , 10));

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserConfiguration.deleteConfig(ConfigParameters.USER_PROPERTIES);
                System.exit(1);
            }
        });
    }
}