package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;
import atdit_ibait_20.database.persistence.Database;
import atdit_ibait_20.database.presentation.SwingPresentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static atdit_ibait_20.database.App.DATABASE;

/**
* In der Klasse wird das Layout für die Einstellungen festgelegt. Es werden Textfelder angelegt und Buttons,
* die dem Nutzer später die Möglichkeit bieten sollen seine Nutzerdaten zu ändern.
**/
public class Einstellungen implements SwingPresentation {
    static final JPanel einstellungen = new JPanel();
    static final JPanel datenAendern = new JPanel();
    static final JPanel geburtsdatum = new JPanel();

    private static final JLabel einstellungenText = new JLabel();
    private static final JLabel veraenderungen = new JLabel();
    private static final JLabel vornameAendernText = new JLabel();
    private static final JLabel nachnameAendernText = new JLabel();
    private static final JLabel geburtsdatumAendernText = new JLabel();
    private static final JLabel passwortAendernText = new JLabel();
    private static final JLabel anredeAendernText = new JLabel();
    private static final JLabel plzAendernText = new JLabel();
    private static final JLabel ortAendernText = new JLabel();
    private static final JLabel strasseAendernText = new JLabel();
    private static final JLabel hausnummerAendernText = new JLabel();
    private static final JLabel familienstandAendernText = new JLabel();
    private static final JLabel mailAendernText = new JLabel();
    private static final JLabel telefonnummerAendernText = new JLabel();
    private static final JLabel staatsangehoerigkeitAendernText = new JLabel();

    private static final  JTextField vornameNeu = new JTextField();
    private static final  JTextField nachnameNeu = new JTextField();
    private static final  JTextField passwortNeu = new JPasswordField();
    private static final  JTextField plzNeu = new JTextField();
    private static final  JTextField ortNeu = new JTextField();
    private static final  JTextField strasseNeu = new JTextField();
    private static final  JTextField hausnummerNeu = new JTextField();
    private static final  JTextField telefonnummerNeu = new JTextField();
    private static final  JTextField mailNeu = new JTextField();
    private static final  JTextField staatsangehoerigkeitNeu = new JTextField();

    private static final JButton vornameAendern = new JButton();
    private static final JButton nachnameAendern = new JButton();
    private static final JButton geburtsdatumAendern = new JButton();
    private static final JButton passwortAendern = new JButton();
    private static final JButton anredeAendern = new JButton();
    private static final JButton plzAendern = new JButton();
    private static final JButton ortAendern = new JButton();
    private static final JButton strasseAendern = new JButton();
    private static final JButton hausnummerAendern = new JButton();
    private static final JButton familienstandAendern = new JButton();
    private static final JButton mailAendern = new JButton();
    private static final JButton telefonnummerAendern = new JButton();
    private static final JButton staatsangehoerigkeitAendern = new JButton();
    static Person angemeldetePerson;
    public Einstellungen(Person person){
        angemeldetePerson = person;
            setStrings();
            setLayout();
            addListeners();
            addComponentsToPanels();
            addPanelsToFrame();
            setFrame();
    }
/**
* @setString ermöglicht es dem Nutzer seine Daten zu ändern.
**/
    static void setStrings(){
        einstellungenText.setText(App.resourceBundle.getString("welcome.to.your.settings"));
        veraenderungen.setText(App.resourceBundle.getString("make.changes.here"));
        vornameAendernText.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("first.name"));
        nachnameAendernText.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("last.name"));
        geburtsdatumAendernText.setText(App.resourceBundle.getString("new.2")+" "+App.resourceBundle.getString("date.of.birth"));
        passwortAendernText.setText(App.resourceBundle.getString("new.2")+" "+App.resourceBundle.getString("password"));
        anredeAendernText.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("title"));
        plzAendernText.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("postcode"));
        ortAendernText.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("city"));
        strasseAendernText.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("street"));
        hausnummerAendernText.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("house.number"));
        familienstandAendernText.setText(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("marital.status"));
        mailAendernText.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("e.mail.address"));
        telefonnummerAendernText.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("phone.number"));
        staatsangehoerigkeitAendern.setText(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("nationality"));

        vornameAendern.setText(App.resourceBundle.getString("first.name")+" "+App.resourceBundle.getString("change"));
        nachnameAendern.setText(App.resourceBundle.getString("last.name")+" "+App.resourceBundle.getString("change"));
        geburtsdatumAendern.setText(App.resourceBundle.getString("date.of.birth")+" "+App.resourceBundle.getString("change"));
        passwortAendern.setText(App.resourceBundle.getString("password")+" "+App.resourceBundle.getString("change"));
        anredeAendern.setText(App.resourceBundle.getString("title")+" "+App.resourceBundle.getString("change"));
        plzAendern.setText(App.resourceBundle.getString("postcode")+" "+App.resourceBundle.getString("change"));
        ortAendern.setText(App.resourceBundle.getString("city")+" "+App.resourceBundle.getString("change"));
        strasseAendern.setText(App.resourceBundle.getString("street")+" "+App.resourceBundle.getString("change"));
        hausnummerAendern.setText(App.resourceBundle.getString("house.number")+" "+App.resourceBundle.getString("change"));
        familienstandAendern.setText(App.resourceBundle.getString("marital.status")+" "+App.resourceBundle.getString("change"));
        mailAendern.setText(App.resourceBundle.getString("change")+" "+App.resourceBundle.getString("e.mail.address"));
        telefonnummerAendern.setText(App.resourceBundle.getString("phone.number")+" "+App.resourceBundle.getString("change"));
        staatsangehoerigkeitAendern.setText(App.resourceBundle.getString("nationality")+" "+App.resourceBundle.getString("change"));

        if (angemeldetePerson != null) {
            RegistrierLayer.cbAnrede.setSelectedItem(angemeldetePerson.getAnrede());
            vornameNeu.setText(angemeldetePerson.getVorname());
            nachnameNeu.setText(angemeldetePerson.getNachname());
            plzNeu.setText(String.valueOf(angemeldetePerson.getPLZ()));
            ortNeu.setText(angemeldetePerson.getOrt());
            strasseNeu.setText(angemeldetePerson.getStrasse());
            hausnummerNeu.setText(angemeldetePerson.getHausnummer());
            mailNeu.setText(angemeldetePerson.getMailAdresse());
            telefonnummerNeu.setText("0" + angemeldetePerson.getTelefonnummer());
            RegistrierLayer.cbFamilienstand.setSelectedItem(angemeldetePerson.getFamilienstand());
            staatsangehoerigkeitNeu.setText(angemeldetePerson.getStaatsangehoerigkeit());

            BasicGeburtsdatum transfer = new BasicGeburtsdatum(angemeldetePerson.getGeburtsdatum());
            RegistrierLayer.GeburtsdatumTag.setSelectedItem(transfer.getGeburtsdatumTag());
            RegistrierLayer.GeburtsdatumMonat.setSelectedItem(transfer.getGeburtsdatumMonat());
            RegistrierLayer.GeburtsdatumJahr.setSelectedItem(transfer.getGeburtsdatumJahr());
        }
    }
