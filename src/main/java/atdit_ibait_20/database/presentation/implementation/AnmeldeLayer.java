package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.persistence.Database;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static atdit_ibait_20.database.App.DATABASE;
import static atdit_ibait_20.database.App.resourceBundle;

/**
 * Die Klasse legt fest welche Komponenten die Anmeldeseite für den Nutzer hat und wie diese am Ende aussieht.
 **/
public class AnmeldeLayer implements SwingPresentation {

    private static final JPanel zurueckPanel = new JPanel();
    private static final JPanel anmeldungsPanel = new JPanel();
    private static final JPanel anmeldungsButtonPanel = new JPanel();
    private static final JPanel fehlerPanel = new JPanel();

    private static final JLabel anmeldename = new JLabel();
    private static final JLabel passwort = new JLabel();
    private static final JLabel falschesPasswort = new JLabel();
    private static final JButton anmeldenButton = new JButton();
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
        StartLayer.fenster.validate();
        StartLayer.fenster.repaint();
    }
    static void setStrings(){
        anmeldename.setText(App.resourceBundle.getString("nick.name"));
        passwort.setText(App.resourceBundle.getString("password"));
        falschesPasswort.setText(App.resourceBundle.getString("wrong.password"));
        anmeldenButton.setText(App.resourceBundle.getString("sign.in"));
    }

    static void resetLogin() {
        tfAnmeldeName.setText("");
        tfAnmeldePasswort.setText("");
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
        falschesPasswort.setForeground(Color.red);
    }


    public void addListeners() {
        removeListeners();
        anmeldenButton.addActionListener(e->this.anmelden());
        zurueckButton.addActionListener(e->this.zurueck());
    }

    public void removeListeners() {
        for (ActionListener listener : anmeldenButton.getActionListeners())
            anmeldenButton.removeActionListener(listener);

        for (ActionListener listener : zurueckButton.getActionListeners())
            zurueckButton.removeActionListener(listener);

    }




    public void addComponentsToPanels() {
        zurueckPanel.add(zurueckButton);
        anmeldungsPanel.add(anmeldename);
        anmeldungsPanel.add(tfAnmeldeName);
        anmeldungsPanel.add(passwort);
        anmeldungsPanel.add(tfAnmeldePasswort);
        anmeldungsButtonPanel.add(anmeldenButton);
        fehlerPanel.add(falschesPasswort);
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
        removePanelsFromFrame();
        String anmeldeName = tfAnmeldeName.getText();
        String anmeldePasswort = new String(tfAnmeldePasswort.getPassword());
        anmeldenCheck(anmeldeName, anmeldePasswort);
    }

    public void anmeldenCheck(String anmeldeName, String anmeldePasswort) {
        Person person;
        if (DATABASE.check_Login(anmeldeName, anmeldePasswort)) {
            person = DATABASE.get_person_by_id(anmeldeName);
            System.out.println(App.resourceBundle.getString("loginSuccess"));
            new Vertragsuebersicht(person);
        } else {
            addPanelsToFrame();
            StartLayer.fenster.add(fehlerPanel);
            System.out.println(resourceBundle.getString("wrongLogin"));
            StartLayer.fenster.validate();
        }
    }

    public void zurueck(){
        removePanelsFromFrame();
        StartLayer.fenster.remove(StartLayer.sprache);
        new StartLayer();
    }
}
