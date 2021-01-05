package ensemble.samples.animation.transitions;

import ensemble.Sample;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.ParallelTransition;
import javafx.animation.ParallelTransitionBuilder;
import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Description:
 * A sample in which various transitions are executed in parallel.
 *
 * @related animation/transitions/FadeTransition
 * @related animation/transitions/FillTransition
 * @related animation/transitions/PathTransition
 * @related animation/transitions/PauseTransition
 * @related animation/transitions/RotateTransition
 * @related animation/transitions/ScaleTransition
 * @related animation/transitions/SequentialTransition
 * @related animation/transitions/StrokeTransition
 * @related animation/transitions/TranslateTransition
 * @see javafx.animation.ParallelTransition
 * @see javafx.animation.ParallelTransitionBuilder
 * @see javafx.animation.Transition
 * Author: LuDaShi
 * Date: 2021-01-05-17:32
 * UpdateDate: 2021-01-05-17:32
 * FileName: ParallelTransitionSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class ParallelTransitionSample extends Sample {

    private ParallelTransition parallelTransition;

    public ParallelTransitionSample() {
        super(400, 150);
        // create rectangle
        Rectangle rect = new Rectangle(-25, -25, 50, 50);
        rect.setArcHeight(15);
        rect.setArcWidth(15);
        rect.setFill(Color.CRIMSON);
        rect.setTranslateX(50);
        rect.setTranslateY(75);
        getChildren().add(rect);
        // create parallel transition to do all 4 transitions at the same time
        parallelTransition = ParallelTransitionBuilder.create()
            .node(rect)
            .children(
                FadeTransitionBuilder.create()
                    .duration(Duration.seconds(3))
                    .node(rect)
                    .fromValue(1)
                    .toValue(0.3)
                    .autoReverse(true)
                    .build(),
                TranslateTransitionBuilder.create()
                    .duration(Duration.seconds(2))
                    .fromX(50)
                    .toX(350)
                    .cycleCount(2)
                    .autoReverse(true)
                    .build(),
                RotateTransitionBuilder.create()
                    .duration(Duration.seconds(3))
                    .byAngle(180)
                    .cycleCount(4)
                    .autoReverse(true)
                    .build(),
                ScaleTransitionBuilder.create()
                    .duration(Duration.seconds(2))
                    .toX(2)
                    .toY(2)
                    .cycleCount(2)
                    .autoReverse(true)
                    .build()
            )
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();

    }

    @Override
    public void play() {
        parallelTransition.play();
    }

    @Override
    public void stop() {
        parallelTransition.stop();
    }

    // REMOVE ME
    public static Node createIconContent() {
        final Rectangle r1 = new Rectangle(20, 20, 20, 20);
        r1.setArcHeight(4);
        r1.setArcWidth(4);
        r1.setFill(Color.web("#349b00"));
        Rectangle r2 = new Rectangle(38, 38, 20, 20);
        r2.setArcHeight(4);
        r2.setArcWidth(4);
        r2.setFill(Color.web("#349b00"));
        r2.setOpacity(0.3);
        r2.setRotate(30);
        Rectangle r3 = new Rectangle(56, 56, 20, 20);
        r3.setArcHeight(4);
        r3.setArcWidth(4);
        r3.setFill(Color.web("#349b00"));
        r3.setOpacity(0.3);
        r3.setRotate(60);
        Rectangle r4 = new Rectangle(74, 74, 20, 20);
        r4.setArcHeight(4);
        r4.setArcWidth(4);
        r4.setFill(Color.web("#349b00"));
        r4.setOpacity(0.3);
        javafx.scene.shape.Line line = new javafx.scene.shape.Line(30, 30, 84, 84);
        line.setStroke(Color.DODGERBLUE);
        line.getStrokeDashArray().setAll(5d, 5d);
        final TranslateTransition tt = new TranslateTransition(Duration.seconds(1), r1);
        tt.setFromX(0);
        tt.setFromY(0);
        tt.setToX(54);
        tt.setToY(54);
        final RotateTransition rt = new RotateTransition(Duration.seconds(1), r1);
        rt.setFromAngle(0);
        rt.setToAngle(180);
        final ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt, rt);
        pt.setCycleCount(Timeline.INDEFINITE);
        pt.setAutoReverse(true);
        Rectangle mouseRect = new Rectangle(0, 0, 114, 114);
        mouseRect.setFill(Color.TRANSPARENT);
        mouseRect.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                pt.play();
            }
        });
        mouseRect.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                pt.stop();
                r1.setTranslateX(0);
                r1.setTranslateY(0);
                r1.setRotate(0);
            }
        });
        return new javafx.scene.Group(r1, r2, r3, r4, line, mouseRect);
    }
    // END REMOVE ME
}
