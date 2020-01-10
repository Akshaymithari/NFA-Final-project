package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private AnchorPane root_login;

    @FXML
    private AnchorPane pane_login;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private JFXButton btn_login;

    @FXML
    void loginControl(ActionEvent event) {
            if(txt_username.getText().equals("admin") && txt_password.getText().equals("admin")) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    ((Node)event.getSource()).getScene().getWindow().hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                txt_username.setText("Invalid");
                txt_password.setText("Invalid");
            }

            }
    }
