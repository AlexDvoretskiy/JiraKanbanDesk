package JiraKanban.AppWindows.StatisticsWindow.Charts;

import JiraKanban.AppWindows.StatisticsWindow.ChartInterface.Chartable;
import JiraKanban.Utils.Statistics.StatisticCounter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import java.util.HashMap;

/**
 * Серия данных для формирования линейного графика
 */
public class AreaChartSeries implements Chartable {
    private ObservableList<XYChart.Series<String, Integer>> data = FXCollections.observableArrayList();
    private HashMap<String, Integer> statisticMap;

    public AreaChartSeries(){
        initStatisticMap();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Количество по статусам");

        for(String key: statisticMap.keySet()){
            series.getData().add(new XYChart.Data<>(key, statisticMap.get(key)));
        }
        data.add(series);
    }

    /**
     * Инициализируем Map, значениями JiraIssue, получаемыми из JiraService
     */
    public void initStatisticMap(){
        statisticMap = StatisticCounter.countIssueQuantityByParameters(StatisticCounter.Mode.STATUS);
    }

    public ObservableList<XYChart.Series<String, Integer>> getData() {
        return data;
    }
}
