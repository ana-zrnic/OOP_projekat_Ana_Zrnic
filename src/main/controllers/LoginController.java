package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tabele.PristupniPodaci;

public class LoginController {

    @FXML
    private Circle logo;

    @FXML
    private TextField emailInpt;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passInpt;

    @FXML
    private CheckBox showPassCB;

    @FXML
    void login(MouseEvent event) {
        //Stage mainWindow = (Stage) emailInpt.getScene().getWindow();
        String email = emailInpt.getText();
        String pass = passInpt.getText();
        System.out.println(email+" "+pass);

        for(PristupniPodaci p : PristupniPodaci.getSviPodaci())
            if(p.getEmail().equals(email) && p.getSifra().equals(pass))
                logo.setFill(Paint.valueOf("blue"));
        //mainWindow.setTitle(title);
    }

    @FXML
    void showPass(MouseEvent event) {

    }

}
