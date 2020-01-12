package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private AnchorPane emp_pane;

    @FXML
    private JFXButton btn_back_emp;

    @FXML
    private Tab tab_employee;

    @FXML
    private AnchorPane tab_emp;

    @FXML
    private JFXListView<?> list_emp;

    @FXML
    private Label txt_background;

    @FXML
    private TextField txt_lname;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_mob_no;

    @FXML
    private TextField txt_email_id;

    @FXML
    private TextField txt_fname;

    @FXML
    private TextField txt_date_of_hire;

    @FXML
    private TextField txt_designation;

    @FXML
    private TextField txt_age;

    @FXML
    private JFXButton btn_emp_edit;

    @FXML
    private JFXButton btn_emp_remove;

    @FXML
    private Tab tab_register;

    @FXML
    private AnchorPane tab_res;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField pincode;

    @FXML
    private JFXTextField mob_no;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXRadioButton male;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXTextField age;

    @FXML
    private JFXDatePicker date_of_hire;

    @FXML
    private JFXTextField experience;

    @FXML
    private JFXTextField designation;

    @FXML
    private JFXButton btn_emp_add;

    @FXML
    private JFXTextArea txt_background_detail;

    @FXML
    void registerEmployee(ActionEvent event) {
        SqliteConnection cone = new SqliteConnection();
        Connection co = cone.conn();
        String gender;
        if (male.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        try {
            String query;
            query = "insert into employee_new values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = co.prepareStatement(query);
            ps.setString(1, fname.getText());
            ps.setString(2, lname.getText());
            ps.setString(3, address.getText());
            ps.setString(4, pincode.getText());
            ps.setString(5, mob_no.getText());
            ps.setString(6, email.getText());
            ps.setString(7, dob.getValue().toString());
            ps.setString(8, gender);
            ps.setString(9, date_of_hire.getValue().toString());
            ps.setString(10, designation.getText());
            ps.setString(11, age.getText());
            ps.setString(12, experience.getText());
            ps.setString(13, txt_background_detail.getText());
            ps.setString(14, password.getText());
            int rs = ps.executeUpdate();
            System.out.println("Record Saved");
            //  PreparedStatement
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backEmp(ActionEvent event) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initList();
    }

    public void initList() {
    }
}