package atdit_ibait_20.database.model.implementation;

import atdit_ibait_20.database.model.Vertrag;
import atdit_ibait_20.database.model.Person;

import java.util.ArrayList;
/**
* Es wird eine Klasse Person erstellt, die das Interface Person implementiert. Weiterhin werden verschiedene primitive Datentypen sowie ein Array angelegt
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
/**
* @Method Im folgenden werden jeder Methode die richtigen Variablen zugeordnet. Durch den set Aufruf, wird es möglich die Daten außerhalb der Klasse zu bearbeiten
* @Param Vorname
* @Param Nachname
* @Param Geburtsdatum
* @Param Passwort
* @Param Anrede
* @Param PLZ
* @Param Ort
* @Param Hausnummer
* @Param Familienstand
* @Param Mailadresse
* @Param Telefonnummer
* @Param Staatsangehörigkeit
* @Param Strasse
* @Param IBAN
*/
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

    private String IBAN;
    /**
    * Es wird ein Konstruktur für BasicPerson angelegt. Darin werden alle Attribute übergeben.
    * @Param Vorname
    * @Param Nachname
    * @Param Geburtsdatum
    * @Param Passwort
    * @Param Anrede
    * @Param PLZ
    * @Param Ort
    * @Param Hausnummer
    * @Param Familienstand
    * @Param Mailadresse
    * @Param Telefonnummer
    * @Param Staatsangehörigkeit
    * @Param Strasse
    * @Param IBAN
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

    public BasicPerson() {

    }

    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum,
                       String passwort, String anrede, int plz, String ort, String hausnummer, String familienstand,
                       String mailadresse, long telefonnummer, String staatsangehoerigkeit, String strasse,
                       ArrayList<Vertrag> vertraege) {
        this(sozialversicherungsnummer, vorname, nachname, geburtsdatum, passwort, anrede, plz, ort, hausnummer, familienstand,
                mailadresse, telefonnummer, staatsangehoerigkeit, strasse, vertraege, null);
    }

    public BasicPerson(String sozialversicherungsnummer, String vorname, String nachname, BasicGeburtsdatum geburtsdatum,
                       String passwort, String anrede, int plz, String ort, String hausnummer, String familienstand,
                       String mailadresse, long telefonnummer, String staatsangehoerigkeit, String strasse) {
        this(sozialversicherungsnummer, vorname, nachname, geburtsdatum, passwort, anrede, plz, ort, hausnummer, familienstand,
                mailadresse, telefonnummer, staatsangehoerigkeit, strasse, null);
    }
/**
* @Methode ermöglicht es Verträge hinzuzufügen
*/

    public void addVertrag(Vertrag vertrag) {
        Vertraege.add(vertrag);
    }
/**
* Es wird für jedes Attribut der Parameter angelegt, damit man von außerhalb der Klasse darauf zugreifen kann
* @Param Vorname
* @Param Nachname
* @Param Geburtsdatum
* @Param Passwort
* @Param Anrede
* @Param PLZ
* @Param Ort
* @Param Hausnummer
* @Param Familienstand
* @Param Mailadresse
* @Param Telefonnummer
* @Param Staatsangehörigkeit
* @Param Strasse
* @Param IBAN
*/
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


