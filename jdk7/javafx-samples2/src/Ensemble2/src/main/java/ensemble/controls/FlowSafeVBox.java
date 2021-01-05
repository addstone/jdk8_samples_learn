package ensemble.controls;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Description:  FlowSafeVBox, simple vbox that supports flow style children
 * Author: LuDaShi
 * Date: 2021-01-05-16:21
 * UpdateDate: 2021-01-05-16:21
 * FileName: FlowSafeVBox
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class FlowSafeVBox extends Pane {

    private double vgap = 8;

    @Override
    protected double computeMinWidth(double height) {
        double minWidth = 0;
        for (Node child : getChildren()) {
            minWidth = Math.max(minWidth, child.minWidth(-1));
        }
        return getPadding().getLeft() + minWidth + getPadding().getRight();
    }

    @Override
    protected double computeMinHeight(double width) {
        double minHeight = 0;
        for (Node child : getChildren()) {
            minHeight += child.minWidth(-1) + vgap;
        }
        if (!getChildren().isEmpty()) {
            minHeight -= vgap;
        }
        return getPadding().getTop() + minHeight + getPadding().getBottom();
    }

    @Override
    protected double computePrefWidth(double height) {
        double prefWidth = 0;
        for (Node child : getChildren()) {
            prefWidth = Math.max(prefWidth, child.prefWidth(-1));
        }
        return prefWidth;
    }

    @Override
    protected double computePrefHeight(double width) {
        double height = 0;
        for (Node child : getChildren()) {
            height += vgap + child.prefHeight(width);
        }
        if (!getChildren().isEmpty()) {
            height -= vgap;
        }
        return getPadding().getTop() + height + getPadding().getBottom();
    }

    @Override
    protected double computeMaxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    protected double computeMaxHeight(double width) {
        return Double.MAX_VALUE;
    }

    @Override
    protected void layoutChildren() {
        double top = getPadding().getTop();
        double left = getPadding().getLeft();
        double right = getPadding().getRight();
        double w = getWidth() - left - right;
        double y = top;
        for (Node child : getChildren()) {
            double prefH = child.prefHeight(w);
            child.resizeRelocate(
                snapPosition(left),
                snapPosition(y),
                snapSize(w),
                snapSize(prefH)
            );
            y += vgap + prefH;
        }
    }
}