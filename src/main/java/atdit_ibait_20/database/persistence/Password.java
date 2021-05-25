package atdit_ibait_20.database.persistence;

/**
 * Das Interface dient zur Festlegung der sicheren generierung eines Passwortes auf Datenbankebene
 * Es benötigt zur Implementierung verschiedener Verschluesselungsmethoden ein Rework
 */
public interface Password {
    /**
     * generiert einen Salt-WErt
     * @return
     */
    static String getSalt() {
        return null;
    }

    /**
     * erstellt das verschluesselte Passwort durch Aufrufen der Methode generateSecurePassword()
     * @param password das vom Benutzer eingegebene Passwort
     * @param salt der Saltwert, welcher zur Verschluesselung dient
     * @return
     */
    static byte[] hash(char[] password, byte[] salt) {
        return new byte[0];
    }

    /**
     * generiert das Verschluesselte Passwort
     * @param password das eingegebene Passwort
     * @param salt der Saltwert
     * @return das verschluesselte Passwort
     */
    static String generateSecurePassword(String password, String salt) {
        return null;
    }

    /**
     * ueberprueft das vom Benutzer eingegebene Passwort mit dem abgespeicherten Passwort
     * Dafuer wird das eingegebene Passwort mit den Saltwert verschluesselt und mit dem verschluessleten Passwort
     * auf der Datenbank abgeglichen
     * @param providedPassword
     * @param securePassword
     * @param salt
     * @return
     */
    static boolean verifyUserPassword(String providedPassword, String securePassword, String salt) {
        return false;
    }

}
