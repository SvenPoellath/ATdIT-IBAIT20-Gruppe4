package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Vertrag;
import atdit_ibait_20.database.model.Person;

import java.util.ArrayList;
/**
* Es wird eine Klasse Person erstellt, die das Interface Person implementiert.
 * Hier werden alle Informationen zur Person abgespeichert
*/
public class BasicPerson implements Person {
    private String Sozialversicherungsnummer;
    private String Vorname;
    private String Nachname;
    private BasicGeburtsdatum Geburtsdatum;
    private ArrayList<Vertrag> Vertraege = new ArrayList<>();
    private String Passwort;
    private String Anrede;
    private int PLZ;
    private String Ort;
    private String Hausnummer;
    private String Familienstand;
    private String Mailadresse;
    private long telefonnummer;
    private String Staatsangehoerigkeit;
    private String Strasse;
    private String IBAN;

    public void setSozialversicherungsnummer(String sozialversicherungsnummer) {
        Sozialversicherungsnummer = sozialversicherungsnummer;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public void setGeburtsdatum(BasicGeburtsdatum geburtsdatum) {
        Geburtsdatum = geburtsdatum;
    }

    public void setPasswort(String passwort) {
        Passwort = passwort;
    }

    public void setAnrede(String anrede) {
        Anrede = anrede;
    }

    public void setPLZ(int PLZ) {
        this.PLZ = PLZ;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public void setHausnummer(String hausnummer) {
        Hausnummer = hausnummer;
    }

    public void setFamilienstand(String familienstand) {
        Familienstand = familienstand;
    }

    public void setMailAdresse(String mailadresse) {
        Mailadresse = mailadresse;
    }

    public void setTelefonnummer(long telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public void setStaatsangehoerigkeit(String staatsangehoerigkeit) {
        Staatsangehoerigkeit = staatsangehoerigkeit;
    }

    public void setStrasse(String strasse) {
        Strasse = strasse;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    /**
     * dieser Konstruktor wird aufgerufen wenn alle Variablen der Person befuellt werden
     * @param sozialversicherungsnummer
     * @param vorname
     * @param nachname
     * @param geburtsdatum
     * @param passwort
     * @param anrede
     * @param plz
     * @param ort
     * @param hausnummer
     * @param familienstand
     * @param mailadresse
     * @param telefonnummer
     * @param staatsangehoerigkeit
     * @param strasse
     * @param vertraege bei anderen nicht vorhanden
     * @param iban bei anderen nicht vorhanden
     */
    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum,
                       String passwort, String anrede, int plz, String ort, String hausnummer, String familienstand,
                       String mailadresse, long telefonnummer, String staatsangehoerigkeit, String strasse,
                       ArrayList<Vertrag> vertraege, String iban) {
        this.Sozialversicherungsnummer = sozialversicherungsnummer;
        this.Vorname = vorname;
        this.Nachname = nachname;
        this.Geburtsdatum = geburtsdatum;
        if (vertraege != null)
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

    /**
     * Dieser Konstruktor dient der Erstellung einer Person im Programm, damit dieser die angemeldetet Person
     * weiter uebergeben werden kann
     */
    public BasicPerson() {

    }

    /**
     * Dieser Konstruktor wird aufgerufen wenn der Person alle Parameter bis auf die IBAN uebergeben werden
     * @param sozialversicherungsnummer
     * @param vorname
     * @param nachname
     * @param geburtsdatum
     * @param passwort
     * @param anrede
     * @param plz
     * @param ort
     * @param hausnummer
     * @param familienstand
     * @param mailadresse
     * @param telefonnummer
     * @param staatsangehoerigkeit
     * @param strasse
     * @param vertraege
     */
    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum,
                       String passwort, String anrede, int plz, String ort, String hausnummer, String familienstand,
                       String mailadresse, long telefonnummer, String staatsangehoerigkeit, String strasse,
                       ArrayList<Vertrag> vertraege) {
        this(sozialversicherungsnummer, vorname, nachname, geburtsdatum, passwort, anrede, plz, ort, hausnummer, familienstand,
                mailadresse, telefonnummer, staatsangehoerigkeit, strasse, vertraege, null);
    }

    /**
     * Dieser Konstruktor wird aufgerufen wenn alle Parameter bis auf Vertraege und IBAN uebergeben werden
     * @param sozialversicherungsnummer
     * @param vorname
     * @param nachname
     * @param geburtsdatum
     * @param passwort
     * @param anrede
     * @param plz
     * @param ort
     * @param hausnummer
     * @param familienstand
     * @param mailadresse
     * @param telefonnummer
     * @param staatsangehoerigkeit
     * @param strasse
     */
    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum,
                       String passwort, String anrede, int plz, String ort, String hausnummer, String familienstand,
                       String mailadresse, long telefonnummer, String staatsangehoerigkeit, String strasse) {
        this(sozialversicherungsnummer, vorname, nachname, geburtsdatum, passwort, anrede, plz, ort, hausnummer, familienstand,
                mailadresse, telefonnummer, staatsangehoerigkeit, strasse, null);
    }
/**
* es wird ein Vertrag in die ArrayList der Person hinzugefuegt
*/

    public void addVertrag(Vertrag vertrag) {
        Vertraege.add(vertrag);
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
        return Geburtsdatum.getGeburtsdatumJahr() + "-" + Geburtsdatum.getGeburtsdatumMonat() + "-" + Geburtsdatum.getGeburtsdatumTag();
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
    public String getHausnummer() {
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
    public long getTelefonnummer() {
        return this.telefonnummer;
    }

    @Override
    public String getStaatsangehoerigkeit() {
        return this.Staatsangehoerigkeit;
    }

    @Override
    public String getIBAN() {
        return this.IBAN;
    }
}


