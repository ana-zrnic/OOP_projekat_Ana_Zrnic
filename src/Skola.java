import java.util.ArrayList;

public class Skola {
    private static ArrayList<Skola> sveSkole = new ArrayList<>();
    private String naziv, grad, mjesto, drzava;

    public Skola (String naziv, String grad, String mjesto, String drzava){
        this.drzava = drzava;
        this.grad = grad;
        this.mjesto = mjesto;
        this.naziv = naziv;
    }
}
