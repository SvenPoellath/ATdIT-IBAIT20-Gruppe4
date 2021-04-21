package atdit_ibait_20.database.presentation.implementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartLayer {
    static JFrame fenster = new JFrame();
    JPanel willkommenPanel = new JPanel();
    JPanel eingangsButtonsPanel = new JPanel();

    JLabel willkommenSchrift = new JLabel("Willkommen in unserer App");

    JButton registrierenFensterButton = new JButton("Registrieren");
    JButton anmeldenFensterButton = new JButton("Anmelden");

    public StartLayer(){
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle("Krankenkassenapp");
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(500, 250);
        fenster.setLayout(new GridLayout(0,1));

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
