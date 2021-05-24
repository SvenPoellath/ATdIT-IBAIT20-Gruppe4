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
 * Die Klasse legt fest welche grafischen Komponenten das FrontEnd
 */
public class AnmeldeLayer implements SwingPresentation {

    private static final JPanel zurueckPanel = new JPanel();
    private static final JPanel anmeldungsPanel = new JPanel();
    private static final JPanel anmeldungsButtonPanel = new JPanel();
    private static final JPanel fehlerPanel = new JPanel();

    private static final JPasswordField tfAnmeldePasswort = new JPasswordField();

    /**
     * Das genaue Aussehen des Layoutes wird in den @Method der Setter festgelegt
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


    static void resetLogin() {
        textField1.setText("");
        tfAnmeldePasswort.setText("");
    }

    /**
     * Die Eingaben des Nutzers werden eingelesen und mit den in der Datenbank hinterlegten Daten abgeglichen. Wenn die Daten übereinstimmen bekommen der Nutzer die Ausgabe
     * "Anmeldung erfolgreich". Wenn die Daten nicht übereinstimmen bekommt der Nutzer die Ausgabe "Wrong id/password entered" und hat die Möglichkeit wieder auf die
     * Anmeldungsseite zurück zu gehen.
     * @param anmeldeName
     * @param anmeldePasswort
     * @method anmelden
     * @method anmeldenCheck
     * @method zurueck
     **/

    public void anmelden() {
        removePanelsFromFrame();
        String anmeldeName = textField1.getText();
        String anmeldePasswort = new String(tfAnmeldePasswort.getPassword());
        anmeldenCheck(anmeldeName, anmeldePasswort);
    }

    public void anmeldenCheck(String anmeldeName, String anmeldePasswort) {
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

    public void zurueck(){
        removePanelsFromFrame();
        MasterController.fenster.remove(StartLayer.sprache);
        App.masterController.loadStartLayer();
    }
}
