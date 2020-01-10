package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class productController {

    @FXML
    private AnchorPane product;

    @FXML
    private Pane product_infopane;

    @FXML
    private JFXButton btn_productadd;

    @FXML
    private JFXButton btn_productdel;

    @FXML
    private JFXButton btn_product_add;

    @FXML
    private Pane product_pane;

    @FXML
    private LineChart<?, ?> product_rate;

    @FXML
    private Pane products;

    @FXML
    private JFXButton btn_transport;

    @FXML
    public void backProduct(ActionEvent event) {
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