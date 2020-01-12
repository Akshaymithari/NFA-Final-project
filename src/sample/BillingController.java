package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

public class BillingController {

    @FXML
    private AnchorPane billing_pane;

    @FXML
    private JFXButton btn_back_bill;

    @FXML
    private AnchorPane billing_tab;

    @FXML
    private JFXTextField company_name;

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
    private JFXTextField product_name;

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
    @FXML
    void clearAll(ActionEvent event) {

    }

    @FXML
    void saveDetails(ActionEvent event) throws SQLException {
        SqliteConnection con=new SqliteConnection();
        Connection co=con.conn();
        try {
            DatabaseMetaData dbm=co.getMetaData();
            ResultSet rm= dbm.getTables(null,null,"orders_late",null);
            if(rm.next()) {
               String query = "Insert into orders_late(company_name,address,mobile_no,order_date,despatch_date,po_no,transport,gst_no,product_name,length,width,height,cgst,sgst,igst,no_of_rolls,rate_per_roll_or_unit,no_of_bags,sub_amount,packaging_price,round_off,grand_totaL,amount_in_words)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

               PreparedStatement ps = co.prepareStatement(query);

                float rate=Integer.parseInt(rate_roll.getText());
                float qty=Integer.parseInt(no_rolls.getText());
                float price=rate*qty;
                float pck=Integer.parseInt(packaging_amt.getText());
                float sgs=Float.parseFloat(sgst.getText());
                float cgs=Float.parseFloat(cgst.getText());
                float igs=Float.parseFloat(igst.getText());
                float grand=price+pck+((price+pck)*sgs/100)+((price+pck)*cgs/100)+((price+pck)*igs/100);
                ps.setString(1,company_name.getText());
                ps.setString(2,address.getText());
                ps.setString(3,mobile_no.getText());
                ps.setString(4,order_date.getValue().toString());
                ps.setString(5,despatch_date.getValue().toString());
                ps.setString(6,po_no.getText());
                ps.setString(7,transport.getText());
                ps.setString(8,gst_no.getText());
                ps.setString(9,product_name.getText());
                ps.setString(10,length.getText());
                ps.setString(11,width.getText());
                ps.setString(12,height.getText());
                ps.setFloat(13,Float.parseFloat(cgst.getText()));
                ps.setFloat(14,Float.parseFloat(sgst.getText()));
                ps.setFloat(15,Float.parseFloat(igst.getText()));
                ps.setString(16,no_rolls.getText());
                ps.setString(17,rate_roll.getText());
                ps.setString(18,no_of_bags.getText());
                ps.setString(19,String.valueOf(price));
                ps.setString(20,packaging_amt.getText());
                ps.setString(21,String.valueOf(grand));
                ps.setString(22,String.valueOf(grand));
                ps.setString(23,String.valueOf(grand));
                int rs = ps.executeUpdate();
                System.out.println("Details Saved: "+rs);
                //String s="drop table orders";
                //PreparedStatement ps = co.prepareStatement(s);
               // boolean rs=ps.execute();
               // System.out.println("Deleted Table");
                String qu="select * from orders_late";
                PreparedStatement ps2 = co.prepareStatement(qu);
                ResultSet res= ps2.executeQuery();
                while(res.next()) {
                    System.out.print(res.getString(5)+" ");
                    System.out.print(res.getString(6)+" ");
                    System.out.print(res.getString(22)+" ");
                    System.out.print(res.getString(23)+" ");
                    //System.out.print(res.getString(5)+" ");
                }
                }
            else {
                String s=" create table orders_late(inv_no INTEGER PRIMARY KEY, company_name TEXT, ADDRESS TEXT, mobile_no VARCHAR(12),order_date TEXT,despatch_date TEXT, po_no TEXT,transport TEXT,gst_no TEXT, product_name TEXT,length TEXT,width TEXT,height TEXT, cgst REAL, sgst REAL, igst REAL,no_of_rolls TEXT, rate_per_roll_or_unit TEXT,no_of_bags TEXT,sub_amount TEXT,packaging_price TEXT, ROUND_OFF TEXT, GRAND_TOTAL TEXT, AMOUNT_IN_WORDS TEXT)";
                PreparedStatement ps = co.prepareStatement(s);
                boolean rs=ps.execute();
                System.out.println("Created Table");
            }
        }catch(Exception e){e.printStackTrace();}
                       }


            }