package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.persistence.implementation.DatabaseService;
import atdit_ibait_20.database.model.implementation.BasicPerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnmeldeLayer {

    private final JPanel zurueckPanel = new JPanel();
    private final JPanel anmeldungsPanel = new JPanel();
    private final JPanel anmeldungsButtonPanel = new JPanel();
    private final JPanel fehlerPanel = new JPanel();

    private final JTextField tfAnmeldeName = new JTextField();
    private final JPasswordField tfAnmeldePasswort = new JPasswordField();

    private final JButton zurueckButton = new JButton("<--");
    private final JButton anmeldeButton = new JButton(App.resourceBundle.getString("sign.in"));

    public AnmeldeLayer() {
        zurueckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        zurueckPanel.setLayout(new FlowLayout());
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        anmeldungsPanel.setLayout(new GridLayout(0, 2));
        anmeldungsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));

        anmeldeButton.addActionListener(new ButtonListener());
        zurueckButton.addActionListener(new ButtonListener());

        JLabel anmeldeName = new JLabel(App.resourceBundle.getString("nick.name"));
        JLabel anmeldePasswort = new JLabel(App.resourceBundle.getString("password"));
        JLabel falschesPasswort = new JLabel((App.resourceBundle.getString("wrong.password")));

        zurueckPanel.add(zurueckButton);
        anmeldungsPanel.add(anmeldeName);
        anmeldungsPanel.add(tfAnmeldeName);
        anmeldungsPanel.add(anmeldePasswort);
        anmeldungsPanel.add(tfAnmeldePasswort);
        anmeldungsButtonPanel.add(anmeldeButton);

        falschesPasswort.setForeground(Color.red);
        fehlerPanel.add(falschesPasswort);

        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(anmeldungsPanel);
        StartLayer.fenster.add(anmeldungsButtonPanel);
        StartLayer.fenster.setSize(250, 325);

    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicPerson person;
            StartLayer.fenster.remove(anmeldungsPanel);
            StartLayer.fenster.remove(anmeldungsButtonPanel);
            StartLayer.fenster.remove(StartLayer.sprache);
            StartLayer.fenster.remove(zurueckPanel);
            StartLayer.fenster.remove(fehlerPanel);
            if (e.getSource().equals(zurueckButton)) {
                new StartLayer();
            } else if (e.getSource().equals(anmeldeButton)) {
                if (DatabaseService.check_Login(tfAnmeldeName.getText(), new String(tfAnmeldePasswort.getPassword()))) {
                    person = DatabaseService.get_person_by_id(tfAnmeldeName.getText());
                    System.out.println("Anmeldung erfolgreich.");
                    new Vertragsuebersicht(person);
                } else {
                    StartLayer.fenster.add(anmeldungsPanel);
                    StartLayer.fenster.add(anmeldungsButtonPanel);
                    StartLayer.fenster.add(StartLayer.sprache);
                    StartLayer.fenster.add(zurueckPanel);
                    StartLayer.fenster.add(fehlerPanel);
                    System.out.println("Wrong id/password entered.");
                    StartLayer.fenster.validate();
                }
            }
        }
    }
}
