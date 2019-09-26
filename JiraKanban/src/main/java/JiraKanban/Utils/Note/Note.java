package JiraKanban.Utils.Note;

import JiraKanban.JiraService.JiraIssue;
import JiraKanban.JiraService.JiraMetadata;
import JiraKanban.AppWindows.MainWindow.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Класс-контейнер для формирования записи
 */
public class Note extends VBox {
    private JiraKanban.Utils.Note.NoteTextFlow noteText;
    private Hyperlink headerLabel;
    private Text status;
    private HBox headerBox;

    private long priorityID;
    private long statusID;
    private long issueTypeID;
    private String statusName;

    private final int width = 300;

    public Note(JiraIssue jiraIssue){
        priorityID = jiraIssue.getPriorityID();
        statusID = jiraIssue.getStatusID();
        issueTypeID = jiraIssue.getIssueTypeID();
        statusName = jiraIssue.getStatus();

        status = new Text(statusName);
        status.getStyleClass().add("noteStatus");
        status.setFill(Color.color(0.26, 0.53, 0.98));

        noteText = new JiraKanban.Utils.Note.NoteTextFlow(jiraIssue.getCreateInfo(), jiraIssue.getNoteSummary(), jiraIssue.getAssigneeInfo());
        noteText.setPadding(new Insets(5,5,5,5));

        headerLabel = new Hyperlink(jiraIssue.getLabel());
        headerLabel.getStyleClass().add("headerLabel");
        headerLabel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainWindow.getHostService().showDocument(jiraIssue.getHyperlink());
            }
        });

        initHeaderBox();

        this.getChildren().add(headerBox);
        this.getChildren().add(noteText);

        getStyleClass().add("noteStyle");
        setPrefWidth(width);
    }

    public void initHeaderBox(){
        headerBox = new HBox();
        headerBox.setSpacing(2);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        ImageView priorityImage = initStatusIcon();
        ImageView typeImage = initIssueTypeIcon();
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        headerBox.getChildren().add(headerLabel);
        headerBox.getChildren().add(status);
        headerBox.getChildren().add(region);
        headerBox.getChildren().add(typeImage);
        headerBox.getChildren().add(priorityImage);
    }

    public ImageView initStatusIcon(){
        String imgPath = JiraMetadata.getPathByPriority(priorityID);
        Image image = new Image(imgPath);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public ImageView initIssueTypeIcon(){
        String imgPath = JiraMetadata.getPathByIssueType(issueTypeID);
        Image image = new Image(imgPath);
        ImageView imageView = new ImageView(image);
        return imageView;
    }


    public long getPriorityID() {
        return priorityID;
    }

    public long getStatusID() {
        return statusID;
    }
}
