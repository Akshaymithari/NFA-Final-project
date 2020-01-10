package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class drawerController {

    @FXML
    private VBox drawer_menus;

    @FXML
    private JFXButton btn_home;

    @FXML
    private JFXButton btn_product;

    @FXML
    private JFXButton btn_emp;

    @FXML
    private JFXButton btn_billing;

    @FXML
    private JFXButton btn_about;

    @FXML
    private JFXButton btn_help;

    @FXML
    void openProduct(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));
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
    @FXML
    void openEmployee(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("employee.fxml"));
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
    @FXML
    void openBilling(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("billing.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}