package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Lavatrice extends Elettrodomestico {
    private static final double CONSUMO_BASE = 1.2;
    private final int velocitaCentrifuga;

    public Lavatrice(JSONObject config) {
        super(config);
        if (!config.has("velocita_centrifuga"))
            throw new MissingArgumentConfigurationException("Manca 'velocita_centrifuga' per Lavatrice.");
        this.velocitaCentrifuga = config.getInt("velocita_centrifuga");
    }

    @Override
    protected double consumoBase() { return CONSUMO_BASE; }

    @Override
    protected double consumoSpecifico() {
        return CONSUMO_BASE * (velocitaCentrifuga / 1000.0);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("tipo", "lavatrice");
        obj.put("velocita_centrifuga", velocitaCentrifuga);
        obj.put("modalita", modalita.name());
        obj.put("classe_energetica", classeEnergetica.name());
        return obj;
    }
}