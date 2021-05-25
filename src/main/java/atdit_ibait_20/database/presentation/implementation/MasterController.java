package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;

/**
* Die Klasse legt das Startfenster der Versicherungs App  fest
* @method die einzelnen Methoden legen fest wie groß jedes einzelne Element ist
*/
public class MasterController {
    static final JFrame fenster = new JFrame();
    static String woBinIch = "";
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
    public void loadAnmeldeLayer(){
        woBinIch = "anmelden";
        fenster.setSize(250, 325);
        SwingPresentation.clearAllTextFields();
        new AnmeldeLayer();
    }
    public void loadEinstellungsLayer(Person angemeldetePerson){
        woBinIch = "einstellungen";
        MasterController.fenster.setSize(1300,500);
        SwingPresentation.clearAllTextFields();
        new EinstellungsLayer(angemeldetePerson);
    }
    public void loadRegistrierLayer(){
        woBinIch = "registrieren";
        MasterController.fenster.setSize(400,600);
        SwingPresentation.clearAllTextFields();
        new RegistrierLayer();
    }
    public void loadVertragHinzufuegenLayer(Person angemeldetePerson){
        woBinIch = "vertragHinzufuegen";
        MasterController.fenster.setSize(400,400);
        SwingPresentation.clearAllTextFields();
        new VertragHinzufuegenLayer(angemeldetePerson);
    }
    public void loadVertragsuebersicht(Person angemeldetePerson){
        woBinIch = "vertragsübersicht";
        MasterController.fenster.setSize(400,400);
        SwingPresentation.clearAllTextFields();
        new VertragsuebersichtsLayer(angemeldetePerson);
    }
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
