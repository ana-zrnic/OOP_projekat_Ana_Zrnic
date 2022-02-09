package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import radSaBazom.dbMetode;
import tabele.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

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
    private Button pocetna1;

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
    private Pane ucObrazac;

    @FXML
    private RadioButton z;

    @FXML
    private RadioButton z1;

    @FXML
    private Button logoutBtn;

    @FXML
    private ScrollPane prikaziSve;

    @FXML
    private AnchorPane prikaziSvePane;

    @FXML
    private Button novaOcjena;

    @FXML
    private Button noviIzostanak;

    @FXML
    private Button sviIzostanci;

    @FXML
    private Pane zaposliSe;
    @FXML
    private Button dodajNoviPosao;
    @FXML
    private ChoiceBox izaberiSk;
    @FXML
    private ChoiceBox izaberiPr;

    @FXML
    private Pane ocjObrazac;
    @FXML
    private Button dodajOcjnBtn;
    @FXML
    private MenuButton ocjSkola;
    @FXML
    private MenuButton ocjPredmet;
    @FXML
    private MenuButton ocjUcenik;
    @FXML
    private DatePicker ocjDatum;
    @FXML
    private ChoiceBox ocjOcjena;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int idUlogovanog;


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
        idUlogovanog = pronadjiIdUlogovanog(u.getKorisnickoIme());
        if(u.getPol()==1){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-m.png")));
            profilePctr.setImage(image);
        }
        else {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-f.png")));
            profilePctr.setImage(image);
        }
    }
    private int pronadjiIdUlogovanog(String korisnickoIme){
        for(Profesor p : Profesor.getSviProfesori().values())
            if(p.getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme))
                return p.getId();
        return -1;
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
        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);
        int Yodstojanje = 0;
        for(Predmet p : vratiListuMojihPredmeta(idUlogovanog, true)){
            ucitajListu(p.getId(), Yodstojanje, 2);
            Yodstojanje+=215;
        }
    }
    private ArrayList<Predmet> vratiListuMojihPredmeta(int id, boolean flag){ //napraviti da bude id?
        ArrayList<Predmet> temp = new ArrayList<>();
        for(PredmetUskoli p : PredmetUskoli.getSviPredmetiSkole().values())
            if(p.getProfesor().getId()==id && flag)
                temp.add(p.getPredmet());
            else if(p.getProfesor().getId()!=id && !flag)
                temp.add(p.getPredmet());
        return temp;
    }

    @FXML
    void prikaziMojeSkole(MouseEvent event) {
        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);
        int Yodstojanje = 0;
        for(Skola s : vratiListuMojihSkola(idUlogovanog)){
            ucitajListu(s.getId(), Yodstojanje, 3);
            Yodstojanje+=215;
        }
    }
    private ArrayList<Skola> vratiListuMojihSkola(int id){ //napraviti da bude id?
        ArrayList<Skola> temp = new ArrayList<>();
        for(PredmetUskoli p : PredmetUskoli.getSviPredmetiSkole().values())
            if(p.getProfesor().getId()==id)
                if(!skolaPostoji(temp, p.getSkola()))   // da ovog nema vratio bi vise puta istu skolu u kojoj ima vise predmeta
                    temp.add(p.getSkola());
        return temp;
    }
    private boolean skolaPostoji(ArrayList<Skola> lista, Skola skola){
        if(lista!=null)
            for (Skola s : lista)
                if (s.equals(skola))
                    return true;
        return false;
    }

    @FXML
    void prikaziSveProfesore(MouseEvent event) {
        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);
        int Yodstojanje = 0;
        for(Profesor p : Profesor.getSviProfesori().values()){
           ucitajListu(p.getId(), Yodstojanje, 0);
            Yodstojanje+=215;
        }
    }
    private void ucitajListu (int id, int Yodstojanje, int tipListe){
        Pane listaPane = new Pane();
        listaPane.setId(Integer.toString(id));
        listaPane.setPrefHeight(200);
        listaPane.setPrefWidth(700);
        listaPane.setLayoutX(25);
        listaPane.setLayoutY(15+Yodstojanje);
        listaPane.setStyle("-fx-background-color: #dfe8f7;");

        Image image;
        Label label = new Label();
        Label info = new Label();

        switch(tipListe) {
            case 0:
                if(Profesor.getSviProfesori().get(id).getPol() == 1)
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-m.png")));
                else
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-f.png")));
                label.setText(Profesor.getSviProfesori().get(id).getIme() + " " + Profesor.getSviProfesori().get(id).getPrezime());

                break;
            case 1:
                if(Ucenik.getSviUcenici().get(id).getPol() == 1)
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/student-m.png")));
                else
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/student-f.png")));
                label.setText(Ucenik.getSviUcenici().get(id).getIme() + " " + Ucenik.getSviUcenici().get(id).getPrezime());
                break;
            case 2:
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/add-new-sub.png")));
                label.setText(Predmet.getSviPredmeti().get(id).getNaziv()+" "+ Predmet.getSviPredmeti().get(id).getRazred());
                //info.setText("Skola: "+skolaPredmeta());
                break;
            case 3:
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/add-new-sch.png")));
                label.setText(Skola.getSveSkole().get(id).getNaziv() + " " +Skola.getSveSkole().get(id).getMjesto());
                break;
            default:
                System.out.println("nije dobar tip liste");
                image = null;
        }

        ImageView imageView = new ImageView(image);
        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.QUALITY);
        imageView.setFitHeight(128);
        imageView.setFitWidth(138);
        imageView.setLayoutX(33);
        imageView.setLayoutY(14);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setLayoutX(25);
        label.setLayoutY(154);
        label.setPrefWidth(138);
        label.setFont(Font.font(15));

        info.setLayoutX(188);
        info.setAlignment(Pos.TOP_LEFT);
        info.setLayoutY(17);
        info.setPrefHeight(167);
        info.setPrefWidth(490);
        info.setFont(Font.font(15));


        listaPane.getChildren().add(info);
        listaPane.getChildren().add(imageView);
        listaPane.getChildren().add(label);
        prikaziSvePane.getChildren().add(listaPane);
    }

    @FXML
    void PosaoObrazac(MouseEvent event) {
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Izaberite novi posao");
        zaposliSe.setVisible(true);
        pocetna.setVisible(true);
        izaberiSk.getItems().clear();
        izaberiPr.getItems().clear();
        for(Skola s : Skola.getSveSkole().values()){
            //MenuItem item = new MenuItem(s.getNaziv()+" "+s.getMjesto());
             izaberiSk.getItems().add(s.getNaziv()+" "+s.getMjesto());
        }
        for(Predmet p : Predmet.getSviPredmeti().values()){
            //MenuItem item = new MenuItem();
            izaberiPr.getItems().add(p.getNaziv()+" "+p.getRazred());
        }
    }


    @FXML
    void prikaziSveUcenike(MouseEvent event) {
        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);
        int Yodstojanje = 0;
        for(Ucenik u : Ucenik.getSviUcenici().values()){
            ucitajListu(u.getId(), Yodstojanje, 1);
            Yodstojanje+=215;
        }
    }

    @FXML
    void prikaziPocetnu(MouseEvent event) {
        Pane[] obrasci = {profObrazac, predObrazac, ucObrazac, skObrazac,zaposliSe,ocjObrazac};
        Button btn = (Button) event.getSource();
        Pane pane = obrazac;
        for (Pane ch : obrasci)
            if(ch.isVisible())
                ch.setVisible(false);
        pane.setVisible(false);
        desniPane.setVisible(true);
        btn.setVisible(false);
    }

    @FXML
    void prikaziPocetnu1(MouseEvent event) {
        Button btn = (Button) event.getSource();
        for (Node ch : prikaziSvePane.getChildren())
            ch.setVisible(false);
        prikaziSve.setVisible(false);
        desniPane.setVisible(true);
        btn.setVisible(false);
    }


    @FXML
    void dodajProfesora(MouseEvent event) {
        //test.setText("dodaj profesora");
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Dodaj profesora");
        profObrazac.setVisible(true);
        pocetna.setVisible(true);
    }

    @FXML
    void dodajSkolu(MouseEvent event) {
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Kreiraj skolu");
        skObrazac.setVisible(true);
        pocetna.setVisible(true);
    }

    @FXML
    void dodajUcenika(MouseEvent event) {
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Dodaj ucenika");
        ucObrazac.setVisible(true);
        pocetna.setVisible(true);
    }

    @FXML
    void dodajPredmet(MouseEvent event) {
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Kreiraj predmet");
        predObrazac.setVisible(true);
        pocetna.setVisible(true);
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

    @FXML
    void dodajOcjenu(MouseEvent event){
        //iz menu item uzeti objekat skole od izabranog teksta
        //uceniciSkole(Skola.getSveSkole().get(2)); //ovdje proslijediti skolu koja je selektovana
        //iz te liste vratiti ucenike koji su u selektovanom predmetu
        desniPane.setVisible(false);
        obrazac.setVisible(true);
        naslovObrasca.setText("Unesite ocjenu");
        ocjObrazac.setVisible(true);
        pocetna.setVisible(true);
        ocjSkola.getItems().clear();
        ocjSkola.setText("-");

        //ocjPredmet.getItems().clear();
        ocjPredmet.setText("-");
        ocjPredmet.setDisable(true);
        //ocjUcenik.getItems().clear();
        ocjUcenik.setText("-");
        ocjUcenik.setDisable(true);
        ocjOcjena.getItems().clear();
        ocjOcjena.setDisable(true);
        ocjDatum.setValue(null);
        ocjDatum.setDisable(true);

        for(int i=1; i<=5; i++)
            ocjOcjena.getItems().add(i+"");

        for(Skola s : vratiListuMojihSkola(idUlogovanog))
            ocjSkola.getItems().add(new MenuItem(s.getNaziv()+", "+s.getMjesto()));
        for(MenuItem i : ocjSkola.getItems())
            i.setOnAction(eventSk -> {                              //
                ocjSkola.setText(i.getText());
                ocjPredmet.setText("-");
                ocjPredmet.getItems().clear();
                ocjPredmet.setDisable(false);
                ocjUcenik.setDisable(true);
                ocjUcenik.setText("-");
                ocjOcjena.setDisable(true);
                ocjDatum.setDisable(true);
                for(Predmet p : predmetiSkole(vratiSkolu(ocjSkola.getText())))
                    ocjPredmet.getItems().add(new MenuItem(p.getNaziv()+", "+p.getRazred()));
                for(MenuItem j : ocjPredmet.getItems())             //
                    j.setOnAction(eventPr -> {
                        ocjPredmet.setText(j.getText());
                        ocjUcenik.setText("-");
                        ocjUcenik.getItems().clear();
                        ocjUcenik.setDisable(false);
                        ocjOcjena.setDisable(true);
                        ocjDatum.setDisable(true);
                        for(Ucenik u : uceniciPredmeta(vratiPredmet(ocjPredmet.getText()), uceniciSkole(vratiSkolu(ocjSkola.getText())))){
                            ocjUcenik.getItems().add(new MenuItem(u.getIme()+" "+u.getPrezime()));
                        }
                        for(MenuItem k : ocjUcenik.getItems())      //
                            k.setOnAction(eventUc -> {
                                ocjUcenik.setText(k.getText());
                                ocjOcjena.setDisable(false);
                                ocjDatum.setDisable(false);
                            });
                    });
            });
    }
    private ArrayList<Predmet> predmetiSkole (Skola skola){
        ArrayList<Predmet> temp = new ArrayList<>();
        for(PredmetUskoli ps : PredmetUskoli.getSviPredmetiSkole().values())
            if(vratiListuMojihPredmeta(idUlogovanog,true).contains(ps.getPredmet()) && ps.getSkola()==skola && ps.getProfesor().getId()==idUlogovanog)
                temp.add(ps.getPredmet());
                //System.out.println();
        return temp;
    }
    private Skola vratiSkolu(String naziv){
        for (Skola s : Skola.getSveSkole().values())
            if (s.getNaziv().equals(naziv.split(", ")[0])
                    && s.getMjesto().equals(naziv.split(", ")[1]))
                return s;
        return null;
    }
    private Predmet vratiPredmet(String naziv){
        for (Predmet p : Predmet.getSviPredmeti().values())
            if (p.getNaziv().equals(naziv.split(", ")[0])
                    && p.getRazred()==Integer.parseInt(naziv.split(", ")[1]))
                return p;
        return null;
    }
    private ArrayList<ArrayList<Ucenik>> uceniciSkole(Skola skola){
        ArrayList<Ucenik> temp = new ArrayList<>();
        ArrayList<Ucenik> uceniciDrugihSkola = new ArrayList<>();
        ArrayList<Ucenik> uceniciBezSkole = new ArrayList<>();
        for(Ocjena o : Ocjena.getSveOcjene().values())
            if(!temp.contains(o.getUcenik())){
                if(o.getPredmet().getSkola()==skola){
                    temp.add(o.getUcenik());
                    System.out.println("ucenik skole "+skola.getNaziv()+skola.getMjesto()+", "+o.getUcenik().getIme()+" "+o.getUcenik().getPrezime());
                }
                else{
                    uceniciDrugihSkola.add(o.getUcenik());
                }
            }
        for (Izostanci i : Izostanci.getSviIzostanci().values())
            if(!temp.contains(i.getUcenik())){
                if(i.getPredmet().getSkola()==skola){
                    temp.add(i.getUcenik());
                    System.out.println("ucenik skole "+skola.getNaziv()+skola.getMjesto()+", "+i.getUcenik().getIme()+" "+i.getUcenik().getPrezime());
                }
                else{
                    uceniciDrugihSkola.add(i.getUcenik());
                }
            }
        for(Ucenik u : Ucenik.getSviUcenici().values())
            if(!temp.contains(u) && !uceniciDrugihSkola.contains(u))
                uceniciBezSkole.add(u);
        return new ArrayList<ArrayList<Ucenik>>(){
            {
                add(temp);
                add(uceniciBezSkole);
            }
        };
    }
    private ArrayList<Ucenik> uceniciPredmeta(Predmet predmet, ArrayList<ArrayList<Ucenik>> lista){
        ArrayList<Ucenik> temp = new ArrayList<>();
        ArrayList<Ucenik> uceniciDrugogProfesora = new ArrayList<>();
        for(Ucenik u : lista.get(1))    //svi ucenici bez ikakve skole
            temp.add(u);

        for(Ucenik u : lista.get(0)){   //ucenici iste skole
            for (Ocjena o : Ocjena.getSveOcjene().values()){
                if( !temp.contains(o.getUcenik())
                        && o.getUcenik()==u
                        && o.getPredmet().getPredmet()==predmet){
                    if(o.getPredmet().getProfesor().getId()==idUlogovanog){
                        temp.add(u);    //ucenik skole koji slusa predm kod profesora
                        System.out.println("Ucenik predmeta: "+predmet.getNaziv()+predmet.getRazred()+"je "+u.getIme()+" "+u.getPrezime());
                    }
                    else {
                        uceniciDrugogProfesora.add(u); //ucenik skole koji vec slusa taj predmet kod drugog prof
                        System.out.println("ucenik koji vec slusa predmet "+u.getIme()+" "+u.getPrezime());
                    }
                }
            }
            for (Izostanci i :Izostanci.getSviIzostanci().values()){
                if( !temp.contains(i.getUcenik())
                        && i.getUcenik()==u
                        && i.getPredmet().getPredmet()==predmet){
                    if(i.getPredmet().getProfesor().getId()==idUlogovanog){
                        temp.add(u);    //ucenik skole koji slusa predm kod profesora
                        System.out.println("Ucenik predmeta: "+predmet.getNaziv()+predmet.getRazred()+"je "+u.getIme()+" "+u.getPrezime());
                    }
                    else {
                        uceniciDrugogProfesora.add(u); //ucenik skole koji vec slusa taj predmet kod drugog prof
                        System.out.println("ucenik koji vec slusa predmet "+u.getIme()+" "+u.getPrezime());
                    }
                }
            }
            for(Ocjena o : Ocjena.getSveOcjene().values()){
                if( !temp.contains(u) //ako je ovo tacno onda sigurno nije kod tog prof ali jeste u skoli mozda kod drugog prof
                        && !uceniciDrugogProfesora.contains(u) //ako je ovo tacno onda ne slusa predmet uopste
                        && o.getUcenik()==u
                        && o.getPredmet().getPredmet().getRazred()==predmet.getRazred()){   //ako je ovo tacno ucenik je dobar razred za ovaj predmet
                    temp.add(u);
                    System.out.println("ucenik skole a nije u predmetu i dobar razred "+u.getIme()+" "+u.getPrezime());
                    //System.out.println(o.getPredmet().getId());
                    //System.out.println(predmet.getNaziv());
                }
            }
            for(Izostanci i : Izostanci.getSviIzostanci().values()){
                if( !temp.contains(u) //ako je ovo tacno onda sigurno nije kod tog prof ali jeste u skoli mozda kod drugog prof
                        && !uceniciDrugogProfesora.contains(u) //ako je ovo tacno onda ne slusa predmet uopste
                        && i.getUcenik()==u
                        && i.getPredmet().getPredmet().getRazred()==predmet.getRazred()){   //ako je ovo tacno ucenik je dobar razred za ovaj predmet
                    temp.add(u);
                    System.out.println("ucenik skole a nije u predmetu i dobar razred "+u.getIme()+" "+u.getPrezime());
                    //System.out.println(o.getPredmet().getId());
                    //System.out.println(predmet.getNaziv());
                }
            }
        }
        return temp;
    }
    @FXML
    void podnesiOcjenu(MouseEvent event){
        //if tona uslova ispunjeno.......
        dbMetode.dodajOcjenu(
                ucenikId(ocjUcenik.getText()),
                predmetUSkoliId(
                        ocjPredmet.getText().split(", ")[0],
                        ocjSkola.getText().split(", ")[0],
                        idUlogovanog),
                Integer.parseInt(ocjOcjena.getValue().toString()),
                LocalDate.parse(ocjDatum.getValue().toString()));
    }

    @FXML
    void dodajIzostanak(){

    }

    @FXML
    void podnesiPosao(MouseEvent event){
        if(izaberiSk.getValue()==null || izaberiPr.getValue()==null)
            naslovObrasca.setText("Prazno polje, odabir nije dobar");
        else{
            if(!posaoPostoji(izaberiSk.getValue().toString(), izaberiPr.getValue().toString())){
                naslovObrasca.setText("Uspjesno ste kreirali novu skolu");
                dbMetode.dodajPosao(skolaId(izaberiSk.getValue().toString()), predmetId(izaberiPr.getValue().toString()), idUlogovanog);
                Pane pane = (Pane) dodajNoviPosao.getParent();
                pane.setVisible(false);
                izaberiPr.setValue(null);
                izaberiSk.setValue(null);
            }
            else
                naslovObrasca.setText("Greska, podaci vec postoje");
        }

    }
    private boolean posaoPostoji(String skola, String predmet){
        for(PredmetUskoli p : PredmetUskoli.getSviPredmetiSkole().values())
            if((p.getSkola().getNaziv()+" "+p.getSkola().getMjesto()).equals(skola) &&
                    (p.getPredmet().getNaziv()+" "+p.getPredmet().getRazred()).equals(predmet)){
                return true;
            }
        return false;
    }
    private int skolaId(String skola){
        for(Skola s : Skola.getSveSkole().values())
            if((s.getNaziv()+" "+s.getMjesto()).equals(skola))
                return s.getId();
        return -1;
    }
    private int predmetId(String predmet){
        for(Predmet p : Predmet.getSviPredmeti().values())
            if((p.getNaziv()+" "+p.getRazred()).equals(predmet))
                return p.getId();
        return -1;
    }
    private int ucenikId(String ucenik){
        for(Ucenik u : Ucenik.getSviUcenici().values())
            if((u.getIme()+" "+u.getPrezime()).equals(ucenik))
                return u.getId();
        return -1;
    }
    private int predmetUSkoliId(String predmet, String skola, int id){
        for(PredmetUskoli p : PredmetUskoli.getSviPredmetiSkole().values())
            if(p.getPredmet().getId()==predmetId(predmet) && p.getSkola().getId()==skolaId(skola) && p.getProfesor().getId()==idUlogovanog)
                return p.getId();
        return -1;
    }



}
