package JiraKanban.AppWindows.FilterWindow;

import JiraKanban.AppWindows.Controllers;
import JiraKanban.JiraService.JiraMetadata;
import JiraKanban.Utils.Configurations.ConfigParameters;
import JiraKanban.Utils.Configurations.UserConfiguration;
import JiraKanban.Utils.GUI.ProgressInfoPane;
import JiraKanban.Utils.GUI.ScaledButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class FilterController {

    @FXML
    CheckBox blockerPriority;

    @FXML
    CheckBox criticalPriority;

    @FXML
    CheckBox highPriority;

    @FXML
    CheckBox minorPriority;

    @FXML
    CheckBox trivialPriority;

    @FXML
    CheckBox openStatus;

    @FXML
    CheckBox reopenStatus;

    @FXML
    CheckBox inProgressStatus;

    @FXML
    CheckBox analysisStatus;

    @FXML
    CheckBox infoRequiredStatus;

    @FXML
    CheckBox developStatus;

    @FXML
    CheckBox solvedStatus;

    @FXML
    CheckBox closedStatus;

    @FXML
    CheckBox modificationType;

    @FXML
    CheckBox changeRequestType;

    @FXML
    CheckBox requestType;

    @FXML
    CheckBox bugIssueType;

    @FXML
    CheckBox requirementType;

    @FXML
    CheckBox componentDevType;

    @FXML
    HBox buttonPanel;

    private HashMap<String, CheckBox> priorityCheckBoxes;
    private HashMap<String, CheckBox> typeCheckBoxes;
    private HashMap<String, CheckBox> statusCheckBoxes;

    @FXML
    public void initialize(){
        priorityCheckBoxes = new HashMap<>();
        typeCheckBoxes = new HashMap<>();
        statusCheckBoxes = new HashMap<>();

        initCheckBoxes();
        initButtons();
    }

    public void initCheckBoxes(){
        initPriorityCheckBoxes();
        initTypeCheckBoxes();
        initStatusCheckBoxes();
    }

    private void initStatusCheckBoxes() {
        statusCheckBoxes.put(JiraMetadata.getOpenStatusValue(), openStatus);
        statusCheckBoxes.put(JiraMetadata.getReopenStatusValue(), reopenStatus);
        statusCheckBoxes.put(JiraMetadata.getInfoRequiredStatusValue(), inProgressStatus);
        statusCheckBoxes.put(JiraMetadata.getAnalysisStatusValue(), analysisStatus);
        statusCheckBoxes.put(JiraMetadata.getInfoRequiredStatusValue(), infoRequiredStatus);
        statusCheckBoxes.put(JiraMetadata.getDevelopStatusValue(), developStatus);
        statusCheckBoxes.put(JiraMetadata.getSolvedStatusValue(), solvedStatus);

        setCheckBoxValues(statusCheckBoxes, ConfigParameters.STATUS_CLASSPATH);
    }

    private void initTypeCheckBoxes() {
        modificationType.setGraphic(new ImageView(new Image(JiraMetadata.getPathByIssueType(4))));
        changeRequestType.setGraphic(new ImageView(new Image(JiraMetadata.getPathByIssueType(10505))));
        requestType.setGraphic(new ImageView(new Image(JiraMetadata.getPathByIssueType(10504))));
        bugIssueType.setGraphic(new ImageView(new Image(JiraMetadata.getPathByIssueType(1))));
        requirementType.setGraphic(new ImageView(new Image(JiraMetadata.getPathByIssueType(10504))));
        componentDevType.setGraphic(new ImageView(new Image(JiraMetadata.getPathByIssueType(10101))));

        typeCheckBoxes.put(JiraMetadata.getModificationTypeValue(), modificationType);
        typeCheckBoxes.put(JiraMetadata.getChangeRequestTypeValue(), changeRequestType);
        typeCheckBoxes.put(JiraMetadata.getRequestTypeValue(), requestType);
        typeCheckBoxes.put(JiraMetadata.getBugIssueTypeValue(), bugIssueType);
        typeCheckBoxes.put(JiraMetadata.getRequirementTypeValue(), requirementType);
        typeCheckBoxes.put(JiraMetadata.getComponentDevTypeValue(), componentDevType);

        setCheckBoxValues(typeCheckBoxes, ConfigParameters.TYPE_CLASSPATH);
    }

    private void initPriorityCheckBoxes() {
        blockerPriority.setGraphic(new ImageView(new Image(JiraMetadata.getPathByPriority(1))));
        criticalPriority.setGraphic(new ImageView(new Image(JiraMetadata.getPathByPriority(2))));
        highPriority.setGraphic(new ImageView(new Image(JiraMetadata.getPathByPriority(3))));
        minorPriority.setGraphic(new ImageView(new Image(JiraMetadata.getPathByPriority(4))));
        trivialPriority.setGraphic(new ImageView(new Image(JiraMetadata.getPathByPriority(5))));

        priorityCheckBoxes.put(JiraMetadata.getBlockerPriorityValue(), blockerPriority);
        priorityCheckBoxes.put(JiraMetadata.getCriticalPriorityValue(), criticalPriority);
        priorityCheckBoxes.put(JiraMetadata.getHighPriorityValue(), highPriority);
        priorityCheckBoxes.put(JiraMetadata.getMinorPriorityValue(), minorPriority);
        priorityCheckBoxes.put(JiraMetadata.getTrivialPriorityValue(), trivialPriority);

        setCheckBoxValues(priorityCheckBoxes, ConfigParameters.PRIORITY_CLASSPATH);
    }

    private void setCheckBoxValues(HashMap<String, CheckBox> checkBoxMap, String parameterPath){
        for(String key: checkBoxMap.keySet()){
            String path = parameterPath + "." + key + ConfigParameters.SELECTED_PARAMETER;
            String value = UserConfiguration.getConfig(path);

            if(value.equals(ConfigParameters.IS_SELECTED_VALUE)){
                checkBoxMap.get(key).setSelected(true);
            } else {
                checkBoxMap.get(key).setSelected(false);
            }
        }
    }

    public void initButtons(){
        ScaledButton cancelButton = new ScaledButton();
        cancelButton.setText("Отменить");
        cancelButton.setGraphic(new ImageView(new Image("images/ButtonsImg/cancelButtonImg.png")));
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancel();
            }
        });

        ScaledButton acceptButton = new ScaledButton();
        acceptButton.setText("Применить");
        acceptButton.setGraphic(new ImageView(new Image("images/ButtonsImg/acceptButtonImg.png")));
        acceptButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                accept();
            }
        });

        buttonPanel.setSpacing(10);
        buttonPanel.getChildren().add(cancelButton);
        buttonPanel.getChildren().add(acceptButton);
    }

    public void accept(){
        //TODO: проверка на не пустоту всех чекбоксов по категориям отдельно
        changeFilterConfigurations(priorityCheckBoxes, ConfigParameters.PRIORITY_CLASSPATH);
        changeFilterConfigurations(statusCheckBoxes, ConfigParameters.STATUS_CLASSPATH);
        changeFilterConfigurations(typeCheckBoxes, ConfigParameters.TYPE_CLASSPATH);

        Controllers.getMainWindowController().update();
        FilterWindow.getStage().close();
    }

    public void cancel(){
        FilterWindow.getStage().close();
    }

    private void changeFilterConfigurations(HashMap<String, CheckBox> checkBoxMap, String parameterPath) {
        for(String key: checkBoxMap.keySet()){
            String path = parameterPath + "." + key + ConfigParameters.SELECTED_PARAMETER;
            String value;

            if(checkBoxMap.get(key).isSelected()){
                value = ConfigParameters.IS_SELECTED_VALUE;
            } else {
                value = ConfigParameters.NOT_SELECTED_VALUE;
            }
            UserConfiguration.setConfig(path, value);
        }
    }
}
