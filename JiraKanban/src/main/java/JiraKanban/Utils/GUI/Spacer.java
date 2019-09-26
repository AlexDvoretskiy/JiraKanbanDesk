package JiraKanban.Utils.GUI;

import javafx.scene.layout.Region;

public class Spacer extends Region {
    private int width;
    private int height;

    public Spacer(int width, int height){
        this.width = width;
        this.height = height;

        setPrefHeight(height);
        setPrefWidth(width);
    }
}
