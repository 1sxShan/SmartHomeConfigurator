package configuration;

import home_appliances.Elettrodomestico;
import java.util.ArrayList;
import java.util.List;

public class Casa {
    private String nome;
    private double costo;
    private List<Elettrodomestico> elettrodomestici;

    public Casa(String nome, double costo) {
        this.nome = nome;
        this.costo = costo;
        this.elettrodomestici = new ArrayList<>(); // Fondamentale!
    }

    public void addAppliance(Elettrodomestico e) {
        this.elettrodomestici.add(e);
    }

    // Getter per testare il caricamento
    public String getNome() { return nome; }
    public double getCosto() { return costo; }
    public List<Elettrodomestico> getElettrodomestici() { return elettrodomestici; }
}