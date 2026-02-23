package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class PannelliFotovoltaici extends Elettrodomestico {
    private static final double PRODUZIONE_PER_MQ = 0.2;
    private final double superficie;
    private final int numPannelli;

    public PannelliFotovoltaici(JSONObject config) {
        super(config);
        if (!config.has("superficie") || !config.has("num_pannelli"))
            throw new MissingArgumentConfigurationException("Mancano 'superficie' o 'num_pannelli' per Pannelli.");
        this.superficie = config.getDouble("superficie");
        this.numPannelli = config.getInt("num_pannelli");
    }

    @Override
    protected double consumoBase() { return 0; }

    @Override
    protected double consumoSpecifico() {
        return -(superficie * numPannelli * PRODUZIONE_PER_MQ);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("tipo", "pannelli");
        obj.put("superficie", superficie);
        obj.put("num_pannelli", numPannelli);
        obj.put("modalita", modalita.name());
        obj.put("classe_energetica", classeEnergetica.name());
        return obj;
    }
}