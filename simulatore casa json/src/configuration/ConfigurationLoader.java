package configuration;

import home_appliances.*;
import exception.ApplianceDoesntExistException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;

public class ConfigurationLoader {

    public Casa loadConfig(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONObject root = new JSONObject(new JSONTokener(reader));

            // Creiamo l'oggetto Casa con i dati generali
            Casa casa = new Casa(
                    root.getString("nome_casa"),
                    root.getDouble("costo_kwh")
            );

            // Prendiamo l'array dei dispositivi
            JSONArray dispositivi = root.getJSONArray("dispositivi");

            for (int i = 0; i < dispositivi.length(); i++) {
                JSONObject obj = dispositivi.getJSONObject(i);
                Elettrodomestico e = parseAppliance(obj);
                casa.addAppliance(e);
            }

            return casa;

        } catch (Exception e) {
            // Gestisce errori di lettura file o JSON malformati
            throw new RuntimeException("Errore durante il caricamento della configurazione: " + e.getMessage());
        }
    }

    private Elettrodomestico parseAppliance(JSONObject json) {
        String tipo = json.getString("tipo").toLowerCase();

        return switch (tipo) {
            case "lavatrice" -> new Lavatrice(json);
            case "frigo" -> new Frigo(json);
            case "lavastoviglie" -> new Lavastoviglie(json);
            case "asciugatrice" -> new Asciugatrice(json);
            case "pannelli" -> new PannelliFotovoltaici(json);
            case "riscaldamento" -> new Riscaldamento(json);
            default -> throw new ApplianceDoesntExistException("Tipo non riconosciuto: " + tipo);
        };
    }
}