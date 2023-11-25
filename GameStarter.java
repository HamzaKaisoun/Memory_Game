import javax.swing.JFrame; // Klassen aus Bibliothek Imoprtien?
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javafx.scene.control.RadioButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JTextField;
import java.awt.*;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

import javax.swing.border.EmptyBorder;

/**
 * Beschreiben Sie hier die Klasse GameStarter.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class GameStarter extends JFrame implements ActionListener//fenster Start Memory game vererben
{
    private JPanel mainPanel;//Objekte
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel schwierigkeitsPanel;
    private JPanel radioButtonsPanel;
    private JRadioButton radioButton1;
    private  JRadioButton radioButton2;

    private JLabel schwierigkeitsLabel;
    private JLabel spielerAnzahlsLabel2;

    private String text;

    private JTextField textFeld;
    private JButton start;
    private JButton beenden;
    private JButton abbrechen;
    private String bezeichnung;

    private JFrame gameFrame;

    GameStarter(String bezeichnung)// 1 struckt constructor 
    {
        this.bezeichnung=bezeichnung; // Parameter Bezeichnung in variable bezeichnung speichern "Start Game"
        mainPanel= new JPanel(new GridLayout(3,0));//3 zeilen 
        this.add(mainPanel);//Main panel zu jframe hinfügen

        panel1= new JPanel(new FlowLayout(FlowLayout.CENTER));// Reihnfloge
        panel2= new JPanel(new FlowLayout(FlowLayout.CENTER) );
        panel3= new JPanel(new GridLayout(2,0));

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        mainPanel.setBorder(BorderFactory.createLineBorder(Color.
                BLACK));// schwarze grenzen

        
        schwierigkeitsLabel = new JLabel("Schwierigkeit:");// Text
        panel1.add(schwierigkeitsLabel);

        radioButton1 = new JRadioButton("4x6");
        radioButton2 = new JRadioButton("6x8");
        radioButton1.setSelected(true);// hier ist der Anfang des Spieles

        radioButton1.addActionListener(this);// damit er die Anweisung dürchführt
        radioButton2.addActionListener(this);

        panel1.add(radioButton1);
        panel1.add(radioButton2);

        spielerAnzahlsLabel2 = new JLabel("Spieleranzahl:");
        panel2.add(spielerAnzahlsLabel2);

        textFeld = new JTextField(2);//textfeld um ein text einzugeben
        panel2.add(textFeld);
        // textFeld.setColumns(2);//Methode um die Anzahl der Spalten eingeben(Breite)

        start = new JButton("Start New Game");
        beenden = new JButton("Beenden");

        beenden.addActionListener(this);
        start.addActionListener(this);

        panel3.add(start);
        panel3.add(beenden);

        setTitle(bezeichnung); // titel für fenster geben
        setSize(300, 300);// Abmaße vom fenster
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fenster bei x schließen
        setResizable(false);// damit der fenster nicht verkleinert oder vergrößert
        setVisible(true);//  Fenster zeigen
    }

    GameStarter(String bezeichnung,JFrame gameFrame)// Zewiter constructor, wenn man zwei parametern gibt, wird dann diesen constructor vervwendet
    {
        this.gameFrame=gameFrame;// Jframe gameframe hier speichern
        gameFrame.setVisible(false);// diese gameframe nicht sichtbar
        this.bezeichnung=bezeichnung;
        mainPanel= new JPanel(new GridLayout(3,0));
        this.add(mainPanel);

        panel1= new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel2= new JPanel(new FlowLayout(FlowLayout.CENTER) );
        panel3= new JPanel(new GridLayout(2,0));

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        mainPanel.setBorder(BorderFactory.createLineBorder(Color.
                BLACK));

        
        schwierigkeitsLabel = new JLabel("Schwierigkeit:");
        panel1.add(schwierigkeitsLabel);

        radioButton1 = new JRadioButton("4x6");
        radioButton2 = new JRadioButton("6x8");
        radioButton1.setSelected(true);

        radioButton1.addActionListener(this);
        radioButton2.addActionListener(this);

        panel1.add(radioButton1);
        panel1.add(radioButton2);

        spielerAnzahlsLabel2 = new JLabel("Spieleranzahl:");
        panel2.add(spielerAnzahlsLabel2);

        textFeld = new JTextField(1);
        panel2.add(textFeld);
        textFeld.setColumns(2);

        start = new JButton("Start New Game");
        abbrechen = new JButton("Abbrechen");

        abbrechen.addActionListener(this);
        start.addActionListener(this);

        panel3.add(start);
        panel3.add(abbrechen);

        setTitle(bezeichnung);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);// fenster anzeigen
    }

    public void actionPerformed(ActionEvent e)//Anweisung für buuton..... durchführen(Methode)
    {

        if (e.getSource()==beenden)// um zu wissen was gedrückt wurde(Funktion für beenden eingeben damit sie schließt)
        {
            System.exit(0);
        }
        if (e.getSource()==abbrechen)
        {
            this.setVisible(false);// Game starter schlißen 
            gameFrame.setVisible(true);// zurück zum Spiel
        }
        if (e.getSource()==start)
        {
            text = textFeld.getText(); // text Eingabe speichern
            int spielerAnzahl=0;
            boolean exceptionChecker=false; 
            // Try Fehlerbehandlung block
            try {
                spielerAnzahl= Integer.valueOf(text);// Methode um ein String zu integer zu konvetieren
                // wenn das nicht klappt gehe zu catch fehlermeldung 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Nur Zahlen 1-4 eingeben!", 
                    "Warnung", JOptionPane.WARNING_MESSAGE);
                exceptionChecker=true;// wenn der Text nicht integer

            }

            if(spielerAnzahl>4 || spielerAnzahl<1)
            {
                if(!exceptionChecker)// wenn der Zahl <4>1 auch Fehlermeldung
                {
                    JOptionPane.showMessageDialog(null, "Nur Zahlen 1-4 eingeben!", 
                        "Warunung", JOptionPane.WARNING_MESSAGE);
                }
            }
            else 
            {
                if(radioButton1.isSelected())
                {
                    new Game(bezeichnung,4,6,spielerAnzahl);// Verbindung zum Spiel
                }else
                {
                    new Game(bezeichnung,6,8,spielerAnzahl);
                }
                this.setVisible(false);
            }

            
        }
        
        if (e.getSource()==radioButton1)// damit ich sicher steller dass nur ein radiobutton ausgewählt wurde
        {
            radioButton1.setSelected(true);
            radioButton2.setSelected(false);
        }
        if (e.getSource()==radioButton2)
        {
            radioButton2.setSelected(true);
            radioButton1.setSelected(false);
        }

    }

}

 
