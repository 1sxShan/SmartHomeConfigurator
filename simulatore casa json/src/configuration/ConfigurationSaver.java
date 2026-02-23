package configuration;

import home_appliances.Elettrodomestico;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;

public class ConfigurationSaver {
    public void saveConfig(String filePath, Casa casa) {
        try (FileWriter writer = new FileWriter(filePath)) {
            JSONObject root = new JSONObject();
            root.put("nome_casa", casa.getNome());
            root.put("costo_kwh", casa.getCosto());

            JSONArray dispositivi = new JSONArray();
            for (Elettrodomestico e : casa.getElettrodomestici())
                dispositivi.put(e.toJSON());

            root.put("dispositivi", dispositivi);
            writer.write(root.toString(2));
        } catch (Exception e) {
            throw new RuntimeException("Errore salvataggio: " + e.getMessage());
        }
    }
}