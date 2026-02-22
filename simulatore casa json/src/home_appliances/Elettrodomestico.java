package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public abstract class Elettrodomestico {
    protected double consumoBase;

    public Elettrodomestico(JSONObject obj) {
        // Controllo se il campo base è presente come da UML
        if (!obj.has("consumo_orario")) {
            throw new MissingArgumentConfigurationException("Parametro 'consumo_orario' mancante.");
        }
        this.consumoBase = obj.getDouble("consumo_orario");
    }

    // Metodo astratto per il calcolo specifico di ogni dispositivo
    public abstract double getConsumoEffettivo();
}