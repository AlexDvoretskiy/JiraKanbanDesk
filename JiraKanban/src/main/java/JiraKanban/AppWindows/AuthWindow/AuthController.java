package JiraKanban.AppWindows.AuthWindow;

import JiraKanban.JiraService.JiraService;
import JiraKanban.AppWindows.MainWindow.MainWindow;
import JiraKanban.Utils.Configurations.ConfigParameters;
import JiraKanban.Utils.Configurations.UserConfiguration;
import JiraKanban.Utils.GUI.JiraAlert;
import JiraKanban.Utils.GUI.ProgressInfoPane;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class AuthController {

    @FXML
    TextField jiraLinkField;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    CheckBox saveConfigs;

    @FXML
    CheckBox enableProxyCheckBox;

    @FXML
    TextField proxyIPtextField;

    @FXML
    TextField proxyPortTextField;

    private Logger logger;

    @FXML
    public void initialize(){
        logger = Logger.getLogger(AuthController.class);

        loginField.setText(UserConfiguration.getConfig(ConfigParameters.LOGIN_CLASSPATH));
        passwordField.setText(UserConfiguration.getConfig(ConfigParameters.PASSWORD_CLASSPATH));
    }

    public void login(){
        logger.info("Запуск процесса авторизации");
        try {
            if(checkTextFieldsEmptiness()){
                new JiraAlert("Ошибка", "Поля авторизации и настроек прокси-сервера, при его использовании, не могут быть пустыми");
                return;
            }
            useProxy();

            long start = System.currentTimeMillis();
            ProgressInfoPane.run();

            JiraService.setJiraUrl(jiraLinkField.getText());
            JiraService.setLogin(loginField.getText());
            JiraService.setPassword(passwordField.getText());
            JiraService.connect();

            if(JiraService.isAuthSuccess()) {
                Stage stage = new Stage();
                new MainWindow().start(stage);
                MainWindow.getPrimaryStage().show();

                saveConfigurations();
                ProgressInfoPane.interrupt();
                AuthWindow.getStage().close();
            }

            ProgressInfoPane.dispose();
            long finish = System.currentTimeMillis();
            logger.info("Время авторизации - " + (finish-start));
        } catch (Exception e) {
            logger.error("Ошибка авторизации " + e);
        }
    }

    public void copyFile(){
        try {
            File destDir = new File("C:\\Users\\Alexa\\Desktop");
            File srcFile = new File("./logging/log_file.log");
            FileUtils.copyFileToDirectory(srcFile, destDir);
        } catch(Exception e) {
            new JiraAlert("Ошибка", e.getMessage());
        }
    }

    public void saveConfigurations(){
        if (saveConfigs.isSelected()) {
            UserConfiguration.setConfig(ConfigParameters.LOGIN_CLASSPATH, loginField.getText());
            UserConfiguration.setConfig(ConfigParameters.PASSWORD_CLASSPATH, passwordField.getText());
        }

        UserConfiguration.setConfig(ConfigParameters.PROXY_IP_CLASSPATH, proxyIPtextField.getText());
        UserConfiguration.setConfig(ConfigParameters.PROXY_PORT_CLASSPATH, proxyPortTextField.getText());
    }

    public void editProxyConfigurations(){
        if(enableProxyCheckBox.isSelected()){
            proxyIPtextField.setDisable(false);
            proxyPortTextField.setDisable(false);
        }
    }

    private boolean checkTextFieldsEmptiness() {
        return (proxyIPtextField.getText().isEmpty() || proxyPortTextField.getText().isEmpty()
                || loginField.getText().isEmpty() || passwordField.getText().isEmpty());
    }

    public void useProxy(){
        if (enableProxyCheckBox.isSelected()) {
            Properties props = System.getProperties();
            props.put("http.proxyHost", proxyIPtextField.getText());
            props.put("http.proxyPort", proxyPortTextField.getText());
        }
    }
}

