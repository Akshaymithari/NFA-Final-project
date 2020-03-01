package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class productController implements Initializable {

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
    public void initialize(URL location, ResourceBundle resources) {
        SqliteConnection co=new SqliteConnection();
        Connection conn = co.conn();

        productLine();
    }

    public void productLine() {

        XYChart.Series set1=new XYChart.Series<>();
        set1.setName("COTTON");
        set1.getData().add(new XYChart.Data("JAN",100) );
        set1.getData().add(new XYChart.Data("FEB",92) );
        set1.getData().add(new XYChart.Data("MAR",50) );
        set1.getData().add(new XYChart.Data("APR",100) );
        set1.getData().add(new XYChart.Data("MAY",69) );
        set1.getData().add(new XYChart.Data("JUN",69) );
        set1.getData().add(new XYChart.Data("JUL",69) );
        set1.getData().add(new XYChart.Data("AUG",69) );
        set1.getData().add(new XYChart.Data("SEP",47) );
        set1.getData().add(new XYChart.Data("OCT",69) );
        set1.getData().add(new XYChart.Data("NOV",69) );
        set1.getData().add(new XYChart.Data("DEC",69) );

        XYChart.Series set2=new XYChart.Series<>();
        set2.setName("TERRYCOTTA");
        set2.getData().add(new XYChart.Data("JAN",100) );
        set2.getData().add(new XYChart.Data("FEB",97) );
        set2.getData().add(new XYChart.Data("MAR",50) );
        set2.getData().add(new XYChart.Data("APR",100) );
        set2.getData().add(new XYChart.Data("MAY",69) );
        set2.getData().add(new XYChart.Data("JUN",69) );
        set2.getData().add(new XYChart.Data("JUL",69) );
        set2.getData().add(new XYChart.Data("AUG",69) );
        set2.getData().add(new XYChart.Data("SEP",39) );
        set2.getData().add(new XYChart.Data("OCT",55) );
        set2.getData().add(new XYChart.Data("NOV",69) );
        set2.getData().add(new XYChart.Data("DEC",69) );

        product_rate.getYAxis().setLabel("SALES(QUANTITY)");
        product_rate.getXAxis().setLabel("MONTHS");


        product_rate.getYAxis().setTickLabelsVisible(true);

        product_rate.getYAxis().setTickMarkVisible(true);
        product_rate.getXAxis().setTickMarkVisible(true);
        product_rate.getXAxis().setTickLabelsVisible(true);
        product_rate.getData().add(set1);
        product_rate.getData().add(set2);
    }

}