package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tabele.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

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
    void dodajOcjenuProf(MouseEvent event) {

    }

    @FXML
    void prikaziPocetnu(MouseEvent event) {

    }

    @FXML
    void prikaziSveOcjene(MouseEvent event) {
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
