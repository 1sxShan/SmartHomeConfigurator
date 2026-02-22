import configuration.*;
import home_appliances.Elettrodomestico;


void main(){

    var configLoader = new ConfigurationLoader();

    var casa = configLoader.loadConfig("simulatore casa json/src/config.json"); // apre la configurazione

    IO.println(casa.getElettrodomestici());



}