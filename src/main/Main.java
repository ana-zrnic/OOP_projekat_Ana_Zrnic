package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import radSaBazom.dbConn;
import radSaBazom.dbMetode;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resources/homepage-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("E-dnevnik");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

        dbConn konekcija = new dbConn();
        dbMetode.kreirajPristupnePodatke();
        dbMetode.kreirajProfesore();
        dbMetode.kreirajUcenike();
    }

    public static void main(String[] args) {
        launch();
    }
}