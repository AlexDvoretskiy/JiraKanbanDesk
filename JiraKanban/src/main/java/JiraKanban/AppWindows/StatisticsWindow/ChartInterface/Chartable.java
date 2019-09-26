package JiraKanban.AppWindows.StatisticsWindow.ChartInterface;

import javafx.collections.ObservableList;

/**
 * Интерфейс для реализации графиков
 */
public interface Chartable {

    public void initStatisticMap();

    public ObservableList getData();

}
