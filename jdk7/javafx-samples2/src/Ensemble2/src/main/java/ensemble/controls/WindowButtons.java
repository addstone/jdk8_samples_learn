package ensemble.controls;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Description:  Vertical box with 3 small buttons for window close, minimize and maximize.
 * Description:  带有3个小按钮的垂直框，用于关闭，最小化和最大化窗口。.
 * Author: LuDaShi
 * Date: 2021-01-05-13:23
 * UpdateDate: 2021-01-05-13:23
 * FileName: WindowButtons
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class WindowButtons extends VBox {

    private Stage stage;
    private Rectangle2D backupWindowBounds = null;
    private boolean maximized = false;

    public WindowButtons(final Stage stage) {
        super(4);
        this.stage = stage;
        // create buttons
        VBox buttonBox = new VBox(4);
        Button closeBtn = new Button();
        closeBtn.setId("window-close");
        closeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        Button minBtn = new Button();
        minBtn.setId("window-min");
        minBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setIconified(true);
            }
        });
        Button maxBtn = new Button();
        maxBtn.setId("window-max");
        maxBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                toogleMaximized();
            }
        });
        getChildren().addAll(closeBtn, minBtn, maxBtn);
    }

    public void toogleMaximized() {
        final Screen screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1).get(0);
        if (maximized) {
            maximized = false;
            if (backupWindowBounds != null) {
                stage.setX(backupWindowBounds.getMinX());
                stage.setY(backupWindowBounds.getMinY());
                stage.setWidth(backupWindowBounds.getWidth());
                stage.setHeight(backupWindowBounds.getHeight());
            }
        } else {
            maximized = true;
            backupWindowBounds = new Rectangle2D(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
            stage.setX(screen.getVisualBounds().getMinX());
            stage.setY(screen.getVisualBounds().getMinY());
            stage.setWidth(screen.getVisualBounds().getWidth());
            stage.setHeight(screen.getVisualBounds().getHeight());
        }
    }

    public boolean isMaximized() {
        return maximized;
    }
}

