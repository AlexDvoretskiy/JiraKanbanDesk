package JiraKanban.AppWindows.SettingsWindow.SettingsComponents;

import JiraKanban.Utils.GUI.ScaledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserGroupComponent extends VBox {

    public UserGroupComponent (){
        Label headerLabel = new Label("Настройки группы");
        headerLabel.setPadding(new Insets(0, 0, 15, 0));

        Label groupHeader = new Label("Текущий список группы");
        groupHeader.setPadding(new Insets(0, 0, 5, 0));

        TextArea groupList = new TextArea();
        groupList.setEditable(false);
        groupList.setPrefWidth(350);

        HBox addBox = new HBox();
        addBox.setPadding(new Insets(10, 0, 5, 0));
        TextField addField = new TextField();
        addField.setPromptText("Введите логин пользователя ...");
        AddToGroupButton addToGroupButton = new AddToGroupButton();
        addBox.getChildren().add(addField);
        addBox.getChildren().add(addToGroupButton);

        this.getChildren().add(headerLabel);
        this.getChildren().add(groupHeader);
        this.getChildren().add(groupList);
        this.getChildren().add(addBox);
    }
}

class AddToGroupButton extends ScaledButton {
    private String imgPath = "images/ButtonsImg/settingsWindowImg/addToGroupButtonImg.png";

    public AddToGroupButton (){
        super();

        ImageView imageView= new ImageView(new Image(imgPath));
        setGraphic(imageView);
        setText("Добавить");
        setPadding(new Insets(0, 0, 0, 10));

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO: Реализовать метод добавления в группу
            }
        });
    }
}
