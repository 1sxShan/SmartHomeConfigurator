package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Frigo extends Elettrodomestico {
    private static final double CONSUMO_BASE = 0.15;
    private final double temperatura;

    public Frigo(JSONObject config) {
        super(config);
        if (!config.has("temperatura"))
            throw new MissingArgumentConfigurationException("Manca 'temperatura' per Frigo.");
        this.temperatura = config.getDouble("temperatura");
    }

    @Override
    protected double consumoBase() { return CONSUMO_BASE; }

    @Override
    protected double consumoSpecifico() {
        return CONSUMO_BASE * (8.0 / temperatura);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("tipo", "frigo");
        obj.put("temperatura", temperatura);
        obj.put("modalita", modalita.name());
        obj.put("classe_energetica", classeEnergetica.name());
        return obj;
    }
}