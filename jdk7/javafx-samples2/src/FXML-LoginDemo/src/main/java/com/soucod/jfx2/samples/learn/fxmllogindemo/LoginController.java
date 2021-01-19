package com.soucod.jfx2.samples.learn.fxmllogindemo;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Description:  Login Controller.
 * Author: LuDaShi
 * Date: 2021-01-19-9:24
 * UpdateDate: 2021-01-19-9:24
 * FileName: LoginController
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */
public class LoginController extends AnchorPane implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;

    private Main application;


    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
        userId.setPromptText("demo");
        password.setPromptText("demo");

    }


    public void processLogin(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            errorMessage.setText("Hello " + userId.getText());
        } else {
            if (!application.userLogging(userId.getText(), password.getText())) {
                errorMessage.setText("Username/Password is incorrect");
            }
        }
    }
}
