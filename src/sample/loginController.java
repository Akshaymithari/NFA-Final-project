package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

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
    private JFXButton btn_reg;



    @FXML
    void loginControl(ActionEvent event) throws SQLException, IOException {
          SqliteConnection co= new SqliteConnection();
          Connection con=co.conn();
        DatabaseMetaData data=con.getMetaData();
        ResultSet rm=data.getTables(null,null,"employee_new",null);
        if(rm.next()){
            String que="select * from employee_new where Email_Id=? and Password=?";
            PreparedStatement pre=con.prepareStatement(que);
            pre.setString(1,txt_username.getText());
            pre.setString(2,txt_password.getText());
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            }else{
                System.out.println("login failed....!");
            }
        }else{
            String que="create table employee_new(First_Name Text, Last_Name Text,Address Text,pincode TEXT,Mobile_No Text,Email_id Text,Date_of_Birth TEXT,sex TEXT,Date_of_Hire Text,Designation Text,Age Text,Experience TEXT,Background Text,Password Text)";
            PreparedStatement pre=con.prepareStatement(que);
            boolean rs=pre.execute();
            System.out.println("Created Table");
        }
            }
    @FXML
    void registeremp(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("employee.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

            }

