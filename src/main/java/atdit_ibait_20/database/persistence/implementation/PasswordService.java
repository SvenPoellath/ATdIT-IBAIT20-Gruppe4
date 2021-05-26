package atdit_ibait_20.database.persistence.implementation;

import atdit_ibait_20.database.persistence.Database;
import atdit_ibait_20.database.persistence.Password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
/**
*Die Klasse dient der Absicherung des Passwortes auf Datenbankebene
 * Es wird verschluesselt durch den Saltwert und kann nur durch diesen verifiziert werden
 * Das unverschluesselte Passwort wird auf der Datenbank nicht abgespeichert
*/
public class PasswordService implements Password {

    private static final int LENGTH = 32;
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * generiert einen Salt-Wert
     * @return
     */
    public static String getSalt() {
        StringBuilder returnValue = new StringBuilder(32);

        for (int i = 0; i < LENGTH; i++)
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
    return new String (returnValue);
    }
    /**
     * erstellt das verschluesselte Passwort durch Aufrufen der Methode generateSecurePassword()
     * @param password das vom Benutzer eingegebene Passwort
     * @param salt der Saltwert, welcher zur Verschluesselung dient
     * @return
     */
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing the password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
    /**
     * generiert das Verschluesselte Passwort
     * @param password das eingegebene Passwort
     * @param salt der Saltwert
     * @return das verschluesselte Passwort
     */
    public static String generateSecurePassword(String password, String salt) {
        String returnValue;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        returnValue = Base64.getEncoder().encodeToString(securePassword);
        return returnValue;
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
    public static boolean verifyUserPassword(String providedPassword, String securePassword, String salt) {
        boolean returnValue;
        if (salt == null)
           return false;
        String passwordToTest = generateSecurePassword(providedPassword,salt);
        returnValue = passwordToTest.equals(securePassword);
        return returnValue;
    }
}
