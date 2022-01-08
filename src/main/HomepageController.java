package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import radSaBazom.dbMetode;
import tabele.PristupniPodaci;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomepageController{
    @FXML
    private AnchorPane desniPane;

    @FXML
    private Button dodajPredBtn;

    @FXML
    private Button dodajProfBtn;

    @FXML
    private Button dodajSkBtn;

    @FXML
    private Button dodajUcBtn;

    @FXML
    private Group genderGrp;

    @FXML
    private Group genderGrp1;

    @FXML
    private ToggleGroup group;

    @FXML
    private ToggleGroup group1;

    @FXML
    private AnchorPane homepagePane;

    @FXML
    private AnchorPane lijeviPane;

    @FXML
    private RadioButton m;

    @FXML
    private RadioButton m1;

    @FXML
    private Button mojaOcjena;

    @FXML
    private Button mojeSkole;

    @FXML
    private Button mojiPredmeti;

    @FXML
    private Label naslovObrasca;

    @FXML
    private TextField novaSkDrzava;

    @FXML
    private TextField novaSkGrad;

    @FXML
    private TextField novaSkMjesto;

    @FXML
    private TextField novaSkNaziv;

    @FXML
    private Button novaSkola;

    @FXML
    private TextField noviPredNaziv;

    @FXML
    private TextField noviPredRaz;

    @FXML
    private Button noviPredmet;

    @FXML
    private TextField noviProfIme;

    @FXML
    private TextField noviProfMail;

    @FXML
    private TextField noviProfPrezime;

    @FXML
    private TextField noviProfUsrnm;

    @FXML
    private Button noviProfesor;

    @FXML
    private TextField noviUcIme;

    @FXML
    private TextField noviUcMail;

    @FXML
    private TextField noviUcPrezime;

    @FXML
    private TextField noviUcUsrnm;

    @FXML
    private Button noviUcenik;

    @FXML
    private Pane obrazac;

    @FXML
    private Button pocetna;

    @FXML
    private Pane predObrazac;

    @FXML
    private Pane profObrazac;

    @FXML
    private ImageView profilePctr;

    @FXML
    private Label profileUsrnm;

    @FXML
    private Pane skObrazac;

    @FXML
    private Button sveSkole;

    @FXML
    private Button sviPredmeti;

    @FXML
    private Button sviProfesori;

    @FXML
    private Button sviUcenici;

    @FXML
    private Label test;

    @FXML
    private Label test2;

    @FXML
    private Pane ucObrazac;

    @FXML
    private RadioButton z;

    @FXML
    private RadioButton z1;

    @FXML
    private Button logoutBtn;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void logout(MouseEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("resources/login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*@FXML
    void setProfInfo (MouseEvent event){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        User u = (User) stage.getUserData();
    }*/
    public void setInfo(User u) {
        //User u = (User) primaryStage.getUserData();
        profileUsrnm.setText(u.getKorisnickoIme());
        if(u.getPol()==1){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-m.png")));
            profilePctr.setImage(image);
        }
        else {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-f.png")));
            profilePctr.setImage(image);
        }
    }

    @FXML
    void izadjiIkona(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setOpacity(0.8);
    }

    @FXML
    void predjiIkona(MouseEvent event) {
        Button btn = (Button) event.getSource();
        btn.setOpacity(1);
    }

    @FXML
    void prikaziMojePredmete(MouseEvent event) {

    }

    @FXML
    void prikaziMojeSkole(MouseEvent event) {

    }

    @FXML
    void prikaziPocetnu(MouseEvent event) {
        Pane[] obrasci = {profObrazac, predObrazac, ucObrazac, skObrazac};
        Button btn = (Button) event.getSource();
        Pane pane = (Pane) btn.getParent();
        for (Pane ch : obrasci)
            if(ch.isVisible())
                ch.setVisible(false);
        pane.setVisible(false);
        desniPane.setVisible(true);
    }

    @FXML
    void prikaziSveProfesore(MouseEvent event) {
            test.setText("prikazi sve profesore");
    }

    @FXML
    void dodajProfesora(MouseEvent event) {
        //test.setText("dodaj profesora");
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Dodaj profesora");
        profObrazac.setVisible(true);
    }

    @FXML
    void dodajSkolu(MouseEvent event) {
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Kreiraj skolu");
        skObrazac.setVisible(true);
    }

    @FXML
    void dodajUcenika(MouseEvent event) {
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Dodaj ucenika");
        ucObrazac.setVisible(true);
    }

    @FXML
    void dodajPredmet(MouseEvent event) {
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Kreiraj predmet");
        predObrazac.setVisible(true);
    }

    @FXML
    void podnesiProf(MouseEvent event) throws Exception { //samo za prof
        boolean tacnostObrasca = true;
        String korisnickoIme = noviProfUsrnm.getText();
        String ime = noviProfIme.getText();
        String prezime = noviProfPrezime.getText();
        int pol = -1;
        String email = noviProfMail.getText();
        String sifra = korisnickoIme+"123";

        if(z.isSelected())
            pol=0;
        else if(m.isSelected())
            pol=1;
        else
            tacnostObrasca=false;

        if(korisnickoIme.equals("") || ime.equals("") || prezime.equals("") || email.equals(""))
            tacnostObrasca = false;

        if(tacnostObrasca){
            int id = dbMetode.dodajPristupnePodatke(email,korisnickoIme,sifra);
            dbMetode.dodajProfesora(ime, prezime, pol, id);
            //Button btn = (Button) event.getSource();
            Pane pane = (Pane) dodajProfBtn.getParent();
            noviProfIme.clear();
            noviProfPrezime.clear();
            noviProfMail.clear();
            noviProfUsrnm.clear();
            if(pol==1)
                m.setSelected(false);
            else
                z.setSelected(false);
            pane.setVisible(false);
            naslovObrasca.setText("Uspjesno ste kreirali novog profesora");
        }
        else {
            naslovObrasca.setText("Podaci nisu dobri");
        }
    }

    @FXML
    void podnesiUc(MouseEvent event) throws Exception {
        boolean tacnostObrasca = true;
        String korisnickoIme = noviUcUsrnm.getText();
        String ime = noviUcIme.getText();
        String prezime = noviUcPrezime.getText();
        int pol = -1;
        String email = noviUcMail.getText();
        String sifra = korisnickoIme+"123";

        if(korisnickoIme.equals("") || ime.equals("") || prezime.equals(""))
            tacnostObrasca = false;
        if(z1.isSelected())
            pol=0;
        else if(m1.isSelected())
            pol=1;
        else
            tacnostObrasca=false;

        if(tacnostObrasca){
            int id = dbMetode.dodajPristupnePodatke(email,korisnickoIme,sifra);
            dbMetode.dodajUcenika(ime, prezime, pol, id);
            //Button btn = (Button) event.getSource();
            Pane pane = (Pane) dodajUcBtn.getParent();
            noviUcIme.clear();
            noviUcPrezime.clear();
            noviUcMail.clear();
            noviUcUsrnm.clear();
            if(pol==1)
                m1.setSelected(false);
            else
                z1.setSelected(false);
            pane.setVisible(false);
            naslovObrasca.setText("Uspjesno ste kreirali novog ucenika");
        }
        else {
            naslovObrasca.setText("Podaci nisu dobri");
        }

    }

    @FXML
    void podnesiPred(MouseEvent event) {
        boolean tacnostObrasca = true;
        String naziv = null;
        int razred = -1;
        if(!noviPredNaziv.getText().equals("") && !noviPredRaz.getText().equals("")){
            naziv = noviPredNaziv.getText();
            razred = Integer.parseInt(noviPredRaz.getText());
        }
        else
            tacnostObrasca=false;

        if(tacnostObrasca){
            dbMetode.dodajPredmet(naziv, razred);
            Pane pane = (Pane) dodajPredBtn.getParent();
            noviPredNaziv.clear();
            noviPredRaz.clear();
            pane.setVisible(false);
            naslovObrasca.setText("Uspjesno ste kreirali novi predmet");
        }
        else
            naslovObrasca.setText("Podaci nisu dobri");
    }

    @FXML
    void podnesiSk(MouseEvent event){
        boolean tacnostObrasca = true;
        String naziv = novaSkNaziv.getText();
        String grad = novaSkGrad.getText();
        String mjesto = novaSkMjesto.getText();
        String drzava = novaSkDrzava.getText();
        if(naziv.equals("") || grad.equals("") || mjesto.equals("") || drzava.equals("")){
            tacnostObrasca = false;
        }

        if(tacnostObrasca){
            dbMetode.dodajSkolu(naziv,grad,mjesto,drzava);
            Pane pane = (Pane) dodajSkBtn.getParent();
            novaSkMjesto.clear();
            novaSkNaziv.clear();
            novaSkDrzava.clear();
            novaSkGrad.clear();
            pane.setVisible(false);
            naslovObrasca.setText("Uspjesno ste kreirali novu skolu");
        }
        else
            naslovObrasca.setText("Podaci nisu dobri");
    }



}
