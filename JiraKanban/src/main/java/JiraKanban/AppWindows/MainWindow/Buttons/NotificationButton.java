package JiraKanban.AppWindows.MainWindow.Buttons;

import JiraKanban.Utils.Notification.TrayNotification;
import JiraKanban.Utils.GUI.ScaledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NotificationButton extends ScaledButton {
    private final String enableImgPath = "images/ButtonsImg/mainWindowImg/enableNotification.Img.png";
    private final String disableImgPath = "images/ButtonsImg/mainWindowImg/disableNotificationImg.png";
    private final String tooltip = "Уведомления";

    private final ImageView enableView = new ImageView(new Image(enableImgPath));
    private final ImageView disableView = new ImageView(new Image(disableImgPath));

    private boolean isNotificationEnable = true;

    public NotificationButton() {
        super();

        ImageView imageView= new ImageView(new Image(enableImgPath));
        setGraphic(imageView);

        setTooltip(new Tooltip(tooltip));

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isNotificationEnable){
                    isNotificationEnable = false;
                } else {
                    isNotificationEnable = true;
                }
                changeImage();
            }
        });
    }

    private void changeImage() {
        if(isNotificationEnable){
            setGraphic(enableView);
            TrayNotification.setEnableMode(true);
            TrayNotification.addMessage("Уведомления включены");
        } else {
            setGraphic(disableView);
            TrayNotification.addMessage("Уведомления отключены");
            TrayNotification.setEnableMode(false);
        }
    }
}
