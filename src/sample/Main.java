package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("billing.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (IOException e){
                e.printStackTrace();
        }


    }
        public static void main(String[] args) {
        launch(args);
    }
}
