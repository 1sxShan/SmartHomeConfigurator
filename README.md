# Configuratore di Elettrodomestici Domestici

Un'applicazione Java per configurare e gestire gli elettrodomestici di una casa, con supporto per modalità operative, classi energetiche e calcolo dei consumi.

## Descrizione

Il progetto permette di gestire una casa con i suoi elettrodomestici, tenendo conto di:
- Consumo energetico specifico per ogni tipo di elettrodomestico
- Modalità operative (ECO, Adaptive, Performance)
- Classe energetica (A → G)
- Configurazione tramite file JSON

## Struttura del Progetto

### Package

- **`configuration`**: Contiene le classi per la gestione della configurazione
- **`home_appliances`**: Contiene le classi degli elettrodomestici
- **`exception`**: Contiene le eccezioni personalizzate

### Classi Principali

#### Package `configuration`
- **`Casa`**: Classe contenitore per gli elettrodomestici, espone `addAppliance` e `delAppliance`
- **`ConfigurationLoader`**: Legge `config.json` e inizializza gli oggetti tramite `parseAppliance`
- **`ConfigurationSaver`**: Salva lo stato corrente della casa in `config.json`

#### Package `home_appliances`
- **`Elettrodomestico`**: Classe astratta base, gestisce `Modalita` e `ClasseEnergetica`
- **`Lavatrice`**: Parametro specifico: `velocita_centrifuga`
- **`Lavastoviglie`**: Parametro specifico: `programma` (1=Eco, 2=Intensivo, 3=Rapido)
- **`Asciugatrice`**: Parametro specifico: `temperatura_asciugatura`
- **`Frigo`**: Parametro specifico: `temperatura`
- **`PannelliFotovoltaici`**: Parametri specifici: `superficie`, `num_pannelli` — produce energia (consumo negativo)
- **`Riscaldamento`**: Parametro specifico: `temperatura_desiderata`

#### Package `exception`
- **`ApplianceDoesntExistException`**: Tipo di elettrodomestico non riconosciuto nel JSON
- **`MissingArgumentConfigurationException`**: Parametro obbligatorio mancante nel JSON

### Enum
- **`Modalita`**: `ECO` (×0.8), `ADAPTIVE` (×1.0), `PERFORMANCE` (×1.3)
- **`ClasseEnergetica`**: `A`, `B`, `C`, `D`, `E`, `F`, `G` — informativa, inserita dall'utente

## Configurazione

Il sistema utilizza un file JSON (`config.json`) per caricare e salvare la configurazione della casa.

### Struttura del file:
```json
{
  "nome_casa": "Casa Smart",
  "costo_kwh": 0.22,
  "dispositivi": [
    { "tipo": "lavatrice", "velocita_centrifuga": 1000, "modalita": "ADAPTIVE", "classe_energetica": "A" },
    { "tipo": "frigo", "temperatura": 4, "modalita": "ECO", "classe_energetica": "A" },
    { "tipo": "lavastoviglie", "programma": 2, "modalita": "PERFORMANCE", "classe_energetica": "B" },
    { "tipo": "asciugatrice", "temperatura_asciugatura": 60, "modalita": "ADAPTIVE", "classe_energetica": "C" },
    { "tipo": "pannelli", "superficie": 12.5, "num_pannelli": 5, "modalita": "ADAPTIVE", "classe_energetica": "A" },
    { "tipo": "riscaldamento", "temperatura_desiderata": 21.0, "modalita": "ECO", "classe_energetica": "B" }
  ]
}
```

### Parametri per tipo:

| Tipo             | Parametro specifico       | Unità  |
|------------------|---------------------------|--------|
| `lavatrice`      | `velocita_centrifuga`     | rpm    |
| `frigo`          | `temperatura`             | °C     |
| `lavastoviglie`  | `programma`               | 1/2/3  |
| `asciugatrice`   | `temperatura_asciugatura` | °C     |
| `pannelli`       | `superficie`, `num_pannelli` | m², n |
| `riscaldamento`  | `temperatura_desiderata`  | °C     |

Tutti i tipi accettano anche `modalita` e `classe_energetica`.

## Funzionalità

**`1.`** **Aggiungere** un elettrodomestico, specificando tipo, modalità, classe energetica e parametri specifici

**`2.`** **Visualizzare** la casa con tutti gli elettrodomestici, la loro classe energetica, modalità e consumo effettivo

**`3.`** **Rimuovere** un elettrodomestico dalla casa

**`4.`** **Salvare** la configurazione corrente su `config.json`

## Calcolo dei Consumi

Il consumo effettivo di ogni elettrodomestico è calcolato come:

```
consumoEffettivo = consumoSpecifico() × moltiplicatoreModalità
```

Dove `consumoSpecifico()` varia per ogni tipo:
- **Lavatrice**: proporzionale alla velocità di centrifuga
- **Frigo**: inversamente proporzionale alla temperatura
- **Lavastoviglie**: dipende dal programma scelto
- **Asciugatrice**: proporzionale alla temperatura di asciugatura
- **Pannelli**: valore negativo (produzione di energia)
- **Riscaldamento**: proporzionale alla temperatura desiderata

## Risoluzione Problemi

| Errore | Causa | Soluzione |
|--------|-------|-----------|
| `RuntimeException: Errore durante il caricamento` | File JSON non trovato o malformato | Verificare il percorso e la sintassi del file |
| `ApplianceDoesntExistException` | Valore di `tipo` non riconosciuto | Usare uno dei tipi supportati (vedi tabella) |
| `MissingArgumentConfigurationException` | Parametro obbligatorio assente | Aggiungere il campo mancante nel JSON |
