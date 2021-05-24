package atdit_ibait_20.database;

import atdit_ibait_20.database.persistence.Database;
import atdit_ibait_20.database.persistence.implementation.SQLiteService;
import atdit_ibait_20.database.presentation.implementation.StartLayer;

import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

public class App {
    public static final String RESOURCE_BUNDLE_PATH = "i18n/GUI/GUI";
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_PATH,Locale.getDefault());
    public static final Database DATABASE = new SQLiteService();

    /**
    * @Methode Main startet die Applikation und baut die Verbindung zur Datenbank auf
    **/
    public static void main (String[] args) {
        new StartLayer();
        DATABASE.create_tables();
    }
}
