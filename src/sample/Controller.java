package sample;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller<employee> implements Initializable {

    @FXML
    private TableView<ObservableList> tablet;

    @FXML
    private TableColumn<?, String> idt;

    @FXML
    private TableColumn<?, ?> namet;

    @FXML
    private TableColumn<?, ?> salaryt;

    @FXML
    private Button insert;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField salary;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private JFXTextArea result;
    public ObservableList<ObservableList> data;


    @FXML
    void insert(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:tuto.sqlite");
            PreparedStatement pre = con.prepareStatement("insert into emp1(emp_name) values(?)");
            pre.setString(1, "Somesh");
            int rs = pre.executeUpdate();
            System.out.println(rs);

            PreparedStatement ps = con.prepareStatement("select * from emp1");
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                System.out.println(rs1.getInt(1) + rs1.getString(2));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void update(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:tuto.sqlite");
        PreparedStatement pre = con.prepareStatement("Update emp1 set emp_name='Devil' where emp_id=1");

        int rs;
        rs = pre.executeUpdate();
        System.out.println(rs);

    }

    public void delete1(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:tuto.sqlite");
        PreparedStatement pre = con.prepareStatement("delete from emp1 where emp_id='"+id.getText()+"'");
        int rs1;
        rs1 = pre.executeUpdate();
        System.out.println(rs1);

        /////////////////////////////

       //TABLE VIEW AND DATA




        //CONNECTION DATABASE

        Connection c;
        data = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tuto.sqlite");

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from emp1";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                //rs.getMetaData().getColumnName(i + 1)
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tablet.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tablet.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //TABLE VIEW AND DATA
        ObservableList<ObservableList> data;



        //CONNECTION DATABASE

        Connection c;
        data = FXCollections.observableArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tuto.sqlite");

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from emp1";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);

            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                //rs.getMetaData().getColumnName(i + 1)
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tablet.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tablet.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    }


