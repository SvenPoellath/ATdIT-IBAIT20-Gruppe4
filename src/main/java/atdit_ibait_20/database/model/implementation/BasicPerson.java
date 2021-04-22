package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.AccountingData;
import atdit_ibait_20.database.model.Person;

public class BasicPerson implements Person {
    private final int Sozialversicherungsnummer;
    private final String Vorname;
    private final String Nachname;
    private final BasicGeburtsdatum Geburtsdatum;
    private final AccountingData accountingData;
    private final String Passwort;

    public BasicPerson(int sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum, AccountingData accountingData, String passwort) {
        this.Sozialversicherungsnummer = sozialversicherungsnummer;
        this.Vorname = vorname;
        this.Nachname = nachname;
        this.Geburtsdatum = geburtsdatum;
        this.accountingData = accountingData;
        this.Passwort = passwort;
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
    public String getGeburtsdatum() {
        return  Geburtsdatum.getGeburtsdatumJahr()+ "-" + Geburtsdatum.getGeburtsdatumMonat() + "-" + Geburtsdatum.getGeburtsdatumTag();
    }


    @Override
    public AccountingData getAccountingData() {
        return this.accountingData;
    }

    @Override
    public String getPasswort() {
        return this.Passwort;
    }
}
