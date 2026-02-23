package home_appliances;

import org.json.JSONObject;

public abstract class Elettrodomestico {
    protected Modalita modalita;
    protected ClasseEnergetica classeEnergetica;

    public Elettrodomestico(JSONObject obj) {
        String mod = obj.optString("modalita", "ADAPTIVE").toUpperCase();
        this.modalita = Modalita.valueOf(mod);

        String classe = obj.optString("classe_energetica", "C").toUpperCase();
        this.classeEnergetica = ClasseEnergetica.valueOf(classe);
    }

    public double getConsumoEffettivo() {
        return consumoSpecifico() * modalita.moltiplicatore();
    }

    public ClasseEnergetica getClasseEnergetica() { return classeEnergetica; }
    public void setClasseEnergetica(ClasseEnergetica c) { this.classeEnergetica = c; }
    public Modalita getModalita() { return modalita; }
    public void setModalita(Modalita m) { this.modalita = m; }

    protected abstract double consumoBase();
    protected abstract double consumoSpecifico();
    public abstract JSONObject toJSON();
}