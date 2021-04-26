package atdit_ibait_20.database.presentation.implementation;

import atdit_ibait_20.database.App;
import atdit_ibait_20.database.model.Person;
import atdit_ibait_20.database.model.implementation.BasicGeburtsdatum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Einstellungen {
    JPanel einstellungen = new JPanel();
    JPanel datenAendern = new JPanel();
    JPanel geburtsdatum = new JPanel();

    JLabel einstellungenText = new JLabel(App.resourceBundle.getString("welcome.to.your.settings"));
    JLabel veraenderungen = new JLabel(App.resourceBundle.getString("make.changes.here"));
    JLabel vornameAendernText = new JLabel(App.resourceBundle.getString("new.1")+App.resourceBundle.getString("first.name"));
    JLabel nachnameAendernText = new JLabel(App.resourceBundle.getString("new.1")+App.resourceBundle.getString("last.name"));
    JLabel geburtsdatumAendernText = new JLabel(App.resourceBundle.getString("new.2")+App.resourceBundle.getString("date.of.birth"));
    JLabel passwortAendernText = new JLabel(App.resourceBundle.getString("new.2")+App.resourceBundle.getString("password"));
    JLabel anredeAendernText = new JLabel(App.resourceBundle.getString("new.3")+App.resourceBundle.getString("title"));
    JLabel plzAendernText = new JLabel(App.resourceBundle.getString("new.3")+App.resourceBundle.getString("postcode"));
    JLabel ortAendernText = new JLabel(App.resourceBundle.getString("new.1")+App.resourceBundle.getString("city"));
    JLabel strasseAendernText = new JLabel(App.resourceBundle.getString("new.3")+App.resourceBundle.getString("street"));
    JLabel hausnummerAendernText = new JLabel(App.resourceBundle.getString("new.3")+App.resourceBundle.getString("house.number"));
    JLabel familienstandAendernText = new JLabel(App.resourceBundle.getString("new.1")+App.resourceBundle.getString("marital.status"));
    JLabel mailAendernText = new JLabel(App.resourceBundle.getString("new.3")+App.resourceBundle.getString("e.mail.address"));
    JLabel telefonnummerAendernText = new JLabel(App.resourceBundle.getString("new.3")+App.resourceBundle.getString("phone.number"));
    JLabel staatsangehörigkeitAendernText = new JLabel(App.resourceBundle.getString("new.3")+App.resourceBundle.getString("nationality"));

    JTextField vornameNeu = new JTextField();
    JTextField nachnameNeu = new JTextField();
    JTextField passwortNeu = new JTextField();
    JTextField anredeNeu = new JTextField();
    JTextField plzNeu = new JTextField();
    JTextField ortNeu = new JTextField();
    JTextField strasseNeu = new JTextField();
    JTextField hausnummerNeu = new JTextField();
    JTextField telefonnummerNeu = new JTextField();
    JTextField familienstandNeu = new JTextField();
    JTextField mailNeu = new JTextField();
    JTextField staatsangehoerigkeitNeu = new JTextField();

    JButton vornameAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("first.name"));
    JButton nachnameAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("last.name"));
    JButton geburtsdatumAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("date.of.birth"));
    JButton passwortAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("password"));
    JButton anredeAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("title"));
    JButton plzAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("postcode"));
    JButton ortAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("city"));
    JButton strasseAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("street"));
    JButton hausnummerAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("house.number"));
    JButton familienstandAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("marital.status"));
    JButton mailAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("e.mail.address"));
    JButton telefonnummerAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("phone.number"));
    JButton staatsangehörigkeitAendern = new JButton(App.resourceBundle.getString("change")+App.resourceBundle.getString("nationality"));

    Person angemeldetePerson;
    public Einstellungen(Person person){
        angemeldetePerson = person;
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
        datenAendern.add(familienstandNeu);
        datenAendern.add(familienstandAendern);
        datenAendern.add(staatsangehörigkeitAendernText);
        datenAendern.add(staatsangehoerigkeitNeu);
        datenAendern.add(strasseAendern);
        geburtsdatum.add(geburtsdatumAendernText);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumTag);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumMonat);
        geburtsdatum.add(RegistrierLayer.GeburtsdatumJahr);
        geburtsdatum.add(geburtsdatumAendern);




    }
    class aendern implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(vornameAendern))
                angemeldetePerson.setVorname(vornameNeu.getText());
            if(e.getSource().equals(nachnameAendern))
                angemeldetePerson.setNachname(nachnameNeu.getText());
            if(e.getSource().equals(geburtsdatumAendern))
                angemeldetePerson.setGeburtsdatum(new BasicGeburtsdatum((int) RegistrierLayer.GeburtsdatumTag.getSelectedItem(),(int)RegistrierLayer.GeburtsdatumMonat.getSelectedItem(),(int)RegistrierLayer.GeburtsdatumJahr.getSelectedItem()));
            if(e.getSource().equals(passwortAendern))
                angemeldetePerson.setPasswort(passwortNeu.getText());
            if(e.getSource().equals(anredeAendern))
                angemeldetePerson.setAnrede(anredeNeu.getText());
            if(e.getSource().equals(plzAendern))
                angemeldetePerson.setPLZ(Integer.parseInt(plzNeu.getText()));
            if(e.getSource().equals(ortAendern))
                angemeldetePerson.setOrt(ortNeu.getText());
            if(e.getSource().equals(strasseAendern))
                angemeldetePerson.setStrasse(strasseNeu.getText());
            if(e.getSource().equals(hausnummerAendern))
                angemeldetePerson.setHausnummer(hausnummerNeu.getText());
            if(e.getSource().equals(familienstandAendern))
                angemeldetePerson.setFamilienstand(familienstandNeu.getText());
            if(e.getSource().equals(mailAendern))
                angemeldetePerson.setMailAdresse(mailNeu.getText());
            if(e.getSource().equals(telefonnummerAendern))
                angemeldetePerson.setTelefonnummer(Integer.parseInt(telefonnummerNeu.getText()));
            if(e.getSource().equals(staatsangehörigkeitAendern))
                angemeldetePerson.setStaatsangehoerigkeit(staatsangehoerigkeitNeu.getText());
        }
    }
}
