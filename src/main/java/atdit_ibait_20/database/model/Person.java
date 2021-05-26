package atdit_ibait_20.database.model;

import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;

import java.util.ArrayList;

/**
 * Das Interface zu der Person
 */
public interface Person {
    public String getSozialversicherungsnummer();
    public String getVorname();
    public String getNachname();
    public String getGeburtsdatum();
    public ArrayList<Vertrag> getVertraege();
    public String getPasswort();
    public String getAnrede();
    public int getPLZ();
    public String getOrt();
    public String getStrasse();
    public String getHausnummer();
    public String getFamilienstand();
    public String getMailAdresse();
    public long getTelefonnummer();
    public String getStaatsangehoerigkeit();
    public String getIBAN();
    public void addVertrag(Vertrag vertrag);
    public void setSozialversicherungsnummer(String sozialversicherungsnummer);
    public void setVorname(String vorname);
    public void setNachname(String nachname);
    public void setGeburtsdatum(BasicGeburtsdatum geburtsdatum);
    public void setPasswort(String passwort);
    public void setAnrede(String anrede);
    public void setPLZ(int plz);
    public void setOrt(String ort);
    public void setStrasse(String strasse);
    public void setHausnummer(String hausnummer);
    public void setFamilienstand(String familienstand);
    public void setMailAdresse(String mailAdresse);
    public void setTelefonnummer(long telefonnummer);
    public void setStaatsangehoerigkeit(String staatsangehoerigkeit);
    public void setIBAN(String iban);
}
