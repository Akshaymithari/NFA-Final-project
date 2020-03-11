package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AnnualBill_controller implements Initializable {

    @FXML
    private JFXListView<String> inv;

    @FXML
    private JFXListView<String> date;

    @FXML
    private JFXListView<String> podate;

    @FXML
    private JFXListView<String> mat;

    @FXML
    private JFXListView<String> qty;

    @FXML
    private JFXListView<String> rpr;

    @FXML
    private JFXListView<String> gt;


    @FXML
    private JFXTextField lb1;

    @FXML
    private Button print;

    @FXML
    private Button show;

    @FXML
    private AnchorPane myprint1;

@FXML
private  JFXComboBox<String> comb_company_name;
    //KeyEvent.KEY_TYPED
private  String s;
    @FXML
    void printbill(ActionEvent event) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout paisagem = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        Window stage =  ((Node)event.getSource()).getScene().getWindow();

        PrinterJob job = PrinterJob.createPrinterJob(printer);
        myprint1.getChildren().remove(print);
        myprint1.getChildren().remove(show);
        job.getJobSettings().setPageLayout(paisagem);
        if (job != null) {
            boolean showDialog = job.showPrintDialog(stage);
            if (showDialog) {
                myprint1.setScaleX(0.6);
                myprint1.setScaleY(0.6);

                boolean success = job.printPage(myprint1);
                if (success) {
                    job.endJob();
                }
                myprint1.setTranslateX(0);
                myprint1.setTranslateY(0);
                myprint1.setScaleX(1.0);
                myprint1.setScaleY(1.0);


            }
        }
    }

    EventHandler<KeyEvent> ke = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            ObservableList<String> ob = FXCollections.observableArrayList();
            comb_company_name.getItems().clear();
            SqliteConnection sql = new SqliteConnection();
            Connection con = sql.conn();
            String s = comb_company_name.getEditor().getText();
            if (s == null) {
                s = "";
            }
            String qu = "select company_name from orders_late where company_name like '" + s + "%'";
            ResultSet rs = null;
            try {
                PreparedStatement ps = con.prepareStatement(qu);
                rs = ps.executeQuery();
                while (rs.next()) {
                    comb_company_name.getItems().add(rs.getString("company_name"));
                }
                // System.out.println(rs.getString(1));
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //comb_company_name.getItems().add(rs.getString(1));
            comb_company_name.show();
        }
    };

    EventHandler<MouseEvent> me = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            s = comb_company_name.getSelectionModel().getSelectedItem();
            SqliteConnection sql = new SqliteConnection();
            Connection con = sql.conn();
            String qu = "select * from orders_late where company_name like '" + s + "%'";
            ResultSet rs = null;
            try {
                PreparedStatement ps = con.prepareStatement(qu);
                rs = ps.executeQuery();
                while (rs.next()) {
                    inv.getItems().add(rs.getString("inv_no"));
                    date.getItems().add(rs.getString("despatch_date"));
                    podate.getItems().add(rs.getString("order_date"));
                    mat.getItems().add(rs.getString("product_name"));
                    qty.getItems().add(rs.getString("no_of_rolls"));
                    rpr.getItems().add(rs.getString("rate_per_roll_or_unit"));
                    gt.getItems().add(rs.getString("GRAND_TOTAL"));

                }
                // System.out.println(rs.getString(1));
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    @FXML
    void showtotal(ActionEvent event) throws SQLException {
        SqliteConnection sql = new SqliteConnection();
        Connection con = sql.conn();
        PreparedStatement pr=con.prepareStatement("select sum(GRAND_TOTAL) from orders_late where company_name like '" + s + "%'");
        ResultSet r=pr.executeQuery();

        lb1.setText(String.valueOf(r.getString(1)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comb_company_name.addEventFilter(KeyEvent.KEY_TYPED,ke);
        comb_company_name.addEventFilter(MouseEvent.MOUSE_CLICKED, me);
    }
}
