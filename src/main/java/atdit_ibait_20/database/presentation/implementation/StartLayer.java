package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

public class StartLayer{


    static final JFrame fenster = new JFrame();
    private final JPanel willkommenPanel = new JPanel();
    private final JPanel eingangsButtonsPanel = new JPanel();
    static final JPanel sprache = new JPanel();
    private final JButton registrierenFensterButton = new JButton(App.resourceBundle.getString("register"));
    private final JButton anmeldenFensterButton = new JButton(App.resourceBundle.getString("sign.in"));



    public StartLayer(){
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle(App.resourceBundle.getString("health.insurance.app"));
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(200, 300);
        fenster.setLayout(new FlowLayout());





        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));

        String[] sprachen = {App.resourceBundle.getString("choose.your.language"),App.resourceBundle.getString("german"),App.resourceBundle.getString("english")};
        JComboBox<String> spracheWaelen = new JComboBox<>(sprachen);

        registrierenFensterButton.addActionListener(new AnfangsButtonListener());
        anmeldenFensterButton.addActionListener(new AnfangsButtonListener());
        spracheWaelen.addItemListener(new SpracheWaelen());

        JLabel willkommenSchrift = new JLabel(App.resourceBundle.getString("welcome.to.our.app"));
        sprache.removeAll();
        sprache.add(spracheWaelen);
        willkommenPanel.add(willkommenSchrift);
        eingangsButtonsPanel.add(registrierenFensterButton);
        eingangsButtonsPanel.add(anmeldenFensterButton);
        fenster.add(sprache);
        fenster.add(willkommenPanel);
        fenster.add(eingangsButtonsPanel);
        fenster.validate();


    }
    class AnfangsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
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


    static class SpracheWaelen implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            if(cb.getSelectedIndex()==1){
                Locale.setDefault(Locale.GERMANY);
            }
            else if(cb.getSelectedIndex()==2){
                Locale.setDefault(Locale.ENGLISH);
            }

        }
    }
}
