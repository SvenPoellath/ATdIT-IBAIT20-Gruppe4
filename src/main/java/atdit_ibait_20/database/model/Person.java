package atdit_ibait_20.database.model;

public interface Person {
    public String getSozialversicherungsnummer();
    public String getVorname();
    public String getNachname();
    public String getGeburtsdatum();
    public AccountingData getAccountingData();
    public String getPasswort();
}
