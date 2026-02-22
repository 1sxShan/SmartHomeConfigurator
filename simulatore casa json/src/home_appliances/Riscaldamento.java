package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Riscaldamento extends Elettrodomestico {
    private final double temperaturaDesiderata;

    public Riscaldamento(JSONObject config) {
        super(config); // Convalida 'consumo_orario'

        if (!config.has("temperatura_desiderata")) {
            throw new MissingArgumentConfigurationException("Manca 'temperatura_desiderata' per il Riscaldamento.");
        }
        this.temperaturaDesiderata = config.getDouble("temperatura_desiderata");
    }

    @Override
    public double getConsumoEffettivo() {
        // Logica di configurazione: più alta è la temperatura impostata, maggiore è il carico base
        return consumoBase * (temperaturaDesiderata / 20.0);
    }

    public double getTemperaturaDesiderata() {
        return temperaturaDesiderata;
    }
}