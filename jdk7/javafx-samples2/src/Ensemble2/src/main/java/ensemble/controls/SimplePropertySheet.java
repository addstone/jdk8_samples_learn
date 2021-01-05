package ensemble.controls;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Description:   A very simple property sheet that is used by samples to show controls to play with.
 * Description:   样本用来显示要玩的控件的非常简单的属性表。
 * Author: LuDaShi
 * Date: 2021-01-05-15:32
 * UpdateDate: 2021-01-05-15:32
 * FileName: SimplePropertySheet
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class SimplePropertySheet extends GridPane {

    private static final NumberFormat twoDp = new DecimalFormat("0.##");

    public SimplePropertySheet(PropDesc... properties) {
        getStyleClass().add("simple-property-sheet");
        setVgap(10);
        setHgap(10);
        int row = 0;
        for (PropDesc property : properties) {
            final PropDesc prop = property;
            Label propName = new Label(prop.name + ":");
            propName.getStyleClass().add("sample-control-grid-prop-label");
            GridPane.setConstraints(propName, 0, row);
            getChildren().add(propName);
            if (prop.valueModel instanceof DoubleProperty) {
                final Label valueLabel = new Label(twoDp.format(prop.initialValue));
                GridPane.setConstraints(valueLabel, 2, row);
                final Slider slider = new Slider();
                slider.setMin(prop.min);
                slider.setMax(prop.max);
                slider.setValue(((Number) prop.initialValue).doubleValue());
                GridPane.setConstraints(slider, 1, row);
                slider.setMaxWidth(Double.MAX_VALUE);
                slider.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable ov) {
                        set(prop.valueModel, slider.getValue());
                        valueLabel.setText(twoDp.format(slider.getValue()));
                    }
                });
                getChildren().addAll(slider, valueLabel);
            } else { //if (prop.property.getType() == Color.class || prop.property.getType() == Paint.class) {
                // FIXME we assume anything that isn't a double property is now a colour property
                final Rectangle colorRect = new Rectangle(20, 20, (Color) prop.initialValue);
                colorRect.setStroke(Color.GRAY);
                final Label valueLabel = new Label(formatWebColor((Color) prop.initialValue));
                valueLabel.setGraphic(colorRect);
                valueLabel.setContentDisplay(ContentDisplay.LEFT);
                GridPane.setConstraints(valueLabel, 2, row);
                final SimpleHSBColorPicker colorPicker = new SimpleHSBColorPicker();
                GridPane.setConstraints(colorPicker, 1, row);
                colorPicker.getColor().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable valueModel) {
                        Color c = colorPicker.getColor().get();
                        set(prop.valueModel, c);
                        valueLabel.setText(formatWebColor(c));
                        colorRect.setFill(c);
                    }
                });
                getChildren().addAll(colorPicker, valueLabel);
            }
            row++;
        }
    }

    private static String formatWebColor(Color c) {
        String r = Integer.toHexString((int) (c.getRed() * 255));
        if (r.length() == 1) {
            r = "0" + r;
        }
        String g = Integer.toHexString((int) (c.getGreen() * 255));
        if (g.length() == 1) {
            g = "0" + g;
        }
        String b = Integer.toHexString((int) (c.getBlue() * 255));
        if (b.length() == 1) {
            b = "0" + b;
        }
        return "#" + r + g + b;
    }

    public static Object get(ObservableValue valueModel) {
        if (valueModel instanceof DoubleProperty) {
            return ((DoubleProperty) valueModel).get();
        } else if (valueModel instanceof ObjectProperty) {
            return ((ObjectProperty) valueModel).get();
        }

        return null;
    }

    public static void set(ObservableValue valueModel, Object value) {
        if (valueModel instanceof DoubleProperty) {
            ((DoubleProperty) valueModel).set((Double) value);
        } else if (valueModel instanceof ObjectProperty) {
            ((ObjectProperty) valueModel).set(value);
        }
    }

    public static final class PropDesc {

        private String name;
        private Double min;
        private Double max;
        private Object initialValue;
        private ObservableValue valueModel;

        public PropDesc(String name, ObservableValue valueModel) {
            this.name = name;
            this.valueModel = valueModel;
            this.initialValue = get(valueModel);
        }

        public PropDesc(String name, DoubleProperty valueModel, Double min, Double max) {
            this.name = name;
            this.valueModel = valueModel;
            this.initialValue = valueModel.get();
            this.min = min;
            this.max = max;
        }
    }
}
