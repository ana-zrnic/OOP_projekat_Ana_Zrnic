package main;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import radSaBazom.dbMetode;
import tabele.PristupniPodaci;

import java.util.Collection;
import java.util.Locale;

public class HomepageController {
    @FXML
    private AnchorPane desniPane;

    @FXML
    private Button dodajProfBtn;

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
    private Button novaSkola;

    @FXML
    private Button noviPredmet;

    @FXML
    private TextField noviProfIme;

    @FXML
    private TextField noviProfPrezime;

    @FXML
    private TextField noviProfUsrnm;

    @FXML
    private TextField noviProfMail;

    @FXML
    private TextField noviUcIme;

    @FXML
    private TextField noviUcPrezime;

    @FXML
    private TextField noviUcUsrnm;

    @FXML
    private TextField noviUcMail;

    @FXML
    private Button noviProfesor;

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
    private Button dodajUcBtn;


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
    void podnesiProf(MouseEvent event) { //samo za prof
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
            Button btn = (Button) event.getSource();
            Pane pane = (Pane) btn.getParent();
            pane.setVisible(false);
            naslovObrasca.setText("Uspjesno ste kreirali novog profesora");
        }
        else {
            naslovObrasca.setText("Podaci nisu dobri");
        }
    }

    @FXML
    void podnesiUc(MouseEvent event) {
        Boolean tacnostObrasca = true;
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
            Button btn = (Button) event.getSource();
            Pane pane = (Pane) btn.getParent();
            pane.setVisible(false);
            naslovObrasca.setText("Uspjesno ste kreirali novog ucenika");
        }
        else {
            naslovObrasca.setText("Podaci nisu dobri");
        }

    }


}
