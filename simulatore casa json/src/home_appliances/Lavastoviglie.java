package home_appliances;

import exception.MissingArgumentConfigurationException;
import org.json.JSONObject;

public class Lavastoviglie extends Elettrodomestico {
    private static final double CONSUMO_BASE = 1.8;
    private final int programma;

    public Lavastoviglie(JSONObject config) {
        super(config);
        if (!config.has("programma"))
            throw new MissingArgumentConfigurationException("Manca 'programma' per Lavastoviglie.");
        this.programma = config.getInt("programma");
    }

    @Override
    protected double consumoBase() { return CONSUMO_BASE; }

    @Override
    protected double consumoSpecifico() {
        return switch (programma) {
            case 1 -> CONSUMO_BASE * 1.0;  // Eco
            case 2 -> CONSUMO_BASE * 1.5;  // Intensivo
            case 3 -> CONSUMO_BASE * 0.8;  // Rapido
            default -> throw new MissingArgumentConfigurationException("Programma non valido.");
        };
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("tipo", "lavastoviglie");
        obj.put("programma", programma);
        obj.put("modalita", modalita.name());
        obj.put("classe_energetica", classeEnergetica.name());
        return obj;
    }
}