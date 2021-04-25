package atdit_ibait_20.database.model;

import java.util.ArrayList;

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
    public int getTelefonnummer();
    public String getStaatsangehoerigkeit();
    public int getIBAN();
    public void addVertrag(Vertrag vertrag);
}
