package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    public Connection conn(){
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:bigdata.sqlite");

        }catch (ClassNotFoundException | SQLException ev){
            return null;
        }
    }
}
