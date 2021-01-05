package ensemble.samples.animation.timelines;

import ensemble.Sample;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Description:
 * A sample that demonstrates events triggered during timeline play. The circle
 * changes its radius in a linear fashion during each key frame and randomly
 * jumps to a new location along the x coordinate at the end of the key frame.
 *
 * @see javafx.animation.KeyFrame
 * @see javafx.animation.KeyValue
 * @see javafx.animation.Timeline
 * @see javafx.util.Duration
 * @see javafx.event.ActionEvent
 * @see javafx.event.EventHandler
 * Author: LuDaShi
 * Date: 2021-01-05-17:24
 * UpdateDate: 2021-01-05-17:24
 * FileName: TimelineEventsSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class TimelineEventsSample extends Sample {

    //main timeline
    private Timeline timeline;
    private AnimationTimer timer;

    //variable for storing actual frame
    private Integer i = 0;

    public TimelineEventsSample() {
        super(70, 70);
        //create a circle with effect
        final Circle circle = new Circle(20, Color.rgb(156, 216, 255));
        circle.setEffect(new Lighting());
        //create a text inside a circle
        final Text text = new Text(i.toString());
        text.setStroke(Color.BLACK);
        //create a layout for circle with text inside
        final StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX(30);
        stack.setLayoutY(30);

        //create a timeline for moving the circle

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        //one can add a specific action when each frame is started. There are one or more frames during
        // executing one KeyFrame depending on set Interpolator.
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                text.setText(i.toString());
                i++;
            }

        };

        //create a keyValue with factory: scaling the circle 2times
        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 2);
        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 2);

        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.seconds(2);
        //one can add a specific action when the keyframe is reached
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stack.setTranslateX(Math.random() * 200 - 100);
                //reset counter
                i = 0;
            }
        };

        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);

        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        getChildren().add(stack);
    }

    @Override
    public void play() {
        timeline.play();
        timer.start();
    }

    @Override
    public void stop() {
        timeline.stop();
        timer.stop();
    }

    // REMOVE ME
    public static Node createIconContent() {
        final Circle circle1 = new Circle(30, 40, 10, Color.web("#1c89f4", 0.95));
        Circle circle2 = new Circle(79, 74, 10, Color.web("#1c89f4", 0.55));

        final Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);

        tl.getKeyFrames().addAll
            (new KeyFrame(Duration.ZERO, new KeyValue(circle1.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(650),
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            circle1.setCenterY(74);
                        }
                    }),
                new KeyFrame(Duration.seconds(1.3),
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            circle1.setCenterY(40);
                        }
                    },
                    new KeyValue(circle1.translateXProperty(), 54)));

        javafx.scene.shape.Rectangle mouseRect = new javafx.scene.shape.Rectangle(0, 0, 114, 114);
        mouseRect.setFill(Color.TRANSPARENT);

        javafx.scene.Group g = new javafx.scene.Group(mouseRect, circle1, circle2);
        g.setEffect(new Lighting());

        g.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                circle1.setCenterY(40);
                tl.play();
            }
        });
        g.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                tl.pause();
            }
        });

        return g;
    }
    // END REMOVE ME
}