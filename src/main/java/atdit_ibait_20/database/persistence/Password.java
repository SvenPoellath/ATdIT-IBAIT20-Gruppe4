package atdit_ibait_20.database.persistence;

public interface Password {
    static String getSalt() {
        return null;
    }

    static byte[] hash(char[] password, byte[] salt) {
        return new byte[0];
    }

    static String generateSecurePassword(String password, String salt) {
        return null;
    }

    static boolean verifyUserPassword(String providedPassword, String securePassword, String salt) {
        return false;
    }

}
