package ensemble.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 * Description:  此类描述
 * Author: LuDaShi
 * Date: 2021-01-05-15:35
 * UpdateDate: 2021-01-05-15:35
 * FileName: ChartActions
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class ChartActions {

    public static void addDataItemStrNum(final XYChart<String, Number> chart) {
        if (chart.getData() == null) {
            chart.setData(FXCollections.<XYChart.Series<String, Number>>observableArrayList());
        }
        if (chart.getData().isEmpty()) {
            chart.getData().add(new XYChart.Series<String, Number>());
        }

        int sIndex = (int) (Math.random() * chart.getData().size());
        XYChart.Series<String, Number> series = chart.getData().get(sIndex);

        Set<String> existingYears = new HashSet<String>();
        for (Data<String, Number> data : series.getData()) {
            existingYears.add(data.getXValue());
        }

        int randomYear = 1900 + (int) (Math.round(12 * Math.random()) * 10);
        while (existingYears.contains(Integer.toString(randomYear))) {
            randomYear++;
        }
        series.getData().add(new Data<String, Number>(Integer.toString(randomYear), 10 + (Math.random() * 3800)));
    }

    public static void addDataItemNumStr(final XYChart<Number, String> chart) {
        if (chart.getData() == null) {
            chart.setData(FXCollections.<XYChart.Series<Number, String>>observableArrayList());
        }
        if (chart.getData().isEmpty()) {
            chart.getData().add(new XYChart.Series<Number, String>());
        }

        int sIndex = (int) (Math.random() * chart.getData().size());
        XYChart.Series<Number, String> series = chart.getData().get(sIndex);

        Set<String> existingYears = new HashSet<String>();
        for (Data<Number, String> data : series.getData()) {
            existingYears.add(data.getYValue());
        }

        int randomYear = 1900 + (int) (Math.round(12 * Math.random()) * 10);
        while (existingYears.contains(Integer.toString(randomYear))) {
            randomYear++;
        }
        series.getData().add(new Data<Number, String>(10 + (Math.random() * 3800), Integer.toString(randomYear)));
    }

    public static void deleteDataItem(XYChart<?, ?> chart) {
        if (chart.getData() == null) {
            return;
        }
        List<Integer> notEmpty = new ArrayList<Integer>();
        for (int i = 0; i < chart.getData().size(); i++) {
            XYChart.Series s = chart.getData().get(i);
            if (s != null && !s.getData().isEmpty()) {
                notEmpty.add(i);
            }
        }
        if (!notEmpty.isEmpty()) {
            XYChart.Series s = chart.getData().get(
                notEmpty.get((int) (Math.random() * notEmpty.size())));
            s.getData().remove((int) (Math.random() * s.getData().size()));
        }
    }
}
