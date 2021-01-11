package ensemble.samples.animation.transitions;

import ensemble.Sample;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Description:
 * A sample in which the opacity of a node changes over a given time.
 * 一个示例，其中节点的不透明度在给定时间内改变。
 *
 * @related animation/transitions/FillTransition
 * @related animation/transitions/ParallelTransition
 * @related animation/transitions/PathTransition
 * @related animation/transitions/PauseTransition
 * @related animation/transitions/RotateTransition
 * @related animation/transitions/ScaleTransition
 * @related animation/transitions/SequentialTransition
 * @related animation/transitions/StrokeTransition
 * @related animation/transitions/TranslateTransition
 * @see javafx.animation.FadeTransition
 * @see javafx.animation.FadeTransitionBuilder
 * @see javafx.animation.Transition
 * Author: LuDaShi
 * Date: 2021-01-05-17:30
 * UpdateDate: 2021-01-05-17:30
 * FileName: FadeTransitionSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class FadeTransitionSample extends Sample {

    private FadeTransition fadeTransition;

    public FadeTransitionSample() {
        super(100, 100);
        Rectangle rect = new Rectangle(0, 0, 100, 100);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setFill(Color.DODGERBLUE);
        getChildren().add(rect);

        fadeTransition = FadeTransitionBuilder.create()
            .duration(Duration.seconds(4))
            .node(rect)
            .fromValue(1)
            .toValue(0.2)
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
    }

    @Override
    public void play() {
        fadeTransition.play();
    }

    @Override
    public void stop() {
        fadeTransition.stop();
    }

    // REMOVE ME
    public static Node createIconContent() {
        Rectangle r1 = new Rectangle(20, 20, 30, 30);
        r1.setArcHeight(4);
        r1.setArcWidth(4);
        r1.setFill(Color.web("#349b00"));
        Rectangle r2 = new Rectangle(35, 35, 30, 30);
        r2.setArcHeight(4);
        r2.setArcWidth(4);
        r2.setFill(Color.web("#349b00"));
        r2.setOpacity(0.75);
        Rectangle r3 = new Rectangle(50, 50, 30, 30);
        r3.setArcHeight(4);
        r3.setArcWidth(4);
        r3.setFill(Color.web("#349b00"));
        r3.setOpacity(0.5);
        Rectangle r4 = new Rectangle(64, 64, 30, 30);
        r4.setArcHeight(4);
        r4.setArcWidth(4);
        r4.setFill(Color.web("#349b00"));
        r4.setOpacity(0.25);
        final javafx.scene.Group g = new javafx.scene.Group(r1, r2, r3, r4);
        final FadeTransition ft = new FadeTransition(Duration.seconds(1), g);
        ft.setFromValue(1);
        ft.setToValue(0.1);
        ft.setAutoReverse(true);
        ft.setCycleCount(Timeline.INDEFINITE);
        Rectangle mouseRect = new Rectangle(0, 0, 114, 114);
        mouseRect.setFill(Color.TRANSPARENT);
        mouseRect.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                ft.play();
            }
        });
        mouseRect.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                ft.pause();
            }
        });
        return new javafx.scene.Group(g, mouseRect);
    }
    // END REMOVE ME
}

