package JiraKanban.Utils.GUI;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ScaledButton extends Button {

    public ScaledButton(){
        setScale();
        setStyle("-fx-background-color: transparent");
    }

    public ScaledButton(String imgPath, String tooltip){
        super();

        ImageView imageView= new ImageView(new Image(imgPath));
        setGraphic(imageView);

        setTooltip(new Tooltip(tooltip));

        VBox.setMargin(this, new Insets(10, 0, 0,0));

        setScale();
        setStyle("-fx-background-color: transparent");
    }

    private void setScale() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 setScaleX(0.8);
                 setScaleY(0.8);
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setScaleX(1.0);
                setScaleY(1.0);
            }
        });
    }
}
