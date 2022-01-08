package main;

public class User {
    private String korisnickoIme;
    private int pol;

    public User(String korisnickoIme){
        this.korisnickoIme = korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setPol(int pol) {
        this.pol = pol;
    }

    public int getPol() {
        return pol;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

}
