package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Lavatrice extends Elettrodomestico {
    private final int velocitaCentrifuga;

    public Lavatrice(JSONObject config) {
        super(config); // Estrae il consumoBase

        if (!config.has("velocita_centrifuga")) {
            throw new MissingArgumentConfigurationException("Manca 'velocita_centrifuga' per Lavatrice.");
        }
        this.velocitaCentrifuga = config.getInt("velocita_centrifuga");
    }

    @Override
    public double getConsumoEffettivo() {
        // Logica di calcolo basata sulla configurazione
        return consumoBase * (velocitaCentrifuga / 1200.0);
    }
}