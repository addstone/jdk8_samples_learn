package ensemble.samples.animation.transitions;

import ensemble.Sample;
import javafx.animation.Animation;
import javafx.animation.PauseTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Description:
 * A sample in which a node pauses over a given time.
 * 节点在给定时间内暂停的样本。
 *
 * @related animation/transitions/FadeTransition
 * @related animation/transitions/FillTransition
 * @related animation/transitions/ParallelTransition
 * @related animation/transitions/PathTransition
 * @related animation/transitions/RotateTransition
 * @related animation/transitions/ScaleTransition
 * @related animation/transitions/SequentialTransition
 * @related animation/transitions/StrokeTransition
 * @related animation/transitions/TranslateTransition
 * @see javafx.animation.PauseTransition
 * @see javafx.animation.PauseTransitionBuilder
 * @see javafx.animation.Transition
 * Author: LuDaShi
 * Date: 2021-01-05-17:34
 * UpdateDate: 2021-01-05-17:34
 * FileName: PauseTransitionSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class PauseTransitionSample extends Sample {

    private Animation animation;

    public PauseTransitionSample() {
        super(400, 150);
        // create rectangle
        Rectangle rect = new Rectangle(-25, -25, 50, 50);
        rect.setArcHeight(15);
        rect.setArcWidth(15);
        rect.setFill(Color.CRIMSON);
        rect.setTranslateX(50);
        rect.setTranslateY(75);
        getChildren().add(rect);

        animation = SequentialTransitionBuilder.create()
            .node(rect)
            .children(
                TranslateTransitionBuilder.create()
                    .duration(Duration.seconds(2))
                    .fromX(50)
                    .toX(200)
                    .build(),
                PauseTransitionBuilder.create()
                    .duration(Duration.seconds(2))
                    .build(),
                TranslateTransitionBuilder.create()
                    .duration(Duration.seconds(2))
                    .fromX(200)
                    .toX(350)
                    .build()
            )
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
    }

    @Override
    public void play() {
        animation.play();
    }

    @Override
    public void stop() {
        animation.stop();
    }
    // REMOVE ME

    public static Node createIconContent() {
        Rectangle rect = new Rectangle(20, 20, 20, 20);
        rect.setArcHeight(4);
        rect.setArcWidth(4);
        rect.setFill(Color.web("#349b00"));
        javafx.scene.shape.Line line = new javafx.scene.shape.Line(30, 30, 84, 84);
        line.setStroke(Color.DODGERBLUE);
        line.getStrokeDashArray().setAll(5d, 5d);
        Rectangle r4 = new Rectangle(74, 74, 20, 20);
        r4.setArcHeight(4);
        r4.setArcWidth(4);
        r4.setFill(Color.RED);
        r4.setOpacity(0.3);
        final SequentialTransition st = SequentialTransitionBuilder.create()
            .node(rect)
            .children(
                TranslateTransitionBuilder.create()
                    .duration(Duration.seconds(2))
                    .fromX(0).fromY(0)
                    .toX(54)
                    .toY(54)
                    .build(),
                PauseTransitionBuilder.create()
                    .duration(Duration.seconds(1))
                    .build())
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
        Rectangle mouseRect = new Rectangle(0, 0, 114, 114);
        mouseRect.setFill(Color.TRANSPARENT);
        mouseRect.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                st.play();
            }
        });
        mouseRect.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                st.pause();
            }
        });
        return new javafx.scene.Group(rect, r4, line, mouseRect);
    }
    // END REMOVE ME
}
