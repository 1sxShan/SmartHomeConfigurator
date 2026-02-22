package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Frigo extends Elettrodomestico {
    private final double temperatura;

    public Frigo(JSONObject config) {
        super(config);

        if (!config.has("temperatura")) {
            throw new MissingArgumentConfigurationException("Manca 'temperatura' per Frigo.");
        }
        this.temperatura = config.getDouble("temperatura");
    }

    @Override
    public double getConsumoEffettivo() {
        // Esempio: più è bassa la temperatura, più consuma rispetto al base
        return consumoBase * (8.0 / temperatura);
    }
}