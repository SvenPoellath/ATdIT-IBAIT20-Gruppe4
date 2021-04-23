package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class AnmeldeLayer {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH);

    JPanel zurueckPanel = new JPanel();
    JPanel anmeldungsPanel = new JPanel();
    JPanel anmeldungsButtonPanel = new JPanel();

    JLabel anmeldeName = new JLabel(resourceBundle.getString("nick.name"));
    JLabel anmeldePasswort = new JLabel(resourceBundle.getString("password"));

    JButton zurueckButton = new JButton("<--");
    JButton anmeldeButton = new JButton(resourceBundle.getString("sign.in"));

    JTextField tfAnmeldeName = new JTextField();
    JPasswordField tfAnmeldePasswort = new JPasswordField();

    public AnmeldeLayer() {
        anmeldeButton.addActionListener(new AnmeldeButtonListener());
        zurueckButton.addActionListener(new ZurueckButtonListener());

        zurueckPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        zurueckPanel.setLayout(new FlowLayout());
        anmeldungsPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        anmeldungsPanel.setLayout(new GridLayout(0, 2));
        anmeldungsButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 10, 30));

        zurueckPanel.add(zurueckButton);
        anmeldungsPanel.add(anmeldeName);
        anmeldungsPanel.add(tfAnmeldeName);
        anmeldungsPanel.add(anmeldePasswort);
        anmeldungsPanel.add(tfAnmeldePasswort);
        anmeldungsButtonPanel.add(anmeldeButton);

        StartLayer.fenster.add(zurueckPanel);
        StartLayer.fenster.add(anmeldungsPanel);
        StartLayer.fenster.add(anmeldungsButtonPanel);
        StartLayer.fenster.setSize(250,250);

    }

    class ZurueckButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StartLayer.fenster.remove(anmeldungsPanel);
            StartLayer.fenster.remove(anmeldungsButtonPanel);
            StartLayer.fenster.remove(zurueckPanel);
            new StartLayer();
        }
    }

    class AnmeldeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


        }

    }
}
