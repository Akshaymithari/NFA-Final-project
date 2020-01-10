package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BillingController {

    @FXML
    private AnchorPane billing_pane;

    @FXML
    private JFXButton btn_back_bill;

    @FXML
    private AnchorPane billing_tab;

    @FXML
    void backBilling(ActionEvent event) {

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

}