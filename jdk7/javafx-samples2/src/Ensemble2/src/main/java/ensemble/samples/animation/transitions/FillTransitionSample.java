package ensemble.samples.animation.transitions;

import ensemble.Sample;
import javafx.animation.FillTransition;
import javafx.animation.FillTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Description:
 * A sample in which the filling of a shape changes over a given time.
 *
 * @related animation/transitions/FadeTransition
 * @related animation/transitions/ParallelTransition
 * @related animation/transitions/PathTransition
 * @related animation/transitions/PauseTransition
 * @related animation/transitions/RotateTransition
 * @related animation/transitions/ScaleTransition
 * @related animation/transitions/SequentialTransition
 * @related animation/transitions/StrokeTransition
 * @related animation/transitions/TranslateTransition
 * @see javafx.animation.FillTransition
 * @see javafx.animation.FillTransitionBuilder
 * @see javafx.animation.Transition
 * Author: LuDaShi
 * Date: 2021-01-05-17:31
 * UpdateDate: 2021-01-05-17:31
 * FileName: FillTransitionSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class FillTransitionSample extends Sample {

    private FillTransition fillTransition;

    public FillTransitionSample() {
        super(100, 100);
        Rectangle rect = new Rectangle(0, 0, 100, 100);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setFill(Color.DODGERBLUE);
        getChildren().add(rect);

        fillTransition = FillTransitionBuilder.create()
            .duration(Duration.seconds(3))
            .shape(rect)
            .fromValue(Color.RED)
            .toValue(Color.DODGERBLUE)
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
    }

    @Override
    public void play() {
        fillTransition.play();
    }

    @Override
    public void stop() {
        fillTransition.stop();
    }

    // REMOVE ME
    public static Node createIconContent() {
        Rectangle rect = new Rectangle(25, 25, 64, 64);
        rect.setArcHeight(15);
        rect.setArcWidth(15);
        rect.setFill(Color.web("#349b00"));
        final FillTransition ft = FillTransitionBuilder
            .create()
            .duration(Duration.seconds(3))
            .shape(rect)
            .fromValue(Color.web("#349b00"))
            .toValue(Color.RED).cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
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
        return new javafx.scene.Group(rect, mouseRect);
    }
    // END REMOVE ME
}
