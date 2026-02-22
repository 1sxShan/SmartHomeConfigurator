package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Lavastoviglie extends Elettrodomestico {
    private final int programma;

    public Lavastoviglie(JSONObject config) {
        super(config); // Convalida 'consumo_orario'

        if (!config.has("programma")) {
            throw new MissingArgumentConfigurationException("Manca il parametro 'programma' per la Lavastoviglie.");
        }
        this.programma = config.getInt("programma");
    }

    @Override
    public double getConsumoEffettivo() {
        return switch (programma) {
            case 1 -> consumoBase * 1.0;  // Eco
            case 2 -> consumoBase * 1.5;  // Intensivo
            case 3 -> consumoBase * 0.8;  // Rapido
            default -> throw new MissingArgumentConfigurationException("Codice programma non valido.");
        };
    }
}