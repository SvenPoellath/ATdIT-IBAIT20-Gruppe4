package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Person;

public class BasicPerson implements Person {
    private final int Sozialversicherungsnummer;
    private final String Vorname;
    private final String Nachname;
    private final int GeburtsdatumTag;
    private final int GeburtsdatumMonat;
    private final int GeburtsdatumJahr;

    public BasicPerson(int sozialversicherungsnummer, String vorname, String nachname, int geburtsdatumTag, int geburtsdatumMonat, int geburtsdatumJahr) {
        Sozialversicherungsnummer = sozialversicherungsnummer;
        Vorname = vorname;
        Nachname = nachname;
        GeburtsdatumTag = geburtsdatumTag;
        GeburtsdatumMonat = geburtsdatumMonat;
        GeburtsdatumJahr = geburtsdatumJahr;
    }

    @Override
    public int getSozialversicherungsnummer() {
        return this.Sozialversicherungsnummer;
    }

    @Override
    public String getVorname() {
        return this.Vorname;
    }

    @Override
    public String getNachname() {
        return this.Nachname;
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
