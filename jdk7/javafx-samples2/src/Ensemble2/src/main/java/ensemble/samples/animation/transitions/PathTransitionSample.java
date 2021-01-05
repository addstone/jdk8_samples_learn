package ensemble.samples.animation.transitions;

import ensemble.Sample;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Description:
 * A sample in which a node moves along a path from end to end over a given time.
 *
 * @related animation/transitions/FadeTransition
 * @related animation/transitions/FillTransition
 * @related animation/transitions/ParallelTransition
 * @related animation/transitions/PauseTransition
 * @related animation/transitions/RotateTransition
 * @related animation/transitions/ScaleTransition
 * @related animation/transitions/SequentialTransition
 * @related animation/transitions/StrokeTransition
 * @related animation/transitions/TranslateTransition
 * @see javafx.animation.PathTransition
 * @see javafx.animation.PathTransitionBuilder
 * @see javafx.animation.Transition
 * Author: LuDaShi
 * Date: 2021-01-05-17:33
 * UpdateDate: 2021-01-05-17:33
 * FileName: PathTransitionSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class PathTransitionSample extends Sample {

    private PathTransition pathTransition;

    public PathTransitionSample() {
        super(400, 260);
        Rectangle rect = new Rectangle(0, 0, 40, 40);
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(Color.ORANGE);
        getChildren().add(rect);
        Path path = PathBuilder.create()
            .elements(
                new MoveTo(20, 20),
                new CubicCurveTo(380, 0, 380, 120, 200, 120),
                new CubicCurveTo(0, 120, 0, 240, 380, 240)
            )
            .build();
        path.setStroke(Color.DODGERBLUE);
        path.getStrokeDashArray().setAll(5d, 5d);
        getChildren().add(path);

        pathTransition = PathTransitionBuilder.create()
            .duration(Duration.seconds(4))
            .path(path)
            .node(rect)
            .orientation(OrientationType.ORTHOGONAL_TO_TANGENT)
            .cycleCount(Timeline.INDEFINITE)
            .autoReverse(true)
            .build();
    }

    @Override
    public void play() {
        pathTransition.play();
    }

    @Override
    public void stop() {
        pathTransition.stop();
    }

    // REMOVE ME
    public static Node createIconContent() {
        Rectangle rect = new Rectangle(20, 20, 10, 10);
        rect.setArcHeight(4);
        rect.setArcWidth(4);
        rect.setFill(Color.web("#349b00"));
        Path path = new Path();
        path.getElements().add(new MoveTo(25, 25));
        path.getElements().add(new CubicCurveTo(100, 0, 100, 55, 55, 55));
        path.getElements().add(new CubicCurveTo(0, 55, 0, 100, 114 - 25, 114 - 25));
        path.setStroke(Color.DODGERBLUE);
        path.getStrokeDashArray().setAll(5d, 5d);
        final PathTransition pt = new PathTransition();
        pt.setDuration(Duration.seconds(4));
        pt.setPath(path);
        pt.setNode(rect);
        pt.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
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
                pt.pause();
            }
        });
        return new javafx.scene.Group(rect, path, mouseRect);
    }
    // END REMOVE ME
}
