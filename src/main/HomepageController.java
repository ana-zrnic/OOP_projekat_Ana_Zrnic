package main;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import radSaBazom.dbMetode;
import tabele.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class HomepageController extends Controller{


    @FXML
    private Button dodajPredBtn;

    @FXML
    private Button dodajProfBtn;

    @FXML
    private Button dodajSkBtn;

    @FXML
    private Button dodajUcBtn;

    @FXML
    private ToggleGroup group;

    @FXML
    private ToggleGroup group1;

    @FXML
    private RadioButton m;

    @FXML
    private RadioButton m1;

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
    private TextField noviPredNaziv;

    @FXML
    private TextField noviPredRaz;

    @FXML
    private TextField noviProfIme;

    @FXML
    private TextField noviProfMail;

    @FXML
    private TextField noviProfPrezime;

    @FXML
    private TextField noviProfUsrnm;

    @FXML
    private TextField noviUcIme;

    @FXML
    private TextField noviUcMail;

    @FXML
    private TextField noviUcPrezime;

    @FXML
    private TextField noviUcUsrnm;

    @FXML
    private Pane predObrazac;

    @FXML
    private Pane profObrazac;

    @FXML
    private Pane skObrazac;

    @FXML
    private Pane ucObrazac;

    @FXML
    private RadioButton z;

    @FXML
    private RadioButton z1;

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
    private Label ocjSveOcjeneUcenika;

    @FXML
    private Pane izObrazac;
    @FXML
    private Button dodajIzBtn;
    @FXML
    private MenuButton izSkola;
    @FXML
    private MenuButton izPredmet;
    @FXML
    private MenuButton izUcenik;
    @FXML
    private DatePicker izDatum;

    @FXML
    void prikaziMojeSkole(MouseEvent event) {
        Button btn = (Button) event.getSource();
        for (Node ch : prikaziSvePane.getChildren())
            ch.setVisible(false);

        ArrayList<Skola> temp = new ArrayList<>();
        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);
        int Yodstojanje = 0;
        for(PredmetUskoli p : vratiListuMojihPredmeta(idUlogovanog,true)){
            if(!temp.contains(p.getSkola())) {
                ucitajListu(p.getSkola().getId(), Yodstojanje, 3);
                Yodstojanje += 215;
                temp.add(p.getSkola());
            }
        }
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
        Pane[] obrasci = {profObrazac, predObrazac, ucObrazac, skObrazac,zaposliSe,ocjObrazac,izObrazac};
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
    void prikaziMojeOcjene(MouseEvent event){
        desniPane.setVisible(false);
        prikaziSve.setVisible(true);
        pocetna1.setVisible(true);
        int Yodstojanje = 0;
        if(vratiListuMojihPredmeta(idUlogovanog,true)!=null){
            for(OcjenaPredmeta o : OcjenaPredmeta.getSveOcjenePredmeta().values()){
                if(o.getPredmet().getProfesor().getId()==idUlogovanog){
                    //System.out.println(o.getId());
                    ucitajListu(o.getId(), Yodstojanje, 4);
                    Yodstojanje += 215;
                }
            }
        }
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
    void dodajOcjenu(MouseEvent event){
        reset(ocjSkola,ocjPredmet,ocjUcenik,ocjDatum);
        naslovObrasca.setText("Unesite ocjenu");
        ocjObrazac.setVisible(true);
        ocjSveOcjeneUcenika.setText("Sve ocjene ucenika: ");
        ocjOcjena.getItems().clear();
        ocjOcjena.setDisable(true);

        for(int i=1; i<=5; i++)
            ocjOcjena.getItems().add(i+"");
        ucitaj(ocjSkola,ocjPredmet,ocjUcenik,ocjDatum,true); //true oznacava da smo u obrascu ocjene

    }
    @FXML
    void dodajIzostanak(MouseEvent event){
        reset(izSkola,izPredmet,izUcenik,izDatum);
        naslovObrasca.setText("Unesite izostanak");
        izObrazac.setVisible(true);
        ucitaj(izSkola,izPredmet,izUcenik,izDatum,false); //false slucaj
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
    private void reset(MenuButton skola, MenuButton predmet, MenuButton ucenik, DatePicker datum){
        desniPane.setVisible(false);
        obrazac.setVisible(true);

        skola.getItems().clear();
        skola.setText("-");
        predmet.setText("-");
        predmet.setDisable(true);
        ucenik.setText("-");
        ucenik.setDisable(true);
        datum.setValue(null);
        datum.setDisable(true);
        pocetna.setVisible(true);
    }

    void ucitaj(MenuButton skola, MenuButton predmet, MenuButton ucenik, DatePicker datum, boolean flag){
        ArrayList<Skola> temp = new ArrayList<>();
        for(PredmetUskoli p : vratiListuMojihPredmeta(idUlogovanog,true)){
            if(!temp.contains(p.getSkola())){
                skola.getItems().add(new MenuItem(p.getSkola().toString()));
                temp.add(p.getSkola());
            }
        }
        for(MenuItem i : skola.getItems())
            i.setOnAction(eventSk -> {                              //
                skola.setText(i.getText());
                predmet.setText("-");
                predmet.getItems().clear();
                predmet.setDisable(false);
                ucenik.setDisable(true);
                ucenik.setText("-");
                datum.setDisable(true);
                if(flag){
                    ocjOcjena.setDisable(true);
                    ocjSveOcjeneUcenika.setText("Sve ocjene ucenika: ");
                }

                for(Predmet p : predmetiSkole(Skola.getSveSkole().get(skolaId(skola.getText()))))
                    predmet.getItems().add(new MenuItem(p.toString()));
                for(MenuItem j : predmet.getItems())
                    j.setOnAction(eventPr -> {
                        predmet.setText(j.getText());
                        ucenik.setText("-");
                        ucenik.getItems().clear();
                        ucenik.setDisable(false);
                        datum.setDisable(true);
                        if(flag){
                            ocjOcjena.setDisable(true);
                            ocjSveOcjeneUcenika.setText("Sve ocjene ucenika: ");
                        }

                        for(Ucenik u : uceniciPredmeta(
                                Predmet.getSviPredmeti().get(predmetId(predmet.getText())),   //predmet
                                uceniciSkole(Skola.getSveSkole().get(skolaId(skola.getText()))))  //skola
                        ){
                            ucenik.getItems().add(new MenuItem(u.toString()));
                        }
                        for(MenuItem k : ucenik.getItems())      //
                            k.setOnAction(eventUc -> {
                                ucenik.setText(k.getText());
                                datum.setDisable(false);
                                if(flag) {
                                    ocjOcjena.setDisable(false);
                                    ocjSveOcjeneUcenika.setText("Sve ocjene ucenika: ");
                                }
                                prikaziOcjeneUcenika(Ucenik.getSviUcenici().get(ucenikId(ucenik.getText())),
                                        Skola.getSveSkole().get(skolaId(skola.getText())),
                                        Predmet.getSviPredmeti().get(predmetId(predmet.getText()))
                                );
                            });
                    });
            });
    }

    private ArrayList<LocalDate> prikaziOcjeneUcenika(Ucenik ucenik, Skola skola, Predmet predmet){
        ocjSveOcjeneUcenika.setText("Sve ocjene ucenika: ");
        ArrayList<LocalDate> temp = new ArrayList<>(); //cuva datume ocjena iz tog predmeta
        for(Ocjena o : Ocjena.getSveOcjene().values())
            if(o.getUcenik()==ucenik
                    && o.getPredmet().getProfesor().getId()==idUlogovanog
                    && o.getPredmet().getSkola()==skola
                    && o.getPredmet().getPredmet()==predmet){
                temp.add(o.getDatum());
                ocjSveOcjeneUcenika.setText(ocjSveOcjeneUcenika.getText()+o.getOcjena()+" ");
            }
        temp.sort(null);
        return temp;
    }
    private ArrayList<LocalDate> prikaziSveOcjeneUcenika(Ucenik ucenik, Skola skola, Predmet predmet){
        ArrayList<LocalDate> temp = new ArrayList<>();
        for(Ocjena o : Ocjena.getSveOcjene().values())
            if(o.getUcenik()==ucenik
                    && o.getPredmet().getSkola()==skola
                    && o.getPredmet().getPredmet()!=predmet){
                temp.add(o.getDatum());
            }
        temp.sort(null);
        return temp;
    }
    private ArrayList<Predmet> predmetiSkole (Skola skola){
        ArrayList<Predmet> temp = new ArrayList<>();
        for(PredmetUskoli ps : PredmetUskoli.getSviPredmetiSkole().values())
            if(vratiListuMojihPredmeta(idUlogovanog,true).contains(ps)
                    && ps.getSkola()==skola
                    && ps.getProfesor().getId()==idUlogovanog)
                temp.add(ps.getPredmet());
        return temp;
    }

    private ArrayList<ArrayList<Ucenik>> uceniciSkole(Skola skola){
        ArrayList<Ucenik> temp = new ArrayList<>();
        ArrayList<Ucenik> uceniciDrugihSkola = new ArrayList<>();
        ArrayList<Ucenik> uceniciBezSkole = new ArrayList<>();
        for(Ocjena o : Ocjena.getSveOcjene().values())
            if(!temp.contains(o.getUcenik())){
                if(o.getPredmet().getSkola()==skola){
                    temp.add(o.getUcenik());
                    System.out.println("ucenik skole "+skola.toString()+", "+o.getUcenik().toString());
                }
                else{
                    uceniciDrugihSkola.add(o.getUcenik());
                }
            }
        for (Izostanci i : Izostanci.getSviIzostanci().values())
            if(!temp.contains(i.getUcenik())){
                if(i.getPredmet().getSkola()==skola){
                    temp.add(i.getUcenik());
                    System.out.println("ucenik skole "+skola.toString()+", "+i.getUcenik().toString());
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
        //svi ucenici bez ikakve skole
        temp.addAll(lista.get(1));

        for(Ucenik u : lista.get(0)){   //ucenici iste skole
            for (Ocjena o : Ocjena.getSveOcjene().values()){
                if( !temp.contains(o.getUcenik())
                        && o.getUcenik()==u
                        && o.getPredmet().getPredmet()==predmet){
                    if(o.getPredmet().getProfesor().getId()==idUlogovanog){
                        temp.add(u);    //ucenik skole koji slusa predm kod profesora
                        System.out.println("Ucenik predmeta: "+predmet.toString()+"je "+u.toString());
                    }
                    else {
                        uceniciDrugogProfesora.add(u); //ucenik skole koji vec slusa taj predmet kod drugog prof
                        System.out.println("ucenik koji vec slusa predmet "+u.toString());
                    }
                }
            }
            for (Izostanci i :Izostanci.getSviIzostanci().values()){
                if( !temp.contains(i.getUcenik())
                        && i.getUcenik()==u
                        && i.getPredmet().getPredmet()==predmet){
                    if(i.getPredmet().getProfesor().getId()==idUlogovanog){
                        temp.add(u);    //ucenik skole koji slusa predm kod profesora
                        System.out.println("Ucenik predmeta: "+predmet.toString()+"je "+u.toString());
                    }
                    else {
                        uceniciDrugogProfesora.add(u); //ucenik skole koji vec slusa taj predmet kod drugog prof
                        System.out.println("ucenik koji vec slusa predmet "+u.toString());
                    }
                }
            }
            for(Ocjena o : Ocjena.getSveOcjene().values()){
                if( !temp.contains(u) //ako je ovo tacno onda sigurno nije kod tog prof ali jeste u skoli mozda kod drugog prof
                        && !uceniciDrugogProfesora.contains(u) //ako je ovo tacno onda ne slusa predmet uopste
                        && o.getUcenik()==u
                        && o.getPredmet().getPredmet().getRazred()==predmet.getRazred()){   //ako je ovo tacno ucenik je dobar razred za ovaj predmet
                    temp.add(u);
                    System.out.println("ucenik skole a nije u predmetu i dobar razred "+u.toString());
                }
            }
            for(Izostanci i : Izostanci.getSviIzostanci().values()){
                if( !temp.contains(u) //ako je ovo tacno onda sigurno nije kod tog prof ali jeste u skoli mozda kod drugog prof
                        && !uceniciDrugogProfesora.contains(u) //ako je ovo tacno onda ne slusa predmet uopste
                        && i.getUcenik()==u
                        && i.getPredmet().getPredmet().getRazred()==predmet.getRazred()){   //ako je ovo tacno ucenik je dobar razred za ovaj predmet
                    temp.add(u);
                    System.out.println("ucenik skole a nije u predmetu i dobar razred "+u.toString());
                }
            }
        }
        return temp;
    }
    @FXML
    void podnesiIzostanak(MouseEvent event){
        boolean tacnostObrasca = true;
        boolean flag = true;
        LocalDate danas = izDatum.getValue();
        for(Node n : izObrazac.getChildren())
            if(n.isDisabled())
                tacnostObrasca = false;
        if(danas==null)
            tacnostObrasca = false;
        if(tacnostObrasca){
            for(Izostanci i : Izostanci.getSviIzostanci().values())
                if(i.getUcenik()==Ucenik.getSviUcenici().get(ucenikId(izUcenik.getText()))){
                    if(i.getDatum().isEqual(danas)){
                        if(i.getPredmet().getPredmet()==Predmet.getSviPredmeti().get(predmetId(izPredmet.getText()))
                                && idUlogovanog==i.getPredmet().getProfesor().getId()) //ucenik danas vec ima izostanak kod ulogovanog prof
                        {
                            naslovObrasca.setText("Vec ste danas dali uceniku izostanak");
                            flag=false;
                            break;
                        }
                        else                //ucenik ima danas izostanak ali kod drugog prof
                        {
                            dodajIzostanakHelp();
                            flag=false;
                            break;
                        }
                    }
                }
            if(flag) //znaci da danas nema izostanaka jos
                dodajIzostanakHelp();
        }
        else
            naslovObrasca.setText("Neko polje je prazno");
    }
    @FXML
    void podnesiOcjenu(MouseEvent event){
        boolean flag=false;
        boolean tacnostObrasca = true;
        boolean odustan = false;
        ArrayList<LocalDate> datumi = prikaziOcjeneUcenika(Ucenik.getSviUcenici().get(ucenikId(ocjUcenik.getText())),   //sve ocjene iz predmeta
                Skola.getSveSkole().get(skolaId(ocjSkola.getText())),
                Predmet.getSviPredmeti().get(predmetId(ocjPredmet.getText())));
        LocalDate najnovijiDatumPredmeta=null;
        if(!datumi.isEmpty())
            najnovijiDatumPredmeta = datumi.get(datumi.size()-1);


        ArrayList<LocalDate> datumi2 = prikaziSveOcjeneUcenika(Ucenik.getSviUcenici().get(ucenikId(ocjUcenik.getText())),   //sve ocjene koje nisu iz pr
                Skola.getSveSkole().get(skolaId(ocjSkola.getText())),
                Predmet.getSviPredmeti().get(predmetId(ocjPredmet.getText())));
        LocalDate najnovijiDatum=null;
        if(!datumi2.isEmpty()) {
            if(datumi2.size()>=2) {
                najnovijiDatum = datumi2.get(datumi2.size() - 1);
                if (najnovijiDatum.isEqual(datumi2.get(datumi2.size() - 2))) {
                    flag = true;
                }
            }
        }

        LocalDate danas = ocjDatum.getValue();
        for(Izostanci i : Izostanci.getSviIzostanci().values())
            if(i.getUcenik()==Ucenik.getSviUcenici().get(ucenikId(ocjUcenik.getText()))
                    && i.getDatum().isEqual(danas))
                odustan=true;

        for(Node n : ocjObrazac.getChildren())  //za disable
            if(n.isDisabled())
                tacnostObrasca = false;
        if(ocjOcjena.getValue()==null || ocjDatum.getValue()==null)
            tacnostObrasca = false;

       if(tacnostObrasca){  //ukoliko je sve korektno popunjeno
           if(!odustan){ //i ako nije odsutan
               if(najnovijiDatum==null && najnovijiDatumPredmeta==null){
                   dodajOcjenuHelp();
                   System.out.println("1");
               }
               else if(najnovijiDatum!=null && najnovijiDatumPredmeta==null){
                   System.out.println("2");
                   if(danas.isEqual(najnovijiDatum) ){
                       if(flag)
                           naslovObrasca.setText("Ucenik vec ima dvije ocjene danas");
                   }
                   else if(danas.isAfter(najnovijiDatum) )
                       dodajOcjenuHelp();
               }
               else if(najnovijiDatum==null && najnovijiDatumPredmeta!=null){
                   System.out.println("3");
                   if(danas.isAfter(najnovijiDatumPredmeta)){
                       if(danas.getDayOfMonth()-datumi.get(datumi.size()-1).getDayOfMonth()>=7)
                           dodajOcjenuHelp();
                       else
                           naslovObrasca.setText("Ucenika ste ocijenili u posljednjih 7 dana");
                   }
                   else
                       naslovObrasca.setText("Datum ove ocjene je stariji od posljednje");
               }
               else{
                   if(danas.isAfter(najnovijiDatumPredmeta)){
                       if(danas.getDayOfMonth()-datumi.get(datumi.size()-1).getDayOfMonth()>=7)
                           dodajOcjenuHelp();
                       else
                           naslovObrasca.setText("Ucenika ste ocijenili u posljednjih 7 dana");
                   }
                   else if(danas.isEqual(najnovijiDatum)){
                       if(!flag)
                           dodajOcjenuHelp();
                       else
                           naslovObrasca.setText("Ucenik vec ima dvije ocjene danas");
                   }
                   else
                       naslovObrasca.setText("Datum ove ocjene je stariji od posljednje");
               }
           }
           else
               naslovObrasca.setText("Ucenik je danas odsutan");
       }
       else naslovObrasca.setText("neko polje je prazno");
    }
    private void dodajOcjenuHelp(){
        dbMetode.dodajOcjenu(
                ucenikId(ocjUcenik.getText()),
                predmetUSkoliId(
                        ocjPredmet.getText(),
                        ocjSkola.getText(),
                        idUlogovanog),
                Integer.parseInt(ocjOcjena.getValue().toString()),
                LocalDate.parse(ocjDatum.getValue().toString())
        );
        naslovObrasca.setText("Uspjesno ste dodali ocjenu");
        Pane pane = (Pane) dodajOcjnBtn.getParent();
        pane.setVisible(false);
    }
    private void dodajIzostanakHelp(){
        dbMetode.dodajIzostanak(
                ucenikId(izUcenik.getText()),
                predmetUSkoliId(
                        izPredmet.getText(),
                        izSkola.getText(),
                        idUlogovanog),
                LocalDate.parse(izDatum.getValue().toString())
        );
        naslovObrasca.setText("Uspjesno ste dodali izostanak");
        Pane pane = (Pane) dodajIzBtn.getParent();
        pane.setVisible(false);
    }

    @FXML
    void podnesiPosao(MouseEvent event){
        if(izaberiSk.getValue()==null || izaberiPr.getValue()==null)
            naslovObrasca.setText("Prazno polje, odabir nije dobar");
        else{
            if(!posaoPostoji()){
                naslovObrasca.setText("Uspjesno ste se zaposlili");
                dbMetode.dodajPosao(skolaId(izaberiSk.getValue().toString()), predmetId(izaberiPr.getValue().toString()), idUlogovanog);
                Pane pane = (Pane) dodajNoviPosao.getParent();
                pane.setVisible(false);
                izaberiPr.setValue(null);
                izaberiSk.setValue(null);
            }
            else {
                naslovObrasca.setText("Greska, podaci vec postoje");
            }
        }
    }
    private boolean posaoPostoji(){
        ArrayList <PredmetUskoli> temp = vratiListuMojihPredmeta(idUlogovanog, true);
        for(PredmetUskoli p : PredmetUskoli.getSviPredmetiSkole().values())
            if(temp.contains(p))
                return true;
        return false;
    }




}
