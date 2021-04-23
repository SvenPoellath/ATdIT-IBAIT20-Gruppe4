package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class StartLayer{
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH);

    static JFrame fenster = new JFrame();
    JPanel willkommenPanel = new JPanel();
    JPanel eingangsButtonsPanel = new JPanel();

    JLabel willkommenSchrift = new JLabel(resourceBundle.getString("welcome.to.our.app"));

    JButton registrierenFensterButton = new JButton(resourceBundle.getString("register"));
    JButton anmeldenFensterButton = new JButton(resourceBundle.getString("sign.in"));

    public StartLayer(){
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle(resourceBundle.getString("health.insurance.app"));
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(200, 250);
        //fenster.setLayout(new GridLayout(0,1));
        fenster.setLayout(new FlowLayout());

        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));

        registrierenFensterButton.addActionListener(new AnfangsButtonListener());
        anmeldenFensterButton.addActionListener(new AnfangsButtonListener());

        willkommenPanel.add(willkommenSchrift);
        eingangsButtonsPanel.add(registrierenFensterButton);
        eingangsButtonsPanel.add(anmeldenFensterButton);

        fenster.add(willkommenPanel);
        fenster.add(eingangsButtonsPanel);


    }
    class AnfangsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(registrierenFensterButton) || e.getSource().equals(anmeldenFensterButton)) {
                fenster.remove(willkommenPanel);
                fenster.remove(eingangsButtonsPanel);
                if(e.getSource().equals(registrierenFensterButton)) {
                    new RegistrierLayer();
                }
                else if(e.getSource().equals(anmeldenFensterButton)) {
                    new AnmeldeLayer();
                }
            }
        }
    }
}
