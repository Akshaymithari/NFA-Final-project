package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.misc.Regexp;

import javax.xml.stream.EventFilter;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class BillingController implements Initializable {

    @FXML
    private AnchorPane billing_pane;

    @FXML
    private JFXButton btn_back_bill;

    @FXML
    private AnchorPane billing_tab;

    @FXML
    private JFXComboBox<String> comb_company_name;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField mobile_no;

    @FXML
    private JFXTextField gst_no;

    @FXML
    private JFXDatePicker despatch_date;

    @FXML
    private JFXTextField invoice_no;

    @FXML
    private JFXTextField po_no;

    @FXML
    private JFXTextField no_of_bags;

    @FXML
    private JFXDatePicker order_date;

    @FXML
    private JFXTextField transport;

    @FXML
    private JFXComboBox<String> product_name;

    @FXML
    private JFXTextField length;

    @FXML
    private JFXTextField width;

    @FXML
    private JFXTextField height;

    @FXML
    private JFXTextField no_rolls;

    @FXML
    private JFXTextField rate_roll;

    @FXML
    private JFXTextField packaging_amt;

    @FXML
    private JFXButton nexttoprint;

    @FXML
    private JFXTextField sgst;

    @FXML
    private JFXTextField cgst;

    @FXML
    private JFXTextField igst;

    @FXML
    private JFXButton btn_clearbill;

    @FXML
    private JFXButton btn_printbill;

    @FXML
    private JFXTextField hsn_code;

    @FXML
    void backBilling(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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

    @FXML
    void clearAll(ActionEvent event) throws SQLException {


    }

    @FXML
    void saveDetails(ActionEvent event) throws SQLException {
        SqliteConnection con = new SqliteConnection();
        Connection co = con.conn();
        try {
            DatabaseMetaData dbm = co.getMetaData();
            ResultSet rm = dbm.getTables(null, null, "orders_late", null);
            if (rm.next()) {
                String query = "Insert into orders_late(company_name,address,mobile_no,order_date,despatch_date,po_no,transport,gst_no,product_name,length,width,height,cgst,sgst,igst,no_of_rolls,rate_per_roll_or_unit,no_of_bags,sub_amount,packaging_price,round_off,grand_totaL,amount_in_words,inv_no,hsn_code)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = co.prepareStatement(query);
                float rate = Integer.parseInt(rate_roll.getText());
                float qty = Integer.parseInt(no_rolls.getText());
                float price = rate * qty;
                float pck = Integer.parseInt(packaging_amt.getText());
                float sgs = Float.parseFloat(sgst.getText());
                float cgs = Float.parseFloat(cgst.getText());
                float igs = Float.parseFloat(igst.getText());
                float grand = price + pck + ((price + pck) * sgs / 100) + ((price + pck) * cgs / 100) + ((price + pck) * igs / 100);
                ps.setString(1, comb_company_name.getSelectionModel().getSelectedItem().toString());
                ps.setString(2, address.getText());
                ps.setString(3, mobile_no.getText());
                ps.setString(4, order_date.getValue().toString());
                ps.setString(5, despatch_date.getValue().toString());
                ps.setString(6, po_no.getText());
                ps.setString(7, transport.getText());
                ps.setString(8, gst_no.getText());
                ps.setString(9, product_name.getSelectionModel().getSelectedItem().toString());
                ps.setString(10, length.getText());
                ps.setString(11, width.getText());
                ps.setString(12, height.getText());
                ps.setFloat(13, Float.parseFloat(cgst.getText()));
                ps.setFloat(14, Float.parseFloat(sgst.getText()));
                ps.setFloat(15, Float.parseFloat(igst.getText()));
                ps.setString(16, no_rolls.getText());
                ps.setString(17, rate_roll.getText());
                ps.setString(18, no_of_bags.getText());
                ps.setString(19, String.valueOf(price));
                ps.setString(20, packaging_amt.getText());
                ps.setString(21, String.valueOf(grand));
                ps.setString(22, String.valueOf(grand));
                ps.setString(23, String.valueOf(grand));
                ps.setString(24,invoice_no.getText());
                ps.setString(25,hsn_code.getText());
                int rs = ps.executeUpdate();
                System.out.println("Details Saved: " + rs);
                //String s="drop table orders";
                //PreparedStatement ps = co.prepareStatement(s);
                // boolean rs=ps.execute();
                // System.out.println("Deleted Table");
                String qu = "select * from orders_late";
                PreparedStatement ps2 = co.prepareStatement(qu);
                ResultSet res = ps2.executeQuery();
                while (res.next()) {
                    System.out.print(res.getString(5) + " ");
                    System.out.print(res.getString(6) + " ");
                    System.out.print(res.getString(22) + " ");
                    System.out.print(res.getString(23) + " ");
                    //System.out.print(res.getString(5)+" ");
                }
            } else {
                String s = " create table orders_late(inv_no Text, company_name TEXT, ADDRESS TEXT, mobile_no VARCHAR(12),order_date TEXT,despatch_date TEXT, po_no TEXT,transport TEXT,gst_no TEXT, product_name TEXT,HSN_CODE text,length TEXT,width TEXT,height TEXT, cgst REAL, sgst REAL, igst REAL,no_of_rolls TEXT, rate_per_roll_or_unit TEXT,no_of_bags TEXT,sub_amount TEXT,packaging_price TEXT, ROUND_OFF TEXT, GRAND_TOTAL TEXT, AMOUNT_IN_WORDS TEXT)";
                PreparedStatement ps = co.prepareStatement(s);
                boolean rs = ps.execute();
                System.out.println("Created Table");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            co.close();
        }
        newPoduct();
    }

    public float productChart(String s) throws SQLException {
        SqliteConnection sql = new SqliteConnection();
        Connection con = sql.conn();
        String qu = "select grand_total from orders_late where product_name=?";
        PreparedStatement ps = con.prepareStatement(qu);
        ps.setString(1, s);
        ResultSet rs = ps.executeQuery();
        float sum = 0;
        while (rs.next()) {
            sum = sum + Float.parseFloat(rs.getString(1));
        }
        rs.close();
        con.close();
        return sum;
    }

    @FXML
    public void ComboSetItems() throws SQLException {


    }

    /*@FXML
    void setItem(ActionEvent event) {
        ObservableList<String> ob= FXCollections.observableArrayList();

        SqliteConnection sql=new SqliteConnection();
        Connection con=sql.conn();
        String s=comb_company_name.getEditor().getText();
        if(s==null) {
            s="";
        }
        String qu="select Name from product where company_name like '"+s+"%'";
        ResultSet rs= null;
        try {
            PreparedStatement ps=con.prepareStatement(qu);
            rs = ps.executeQuery();
            while(rs.next()) {
                comb_company_name.getItems().add(rs.getString(1));
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
*/
    @FXML
    void nexttobill(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bill.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            billprint bc = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Bill");
            stage.show();
            ((Node)event.getSource()).getScene().getWindow().hide();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void newPoduct() {
        no_of_bags.clear();
        product_name.getItems().clear();
        product_name.getEditor().clear();
        length.clear();
        width.clear();
        height.clear();
        no_rolls.clear();
        rate_roll.clear();
        packaging_amt.clear();
        hsn_code.clear();
        cgst.clear();
        igst.clear();
        sgst.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comb_company_name.addEventFilter(KeyEvent.KEY_TYPED, ke);
        comb_company_name.addEventFilter(MouseEvent.MOUSE_CLICKED, me);
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
            String s = comb_company_name.getSelectionModel().getSelectedItem();
            SqliteConnection sql = new SqliteConnection();
            Connection con = sql.conn();
            String qu = "select * from orders_late where company_name like '" + s + "%'";
            ResultSet rs = null;
            try {
                PreparedStatement ps = con.prepareStatement(qu);
                rs = ps.executeQuery();
                while (rs.next()) {
                    address.setText(rs.getString("address"));
                    mobile_no.setText(rs.getString("mobile_no"));
                    gst_no.setText(rs.getString("gst_no"));
                    order_date.getEditor().setText(rs.getString("order_date"));
                    despatch_date.getEditor().setText(rs.getString("despatch_date"));
                    po_no.setText(rs.getString("po_no"));
                }
                // System.out.println(rs.getString(1));
                con.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };



}



