package atdit_ibait_20.database.model;

/**
 * Interface fuer das Geburtsdatum
 */
public interface Geburtsdatum {
    /**
     *
     * @return Tag des Geburtsdatums
     */
    public int getGeburtsdatumTag();

    /**
     *
     * @return Monat des Geburtsdatums
     */
    public int getGeburtsdatumMonat();

    /**
     *
     * @return Jahr des Geburtsdatums
     */
    public int getGeburtsdatumJahr();
}
