package configuration;

import home_appliances.Elettrodomestico;
import java.util.ArrayList;
import java.util.List;

public class Casa {
    private final String nome;
    private final double costo;
    private final List<Elettrodomestico> elettrodomestici;

    public Casa(String nome, double costo) {
        this.nome = nome;
        this.costo = costo;
        this.elettrodomestici = new ArrayList<>();
    }

    public void addAppliance(Elettrodomestico e) { elettrodomestici.add(e); }

    public void delAppliance(int index) {
        if (index < 0 || index >= elettrodomestici.size())
            throw new IndexOutOfBoundsException("Indice non valido.");
        elettrodomestici.remove(index);
    }

    public String getNome() { return nome; }
    public double getCosto() { return costo; }
    public List<Elettrodomestico> getElettrodomestici() { return elettrodomestici; }
}