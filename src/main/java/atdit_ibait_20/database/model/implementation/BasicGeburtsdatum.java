package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Geburtsdatum;
/**
* BasicGeburtsdatum soll alle Attribute der Datenbank enthalten
**/
public class BasicGeburtsdatum implements Geburtsdatum {
    private final int GeburtsdatumTag;
    private final int GeburtsdatumMonat;
    private final int GeburtsdatumJahr;
/**
* Der Konstruktor weiß die Werte aus der Datenbank den Werten im Quellcode zu 
**/
    public BasicGeburtsdatum(int geburtsdatumTag, int geburtsdatumMonat, int geburtsdatumJahr) {
        GeburtsdatumTag = geburtsdatumTag;
        GeburtsdatumMonat = geburtsdatumMonat;
        GeburtsdatumJahr = geburtsdatumJahr;
    }

/**
* Die Werte werden in Integer gecastet
**/
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
