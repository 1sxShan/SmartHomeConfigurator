import configuration.*;
import home_appliances.*;
import org.json.JSONObject;
import java.util.Scanner;

Scanner sc = new Scanner(System.in);

void main() {
    var configLoader = new ConfigurationLoader();
    var casa = configLoader.loadConfig("simulatore casa json/src/config.json");

    int choice;
    do {
        printMenu();
        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> aggiungiElettrodomestico(casa, configLoader);
            case 2 -> visualizzaCasa(casa);
            case 3 -> modificaElettrodomestico(casa);
            case 4 -> rimuoviElettrodomestico(casa);
            case 5 -> new ConfigurationSaver().saveConfig("simulatore casa json/src/config.json", casa);
            case 0 -> System.out.println("Arrivederci!");
            default -> System.out.println("Scelta non valida.");
        }
    } while (choice != 0);
}

void printMenu() {
    System.out.println("\n--- MENU ---");
    System.out.println("1. Aggiungi elettrodomestico");
    System.out.println("2. Visualizza casa");
    System.out.println("3. Modifica elettrodomestico");
    System.out.println("4. Rimuovi elettrodomestico");
    System.out.println("5. Salva configurazione");
    System.out.println("0. Esci");
    System.out.print("Scelta: ");
}

void aggiungiElettrodomestico(Casa casa, ConfigurationLoader configLoader) {
    JSONObject json = new JSONObject();

    System.out.print("Tipo (lavatrice/frigo/lavastoviglie/asciugatrice/pannelli/riscaldamento): ");
    String tipo = sc.nextLine().trim().toLowerCase();
    json.put("tipo", tipo);

    System.out.print("Modalità (ECO/ADAPTIVE/PERFORMANCE): ");
    json.put("modalita", sc.nextLine().trim().toUpperCase());

    System.out.print("Classe energetica (A/B/C/D/E/F/G): ");
    json.put("classe_energetica", sc.nextLine().trim().toUpperCase());

    richiediParametriSpecifici(json, tipo);

    casa.addAppliance(configLoader.parseAppliance(json));
    System.out.println("Elettrodomestico aggiunto.");
}

void richiediParametriSpecifici(JSONObject json, String tipo) {
    switch (tipo) {
        case "lavatrice" -> {
            System.out.print("Velocità centrifuga (rpm): ");
            json.put("velocita_centrifuga", sc.nextInt()); sc.nextLine();
        }
        case "frigo" -> {
            System.out.print("Temperatura (°C): ");
            json.put("temperatura", sc.nextDouble()); sc.nextLine();
        }
        case "lavastoviglie" -> {
            System.out.print("Programma (1=Eco, 2=Intensivo, 3=Rapido): ");
            json.put("programma", sc.nextInt()); sc.nextLine();
        }
        case "asciugatrice" -> {
            System.out.print("Temperatura asciugatura (°C): ");
            json.put("temperatura_asciugatura", sc.nextDouble()); sc.nextLine();
        }
        case "pannelli" -> {
            System.out.print("Superficie per pannello (m²): ");
            json.put("superficie", sc.nextDouble()); sc.nextLine();
            System.out.print("Numero pannelli: ");
            json.put("num_pannelli", sc.nextInt()); sc.nextLine();
        }
        case "riscaldamento" -> {
            System.out.print("Temperatura desiderata (°C): ");
            json.put("temperatura_desiderata", sc.nextDouble()); sc.nextLine();
        }
        default -> System.out.println("Tipo non riconosciuto.");
    }
}

void visualizzaCasa(Casa casa) {
    System.out.println("\nCasa: " + casa.getNome());
    System.out.printf("Costo kWh: %.2f€%n", casa.getCosto());
    System.out.println("Elettrodomestici:");
    var lista = casa.getElettrodomestici();
    for (int i = 0; i < lista.size(); i++) {
        var e = lista.get(i);
        System.out.printf("  %d. %-20s | Classe: %s | Modalità: %-11s | Consumo: %.3f kWh%n",
                i,
                e.getClass().getSimpleName(),
                e.getClasseEnergetica(),
                e.getModalita(),
                e.getConsumoEffettivo());
    }
}

void modificaElettrodomestico(Casa casa) {
    var lista = casa.getElettrodomestici();
    visualizzaCasa(casa);
    System.out.print("Indice da modificare: ");
    int idx = sc.nextInt(); sc.nextLine();
    if (idx < 0 || idx >= lista.size()) { System.out.println("Indice non valido."); return; }

    var e = lista.get(idx);
    System.out.print("Nuova modalità (ECO/ADAPTIVE/PERFORMANCE): ");
    e.setModalita(Modalita.valueOf(sc.nextLine().trim().toUpperCase()));
    System.out.print("Nuova classe energetica (A/B/C/D/E/F/G): ");
    e.setClasseEnergetica(ClasseEnergetica.valueOf(sc.nextLine().trim().toUpperCase()));
    System.out.println("Modificato.");
}

void rimuoviElettrodomestico(Casa casa) {
    visualizzaCasa(casa);
    System.out.print("Indice da rimuovere: ");
    casa.delAppliance(sc.nextInt()); sc.nextLine();
    System.out.println("Rimosso.");
}