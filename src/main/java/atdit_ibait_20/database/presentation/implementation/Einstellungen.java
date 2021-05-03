package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicDatabase;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Einstellungen {
    static final JPanel einstellungen = new JPanel();
    static final JPanel datenAendern = new JPanel();
    static final JPanel geburtsdatum = new JPanel();

    JLabel einstellungenText = new JLabel(App.resourceBundle.getString("welcome.to.your.settings"));
    JLabel veraenderungen = new JLabel(App.resourceBundle.getString("make.changes.here"));
    JLabel vornameAendernText = new JLabel(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("first.name"));
    JLabel nachnameAendernText = new JLabel(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("last.name"));
    JLabel geburtsdatumAendernText = new JLabel(App.resourceBundle.getString("new.2")+" "+App.resourceBundle.getString("date.of.birth"));
    JLabel passwortAendernText = new JLabel(App.resourceBundle.getString("new.2")+" "+App.resourceBundle.getString("password"));
    JLabel anredeAendernText = new JLabel(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("title"));
    JLabel plzAendernText = new JLabel(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("postcode"));
    JLabel ortAendernText = new JLabel(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("city"));
    JLabel strasseAendernText = new JLabel(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("street"));
    JLabel hausnummerAendernText = new JLabel(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("house.number"));
    JLabel familienstandAendernText = new JLabel(App.resourceBundle.getString("new.1")+" "+App.resourceBundle.getString("marital.status"));
    JLabel mailAendernText = new JLabel(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("e.mail.address"));
    JLabel telefonnummerAendernText = new JLabel(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("phone.number"));
    JLabel staatsangehörigkeitAendernText = new JLabel(App.resourceBundle.getString("new.3")+" "+App.resourceBundle.getString("nationality"));

    JTextField vornameNeu = new JTextField();
    JTextField nachnameNeu = new JTextField();
    JTextField passwortNeu = new JPasswordField();
    JTextField anredeNeu = new JTextField();
    JTextField plzNeu = new JTextField();
    JTextField ortNeu = new JTextField();
    JTextField strasseNeu = new JTextField();
    JTextField hausnummerNeu = new JTextField();
    JTextField telefonnummerNeu = new JTextField();
    JTextField mailNeu = new JTextField();
    JTextField staatsangehoerigkeitNeu = new JTextField();

    JButton vornameAendern = new JButton(App.resourceBundle.getString("first.name")+" "+App.resourceBundle.getString("change"));
    JButton nachnameAendern = new JButton(App.resourceBundle.getString("last.name")+" "+App.resourceBundle.getString("change"));
    JButton geburtsdatumAendern = new JButton(App.resourceBundle.getString("date.of.birth")+" "+App.resourceBundle.getString("change"));
    JButton passwortAendern = new JButton(App.resourceBundle.getString("password")+" "+App.resourceBundle.getString("change"));
    JButton anredeAendern = new JButton(App.resourceBundle.getString("title")+" "+App.resourceBundle.getString("change"));
    JButton plzAendern = new JButton(App.resourceBundle.getString("postcode")+" "+App.resourceBundle.getString("change"));
    JButton ortAendern = new JButton(App.resourceBundle.getString("city")+" "+App.resourceBundle.getString("change"));
    JButton strasseAendern = new JButton(App.resourceBundle.getString("street")+" "+App.resourceBundle.getString("change"));
    JButton hausnummerAendern = new JButton(App.resourceBundle.getString("house.number")+" "+App.resourceBundle.getString("change"));
    JButton familienstandAendern = new JButton(App.resourceBundle.getString("marital.status")+" "+App.resourceBundle.getString("change"));
    JButton mailAendern = new JButton(App.resourceBundle.getString("change")+" "+App.resourceBundle.getString("e.mail.address"));
    JButton telefonnummerAendern = new JButton(App.resourceBundle.getString("phone.number")+" "+App.resourceBundle.getString("change"));
    JButton staatsangehörigkeitAendern = new JButton(App.resourceBundle.getString("nationality")+" "+App.resourceBundle.getString("change"));

    Person angemeldetePerson;
    public Einstellungen(Person person){
        angemeldetePerson = person;

        RegistrierLayer.cbAnrede.setSelectedItem(angemeldetePerson.getAnrede());
        vornameNeu.setText(angemeldetePerson.getVorname());
        nachnameNeu.setText(angemeldetePerson.getNachname());
        plzNeu.setText(String.valueOf(angemeldetePerson.getPLZ()));
        ortNeu.setText(angemeldetePerson.getOrt());
        strasseNeu.setText(angemeldetePerson.getStrasse());
        hausnummerNeu.setText(angemeldetePerson.getHausnummer());
        mailNeu.setText(angemeldetePerson.getMailAdresse());
        telefonnummerNeu.setText("0" + String.valueOf(angemeldetePerson.getTelefonnummer()));
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
        staatsangehörigkeitAendern.addActionListener(new aendern());
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
        datenAendern.add(staatsangehörigkeitAendernText);
        datenAendern.add(staatsangehoerigkeitNeu);
        datenAendern.add(staatsangehörigkeitAendern);
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
    class aendern implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(vornameAendern)) {
                angemeldetePerson.setVorname(vornameNeu.getText());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"first_name",angemeldetePerson.getVorname());
            }
            if(e.getSource().equals(nachnameAendern)) {
                angemeldetePerson.setNachname(nachnameNeu.getText());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"last_name",angemeldetePerson.getNachname());
            }
            if(e.getSource().equals(geburtsdatumAendern)) {
                angemeldetePerson.setGeburtsdatum(new BasicGeburtsdatum((int) RegistrierLayer.GeburtsdatumTag.getSelectedItem(), (int) RegistrierLayer.GeburtsdatumMonat.getSelectedItem(), (int) RegistrierLayer.GeburtsdatumJahr.getSelectedItem()));
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"birth_date",angemeldetePerson.getGeburtsdatum());
            }
            if(e.getSource().equals(passwortAendern)) {
                angemeldetePerson.setPasswort(passwortNeu.getText());
                BasicDatabase.update_password_by_id(angemeldetePerson.getSozialversicherungsnummer(),angemeldetePerson.getPasswort());
            }
            if(e.getSource().equals(anredeAendern)) {
                angemeldetePerson.setAnrede(RegistrierLayer.cbAnrede.getSelectedItem().toString());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "form_of_address", angemeldetePerson.getAnrede());
            }
            if(e.getSource().equals(plzAendern)) {
                angemeldetePerson.setPLZ(Integer.parseInt(plzNeu.getText()));
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "zip_code", angemeldetePerson.getPLZ());
            }
            if(e.getSource().equals(ortAendern)) {
                angemeldetePerson.setOrt(ortNeu.getText());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(),"city",angemeldetePerson.getOrt());
            }
            if(e.getSource().equals(strasseAendern)) {
                angemeldetePerson.setStrasse(strasseNeu.getText());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "street", angemeldetePerson.getStrasse());
            }
            if(e.getSource().equals(hausnummerAendern)) {
                angemeldetePerson.setHausnummer(hausnummerNeu.getText());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "house_number", angemeldetePerson.getHausnummer());
            }
            if(e.getSource().equals(familienstandAendern)) {
                angemeldetePerson.setFamilienstand(RegistrierLayer.cbFamilienstand.getSelectedItem().toString());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "marital_status", angemeldetePerson.getFamilienstand());
            }
            if(e.getSource().equals(mailAendern)) {
                angemeldetePerson.setMailAdresse(mailNeu.getText());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "email_address", angemeldetePerson.getMailAdresse());
            }
            if(e.getSource().equals(telefonnummerAendern)) {
                angemeldetePerson.setTelefonnummer(Integer.parseInt(telefonnummerNeu.getText()));
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "phone_number", angemeldetePerson.getTelefonnummer());
            }
            if(e.getSource().equals(staatsangehörigkeitAendern)) {
                angemeldetePerson.setStaatsangehoerigkeit(staatsangehoerigkeitNeu.getText());
                BasicDatabase.update_person_by_id(angemeldetePerson.getSozialversicherungsnummer(), "nationality", angemeldetePerson.getStaatsangehoerigkeit());
            }
        }
    }
}
