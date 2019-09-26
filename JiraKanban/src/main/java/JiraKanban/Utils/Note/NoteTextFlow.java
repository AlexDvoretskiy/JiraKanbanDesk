package JiraKanban.Utils.Note;

import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Класс-компонент для формирования текста записи
 */
public class NoteTextFlow extends TextFlow {
    private Text createInfo;
    private Text noteSummary;
    private Text assigneeInfo;

    private  int flowWidth;
    private int priority;
    private boolean isActive;

    public NoteTextFlow(String createInfo, String noteSummary, String assigneeInfo){
        this.createInfo = new Text(createInfo + "\n");
        this.noteSummary = new Text(noteSummary + "\n");
        this.assigneeInfo = new Text("Исполнитель: \n" + assigneeInfo);

        setStyleClass();
        this.createInfo.setFill(Color.color(0.37, 0.37, 0.37));
        this.noteSummary.setFill(Color.color(0.29, 0.29, 0.29));
        this.assigneeInfo.setFill(Color.color(0.32, 0.32, 0.32));

        getTextFlowBounds(this, this.noteSummary);
        setLineSpacing(4.0);

        ObservableList list = this.getChildren();
        list.addAll(this.createInfo, this.noteSummary, this.assigneeInfo);
        getStyleClass().add("textFlow");
    }

    /**
     * Устанавливаем размерность в зависимости от количества текста
     * @param textFlow изменяемый компонент
     * @param text основной текст записи
     */
    private void getTextFlowBounds(TextFlow textFlow, Text text){
        text.getStyleClass().add("noteText");
        StackPane pane = new StackPane(text);
        pane.layout();
        double width = text.getLayoutBounds().getWidth();
        textFlow.setPrefHeight(getTextFlowHeight(width));
    }

    /**
     * Определение высоты компоненты
     * @param width ширина текста
     * @return количество рядов
     */
    private int getTextFlowHeight(double width){
        int rows = (int) (width / flowWidth) + 3;
        int rowLimit = 10;

        if(rows < rowLimit){
            return rows;
        } else {
            return rowLimit;
        }
    }

    /**
     * Устанавливаем CSS для каждого текстового блока
     */
    private void setStyleClass(){
        this.createInfo.getStyleClass().add("createInfo");
        this.noteSummary.getStyleClass().add("noteSummary");
        this.assigneeInfo.getStyleClass().add("assigneeInfo");
    }
}
