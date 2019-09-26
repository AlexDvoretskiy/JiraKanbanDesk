package JiraKanban.AppWindows.StatisticsWindow.Charts;

import JiraKanban.AppWindows.StatisticsWindow.ChartInterface.Chartable;
import JiraKanban.Utils.Statistics.StatisticCounter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import java.util.HashMap;

/**
 * Класс, формирующий данные для круговой диаграммы
 */
public class PieChartSlices implements Chartable {
    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
    private HashMap<String, Integer> statisticMap;

    public PieChartSlices(){
        initStatisticMap();

        for(String key: statisticMap.keySet()){
            data.add(new PieChart.Data(key, statisticMap.get(key)));
        }
    }

    /**
     * Инициализируем Map, значениями JiraIssue, получаемыми из JiraService
     */
    public void initStatisticMap(){
        statisticMap = StatisticCounter.countIssueQuantityByParameters(StatisticCounter.Mode.PRIORITY);
    }

    public ObservableList<PieChart.Data>  getData() {
        return data;
    }
}
