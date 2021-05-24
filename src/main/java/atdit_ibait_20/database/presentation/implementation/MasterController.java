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
    public void loadStartLayer(){
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
        fenster.setSize(250, 325);
        SwingPresentation.clearAllTextFields();
        new AnmeldeLayer();
    }
    public void loadEinstellungsLayer(Person angemeldetePerson){
        MasterController.fenster.setSize(1300,500);
        SwingPresentation.clearAllTextFields();
        new EinstellungsLayer(angemeldetePerson);
    }
    public void loadRegistrierLayer(){
        MasterController.fenster.setSize(400,600);
        SwingPresentation.clearAllTextFields();
        new RegistrierLayer();
    }
    public void loadVertragHinzufuegenLayer(Person angemeldetePerson){
        MasterController.fenster.setSize(400,400);
        SwingPresentation.clearAllTextFields();
        new VertragHinzufuegenLayer(angemeldetePerson);
    }
    public void loadVertragsuebersicht(Person angemeldetePerson){
        MasterController.fenster.setSize(400,400);
        SwingPresentation.clearAllTextFields();
        new Vertragsuebersicht(angemeldetePerson);
    }
}
