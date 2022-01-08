package main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tabele.PristupniPodaci;
import radSaBazom.dbMetode;

import java.io.IOException;

public class LoginController {

    @FXML
    private Circle logo;

    @FXML
    private Label loginErr;

    @FXML
    private AnchorPane loginPage;

    @FXML
    private TextField emailInpt;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passInpt;

    @FXML
    private Button showPassBtn;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void login(MouseEvent event) throws Exception {
        //login sa usernamom? ako umjesto email stavimo login onda lako mozemo porediti sifre bez petlje
        String email = emailInpt.getText();
        String pass = dbMetode.MD5(passInpt.getText()); //poredi sifre hashovane u md5 hashu

        System.out.println(email+" "+pass);

        for(PristupniPodaci p : PristupniPodaci.getSviPodaci().values())
            if(p.getEmail().equals(email) && p.getSifra().equals(pass)){
                root =  FXMLLoader.load(getClass().getResource("resources/homepage-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(email.equals("") || pass.equals("")){
                    loginErr.setVisible(true);
                    loginErr.setText("Prazan e-mail ili sifra polje");
            }
            else{
                emailInpt.setText("");
                passInpt.setText("");
                loginErr.setVisible(true);
                loginErr.setText("Ne postoji nalog sa ovim podacima");
            }
    }

    @FXML
    void showPass(MouseEvent event) {
        String pass = passInpt.getText();
        passInpt.clear();
        passInpt.setPromptText(pass);
    }

    @FXML
    void hidePass(MouseEvent event) {
        passInpt.setText(passInpt.getPromptText());
        passInpt.setPromptText("unesite sifru");
    }

}
