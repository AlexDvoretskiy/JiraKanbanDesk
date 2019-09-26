package JiraKanban.AppWindows;

import JiraKanban.AppWindows.AuthWindow.AuthController;
import JiraKanban.AppWindows.FilterWindow.FilterController;
import JiraKanban.AppWindows.MainWindow.MainController;
import JiraKanban.AppWindows.SettingsWindow.SettingsController;
import JiraKanban.AppWindows.StatisticsWindow.StatisticsController;

public class Controllers {
    private static AuthController authWindowController;
    private static MainController mainWindowController;
    private static SettingsController settingsWindowController;
    private static FilterController filterWindowController;
    private static StatisticsController statisticsWindowController;

    public static AuthController getAuthWindowController() {
        return authWindowController;
    }

    public static void setAuthWindowController(AuthController authWindowController) {
        Controllers.authWindowController = authWindowController;
    }

    public static MainController getMainWindowController() {
        return mainWindowController;
    }

    public static void setMainWindowController(MainController mainWindowController) {
        Controllers.mainWindowController = mainWindowController;
    }

    public static SettingsController getSettingsWindowController() {
        return settingsWindowController;
    }

    public static void setSettingsWindowController(SettingsController settingsWindowController) {
        Controllers.settingsWindowController = settingsWindowController;
    }

    public static FilterController getFilterWindowController() {
        return filterWindowController;
    }

    public static void setFilterWindowController(FilterController filterWindowController) {
        Controllers.filterWindowController = filterWindowController;
    }

    public static StatisticsController getStatisticsWindowController() {
        return statisticsWindowController;
    }

    public static void setStatisticsWindowController(StatisticsController statisticsWindowController) {
        Controllers.statisticsWindowController = statisticsWindowController;
    }
}
