package home_appliances;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Elettrodomestico {
    protected double consumoBase;

    public Elettrodomestico (JSONObject obj) throws JSONException {
        // ⚠️
    }

    public double getConsumoEffettivo() {
        return consumoBase;
    }


}