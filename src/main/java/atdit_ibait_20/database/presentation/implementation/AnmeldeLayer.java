package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.persistence.implementation.DatabaseService;
import atdit_ibait_20.database.model.implementation.BasicPerson;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnmeldeLayer implements SwingPresentation {

    private final JPanel zurueckPanel = new JPanel();
    private final JPanel anmeldungsPanel = new JPanel();
    private final JPanel anmeldungsButtonPanel = new JPanel();
    private final JPanel fehlerPanel = new JPanel();

    private static final JLabel anmeldeName = new JLabel();
    private static final JLabel anmeldePasswort = new JLabel();
    private static final JLabel falschesPasswort = new JLabel();

    private static final JTextField tfAnmeldeName = new JTextField();
    private static final JPasswordField tfAnmeldePasswort = new JPasswordField();

    private static final JButton zurueckButton = new JButton("<--");
    private static final JButton anmeldeButton = new JButton();

    public AnmeldeLayer(){
        setStrings();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        setFrame();
    }
    static void setStrings(){
        anmeldeName.setText(App.resourceBundle.getString("nick.name"));
        anmeldePasswort.setText(App.resourceBundle.getString("password"));
        falschesPasswort.setText(App.resourceBundle.getString("wrong.password"));
        anmeldeButton.setText(App.resourceBundle.getString("sign.in"));
    }

    @Override
    public void setFrame() {
        StartLayer.fenster.setSize(250, 325);
    }

    @Override
    public void setLayout() {
        zurueckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        zurueckPanel.setLayout(new FlowLayout());
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        anmeldungsPanel.setLayout(new GridLayout(0, 2));
        anmeldungsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));
        falschesPasswort.setForeground(Color.red);
    }

    @Override
    public void addListeners() {
        anmeldeButton.addActionListener(new ButtonListener());
        zurueckButton.addActionListener(new ButtonListener());
    }

    @Override
    public void addComponentsToPanels() {
        anmeldungsPanel.remove(tfAnmeldeName);
        anmeldungsPanel.remove(tfAnmeldePasswort);
        zurueckPanel.add(zurueckButton);
        anmeldungsPanel.add(anmeldeName);
        anmeldungsPanel.add(tfAnmeldeName);
        anmeldungsPanel.add(anmeldePasswort);
        anmeldungsPanel.add(tfAnmeldePasswort);
        anmeldungsButtonPanel.add(anmeldeButton);
        fehlerPanel.add(falschesPasswort);
    }

    @Override
    public void addPanelsToFrame() {
        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(anmeldungsPanel);
        StartLayer.fenster.add(anmeldungsButtonPanel);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicPerson person;
            StartLayer.fenster.remove(anmeldungsPanel);
            StartLayer.fenster.remove(anmeldungsButtonPanel);
            StartLayer.fenster.remove(zurueckPanel);
            StartLayer.fenster.remove(fehlerPanel);
            if (e.getSource().equals(zurueckButton)) {
                StartLayer.fenster.remove(StartLayer.sprache);
                new StartLayer();
            } else if (e.getSource().equals(anmeldeButton)) {
                if (DatabaseService.check_Login(tfAnmeldeName.getText(), new String(tfAnmeldePasswort.getPassword()))) {
                    person = DatabaseService.get_person_by_id(tfAnmeldeName.getText());
                    System.out.println("Anmeldung erfolgreich.");
                    new Vertragsuebersicht(person);
                } else {
                    StartLayer.fenster.add(anmeldungsPanel);
                    StartLayer.fenster.add(anmeldungsButtonPanel);
                    StartLayer.fenster.add(zurueckPanel);
                    StartLayer.fenster.add(fehlerPanel);
                    System.out.println("Wrong id/password entered.");
                    StartLayer.fenster.validate();
                }
            }
        }
    }
}
