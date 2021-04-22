package atdit_ibait_20.database;

import atdit_ibait_20.database.presentation.implementation.StartLayer;

import java.util.ResourceBundle;

public class App {
    public static final String RESOURCE_BUNDLE_PATH = "i18n/GUI/GUI";


    public static void main (String[] args) {
        new StartLayer();
       // new BasicGUI();
    }
}
