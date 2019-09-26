package JiraKanban.Utils.Notification;

import java.awt.*;

public class TrayNotification {
    private static final String HEADER = "Jira Kanban Desk";
    private static boolean enableMode = true;

    public static void addMessage(String message){
        if(!enableMode)
            return;

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            java.awt.Image image = Toolkit.getDefaultToolkit().getImage("images/tray.gif");
            TrayIcon trayIcon = new TrayIcon(image);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            trayIcon.displayMessage(HEADER, message,
                    TrayIcon.MessageType.INFO);
        }
    }

    public static boolean isEnableMode() {
        return enableMode;
    }

    public static void setEnableMode(boolean enableMode) {
        TrayNotification.enableMode = enableMode;
    }
}
