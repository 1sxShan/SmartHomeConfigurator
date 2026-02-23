package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Asciugatrice extends Elettrodomestico {
    private static final double CONSUMO_BASE = 2.5;
    private final double temperaturaAsciugatura;

    public Asciugatrice(JSONObject config) {
        super(config);
        if (!config.has("temperatura_asciugatura"))
            throw new MissingArgumentConfigurationException("Manca 'temperatura_asciugatura' per Asciugatrice.");
        this.temperaturaAsciugatura = config.getDouble("temperatura_asciugatura");
    }

    @Override
    protected double consumoBase() { return CONSUMO_BASE; }

    @Override
    protected double consumoSpecifico() {
        return CONSUMO_BASE * (temperaturaAsciugatura / 60.0);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("tipo", "asciugatrice");
        obj.put("temperatura_asciugatura", temperaturaAsciugatura);
        obj.put("modalita", modalita.name());
        obj.put("classe_energetica", classeEnergetica.name());
        return obj;
    }
}