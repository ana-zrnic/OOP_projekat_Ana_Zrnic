package radSaBazom;

import tabele.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.*;
import java.math.*;

public class dbMetode extends dbConn{

    public static void kreirajPristupnePodatke (){
        String QUERY = "SELECT korisnicko_ime, sifra, email, id FROM pristupni_podaci";
        //String QUERY = "SELECT email3, sifra3, username, id FROM baza2";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {     //posto je dekodiranje tesko najbolje je sacuvati hashovane sifre i onda porediti hashovane sifre
                new PristupniPodaci(rs.getString("korisnicko_ime"), rs.getString("email"), rs.getString("sifra"), rs.getInt("id"));
                //new PristupniPodaci(rs.getString("username"), rs.getString("email3"), rs.getString("sifra3")+"123", rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int dodajPristupnePodatke (String email, String korisnickoIme, String sifra) throws Exception { //zajednicko za prof i ucenike
        String QUERY = "INSERT INTO pristupni_podaci VALUES (DEFAULT, '"+korisnickoIme+"', '"+email+"', '"+MD5(sifra)+"')";
        //String QUERY = "INSERT INTO baza2 VALUES (DEFAULT, '"+email+"', '"+korisnickoIme+"', '"+sifra+"')";
        int id = -1;
        try {
            stmt.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            new PristupniPodaci(korisnickoIme, email, MD5(sifra), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public static void azurirajPristupnePodatke (int id, String sifra) throws Exception { //ne uzimati id?
        sifra = MD5(sifra);
        String QUERY = "UPDATE pristupni_podaci SET sifra = '"+ sifra + "' WHERE id = "+id;
        try {
            stmt.executeUpdate(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String MD5(String s) throws Exception {//ovaj metod je kopiran sa interneta
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        return new BigInteger(1,m.digest()).toString(16);
    }


    public static void kreirajProfesore (){ //preload prilikom ucitavanja?
        String QUERY = "SELECT id, ime, prezime, pol, pristupni_podaci_id FROM profesor";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new Profesor(PristupniPodaci.getSviPodaci().get(rs.getInt("pristupni_podaci_id")),rs.getString("ime"), rs.getString("prezime"),rs.getInt("pol"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dodajProfesora (String ime, String prezime, int pol, int id){
        String QUERY = "INSERT INTO profesor VALUES (DEFAULT, '"+ime+"', '"+prezime+"', '"+pol+"', '"+id+"')";
        try {
            stmt.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            new Profesor(PristupniPodaci.getSviPodaci().get(id), ime, prezime, pol, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void kreirajUcenike (){
        String QUERY = "SELECT id, ime, prezime, pol, pristupni_podaci_id FROM ucenik";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new Ucenik(PristupniPodaci.getSviPodaci().get(rs.getInt("pristupni_podaci_id")),rs.getString("ime"), rs.getString("prezime"),rs.getInt("pol"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dodajUcenika (String ime, String prezime, int pol, int id){
        String QUERY = "INSERT INTO ucenik VALUES (DEFAULT, '"+ime+"', '"+prezime+"', '"+pol+"', '"+id+"')";
        try {
            stmt.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            new Ucenik(PristupniPodaci.getSviPodaci().get(id), ime, prezime, pol, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void kreirajPredmete (){
        String QUERY = "SELECT id, naziv, razred FROM predmet";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new Predmet(rs.getString("naziv"), rs.getInt("razred"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dodajPredmet(String naziv, int razred) {
        String QUERY = "INSERT INTO predmet VALUES (DEFAULT, '"+naziv+"', '"+razred+"')";
        try {
            stmt.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            new Predmet(naziv, razred, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void kreirajSkole(){
        String QUERY = "SELECT id, naziv, grad, mjesto, drzava FROM skola";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new Skola(rs.getString("naziv"), rs.getString("grad"), rs.getString("mjesto"), rs.getString("drzava"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dodajSkolu(String naziv, String grad, String mjesto, String drzava){
        String QUERY = "INSERT INTO skola VALUES (DEFAULT, '"+naziv+"', '"+grad+"', '"+mjesto+"', '"+drzava+"')";
        try {
            stmt.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            new Skola(naziv, grad, mjesto, drzava, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void kreirajPredmeteUSkoli(){
        String QUERY = "SELECT id, predmet_id, skola_id, profesor_id FROM predmet_u_skoli";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                Predmet predmet = Predmet.getSviPredmeti().get(rs.getInt("predmet_id"));
                Skola skola = Skola.getSveSkole().get(rs.getInt("skola_id"));
                Profesor profesor = Profesor.getSviProfesori().get(rs.getInt("profesor_id"));
                new PredmetUskoli(predmet, skola, profesor, rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*public static void dodajMojuSkolu(){

    }*/



}
