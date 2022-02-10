package main;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import radSaBazom.dbMetode;
import tabele.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class HomepageUcController extends Controller{

    @FXML
    private Button anketa;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button mojeOcjene;

    @FXML
    private Button mojiPredmeti;

    @FXML
    private Pane profOcjObrazac;

    @FXML
    private Button dodajPredBtn;

    @FXML
    private Label pitanje1;

    @FXML
    private Label pitanje2;

    @FXML
    private Label pitanje3;

    @FXML
    private Label pitanje4;

    @FXML
    private ChoiceBox izbor1;
    @FXML
    private ChoiceBox izbor2;
    @FXML
    private ChoiceBox izbor3;
    @FXML
    private ChoiceBox izbor4;

    @FXML
    private MenuButton izborPredmeta;

    @FXML
    void dodajOcjenuProf(MouseEvent event) {
        izborPredmeta.getItems().clear();
        izborPredmeta.setText("-");
        ArrayList<PredmetUskoli> temp = vratiListuMojihPredmeta(idUlogovanog); //predmeti iz kojih nisam dala ocjenu
        for(PredmetUskoli p : temp)
            System.out.println(p.getId());
        ArrayList<Label> labele = new ArrayList<Label>(){
            {
                add(pitanje1);
                add(pitanje2);
                add(pitanje3);
                add(pitanje4);
            }
        };
        ArrayList<ChoiceBox> izbori = new ArrayList<>(){
            {
                add(izbor1);
                add(izbor2);
                add(izbor3);
                add(izbor4);
            }
        };
        obrazac.setVisible(true);
        izborPredmeta.setVisible(true);
        izborPredmeta.setDisable(false);
        profOcjObrazac.setVisible(true);
        profOcjObrazac.setDisable(true);
        desniPane.setVisible(false);
        pocetna.setVisible(true);

        for(int i=0; i<4; i++) {
            for (int j = 1; j <= 5; j++)
                (izbori.get(i)).getItems().add(j);
            labele.get(i).setText(Pitanje.getSvaPitanja().get(i+1).getPitanje());
        }

        PredmetUskoli predmet=null;
        //System.out.println(OcjenaPredmeta.getSveOcjenePredmeta().toString());
        for (OcjenaPredmeta o : OcjenaPredmeta.getSveOcjenePredmeta().values()) {
            System.out.println(o.getPredmet().getId());
            if (temp.contains(o.getPredmet()) && o.getUcenik().getId() == idUlogovanog) {
                //vec je ucenik ocijenio iz ovog predmeta
                System.out.println("test");
                temp.remove(o.getPredmet());
            }
        }

        if(!temp.isEmpty()){
            for (PredmetUskoli p : temp)
                izborPredmeta.getItems().add(new MenuItem(p.getPredmet().getNaziv() + " " + p.getPredmet().getRazred()));
            for (MenuItem i : izborPredmeta.getItems())
                i.setOnAction(eventSk -> {
                    profOcjObrazac.setDisable(false);
                    izborPredmeta.setText(i.getText());
                });
        }
    }

    @FXML
    void podnesiOcjenuProf(MouseEvent event){
        ArrayList<ChoiceBox> izbori = new ArrayList<>(){
            {
                add(izbor1);
                add(izbor2);
                add(izbor3);
                add(izbor4);
            }
        };
        if(izbor1.getValue()!=null && izbor2.getValue()!=null && izbor3.getValue()!=null && izbor4.getValue()!=null){
            for(int i=1; i<=4; i++) {
                dbMetode.dodajOcjenuPredmeta(idUlogovanog,
                        predmetUSkoliId(izborPredmeta.getText(), vratiListuMojihPredmeta(idUlogovanog).get(0).getSkola().toString())
                        , i
                        , Integer.parseInt(izbori.get(i-1).getValue().toString()));
                profOcjObrazac.setDisable(true);
                izborPredmeta.setDisable(true);
            }
        }
    }
    @FXML
    void prikaziPocetnu(MouseEvent event) {
        profOcjObrazac.setVisible(false);
        obrazac.setVisible(false);
        desniPane.setVisible(true);
        pocetna.setVisible(false);
    }

    @FXML
    void prikaziSveOcjene(MouseEvent event) {
        Button btn = (Button) event.getSource();
        for (Node ch : prikaziSvePane.getChildren())
            ch.setVisible(false);

        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);

        int Yodstojanje = 0;
        if(vratiListuMojihPredmeta(idUlogovanog)!=null){
            for(Ocjena o : prikaziSveOcjeneUcenika(Ucenik.getSviUcenici().get(idUlogovanog), vratiListuMojihPredmeta(idUlogovanog).get(0).getSkola())){
                System.out.println(o.getId());
                ucitajListu(o.getId(), Yodstojanje, 4);
                Yodstojanje += 215;
            }
        }
    }
    @FXML
    void prikaziSveIzostanke(MouseEvent event){
        Button btn = (Button) event.getSource();
        for (Node ch : prikaziSvePane.getChildren())
            ch.setVisible(false);

        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);

        int Yodstojanje = 0;
        if(!vratiListuMojihIzostanaka().isEmpty()){
            for(Izostanci i : vratiListuMojihIzostanaka()){
                //System.out.println(vratiListuMojihIzostanaka().size());
                ucitajListu(i.getId(), Yodstojanje, 5);
                Yodstojanje += 215;
            }
        }

    }
    private ArrayList<Izostanci> vratiListuMojihIzostanaka(){
        ArrayList<Izostanci> temp = new ArrayList<>();
        for(Izostanci i : Izostanci.getSviIzostanci().values())
            if(i.getUcenik().getId()==idUlogovanog)
                temp.add(i);

        temp.sort((o1,o2) -> o2.getDatum().compareTo(o1.getDatum()));
        return temp;
    }
    private ArrayList<Ocjena> prikaziSveOcjeneUcenika(Ucenik ucenik, Skola skola){
        ArrayList<Ocjena> temp = new ArrayList<>();
        for(Ocjena o : Ocjena.getSveOcjene().values())
            if(o.getUcenik()==ucenik
                    && o.getPredmet().getSkola()==skola){
                temp.add(o);
            }
        temp.sort((o1,o2) -> o2.getDatum().compareTo(o1.getDatum())); //sortira sve ocjene po datumima pocevsi od najnovije
        return temp;
    }



}
