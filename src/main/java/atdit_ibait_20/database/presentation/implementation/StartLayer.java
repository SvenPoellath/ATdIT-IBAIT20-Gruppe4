package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;
/**
* Die Klasse legt das Startfenster fest was erscheint, wenn der Nutzer die Applikation startet
**/
public class StartLayer implements SwingPresentation {


    static final JFrame fenster = new JFrame();

    private static final JPanel willkommenPanel = new JPanel();
    private static final JPanel eingangsButtonsPanel = new JPanel();
    static final JPanel sprache = new JPanel();

    private static String[] sprachen;
    private static JComboBox<String> spracheWaelen;
    private static final JLabel willkommenSchrift = new JLabel();
    private static final JButton registrierenFensterButton = new JButton();
    private static final JButton anmeldenFensterButton = new JButton();

    public StartLayer(){
        setStrings();
        setFrame();
        setLayout();
        addListeners();
        addComponentsToPanels();
        addPanelsToFrame();
        fenster.validate();
    }
    /**
    * @setFrame Legt den Rahmen für das Fester fest
    **/
    public void setFrame(){
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setTitle(App.resourceBundle.getString("health.insurance.app"));
        fenster.pack();
        fenster.setVisible(true);
        fenster.setSize(200, 300);
        fenster.setLayout(new FlowLayout());
    }
    /**
    * @setLayout Legt fest welche Art von Layout die Nutzeroberfläche hat
    **/
    public void setLayout(){
        willkommenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        willkommenPanel.setLayout(new FlowLayout());
        eingangsButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        eingangsButtonsPanel.setLayout(new GridLayout(0,1));
    }
    /**
    * @addListeners Fügt die einzelnen Buttons dem Layout hinzu
    **/
    public void addListeners(){
        registrierenFensterButton.addActionListener(e->registrierenFensterButtonWurdeGedrueckt());
        anmeldenFensterButton.addActionListener(e->anmeldenFensterButtonWurdeGedrueckt());
        spracheWaelen.addItemListener(e->spracheWurdeGeaendert(e));
    }
    
    /**
    * @addComponentsToPanels verknüpft die Buttons mit den Funktionen die passieren sollen, wenn sie gedrückt werden
    **/
    public void addComponentsToPanels(){
        sprache.removeAll();
        sprache.add(spracheWaelen);
        willkommenPanel.add(willkommenSchrift);
        eingangsButtonsPanel.add(registrierenFensterButton);
        eingangsButtonsPanel.add(anmeldenFensterButton);
    }
    
    /**
    * @addPanelsToFrame fügt die Buttons dem Rahmen hinzu
    **/
    public void addPanelsToFrame(){
        fenster.add(sprache);
        fenster.add(willkommenPanel);
        fenster.add(eingangsButtonsPanel);
    }

    @Override
    public void removePanelsFromFrame() {
        fenster.remove(willkommenPanel);
        fenster.remove(eingangsButtonsPanel);
    }

    /**
    * @setString Legt die Schriftzüge der einzelnen Elemente des Layouts fest
    **/
    private static void setStrings(){
        fenster.setTitle(App.resourceBundle.getString("health.insurance.app"));
        anmeldenFensterButton.setText(App.resourceBundle.getString("sign.in"));
        willkommenSchrift.setText(App.resourceBundle.getString("welcome.to.our.app"));
        registrierenFensterButton.setText(App.resourceBundle.getString("register"));
        sprachen = new String[]{App.resourceBundle.getString("choose.your.language"), App.resourceBundle.getString("german"), App.resourceBundle.getString("english")};
        spracheWaelen = new JComboBox<>(sprachen);
    }
    /**
    * Die Klasse liefert die Funktionalität, die es dem Nutzer ermöglicht die Applikation zu schließen
    **/
    void registrierenFensterButtonWurdeGedrueckt(){
        removePanelsFromFrame();
        new RegistrierLayer();
    }
    void anmeldenFensterButtonWurdeGedrueckt(){
        new AnmeldeLayer();
        removePanelsFromFrame();
    }
    /**
    * Die Klasse ermöglicht es die Sprache zu ändern
    **/
    void spracheWurdeGeaendert(ItemEvent e){
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        if(cb.getSelectedItem().equals(App.resourceBundle.getString("german"))){
           setSpracheToGerman();
        }
        else if(cb.getSelectedItem().equals(App.resourceBundle.getString("english"))){
            setSpracheToEnglish();
        }
        resetAllStrings();
    }
    void setSpracheToGerman(){
        Locale.setDefault(Locale.GERMAN);
        App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
    }
    void setSpracheToEnglish(){
        Locale.setDefault(Locale.ENGLISH);
        App.resourceBundle = ResourceBundle.getBundle(App.RESOURCE_BUNDLE_PATH,Locale.getDefault());
    }
    void resetAllStrings(){
        setStrings();
        RegistrierLayer.setStrings();
        AnmeldeLayer.setStrings();
        Einstellungen.setStrings();
        VertragHinzufuegen.setStrings();
        Vertragsuebersicht.setStrings();
    }
}