/**
* Die folgenden @Override Methoden legen fest wie sich das Layout ändert, wenn der Nutzer seine Informationen ändert.
**/
    @Override
    public void setFrame() {
        StartLayer.fenster.setSize(1300,500);
    }

    @Override
    public void setLayout() {
        einstellungen.setLayout(new GridLayout(0,1));
        datenAendern.setLayout(new GridLayout(0,3));
        geburtsdatum.setLayout(new GridLayout(0,5));
    }

    @Override
    public void addListeners() {
        vornameAendern.addActionListener(e->vornameAendernWurdeAusgewaelt());
        nachnameAendern.addActionListener(e->nachnameAendernWurdeAusgewaelt());
        geburtsdatumAendern.addActionListener(e->geburtsdatumAendernWurdeAusgewaelt());
        passwortAendern.addActionListener(e->passwortAendernWurdeAusgewaelt());
        anredeAendern.addActionListener(e->anredeAendernWurdeAusgewaelt());
        plzAendern.addActionListener(e->plzAendernWurdeAusgewaelt());
        ortAendern.addActionListener(e->ortAendernWurdeAusgewaelt());
        strasseAendern.addActionListener(e->strasseAendernWurdeAusgewaelt());
        hausnummerAendern.addActionListener(e->hausnummerAendernWurdeAusgewaelt());
        familienstandAendern.addActionListener(e->familienstandAendernWurdeAusgewaelt());
        mailAendern.addActionListener(e->mailAendernWurdeAusgewaelt());
        staatsangehoerigkeitAendern.addActionListener(e->staatsangehoerigkeitAendernWurdeAusgewaelt());
        telefonnummerAendern.addActionListener(e->telefonnummerAendernWurdeAusgewaelt());
    }

    @Override
    public void addComponentsToPanels() {
        einstellungen.add(einstellungenText);
        einstellungen.add(veraenderungen);
        datenAendern.add(anredeAendernText);
        datenAendern.add(RegistrierLayer.cbAnrede);
        datenAendern.add(anredeAendern);
        datenAendern.add(vornameAendernText);
        datenAendern.add(vornameNeu);
        datenAendern.add(vornameAendern);
        datenAendern.add(nachnameAendernText);
        datenAendern.add(nachnameNeu);
        datenAendern.add(nachnameAendern);
        datenAendern.add(passwortAendernText);
        datenAendern.add(passwortNeu);
        datenAendern.add(passwortAendern);
        datenAendern.add(plzAendernText);
        datenAendern.add(plzNeu);
        datenAendern.add(plzAendern);
        datenAendern.add(ortAendernText);
        datenAendern.add(ortNeu);
        datenAendern.add(ortAendern);
        datenAendern.add(strasseAendernText);
        datenAendern.add(strasseNeu);
        datenAendern.add(strasseAendern);
        datenAendern.add(hausnummerAendernText);
        datenAendern.add(hausnummerNeu);
        datenAendern.add(hausnummerAendern);
        datenAendern.add(mailAendernText);
        datenAendern.add(mailNeu);
        datenAendern.add(mailAendern);
        datenAendern.add(telefonnummerAendernText);
        datenAendern.add(telefonnummerNeu);
        datenAendern.add(telefonnummerAendern);
        datenAendern.add(familienstandAendernText);
        datenAendern.add(RegistrierLayer.cbFamilienstand);
        datenAendern.add(familienstandAendern);
        datenAendern.add(staatsangehoerigkeitAendernText);
        datenAendern.add(staatsangehoerigkeitNeu);
        datenAendern.add(staatsangehoerigkeitAendern);
        geburtsdatum.add(geburtsdatumAendernText);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumTag);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumMonat);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumJahr);
        geburtsdatum.add(geburtsdatumAendern);
    }

    @Override
    public void addPanelsToFrame() {
        StartLayer.fenster.add(einstellungen);
        StartLayer.fenster.add(datenAendern);
        StartLayer.fenster.add(geburtsdatum);
    }

    @Override
    public void removePanelsFromFrame() {
        StartLayer.fenster.remove(einstellungen);
        StartLayer.fenster.remove(datenAendern);
        StartLayer.fenster.remove(geburtsdatum);
    }

    void vornameAendernWurdeAusgewaelt(){
        angemeldetePerson.setVorname(vornameNeu.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "first_name", angemeldetePerson.getVorname());
    }
    void ortAendernWurdeAusgewaelt(){
        angemeldetePerson.setOrt(ortNeu.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "city", angemeldetePerson.getOrt());
    }
    void nachnameAendernWurdeAusgewaelt(){
        angemeldetePerson.setNachname(nachnameNeu.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "last_name", angemeldetePerson.getNachname());
    }
    void geburtsdatumAendernWurdeAusgewaelt(){
        angemeldetePerson.setGeburtsdatum(new BasicGeburtsdatum((int) RegistrierLayer.GeburtsdatumTag.getSelectedItem(), (int) RegistrierLayer.GeburtsdatumMonat.getSelectedItem(), (int) RegistrierLayer.GeburtsdatumJahr.getSelectedItem()));
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "birth_date", angemeldetePerson.getGeburtsdatum());
    }
    void passwortAendernWurdeAusgewaelt(){
        angemeldetePerson.setPasswort(passwortNeu.getText());
        DATABASE.update_password_by_id(angemeldetePerson.getSozialversicherungsnummer(), angemeldetePerson.getPasswort());
    }
    void anredeAendernWurdeAusgewaelt(){
        angemeldetePerson.setAnrede(RegistrierLayer.cbAnrede.getSelectedItem().toString());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "form_of_address", angemeldetePerson.getAnrede());
    }
    void plzAendernWurdeAusgewaelt(){
        angemeldetePerson.setPLZ(Integer.parseInt(plzNeu.getText()));
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "zip_code", angemeldetePerson.getPLZ());
    }
    void strasseAendernWurdeAusgewaelt(){
        angemeldetePerson.setStrasse(strasseNeu.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "street", angemeldetePerson.getStrasse());
    }
    void hausnummerAendernWurdeAusgewaelt(){
        angemeldetePerson.setHausnummer(hausnummerNeu.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "house_number", angemeldetePerson.getHausnummer());
    }
    void familienstandAendernWurdeAusgewaelt(){
        angemeldetePerson.setFamilienstand(RegistrierLayer.cbFamilienstand.getSelectedItem().toString());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "marital_status", angemeldetePerson.getFamilienstand());
    }
    void mailAendernWurdeAusgewaelt(){
        angemeldetePerson.setMailAdresse(mailNeu.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "email_address", angemeldetePerson.getMailAdresse());
    }
    void telefonnummerAendernWurdeAusgewaelt(){
        angemeldetePerson.setTelefonnummer(Long.parseLong(telefonnummerNeu.getText()));
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "phone_number", angemeldetePerson.getTelefonnummer());
    }
    void staatsangehoerigkeitAendernWurdeAusgewaelt(){
        angemeldetePerson.setStaatsangehoerigkeit(staatsangehoerigkeitNeu.getText());
        DATABASE.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "nationality", angemeldetePerson.getStaatsangehoerigkeit());
    }
/**
* Wenn der Nutzer die Daten seiner Person ändert, ermöglicht diese Klasse es die Änderungen in der Datenbank zu speichern
**/

}
