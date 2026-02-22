package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Asciugatrice extends Elettrodomestico {
    private final int temperaturaAsciugatura;

    public Asciugatrice(JSONObject config) {
        super(config); // Convalida 'consumo_orario'

        if (!config.has("temperatura_asciugatura")) {
            throw new MissingArgumentConfigurationException("Manca 'temperatura_asciugatura' per l'Asciugatrice.");
        }
        this.temperaturaAsciugatura = config.getInt("temperatura_asciugatura");
    }

    @Override
    public double getConsumoEffettivo() {
        // Calcolo basato sulla temperatura (es. normalizzato su 60 gradi)
        return consumoBase * (temperaturaAsciugatura / 60.0);
    }
}