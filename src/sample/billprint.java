package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import java.math.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class billprint implements Initializable {
    public String invnos;


    @FXML
    private AnchorPane myPrint;

    @FXML
    private Pane title_pane;

    @FXML
    private Label cname;

    @FXML
    private Label caddress;

    @FXML
    private Label gst_no;

    @FXML
    private Label inv_no;

    @FXML
    private Label o_date;

    @FXML
    private Label transport;

    @FXML
    private Label po_no;

    @FXML
    private Label no_of_bags;

    @FXML
    private Label round_off;

    @FXML
    private Label grand_tot;

    @FXML
    private Label amt_in_words;

    @FXML
    private Label sub_amt;

    @FXML
    private Label pck;

    @FXML
    private JFXListView<String> sr_no;

    @FXML
    private JFXListView<String> hsn_code;

    @FXML
    private JFXListView<String> mat_desc;

    @FXML
    private JFXListView<String> qty;

    @FXML
    private JFXListView<String> rate_roll;

    @FXML
    private JFXListView<String> sgst;

    @FXML
    private JFXListView<String> cgst;

    @FXML
    private JFXListView<String> igst;

    @FXML
    private JFXListView<String> amt;

    @FXML
    private JFXButton print;

    @FXML
    void printbill(ActionEvent event) throws IOException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout paisagem = Printer.getDefaultPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        Window stage =  ((Node)event.getSource()).getScene().getWindow();
        myPrint.getChildren().remove(print);
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        job.getJobSettings().setPageLayout(paisagem);
        System.out.println(invnos);
        if (job != null) {
            boolean showDialog = job.showPrintDialog(stage);
            if (showDialog) {
                myPrint.setScaleX(0.9);
                myPrint.setScaleY(0.9);

                boolean success = job.printPage(myPrint);
                if (success) {
                    job.endJob();
                }
                myPrint.setTranslateX(0);
                myPrint.setTranslateY(0);
                myPrint.setScaleX(1.0);
                myPrint.setScaleY(1.0);
            }
        }
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SqliteConnection sql = new SqliteConnection();
        Connection con = sql.conn();
        String invqu="select inv_no from orders_late";
        try {
        PreparedStatement ps2=con.prepareStatement(invqu);
        ResultSet invrs=ps2.executeQuery();
        while(invrs.next()) {
            invnos=invrs.getString("inv_no");
        }
        String qu = "select * from orders_late where inv_no=?";
            PreparedStatement ps=con.prepareStatement(qu);
            ps.setString(1,invnos);
            DecimalFormat df=new DecimalFormat("0.00");
            ResultSet rs=ps.executeQuery();
            float sub_a=0;
            float pack=0;
            int count=0;
            while (rs.next()){
                count++;
            inv_no.setText(rs.getString("inv_no"));
            o_date.setText(rs.getString("order_date"));
            transport.setText(rs.getString("transport"));
            po_no.setText(rs.getString("po_no"));
            gst_no.setText(rs.getString("gst_no"));
            caddress.setText(rs.getString("address"));
            cname.setText(rs.getString("company_name"));
            sr_no.getItems().add(String.valueOf(count));
            hsn_code.getItems().add(rs.getString("hsn_code"));
            mat_desc.getItems().add(rs.getString("product_name"));
            qty.getItems().add(rs.getString("no_of_rolls"));
            rate_roll.getItems().add(rs.getString("rate_per_roll_or_unit"));
            sgst.getItems().add(rs.getString("sgst"));
            cgst.getItems().add(rs.getString("cgst"));
            igst.getItems().add(rs.getString("igst"));
            amt.getItems().add(String.valueOf(df.format(Double.valueOf(rs.getString("grand_total")))));
            sub_a=sub_a+Float.parseFloat(rs.getString("grand_total"));
            pack=pack+Float.parseFloat(rs.getString("packaging_price"));
            }
            sub_amt.setText(String.valueOf(sub_a));
            pck.setText(String.valueOf(pack));
            round_off.setText(String.valueOf(Math.round(sub_a+pack)));
            grand_tot.setText(String.valueOf(Math.round(sub_a+pack)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}