package tabele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Pitanje {
    /*private static ArrayList<Pitanje> svaPitanja = new ArrayList<>();*/
    private static Map<Integer, Pitanje> svaPitanja = new Hashtable<>();
    private String pitanje;
    private int id;

    public Pitanje (String pitanje, int id){
        this.pitanje = pitanje;
        this.id = id;

        svaPitanja.put(id,this);
    }

    public int getId() {
        return id;
    }

    public String getPitanje() {
        return pitanje;
    }

    public static Map<Integer, Pitanje> getSvaPitanja() {
        return svaPitanja;
    }
}
