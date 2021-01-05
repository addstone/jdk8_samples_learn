package ensemble.samples.animation.transitions;

import ensemble.Sample;
import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Description:
 * A sample in which a node rotates around its center over a given time.
 *
 * @related animation/transitions/FadeTransition
 * @related animation/transitions/FillTransition
 * @related animation/transitions/ParallelTransition
 * @related animation/transitions/PathTransition
 * @related animation/transitions/PauseTransition
 * @related animation/transitions/ScaleTransition
 * @related animation/transitions/SequentialTransition
 * @related animation/transitions/StrokeTransition
 * @related animation/transitions/TranslateTransition
 * @see javafx.animation.RotateTransition
 * @see javafx.animation.RotateTransitionBuilder
 * @see javafx.animation.Transition
 * Author: LuDaShi
 * Date: 2021-01-05-17:36
 * UpdateDate: 2021-01-05-17:36
 * FileName: RotateTransitionSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class RotateTransitionSample extends Sample {

    private RotateTransition rotateTransition;

    public RotateTransitionSample() {
        super(140, 140);

        Rectangle rect = new Rectangle(20, 20, 100, 100);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setFill(Color.ORANGE);
        getChildren().add(rect);

        rotateTransition = RotateTransitionBuilder.create()
            .node(rect)
            .duration(Duration.seconds(4))
            .fromAngle(0)
            .toAngle(720)
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
    }

    @Override
    public void play() {
        rotateTransition.play();
    }

    @Override
    public void stop() {
        rotateTransition.stop();
    }

    // REMOVE ME
    public static Node createIconContent() {
        Rectangle r1 = new Rectangle(25, 25, 64, 64);
        r1.setArcHeight(4);
        r1.setArcWidth(4);
        r1.setFill(Color.web("#349b00"));
        Rectangle r2 = new Rectangle(25, 25, 64, 64);
        r2.setArcHeight(4);
        r2.setArcWidth(4);
        r2.setFill(Color.web("#349b00"));
        r2.setOpacity(0.5);
        r2.setRotate(30);
        Rectangle r3 = new Rectangle(25, 25, 64, 64);
        r3.setArcHeight(4);
        r3.setArcWidth(4);
        r3.setFill(Color.web("#349b00"));
        r3.setRotate(60);
        r3.setOpacity(0.5);
        javafx.scene.Group g = new javafx.scene.Group(r1, r2, r3);
        final RotateTransition rt = new RotateTransition(Duration.seconds(1), g);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAutoReverse(true);
        rt.setFromAngle(0);
        rt.setToAngle(90);
        Rectangle mouseRect = new Rectangle(0, 0, 114, 114);
        mouseRect.setFill(Color.TRANSPARENT);
        mouseRect.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                rt.play();
            }
        });
        mouseRect.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                rt.pause();
            }
        });
        return new javafx.scene.Group(g, mouseRect);
    }
    // END REMOVE ME
}

