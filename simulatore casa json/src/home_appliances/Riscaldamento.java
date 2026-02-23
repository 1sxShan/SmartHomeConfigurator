package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Riscaldamento extends Elettrodomestico {
    private static final double CONSUMO_BASE = 1.5;
    private final double temperaturaDesiderata;

    public Riscaldamento(JSONObject config) {
        super(config);
        if (!config.has("temperatura_desiderata"))
            throw new MissingArgumentConfigurationException("Manca 'temperatura_desiderata' per Riscaldamento.");
        this.temperaturaDesiderata = config.getDouble("temperatura_desiderata");
    }

    @Override
    protected double consumoBase() { return CONSUMO_BASE; }

    @Override
    protected double consumoSpecifico() {
        return CONSUMO_BASE * (temperaturaDesiderata / 20.0);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("tipo", "riscaldamento");
        obj.put("temperatura_desiderata", temperaturaDesiderata);
        obj.put("modalita", modalita.name());
        obj.put("classe_energetica", classeEnergetica.name());
        return obj;
    }
}