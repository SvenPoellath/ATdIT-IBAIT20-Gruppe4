package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Vertrag;
import atdit_ibait_20.database.model.Person;

import java.util.ArrayList;

public class BasicPerson implements Person {
    private final String Sozialversicherungsnummer;
    private final String Vorname;
    private final String Nachname;
    private final BasicGeburtsdatum Geburtsdatum;
    private final ArrayList<Vertrag> Vertraege;
    private final String Passwort;
    private final String Anrede;
    private final int PLZ;
    private final String Ort;
    private final int Hausnummer;
    private final String Familienstand;
    private final String Mailadresse;
    private final int telefonnummer;
    private final String Staatsangehoerigkeit;
    private final String Strasse;
    private final int IBAN;


    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum, String passwort, String anrede, int plz, String ort, int hausnummer, String familienstand, String mailadresse, int telefonnummer, String staatsangehoerigkeit, String strasse, ArrayList<Vertrag> vertraege, int iban) {
        this.Sozialversicherungsnummer = sozialversicherungsnummer;
        this.Vorname = vorname;
        this.Nachname = nachname;
        this.Geburtsdatum = geburtsdatum;
        this.Vertraege = new ArrayList<>(vertraege);
        this.Passwort = passwort;
        this.Anrede = anrede;
        this.PLZ = plz;
        this.Ort = ort;
        this.Hausnummer = hausnummer;
        this.Familienstand = familienstand;
        this.Mailadresse = mailadresse;
        this.telefonnummer = telefonnummer;
        this.Staatsangehoerigkeit = staatsangehoerigkeit;
        this.Strasse = strasse;
        this.IBAN = iban;
    }
    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum, String passwort, String anrede, int plz, String ort, int hausnummer, String familienstand, String mailadresse, int telefonnummer, String staatsangehoerigkeit, String strasse, ArrayList<Vertrag> vertraege) {
        this(sozialversicherungsnummer,vorname,nachname,geburtsdatum,passwort,anrede,plz,ort,hausnummer,familienstand,mailadresse,telefonnummer,staatsangehoerigkeit,strasse,vertraege,0);
    }
    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum, String passwort, String anrede, int plz, String ort, int hausnummer, String familienstand, String mailadresse, int telefonnummer, String staatsangehoerigkeit, String strasse) {
        this(sozialversicherungsnummer,vorname,nachname,geburtsdatum,passwort,anrede,plz,ort,hausnummer,familienstand,mailadresse,telefonnummer, staatsangehoerigkeit,strasse,null);
    }



    @Override
    public String getSozialversicherungsnummer() {
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
    public ArrayList<Vertrag> getVertraege() {
        return this.Vertraege;
    }

    @Override
    public String getPasswort() {
        return this.Passwort;
    }

    @Override
    public String getAnrede() {
        return this.Anrede;
    }

    @Override
    public int getPLZ() {
        return this.PLZ;
    }

    @Override
    public String getOrt() {
        return this.Ort;
    }

    @Override
    public String getStrasse() {
        return this.Strasse;
    }

    @Override
    public int getHausnummer() {
        return this.Hausnummer;
    }

    @Override
    public String getFamilienstand() {
        return this.Familienstand;
    }

    @Override
    public String getMailAdresse() {
        return this.Mailadresse;
    }

    @Override
    public int getTelefonnummer() {
        return this.telefonnummer;
    }

    @Override
    public String getStaatsangehoerigkeit() {
        return this.Staatsangehoerigkeit;
    }

    @Override
    public int getIBAN() {
        return this.IBAN;
    }

    @Override
    public void addVertrag(Vertrag vertrag) {
        Vertraege.add(vertrag);
    }
}
