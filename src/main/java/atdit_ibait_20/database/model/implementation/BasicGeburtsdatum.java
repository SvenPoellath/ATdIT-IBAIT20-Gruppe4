package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Geburtsdatum;
/**
* BasicGeburtsdatum erstellt die Verbindung zur Datenbank und inplementiert das Interface Geburtsdatum
*/
public class BasicGeburtsdatum implements Geburtsdatum {
    private final int GeburtsdatumTag;
    private final int GeburtsdatumMonat;
    private final int GeburtsdatumJahr;
/**
* Der Konstruktor uebergibt die Daten des Geburtsdatums aus dem UI an die Klasse
*/
    public BasicGeburtsdatum(int geburtsdatumTag, int geburtsdatumMonat, int geburtsdatumJahr) {
        GeburtsdatumTag = geburtsdatumTag;
        GeburtsdatumMonat = geburtsdatumMonat;
        GeburtsdatumJahr = geburtsdatumJahr;
    }

/**
* Das Datum aus dem Datenbankeintrag wird in den jeweiligen Tag, Monat und das JAhr aufgeteilt und an die Klasse uebergeben
*/
    public BasicGeburtsdatum(String database_entry) {
        String[] geburtstag = database_entry.split("-");
        GeburtsdatumJahr = Integer.parseInt(geburtstag[0]);
        GeburtsdatumMonat = Integer.parseInt(geburtstag[1]);
        GeburtsdatumTag = Integer.parseInt(geburtstag[2]);
    }


    @Override
    public int getGeburtsdatumTag() {
        return this.GeburtsdatumTag;
    }

    @Override
    public int getGeburtsdatumMonat() {
        return this.GeburtsdatumMonat;
    }

    @Override
    public int getGeburtsdatumJahr() {
        return this.GeburtsdatumJahr;
    }
}
