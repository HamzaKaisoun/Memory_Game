import javax.swing.JFrame;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Beschreiben Sie hier die Klasse GameStarter.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Game extends JFrame implements ActionListener// JFrame und enthält constructor // extends ist vererbung vom Jframe
{
    private JPanel panel= new JPanel();// Game
    private JPanel standsPanel= new JPanel();// panel wo zuege und spieler.....
    private JPanel mainPanel= new JPanel();// hauptPanel
    private JPanel punktePanel= new JPanel();
    private JPanel spielerPanel= new JPanel();
    private JPanel zuegePanel= new JPanel();
    private JButton buttons[];// Button array
    private JMenuBar menuZeile;// Bar (Menübar wo datei ist)
    private JMenuItem beenden,neuesSpiel;// Menüitem ist der Platz wo beenden und neuesspiel sind 
    private  int row,colm;// Zeile und Spalte
    private boolean firstMove=true;// Variable für den ersten zug
    private boolean visible=false;// Variable
    private  int anzahlFelder;// 4*6 oder 6*8
    private int index1, index2;
    private int zahlen[];// zahlen werden eindemonsional in Array gespeichert
    private boolean buttonStatus[];// Status der button ceken ob die sichtbar sind 
    private JLabel amSpielen; // Spieler
    private int spielerAnzahl;
    private int werIstDran=1;// wer ist dran
    private int zuege=0;
    private int[] ergebnis;
    private JLabel anzahlZuege;
    private JLabel spielerPunkte[];

    Game(String bezeichnung,int row, int colm,int spielerAnzahl)//Constructor
    {
        int anzahlFelder=row*colm;// Anzahl der felder 
        this.row=row;//zeilen speichern
        this.colm=colm;//spalten
        this.anzahlFelder=anzahlFelder; // speichern 
        this.spielerAnzahl=spielerAnzahl;

        buttons= new JButton[anzahlFelder];// buttons ohne nummer 4*6 erstellen
        buttonStatus=new boolean[anzahlFelder]; // array erstellen um status zu speichern
        spielerPunkte= new JLabel[spielerAnzahl];//array aus JLabel und der Anzahl von den sind die Spieler

        mainPanel.setLayout(new BorderLayout());//Methode um strucktur vom noth west
        standsPanel.setLayout(new BorderLayout());//
        panel.setLayout(new GridLayout(row,colm,6,6));// Abmaße vom Feld

        standsPanel.setBorder(BorderFactory.createLineBorder(Color.
                BLACK));

        standsPanel.add(zuegePanel,BorderLayout.EAST); 
        standsPanel.add(punktePanel,BorderLayout.CENTER);
        standsPanel.add(spielerPanel,BorderLayout.WEST);

        mainPanel.add(standsPanel,BorderLayout.NORTH);
        mainPanel.add(panel,BorderLayout.CENTER);

        this.add(mainPanel);// bei Frame Main hinzufügen

        JLabel spieler= new JLabel("Spieler:");
        amSpielen= new JLabel("1");
        JLabel punkte= new JLabel("Punkte");
        this.ergebnis= new int[spielerAnzahl];
        JLabel zuege= new JLabel("Züge:");
        anzahlZuege= new JLabel("0");

        spielerPanel.add(spieler);
        spielerPanel.add(amSpielen);
        punktePanel.add(punkte);
        // um 0: hinzufügen 
        for(int i=0; i<spielerAnzahl; i++)
        {
            spielerPunkte[i]= new JLabel(": 0");
            punktePanel.add(spielerPunkte[i]);
        }
        zuegePanel.add(zuege);
        zuegePanel.add(anzahlZuege);

        menuZeile = new JMenuBar();// objekt
        setJMenuBar(menuZeile);// add von zeile menübar
        JMenu datei= new JMenu("Datei");
        neuesSpiel= new JMenuItem("Neues Spiel");
        beenden= new JMenuItem("Beenden");

        menuZeile.add(datei);
        datei.add(neuesSpiel);
        datei.add(beenden);

        beenden.addActionListener(this);
        neuesSpiel.addActionListener(this);

        zahlen= arrayMaker();// Methode zu Array erstellung die sind  Array von 1 bis 12 und dann 1 bis 12
        shuffle(zahlen);// Mischung von Array werte
        for(int i=0; i<anzahlFelder;i++)//um Zahlen in button einzufügen für alle felder
        {
            String value =""+zahlen[i];
            buttons[i] = new JButton(value);// um zahlen einzufügen 

            panel.add(buttons[i]);
            buttons[i].addActionListener(this);// für jeder buuton Actionlistner
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 20));// jeder buuton schrift größe geben
        }


        setTitle(bezeichnung);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true); 
    }

    public void actionPerformed(ActionEvent e)//Methode 
    {

        if (e.getSource()==beenden)
        {
            System.exit(0);
        }
        if (e.getSource()==neuesSpiel)
        {
            new GameStarter("New Memory Game",this);// this um Game zu schließen 
        }

        for (int i=0; i<anzahlFelder;i++)// um die felder vom Anfang bis zum Ende durchgehen
        {

            if (e.getSource()==buttons[i])// gucken auf welches feld ich gedrückt hatte 
            {
                
                if(firstMove==true)
                {
                    for(int j=0; j<anzahlFelder;j++)// forschleife um die Zahlen zu nicht sichtbar machen
                    {
                        buttons[j].setText(" ");
                    }
                    firstMove=false;
                }
                else
                {
                    if(buttonStatus[i]==false)// überprüfen ob der Status false(nicht gedrückt) Aber in diesem fall drücke ich das 
                    {
                        if(visible==true)// ist wieder true 
                        {
                            buttons[i].setText(""+zahlen[i]); // zahl zeigen
                            index2=i; // platznummer nehmen
                            visible=false; // weiter mit dem Code
                            if(sameValue())
                            {
                                buttonStatus[index2]=true;
                                ergebnis[werIstDran-1]+=1;// ein Punkt aktualisieren
                                spielerPunkte[werIstDran-1].setText(": "+ergebnis[werIstDran-1]);// ein Punkt aktualisieren
                                if(isGameFinished())
                                {
                                    new ResultClass(ergebnis,this);
                                }
                            }
                            else if(!sameValue())
                            {
                                werIstDran++;
                                if(werIstDran>=spielerAnzahl+1)
                                {
                                    werIstDran=1;
                                }
                                amSpielen.setText(""+werIstDran);// Spielernummer Aktualsieren
                                buttonStatus[index1]=false;
                            }
                            zuege++;// zug hinzufügen
                            anzahlZuege.setText(""+zuege);// zeigen bei züge 

                        }
                        else
                        {
                            if(!sameValue())// wenn die Zahlen nicht identisch sind wieder unsichtbar mAchen
                            {
                                buttons[index1].setText(" ");
                                buttons[index2].setText(" ");
                            }
                            
                            buttons[i].setText(""+zahlen[i]);// Zahl zeigen
                            index1=i;// Platznummer vom Array
                            buttonStatus[index1]=true;
                            visible=true;// es gibt eine nummer die Sichtbar ist
                        }
                    }
                }

            }
        }

    }

    private int[] arrayMaker() // um Array zu erstellen 1 bis 12 zwei teile
    {
        int zahlen[]=new int[anzahlFelder];
        for(int i=0; i<(anzahlFelder/2);i++)
        {
            zahlen[i]=i;
            zahlen[i+anzahlFelder/2]=i;
        }
        return zahlen;
    }

    private  void shuffle(int [] zahlen) {// mischung 
        Random random= new Random();
        int n=zahlen.length;
        while (n > 0) {  
            int r = random.nextInt(n--);
            int tmp = zahlen[r];
            zahlen[r] = zahlen[n];
            zahlen[n] = tmp;
        }
    }

    private boolean sameValue()// wird üperfrüft ob die zahlen gleich sind
    {
        return zahlen[index1]==zahlen[index2] && index1!=index2;
        //return false ob die zahlen ungelich sind
        //return true ob die zahlen gleich sind
        
    }

    private boolean isGameFinished()// methed lifert boolean wert züruck , und überprüft ob die Spiel beendet ist.
    //ob in dieButtonStatus Array mindestens eine false value hat, dann die spiel ist nicht beendet , ansonst ist 
    //die spiel beendet.
    {
        for(int i=0; i<anzahlFelder;i++)// die ganze Elemente durchgehen
        {
            if(!buttonStatus[i])
            {
                return false;
            }
        }
        return true;
    }

}
