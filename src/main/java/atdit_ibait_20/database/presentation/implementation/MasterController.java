package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;

/**
 * Von hier aus werden die jeweiligen Seiten aufgerufen
 * Die Klasse dient zur zentralen Steuerung des Programmes
 */
public class MasterController {
    /**
     * Auf diesem Fram wird das gesamte Programm angezeigt
     */
    static final JFrame fenster = new JFrame();
    static String woBinIch = "";

    /**
     * ruft die Startseite auf
     */
    public void loadStartLayer(){
        woBinIch = "start";
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle(App.resourceBundle.getString("health.insurance.app"));
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(200, 300);
        fenster.setLayout(new FlowLayout());
        new StartLayer();
        SwingPresentation.clearAllTextFields();
    }

    /**
     * ruft die Anmeldeseite auf
     */
    public void loadAnmeldeLayer(){
        woBinIch = "anmelden";
        fenster.setSize(250, 325);
        SwingPresentation.clearAllTextFields();
        RegistrierLayer.setStrings();
        new AnmeldeLayer();
    }

    /**
     * ruft die Einstellungsseite auf
     * @param angemeldetePerson die auf der App angemeldete Person
     */
    public void loadEinstellungsLayer(Person angemeldetePerson){
        woBinIch = "einstellungen";
        MasterController.fenster.setSize(1300,500);
        SwingPresentation.clearAllTextFields();
        new EinstellungsLayer(angemeldetePerson);
    }

    /**
     * ruft die Registrierseite auf
     */
    public void loadRegistrierLayer(){
        woBinIch = "registrieren";
        MasterController.fenster.setSize(400,600);
        SwingPresentation.clearAllTextFields();
        new RegistrierLayer();
    }
    /**
     * ruft die Vertragsbuchungsseite auf
     */
    public void loadVertragHinzufuegenLayer(Person angemeldetePerson){
        woBinIch = "vertragHinzufuegen";
        MasterController.fenster.setSize(400,400);
        SwingPresentation.clearAllTextFields();
        new VertragHinzufuegenLayer(angemeldetePerson);
    }

    /**
     * ruft die Vertragsuebersichtsseite auf
     * @param angemeldetePerson
     */
    public void loadVertragsuebersicht(Person angemeldetePerson){
        woBinIch = "vertragsübersicht";
        MasterController.fenster.setSize(400,400);
        SwingPresentation.clearAllTextFields();
        new VertragsuebersichtsLayer(angemeldetePerson);
    }

    /**
     * Setzt den Titel der Anwendung sowie die Strings der jeweiligen Seite
     */
    public void resetAllStrings(){
        if(woBinIch.equals("start")){
            StartLayer.setStrings();
        }else if(woBinIch.equalsIgnoreCase("anmelden")){
            AnmeldeLayer.setStrings();
        }else if(woBinIch.equalsIgnoreCase("registrieren")){
            RegistrierLayer.setStrings();
        }else if(woBinIch.equalsIgnoreCase("einstellungen")){
            EinstellungsLayer.setStrings();
        }else if(woBinIch.equalsIgnoreCase("vertragHinzufuegen")){
            VertragHinzufuegenLayer.setStrings();
        }else if(woBinIch.equalsIgnoreCase("vertragsübersicht")){
            VertragsuebersichtsLayer.setStrings();
        }
        MasterController.fenster.setTitle(App.resourceBundle.getString("health.insurance.app"));
    }
}
