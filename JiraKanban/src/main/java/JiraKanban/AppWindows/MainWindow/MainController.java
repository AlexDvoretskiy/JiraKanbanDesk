package JiraKanban.AppWindows.MainWindow;

import JiraKanban.AppWindows.Controllers;
import JiraKanban.AppWindows.FilterWindow.FilterWindow;
import JiraKanban.AppWindows.MainWindow.Buttons.NotificationButton;
import JiraKanban.AppWindows.SettingsWindow.SettingsWindow;
import JiraKanban.AppWindows.StatisticsWindow.StatisticsWindow;
import JiraKanban.JiraService.JiraIssue;
import JiraKanban.JiraService.JiraMetadata;
import JiraKanban.JiraService.JiraService;
import JiraKanban.Utils.Export.ExcelExporter;
import JiraKanban.Utils.GUI.JiraAlert;
import JiraKanban.Utils.GUI.JiraInfo;
import JiraKanban.Utils.Note.Note;
import JiraKanban.Utils.Notification.TrayNotification;
import JiraKanban.Utils.GUI.ScaledButton;
import JiraKanban.Utils.GUI.Spacer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainController {

    @FXML
    VBox openSection;

    @FXML
    VBox infoRequiredSection;

    @FXML
    VBox inProgressSection;

    @FXML
    VBox analysisSection;

    @FXML
    VBox doneSection;

    @FXML
    VBox controlPanel;

    @FXML
    HBox bottomPanel;

    @FXML
    ProgressBar progressBar;

    @FXML
    Label issueQuantity;

    private ArrayList<Note> noteList;
    private ArrayList<JiraIssue> issueList;
    private HashMap<String, ArrayList<Long>> statusesMap;

    private boolean isLaunched = false;

    private Logger logger;

    @FXML
    public void initialize(){
        logger = Logger.getLogger(MainController.class);

        Controllers.setMainWindowController(this);

        noteList = new ArrayList<>();
        issueList = new ArrayList<>();
        statusesMap = new HashMap<>();

        initButtons();
        update();

        isLaunched = true;
    }

    public void update(){
        clearFields();

        if(isLaunched) {
            JiraService.updateIssueList();
        }

        issueList = JiraService.getIssueList();
        statusesMap = JiraMetadata.getStatusesBySection();

        for(JiraIssue jiraIssue: issueList){
            Note note = new Note(jiraIssue);
            noteList.add(note);
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(Note note: noteList){
                    if(statusesMap.get(JiraMetadata.getOpenSectionName()).contains(note.getStatusID())){
                        openSection.getChildren().add(note);
                    } else if (statusesMap.get(JiraMetadata.getInfoRequiredSectionName()).contains(note.getStatusID())){
                        infoRequiredSection.getChildren().add(note);
                    } else if (statusesMap.get(JiraMetadata.getInProgressSectionName()).contains(note.getStatusID())){
                        inProgressSection.getChildren().add(note);
                    } else if(statusesMap.get(JiraMetadata.getAnalysisSectionName()).contains(note.getStatusID())){
                        analysisSection.getChildren().add(note);
                    } else if(statusesMap.get(JiraMetadata.getDoneSectionName()).contains(note.getStatusID())){
                        doneSection.getChildren().add(note);
                    }
                }
                issueQuantity.setText("Всего задач: " + noteList.size());
            }
        });
        TrayNotification.addMessage("Обновлено успешно \n Получено " + JiraService.getIssueList().size() + " записей");
    }

    private void clearFields() {
        noteList.clear();
        issueList.clear();
        statusesMap.clear();

        openSection.getChildren().clear();
        infoRequiredSection.getChildren().clear();
        inProgressSection.getChildren().clear();
        analysisSection.getChildren().clear();
        doneSection.getChildren().clear();
    }

    private void initButtons(){
        ScaledButton updateButton = new ScaledButton("images/ButtonsImg/mainWindowImg/updateButtonImg.png", "Обновить");
        ScaledButton settingsButton = new ScaledButton("images/ButtonsImg/mainWindowImg/settingsButtonImg.png", "Настройки");
        ScaledButton filterButton = new ScaledButton("images/ButtonsImg/mainWindowImg/filterButtonImg.png", "Фильтр");
        ScaledButton groupButton = new ScaledButton("images/ButtonsImg/mainWindowImg/groupButtonImg.png","Моя группа");
        ScaledButton statisticsButton = new ScaledButton("images/ButtonsImg/mainWindowImg/statisticsButtonImg.png", "Статистика");
        ScaledButton exportButton = new ScaledButton("images/ButtonsImg/mainWindowImg/exportButtonImg.png", "Экспортировать");
        NotificationButton notificationButton = new NotificationButton();

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                update();
            }
        });

        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initSettingsWindow();
            }
        });

        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initFilterWindow();
            }
        });

        exportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                export();
            }
        });

        statisticsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initStatisticsWindow();
            }
        });

        controlPanel.getChildren().add(settingsButton);
        controlPanel.getChildren().add(new Spacer(0, 50));
        controlPanel.getChildren().add(updateButton);
        controlPanel.getChildren().add(filterButton);
        controlPanel.getChildren().add(groupButton);
        controlPanel.getChildren().add(new Spacer(0, 50));
        controlPanel.getChildren().add(statisticsButton);
        controlPanel.getChildren().add(exportButton);
        controlPanel.getChildren().add(new Spacer(0, 150));
        controlPanel.getChildren().add(notificationButton);
    }

    public void initSettingsWindow(){
        Stage stage = new Stage();

        try {
            new SettingsWindow(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initStatisticsWindow(){
        Stage stage = new Stage();

        try {
            new StatisticsWindow(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initFilterWindow(){
        Stage stage = new Stage();

        try {
            new FilterWindow(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void export(){
        try {
            ExcelExporter.exportAllRecords();
            new JiraInfo("Выгрузка в Excel", "Файл успешно выгружен на рабочий стол");
        } catch (IOException e) {
            new JiraAlert("Ошибка выгрузки excel файла", e.getMessage());
            logger.error("Ошибка при выгрузке в excel " + e);
        }
    }
}

