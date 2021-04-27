package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.implementation.BasicDatabase;
import atdit_ibait_20.database.model.implementation.BasicPerson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnmeldeLayer {

    private final JPanel zurueckPanel = new JPanel();
    private final JPanel anmeldungsPanel = new JPanel();
    private final JPanel anmeldungsButtonPanel = new JPanel();
    private final JTextField tfAnmeldeName = new JTextField();
    private final JPasswordField tfAnmeldePasswort = new JPasswordField();
    JButton zurueckButton = new JButton("<--");
    JButton anmeldeButton = new JButton(App.resourceBundle.getString("sign.in"));

    public AnmeldeLayer() {

        anmeldeButton.addActionListener(new ButtonListener());

        zurueckButton.addActionListener(new ButtonListener());

        zurueckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        zurueckPanel.setLayout(new FlowLayout());
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        anmeldungsPanel.setLayout(new GridLayout(0, 2));
        anmeldungsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));

        zurueckPanel.add(zurueckButton);
        JLabel anmeldeName = new JLabel(App.resourceBundle.getString("nick.name"));
        anmeldungsPanel.add(anmeldeName);
        anmeldungsPanel.add(tfAnmeldeName);
        JLabel anmeldePasswort = new JLabel(App.resourceBundle.getString("password"));
        anmeldungsPanel.add(anmeldePasswort);
        anmeldungsPanel.add(tfAnmeldePasswort);
        anmeldungsButtonPanel.add(anmeldeButton);

        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(anmeldungsPanel);
        StartLayer.fenster.add(anmeldungsButtonPanel);
        StartLayer.fenster.setSize(250,250);

    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BasicPerson person = new BasicPerson();

            if(e.getSource().equals(zurueckButton)) {
                StartLayer.fenster.remove(anmeldungsPanel);
                StartLayer.fenster.remove(anmeldungsButtonPanel);
                StartLayer.fenster.remove(StartLayer.sprache);
                StartLayer.fenster.remove(zurueckPanel);
                new StartLayer();
            }
            else if(e.getSource().equals(anmeldeButton)){
                if (BasicDatabase.check_Login(tfAnmeldeName.getText(),new String(tfAnmeldePasswort.getPassword()))) {
                    StartLayer.fenster.remove(anmeldungsPanel);
                    StartLayer.fenster.remove(anmeldungsButtonPanel);
                    StartLayer.fenster.remove(StartLayer.sprache);
                    person = BasicDatabase.get_person_by_id(tfAnmeldeName.getText());
                    System.out.println("Anmeldung erfolgreich.");
                    new Vertragsübersicht(person);
                } else
                    System.out.println("Wrong password entered.");

            }
        }
    }
}
