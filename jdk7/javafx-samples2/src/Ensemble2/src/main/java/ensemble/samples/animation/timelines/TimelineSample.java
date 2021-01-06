package ensemble.samples.animation.timelines;

import ensemble.Sample;
import ensemble.controls.SimplePropertySheet;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.util.Duration;

/**
 * Description:
 * A sample that demonstrates the basics of timeline creation.
 *
 * @see javafx.animation.KeyFrame
 * @see javafx.animation.KeyValue
 * @see javafx.animation.Timeline
 * @see javafx.util.Duration
 * Author: LuDaShi
 * Date: 2021-01-05-17:24
 * UpdateDate: 2021-01-05-17:24
 * FileName: TimelineSample
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class TimelineSample extends Sample {

    Timeline timeline;

    public TimelineSample() {
        super(280, 120);

        //create a circle
        final Circle circle = new Circle(25, 25, 20, Color.web("1c89f4"));
        circle.setEffect(new Lighting());

        //create a timeline for moving the circle
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        //one can start/pause/stop/play animation by
        //timeline.play();
        //timeline.pause();
        //timeline.stop();
        //timeline.playFromStart();

        //add the following keyframes to the timeline
        timeline.getKeyFrames().addAll
            (new KeyFrame(Duration.ZERO,
                    new KeyValue(circle.translateXProperty(), 0)),
                new KeyFrame(new Duration(4000),
                    new KeyValue(circle.translateXProperty(), 205)));

        getChildren().add(createNavigation());
        getChildren().add(circle);
        // REMOVE ME
        setControls(
            new SimplePropertySheet.PropDesc("Timeline rate", timeline.rateProperty(), -4d, 4d)
            //TODO it is possible to do it for integer?
        );
        // END REMOVE ME
    }

    @Override
    public void stop() {
        timeline.stop();
    }

    private VBox createNavigation() {
        //method for creating navigation panel
        //start/stop/pause/play from start buttons
        Button buttonStart = new Button("Start");
        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //start timeline
                timeline.play();
            }
        });
        Button buttonStop = new Button("Stop");
        buttonStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //stop timeline
                timeline.stop();
            }
        });
        Button buttonPlayFromStart = new Button("Play From Start");
        buttonPlayFromStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //play from start
                timeline.playFromStart();
            }
        });
        Button buttonPause = new Button("Pause");
        buttonPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //pause from start
                timeline.pause();
            }
        });
        //text showing current time
        final Text currentRateText = new Text("Current time: 0 ms");
        currentRateText.setBoundsType(TextBoundsType.VISUAL);
        timeline.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                int time = (int) timeline.getCurrentTime().toMillis();
                currentRateText.setText("Current time: " + time + " ms");
            }
        });
        //Autoreverse checkbox
        final CheckBox checkBoxAutoReverse = new CheckBox("Auto Reverse");
        checkBoxAutoReverse.setSelected(true);
        checkBoxAutoReverse.selectedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                timeline.setAutoReverse(checkBoxAutoReverse.isSelected());
            }
        });
        //add all navigation to layout
        HBox hBox1 = new HBox(10);
        hBox1.setPadding(new Insets(0, 0, 0, 5));
        hBox1.getChildren().addAll(buttonStart, buttonPause, buttonStop, buttonPlayFromStart);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        HBox hBox2 = new HBox(10);
        hBox2.setPadding(new Insets(0, 0, 0, 35));
        hBox2.getChildren().addAll(checkBoxAutoReverse, currentRateText);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        VBox vBox = new VBox(10);
        vBox.setLayoutY(60);
        vBox.getChildren().addAll(hBox1, hBox2);
        return vBox;
    }

    // REMOVE ME
    public static Node createIconContent() {
        Circle circle1 = new Circle(30, 57, 10, Color.web("#1c89f4", 0.95));
        Circle circle2 = new Circle(79, 57, 10, Color.web("#1c89f4", 0.55));

        final Timeline tl = new Timeline();
        tl.setCycleCount(Timeline.INDEFINITE);

        tl.getKeyFrames().addAll
            (new KeyFrame(Duration.ZERO,
                    new KeyValue(circle1.translateXProperty(), 0)),
                new KeyFrame(Duration.seconds(1.3),
                    new KeyValue(circle1.translateXProperty(), 54)));

        Rectangle mouseRect = new Rectangle(0, 0, 114, 114);
        mouseRect.setFill(Color.TRANSPARENT);

        javafx.scene.Group g = new javafx.scene.Group(mouseRect,
            circle1, circle2);
        g.setEffect(new Lighting());

        g.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
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
