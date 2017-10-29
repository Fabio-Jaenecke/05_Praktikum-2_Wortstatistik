import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Zaehlt die Anzahl Vorkommnisse von Woertern ueber mehrere Zeichenketten.
 * Es lassen sich eine beliebige Anzahl an Zeichenketten uebergeben. Die
 * Statistik wird fortlaufend gefuehrt. Die Wortzaehlung laesst sich jederzeit
 * ausgeben. Die Satzeichen . , ? ! " : ; werden entfernt. Alle Buchstaben
 * werden in Kleinbuchstaben umgewandelt um beispielsweise das Wort 'die'
 * inmitten eines Satzes und das Wort 'Die' am Anfang eines Satzes als gleiches
 * Wort werten zu koennen.
 * 
 * @version 0.1
 * @author daniel lerch, fabio jaenecke
 */
public class Wortanalyse
{
    // Statisch initiiertes Datenfeld mit den Satzzeichen, die nicht gelten sollen:
    private static HashSet<String> satzZeichen = new HashSet<String>();
    static {
        satzZeichen.add(".");
        satzZeichen.add(",");
        satzZeichen.add("?");
        satzZeichen.add("!");
        satzZeichen.add("\"");
        satzZeichen.add(":");
        satzZeichen.add(";");
    }
    // HashMap mit Wort / Anzahl Paaren:
    private HashMap<String, Integer> wortUndHaeufigkeit = new HashMap<String, Integer>();

    /**
     * eingabe() nimmt den beim Aufruf eingegebenen String und bearbeitet ihn nach Vorgaben der Spezifikation:
     * - spit() bei Leerschlägen
     * - allfällige weitere Leerschläge entfernen mit trim()
     * - alles auf Kleinbuchstaben setzen mit toLowerCase()
     * - Satzzeichen entfernen mit entferneSatzzeichen();
     * und fügt die einzelnen Worte der HashMap wortUndHäufigkeit zu:
     * - erst wird geprüft, ob das Wort ein leerer String ist
     * - dann wird geprüft, ob das Wort bereits in wortUndHäufigkeit vorhanden ist, 
     *      - wenn ja, wird der Zähler erhöht,
     *      - wenn nein, wird das Wort in die HashMap eingetrgen und erhält den Zähler 1
     * 
     * @param  textEingabe    ein beliebiger String.
     */
    public void eingabe(String textEingabe){
        String[] worte = textEingabe.split(" "); 

        for  (int i = 0; i < worte.length; i ++){
            String wort = worte[i].trim();
            wort = wort.toLowerCase();
            wort = entferneSatzzeichen(wort);

            if(wort != null && !wort.isEmpty()){
                if(wortUndHaeufigkeit.containsKey(wort)){
                    Integer zaehler = wortUndHaeufigkeit.get(wort);
                    zaehler++;
                    wortUndHaeufigkeit.put(wort, zaehler);
                }
                else {
                    wortUndHaeufigkeit.put(wort, 1);
                }
            }
        }
    }

    /**
     * entferneSatztzeichen() nimmt den beim Aufruf eingegebenen String und entfernt allfällig vorhandene Satzzeichen
     * 
     * @param  wort    ein beliebiger String.
     */
    private String entferneSatzzeichen(String wort){
        for(String zeichen : satzZeichen){
            if(wort.contains(zeichen)){
                wort = wort.replace(zeichen, "");
            }
        }
        return wort;
    }
    /**
     * wortHaeufigkeitDrucken() gibt die HashMap wortUndHaeufigkeit über System.out.println aus
     * 
     * 
     */
    public void wortHaeufigkeitDrucken(){
        Set<Map.Entry<String, Integer>> wortSet = wortUndHaeufigkeit.entrySet();
        for(Map.Entry<String, Integer> wortHaeufigkeit : wortSet){
            System.out.println(wortHaeufigkeit.getKey() + " " + wortHaeufigkeit.getValue());
        }
    }
}
