package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static atdit_ibait_20.database.App.DATABASE;
import static atdit_ibait_20.database.App.resourceBundle;

/**
 * In der Klasse wird das UI fuer das Anmeldefenster geladen
 * Hier kann der Benutzer sich fuer die App anmelden
 */
public class AnmeldeLayer implements SwingPresentation {

    private static final JPanel zurueckPanel = new JPanel();
    private static final JPanel anmeldungsPanel = new JPanel();
    private static final JPanel anmeldungsButtonPanel = new JPanel();
    private static final JPanel fehlerPanel = new JPanel();

    private static final JPasswordField tfAnmeldePasswort = new JPasswordField();

    /**
     * Konstruktor der Klasse AnmeldeLayer
     * Hier wird das UI geladen
     */
    public AnmeldeLayer(){
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        MasterController.fenster.validate();
        MasterController.fenster.repaint();
    }

    static void setStrings(){
        label2.setText(App.resourceBundle.getString("nick.name"));
        label3.setText(App.resourceBundle.getString("password"));
        label4.setText(App.resourceBundle.getString("wrong.password"));
        button1.setText(App.resourceBundle.getString("sign.in"));
        button3.setText("<--");
    }



    public void setLayout() {
        zurueckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        zurueckPanel.setLayout(new FlowLayout());
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        anmeldungsPanel.setLayout(new GridLayout(0, 2));
        anmeldungsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
    }


    public void addListeners() {
        removeListeners();
        button1.addActionListener(e->this.anmelden());
        button3.addActionListener(e->this.zurueck());
    }

    public void removeListeners() {
        for (ActionListener listener : button1.getActionListeners())
            button1.removeActionListener(listener);

        for (ActionListener listener : button3.getActionListeners())
            button3.removeActionListener(listener);

    }

    public void addComponentsToPanels() {
        zurueckPanel.add(button3);
        anmeldungsPanel.add(label2);
        anmeldungsPanel.add(textField1);
        anmeldungsPanel.add(label3);
        anmeldungsPanel.add(tfAnmeldePasswort);
        anmeldungsPanel.add(button1);
        fehlerPanel.add(label4);
    }


    public void addPanelsToFrame() {
        MasterController.fenster.add(zurueckPanel);
        MasterController.fenster.add(anmeldungsPanel);
        MasterController.fenster.add(anmeldungsButtonPanel);
    }


    public void removePanelsFromFrame() {
        MasterController.fenster.remove(zurueckPanel);
        MasterController.fenster.remove(anmeldungsPanel);
        MasterController.fenster.remove(anmeldungsButtonPanel);
        MasterController.fenster.remove(fehlerPanel);
    }

    /**
     * Die eingegebenen LogIn Daten werden geleert
     * damit beim erneuten Laden der Seite keine Daten der vorherigen Person angezeigt werden
     */
    static void resetLogin() {
        textField1.setText("");
        tfAnmeldePasswort.setText("");
    }

    /**
     * Entfernt alle angezeigten Panels
     */

    public void anmelden() {
        removePanelsFromFrame();
        String anmeldeName = textField1.getText();
        String anmeldePasswort = new String(tfAnmeldePasswort.getPassword());
        anmeldenCheck(anmeldeName, anmeldePasswort);
    }

    /**
     * Ueberprueft die eingegebenen Daten
     * @param anmeldeName Sozialversicherungsnummer, auf Datenabnkebene ID zur Anmeldung
     * @param anmeldePasswort Passwort zur Anmeldung
     */
    public void anmeldenCheck(String anmeldeName, String anmeldePasswort) {
/*        bei Änderungen in der Methode welche die Konsolenausgabe ändern
        bitte die TestMethoden in AnmeldeTestLayer
        anpassen oder die Tests durch @Disable oder @Ignore ausschalten*/

        Person person;
        if (DATABASE.check_Login(anmeldeName, anmeldePasswort)) {
            person = DATABASE.get_person_by_id(anmeldeName);
            System.out.println(App.resourceBundle.getString("loginSuccess"));
            App.masterController.loadVertragsuebersicht(person);
        } else {
            addPanelsToFrame();
            MasterController.fenster.add(fehlerPanel);
            System.out.println(resourceBundle.getString("wrongLogin"));
            MasterController.fenster.validate();
        }
    }

    /**
     * um auf die Startseite zurueckzukommen
     */
    public void zurueck(){
        removePanelsFromFrame();
        MasterController.fenster.remove(StartLayer.sprache);
        App.masterController.loadStartLayer();
    }
}
