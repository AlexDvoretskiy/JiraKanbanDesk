package JiraKanban.Utils.Configurations;

import JiraKanban.Utils.GUI.JiraAlert;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.StringJoiner;

public class UserConfiguration {
    private static Configurations configs = new Configurations();
    private static FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder(ConfigParameters.XML_PATH);

    private static Logger logger = Logger.getLogger(UserConfiguration.class);

    public static void addConfig(String classPath, String value){
        try
        {
            logger.info("Запись данных в userConfig.xml по пути: " + classPath + " параметр: " + value);
            XMLConfiguration config = builder.getConfiguration();
            config.addProperty(classPath, value);
            builder.save();
        }
        catch (ConfigurationException cex)
        {
            new JiraAlert("Ошибка", cex.getLocalizedMessage());
            logger.error("Ошибка при записи данных в userConfig.xml по пути: " + classPath + " параметр: " + value + "\n" + cex);
        }
    }

    public static void deleteConfig(String value){
        try
        {
            XMLConfiguration config = builder.getConfiguration();
            builder.getFileHandler().setEncoding("UTF-8");
            config.clearProperty(value);
            builder.save();
            logger.info("Удаление данных в userConfig.xml параметр: " + value + " --> Успешно");
        }
        catch (ConfigurationException cex)
        {
            new JiraAlert("Удаление данных", cex.getMessage());
            logger.error("Ошибка при удалении данных в userConfig.xml параметр: " + value + "\n" + cex);
        }
    }

    public static String getConfig(String classPath){
        String result = "";
        try
        {
            XMLConfiguration config = builder.getConfiguration();
            result = config.getString(classPath);
            logger.info("Получение данных в userConfig.xml по пути: " + classPath + " --> Успешно");
        }
        catch (ConfigurationException cex)
        {
            new JiraAlert("Получение данных", cex.getMessage());
            logger.error("Ошибка получении данных в userConfig.xml по пути: " + classPath + "\n" + cex);
        }
        return result;
    }

    public static void setConfig(String path, String value){
        try
        {
            XMLConfiguration config = builder.getConfiguration();
            builder.getFileHandler().setEncoding("UTF-8");
            config.setProperty(path, value);
            builder.save();
            logger.info("Изменение данных в userConfig.xml по пути: " + path + " параметр: " + value + " --> Успешно");
        }
        catch (ConfigurationException cex)
        {
            new JiraAlert("Ошибка", cex.getMessage());
            logger.error("Ошибка при изменение данных в userConfig.xml по пути: " + path + " параметр: " + value + "\n" + cex);
        }
    }

    public static String getQueryParameters(ArrayList<String> statusKeys, String classPath){
        final String path = classPath + ".";
        StringJoiner stringJoiner = new StringJoiner(",");

        for(String key: statusKeys){
            String value = null;
            String selected = getConfig(path + key + ConfigParameters.SELECTED_PARAMETER);

            if(selected.equals("true")) {
                value = getConfig(path + key + ConfigParameters.VALUE_PARAMETER);
                stringJoiner.add(value);
            }
        }
        return stringJoiner.toString();
    }
}
