package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tabele.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Controller {

    @FXML
    protected AnchorPane homepagePane;

    @FXML
    protected AnchorPane lijeviPane;

    @FXML
    protected AnchorPane desniPane;

    @FXML
    protected ImageView profilePctr;

    @FXML
    protected Label profileUsrnm;

    @FXML
    protected ScrollPane prikaziSve;

    @FXML
    protected AnchorPane prikaziSvePane;

    @FXML
    protected Button pocetna;

    @FXML
    protected Button pocetna1;

    protected Stage stage;
    protected Scene scene;
    protected Parent root;
    protected int idUlogovanog;
    protected boolean nalogTip;
    //protected Skola skolaUc;

    public void setInfo(User u, boolean tipNaloga) {
        profileUsrnm.setText(u.getKorisnickoIme());
        nalogTip = tipNaloga;
        idUlogovanog = pronadjiIdUlogovanog(u.getKorisnickoIme());

        Image image;
        if(u.getPol()==1){
            if(!tipNaloga)
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/student-m.png")));
            else
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-m.png")));
            profilePctr.setImage(image);
        }
        else {
            if(!tipNaloga)
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/student-f.png")));
            else
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-f.png")));
            profilePctr.setImage(image);
        }
    }
    @FXML
    void prikaziMojePredmete(MouseEvent event) {
        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);
        int Yodstojanje = 0;
        if(nalogTip){
            for(PredmetUskoli p : vratiListuMojihPredmeta(idUlogovanog, true)){
                ucitajListu(p.getId(), Yodstojanje, 2);
                Yodstojanje+=215;
            }
        }
        else {
            for (PredmetUskoli p : vratiListuMojihPredmeta(idUlogovanog)) {
                ucitajListu(p.getId(), Yodstojanje, 2);
                Yodstojanje += 215;
            }
        }

    }
    protected ArrayList<PredmetUskoli> vratiListuMojihPredmeta(int id, boolean flag){
        ArrayList<PredmetUskoli> temp = new ArrayList<>();
        for(PredmetUskoli p : PredmetUskoli.getSviPredmetiSkole().values())
            if(p.getProfesor().getId()==id && flag)
                temp.add(p);
        return temp;
    }
    protected ArrayList<PredmetUskoli> vratiListuMojihPredmeta(int id){
        ArrayList<PredmetUskoli> temp = new ArrayList<>();
        //System.out.println(Ucenik.getSviUcenici().get(id).getIme());
        for(Ocjena o : Ocjena.getSveOcjene().values()) {
            if (!temp.contains(o.getPredmet()) && o.getUcenik() == Ucenik.getSviUcenici().get(id)) {
                temp.add(o.getPredmet());
            }
        }
        for(Izostanci i : Izostanci.getSviIzostanci().values()) {
            if (!temp.contains(i.getPredmet()) && i.getUcenik() == Ucenik.getSviUcenici().get(id)) {
                temp.add(i.getPredmet());
            }
        }
        return temp;
    }
    protected void ucitajListu (int id, int Yodstojanje, int tipListe){
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

        switch (tipListe) {
            case 0 -> {
                if (Profesor.getSviProfesori().get(id).getPol() == 1)
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-m.png")));
                else
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/teacher-f.png")));
                label.setText(Profesor.getSviProfesori().get(id).getIme() + " " + Profesor.getSviProfesori().get(id).getPrezime());
            }
            case 1 -> {
                if (Ucenik.getSviUcenici().get(id).getPol() == 1)
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/student-m.png")));
                else
                    image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/student-f.png")));
                label.setText(Ucenik.getSviUcenici().get(id).getIme() + " " + Ucenik.getSviUcenici().get(id).getPrezime());
            }
            case 2 -> {
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/add-new-sub.png")));
                label.setText(PredmetUskoli.getSviPredmetiSkole().get(id).getPredmet().getNaziv() + " " + PredmetUskoli.getSviPredmetiSkole().get(id).getPredmet().getRazred());
                info.setText("Skola: " + PredmetUskoli.getSviPredmetiSkole().get(id).getSkola().getNaziv() + " " + PredmetUskoli.getSviPredmetiSkole().get(id).getSkola().getMjesto()+"\n");
                info.setText(info.getText()+"Ocjene predmeta: ");
                for(Ocjena o : prikaziSveOcjeneUcenika(Ucenik.getSviUcenici().get(idUlogovanog), PredmetUskoli.getSviPredmetiSkole().get(id).getSkola(), PredmetUskoli.getSviPredmetiSkole().get(id).getPredmet()))
                    info.setText(info.getText()+o.getOcjena()+" ");
            }
            case 3 -> {
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/add-new-sch.png")));
                label.setText(Skola.getSveSkole().get(id).getNaziv() + " " + Skola.getSveSkole().get(id).getMjesto());
            }
            case 4 -> {
                image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/img/prof-grade.png")));
                label.setText((Ocjena.getSveOcjene().get(id)).getPredmet().getPredmet().getNaziv()+" "+(Ocjena.getSveOcjene().get(id)).getPredmet().getPredmet().getRazred());
                info.setText("Ocjena: "+Ocjena.getSveOcjene().get(id).getOcjena());
            }
            default -> {
                System.out.println("nije dobar tip liste");
                image = null;
            }
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
    void logout(MouseEvent event) throws IOException {
        root =  FXMLLoader.load(getClass().getResource("resources/login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    protected int pronadjiIdUlogovanog(String korisnickoIme){
        if(nalogTip) {
            for (Profesor p : Profesor.getSviProfesori().values())
                if (p.getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme))
                    return p.getId();
        }
        else
            for(Ucenik u : Ucenik.getSviUcenici().values())
                if(u.getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme))
                    return u.getId();
        return -1;
    }
    protected int skolaId(String skola){
        for(Skola s : Skola.getSveSkole().values())
            if((s.getNaziv()+" "+s.getMjesto()).equals(skola))
                return s.getId();
        return -1;
    }
    protected int predmetId(String predmet){
        for(Predmet p : Predmet.getSviPredmeti().values())
            if((p.getNaziv()+" "+p.getRazred()).equals(predmet))
                return p.getId();
        return -1;
    }
    protected int ucenikId(String ucenik){
        for(Ucenik u : Ucenik.getSviUcenici().values())
            if((u.getIme()+" "+u.getPrezime()).equals(ucenik))
                return u.getId();
        return -1;
    }
    protected int predmetUSkoliId(String predmet, String skola, int id){
        for(PredmetUskoli p : PredmetUskoli.getSviPredmetiSkole().values())
            if(p.getPredmet().getId()==predmetId(predmet) && p.getSkola().getId()==skolaId(skola) && p.getProfesor().getId()==idUlogovanog)
                return p.getId();
        return -1;
    }
    private ArrayList<Ocjena> prikaziSveOcjeneUcenika(Ucenik ucenik, Skola skola, Predmet predmet){ //ocjene jednog predmeta
        ArrayList<Ocjena> temp = new ArrayList<>();
        for(Ocjena o : Ocjena.getSveOcjene().values())
            if(o.getUcenik()==ucenik
                    && o.getPredmet().getSkola()==skola
                    && o.getPredmet().getPredmet()==predmet){
                temp.add(o);
            }
        temp.sort((o1,o2) -> o2.getDatum().compareTo(o1.getDatum())); //sortira ocjene po datumima pocevsi od najnovije
        return temp;
    }
}
