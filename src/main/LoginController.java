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
import tabele.Profesor;

import java.io.IOException;

public class LoginController {

    @FXML
    private Circle logo;

    @FXML
    private Label loginErr;

    @FXML
    private AnchorPane loginPage;

    @FXML
    private TextField usernameInpt;

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
        String username = usernameInpt.getText();
        String pass = dbMetode.MD5(passInpt.getText()); //poredi sifre hashovane u md5 hashu
        System.out.println(usernameInpt.getText()+" "+pass);

        User u = new User(username);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        for(PristupniPodaci p : PristupniPodaci.getSviPodaci().values())
            if(p.getKorisnickoIme().equals(username) && p.getSifra().equals(pass)){
                if(tipNaloga(username)){ //za prof

                    u.setPol(profPol(p.getId()));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/homepage-view.fxml"));
                    root =  loader.load();
                    HomepageController controller = loader.getController();
                    controller.setInfo(u);
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else //ovdje ide za ucenika
                    ;
            }
            else if(username.equals("") || pass.equals("")){
                    loginErr.setVisible(true);
                    loginErr.setText("Prazan e-mail ili sifra polje");
            }
            else{
                usernameInpt.setText("");
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

    private int profPol(int kljuc){
        for(Profesor p : Profesor.getSviProfesori().values())
            if(kljuc==p.getPristupniPodaci().getId())
                return p.getPol();
        return -1;
    }
    private boolean tipNaloga(String usrnm){
        for (Profesor p : Profesor.getSviProfesori().values())
            if(p.getPristupniPodaci().getKorisnickoIme().equals(usrnm))
                return true;
        return false;

    }
}
