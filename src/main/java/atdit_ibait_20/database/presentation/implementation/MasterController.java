package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;

import javax.swing.*;
import java.awt.*;


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
    }
    public void loadAnmeldeLayer(){
        fenster.setSize(250, 325);
        new AnmeldeLayer();
    }
    public void loadEinstellungsLayer(Person angemeldetePerson){
        MasterController.fenster.setSize(1300,500);
        new EinstellungsLayer(angemeldetePerson);
    }
    public void loadRegistrierLayer(){
        MasterController.fenster.setSize(400,600);
        new RegistrierLayer();
    }
    public void loadVertragHinzufuegenLayer(Person angemeldetePerson){
        MasterController.fenster.setSize(400,400);
        new VertragHinzufuegenLayer(angemeldetePerson);
    }
    public void loadVertragsuebersicht(Person angemeldetePerson){
        MasterController.fenster.setSize(400,400);
        new Vertragsuebersicht(angemeldetePerson);
    }
}
