package JiraKanban.Utils.GUI;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProgressInfoPane{
    private static JOptionPane jOptionPane;
    private static JPanel panel;
    private static JTextArea textArea = new JTextArea();;

    private static Date currentDate;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("[dd.mm.yyyy hh:mm:ss] ");

    private static Thread thread;

    public static void show() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(400, 140));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        JLabel label = new JLabel("Идет загрузка информации");
        label.setFont(new Font("Helvetica", Font.BOLD, 15));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        label.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        textArea.setEditable(false);
        currentDate = new Date();
        textArea.append(dateFormat.format(currentDate) + "Идет загрузка информации \n");

        panel.add(label);
        panel.add(textArea);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(progressBar);

       jOptionPane.showOptionDialog(null, panel,"Jira Loader", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, 0);
    }

    public static void add(String message){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                currentDate = new Date();
                textArea.append(dateFormat.format(currentDate) + message + "\n");
            }
        });
    }

    public static void run(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ProgressInfoPane.show();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public static void interrupt(){
        thread.interrupt();
    }

    public static void dispose(){
        if (thread.isInterrupted()) {
            SwingUtilities.windowForComponent(jOptionPane).dispose();
        }
    }
}
