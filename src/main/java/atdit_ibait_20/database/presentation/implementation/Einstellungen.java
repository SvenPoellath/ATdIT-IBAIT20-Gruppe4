package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.persistence.implementation.DatabaseService;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Einstellungen {
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

    JTextField vornameNeu = new JTextField();
    JTextField nachnameNeu = new JTextField();
    JTextField passwortNeu = new JPasswordField();
    JTextField plzNeu = new JTextField();
    JTextField ortNeu = new JTextField();
    JTextField strasseNeu = new JTextField();
    JTextField hausnummerNeu = new JTextField();
    JTextField telefonnummerNeu = new JTextField();
    JTextField mailNeu = new JTextField();
    JTextField staatsangehoerigkeitNeu = new JTextField();

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

    Person angemeldetePerson;
    public Einstellungen(Person person){
        setStringsInEinstellungen();
        angemeldetePerson = person;

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

        vornameAendern.addActionListener(new aendern());
        nachnameAendern.addActionListener(new aendern());
        geburtsdatumAendern.addActionListener(new aendern());
        passwortAendern.addActionListener(new aendern());
        anredeAendern.addActionListener(new aendern());
        plzAendern.addActionListener(new aendern());
        ortAendern.addActionListener(new aendern());
        strasseAendern.addActionListener(new aendern());
        hausnummerAendern.addActionListener(new aendern());
        familienstandAendern.addActionListener(new aendern());
        mailAendern.addActionListener(new aendern());
        staatsangehoerigkeitAendern.addActionListener(new aendern());
        telefonnummerAendern.addActionListener(new aendern());

        einstellungen.setLayout(new GridLayout(0,1));
        datenAendern.setLayout(new GridLayout(0,3));
        geburtsdatum.setLayout(new GridLayout(0,5));

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

        StartLayer.fenster.setSize(1300,500);
        StartLayer.fenster.add(einstellungen);
        StartLayer.fenster.add(datenAendern);
        StartLayer.fenster.add(geburtsdatum);


    }
    static void setStringsInEinstellungen(){
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
    }
    class aendern implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(vornameAendern)) {
                angemeldetePerson.setVorname(vornameNeu.getText());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"first_name",angemeldetePerson.getVorname());
            }
            if(e.getSource().equals(nachnameAendern)) {
                angemeldetePerson.setNachname(nachnameNeu.getText());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"last_name",angemeldetePerson.getNachname());
            }
            if(e.getSource().equals(geburtsdatumAendern)) {
                angemeldetePerson.setGeburtsdatum(new BasicGeburtsdatum((int) RegistrierLayer.GeburtsdatumTag.getSelectedItem(), (int) RegistrierLayer.GeburtsdatumMonat.getSelectedItem(), (int) RegistrierLayer.GeburtsdatumJahr.getSelectedItem()));
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"birth_date",angemeldetePerson.getGeburtsdatum());
            }
            if(e.getSource().equals(passwortAendern)) {
                angemeldetePerson.setPasswort(passwortNeu.getText());
                DatabaseService.update_password_by_id(angemeldetePerson.getSozialversicherungsnummer(),angemeldetePerson.getPasswort());
            }
            if(e.getSource().equals(anredeAendern)) {
                angemeldetePerson.setAnrede(RegistrierLayer.cbAnrede.getSelectedItem().toString());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "form_of_address", angemeldetePerson.getAnrede());
            }
            if(e.getSource().equals(plzAendern)) {
                angemeldetePerson.setPLZ(Integer.parseInt(plzNeu.getText()));
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "zip_code", angemeldetePerson.getPLZ());
            }
            if(e.getSource().equals(ortAendern)) {
                angemeldetePerson.setOrt(ortNeu.getText());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"city",angemeldetePerson.getOrt());
            }
            if(e.getSource().equals(strasseAendern)) {
                angemeldetePerson.setStrasse(strasseNeu.getText());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "street", angemeldetePerson.getStrasse());
            }
            if(e.getSource().equals(hausnummerAendern)) {
                angemeldetePerson.setHausnummer(hausnummerNeu.getText());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "house_number", angemeldetePerson.getHausnummer());
            }
            if(e.getSource().equals(familienstandAendern)) {
                angemeldetePerson.setFamilienstand(RegistrierLayer.cbFamilienstand.getSelectedItem().toString());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "marital_status", angemeldetePerson.getFamilienstand());
            }
            if(e.getSource().equals(mailAendern)) {
                angemeldetePerson.setMailAdresse(mailNeu.getText());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "email_address", angemeldetePerson.getMailAdresse());
            }
            if(e.getSource().equals(telefonnummerAendern)) {
                angemeldetePerson.setTelefonnummer(Integer.parseInt(telefonnummerNeu.getText()));
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "phone_number", angemeldetePerson.getTelefonnummer());
            }
            if(e.getSource().equals(staatsangehoerigkeitAendern)) {
                angemeldetePerson.setStaatsangehoerigkeit(staatsangehoerigkeitNeu.getText());
                DatabaseService.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "nationality", angemeldetePerson.getStaatsangehoerigkeit());
            }
        }
    }
}
