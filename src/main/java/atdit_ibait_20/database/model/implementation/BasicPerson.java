package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.AccountingData;
import atdit_ibait_20.database.model.Person;

public class BasicPerson implements Person {
    private final int Sozialversicherungsnummer;
    private final String Vorname;
    private final String Nachname;
    private final String Geburtsdatum;
    private final AccountingData accountingData;

    public BasicPerson(int sozialversicherungsnummer, String vorname, String nachname, int geburtsdatumTag, int geburtsdatumMonat, int geburtsdatumJahr, String geburtsdatum, AccountingData accountingData) {
        this.Sozialversicherungsnummer = sozialversicherungsnummer;
        this.Vorname = vorname;
        this.Nachname = nachname;
        Geburtsdatum = geburtsdatum;
        this.accountingData = accountingData;
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
        return this.Geburtsdatum;
    }


    @Override
    public AccountingData getAccountingData() {
        return this.accountingData;
    }
}
