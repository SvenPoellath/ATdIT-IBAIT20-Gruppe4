package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.persistence.Database;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import atdit_ibait_20.database.presentation.Layer;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static atdit_ibait_20.database.App.DATABASE;

/**
 * Die Klasse legt fest welche Komponenten die Anmeldeseite für den Nutzer hat und wie diese am Ende aussieht.
 **/
public class AnmeldeLayer implements Layer {

    private static final JPanel zurueckPanel = new JPanel();
    private static final JPanel anmeldungsPanel = new JPanel();
    private static final JPanel anmeldungsButtonPanel = new JPanel();
    private static final JPanel fehlerPanel = new JPanel();

    private static final JTextField tfAnmeldeName = new JTextField();
    private static final JPasswordField tfAnmeldePasswort = new JPasswordField();

    private static final JButton zurueckButton = new JButton("<--");
    /**
     * Das Verhalten der einzelnen Komponenten des Layouts für verschiedene Eingaben des Nutzers wird festgelegt
     **/
    public AnmeldeLayer(){
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        setFrame();
    }
    static void setStrings(){
        Layer.setLabel1(App.resourceBundle.getString("nick.name"));
        Layer.setLabel2(App.resourceBundle.getString("password"));
        Layer.setLabel3(App.resourceBundle.getString("wrong.password"));
        Layer.setButton1(App.resourceBundle.getString("sign.in"));
    }


    public void setFrame() {
        StartLayer.fenster.setSize(250, 325);
    }


    public void setLayout() {
        zurueckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        zurueckPanel.setLayout(new FlowLayout());
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        anmeldungsPanel.setLayout(new GridLayout(0, 2));
        anmeldungsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        label3.setForeground(Color.red);
    }


    public void addListeners() {
        button1.addActionListener(e->this.anmelden());
        zurueckButton.addActionListener(e->this.zurueck());
    }


    public void addComponentsToPanels() {
        zurueckPanel.add(zurueckButton);
        anmeldungsPanel.add(label1);
        anmeldungsPanel.add(tfAnmeldeName);
        anmeldungsPanel.add(label2);
        anmeldungsPanel.add(tfAnmeldePasswort);
        anmeldungsButtonPanel.add(button1);
        fehlerPanel.add(label3);
    }


    public void addPanelsToFrame() {
        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(anmeldungsPanel);
        StartLayer.fenster.add(anmeldungsButtonPanel);
    }


    public void removePanelsFromFrame() {
        StartLayer.fenster.remove(zurueckPanel);
        StartLayer.fenster.remove(anmeldungsPanel);
        StartLayer.fenster.remove(anmeldungsButtonPanel);
        StartLayer.fenster.remove(fehlerPanel);
    }


    /**
     * Die Eingaben des Nutzers werden eingelesen und mit den in der Datenbank hinterlegten Daten abgeglichen. Wenn die Daten übereinstimmen bekommen der Nutzer die Ausgabe
     * "Anmeldung erfolgreich". Wenn die Daten nicht übereinstimmen bekommt der Nutzer die Ausgabe "Wrong id/password entered" und hat die Möglichkeit wieder auf die
     * Anmeldungsseite zurück zu gehen.
     **/

    public void anmelden() {
        Person person;
        removePanelsFromFrame();
        if (DATABASE.check_Login(tfAnmeldeName.getText(), new String(tfAnmeldePasswort.getPassword()))) {
            person = DATABASE.get_person_by_id(tfAnmeldeName.getText());
            System.out.println("Anmeldung erfolgreich.");
            new Vertragsuebersicht(person);
        } else {
            addPanelsToFrame();
            System.out.println("Wrong id/password entered.");
            StartLayer.fenster.validate();
        }
    }

    public void zurueck(){
        removePanelsFromFrame();
        StartLayer.fenster.remove(StartLayer.sprache);
        new StartLayer();
    }
}
