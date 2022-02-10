package tabele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Skola {
    //private static ArrayList<Skola> sveSkole = new ArrayList<>();
    private static Map<Integer, Skola> sveSkole = new Hashtable<>();
    private String naziv, grad, mjesto, drzava;
    private int id;

    public Skola (String naziv, String grad, String mjesto, String drzava, int id){
        this.drzava = drzava;
        this.grad = grad;
        this.mjesto = mjesto;
        this.naziv = naziv;
        this.id = id;

        if(skolaPostoji(this))
            System.out.println("Skola postoji u bazi");
        else
            sveSkole.put(id, this);
    }

    private boolean skolaPostoji(Skola skola){
        if(sveSkole!=null)
            for(Skola s : sveSkole.values())
                if(s.naziv.equals(skola.naziv) && s.mjesto.equals(skola.mjesto) && s.grad.equals(skola.grad) && s.drzava.equals(skola.drzava))
                    return true;
        return false;
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getDrzava() {
        return drzava;
    }

    public String getGrad() {
        return grad;
    }

    public String getMjesto() {
        return mjesto;
    }

    public static Map<Integer, Skola> getSveSkole() {
        return sveSkole;
    }

    @Override
    public String toString() {
        return naziv+" "+mjesto;
    }
}
