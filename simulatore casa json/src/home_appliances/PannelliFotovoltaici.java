package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class PannelliFotovoltaici extends Elettrodomestico {
    private final int nPannelli;
    private final double area;
    private final double rendimento;

    public PannelliFotovoltaici(JSONObject config) {
        super(config);

        if (!config.has("n_pannelli") || !config.has("area") || !config.has("rendimento")) {
            throw new MissingArgumentConfigurationException("Parametri tecnici pannelli incompleti.");
        }

        this.nPannelli = config.getInt("n_pannelli");
        this.area = config.getDouble("area");
        this.rendimento = config.getDouble("rendimento");
    }

    @Override
    public double getConsumoEffettivo() {
        // Restituisce un valore negativo perché produce energia
        double potenzaSoleStandard = 1.367; // kW/m^2
        return -(nPannelli * area * rendimento * potenzaSoleStandard);
    }
}