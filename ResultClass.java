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
 * Klasse ResulClass.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class ResultClass  extends JFrame implements ActionListener//Jframe
{
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private int[] ergebnis;
    private JFrame gameFrame;

    private JLabel gewinnerLabel;
    private int gridLength=0;
    private JPanel[] punktePanel;

    private JButton beenden;
    private JButton newGame;

    public ResultClass(int[] ergebnis,JFrame gameFrame)//constructor
    {
        this.gameFrame=gameFrame;
        this.ergebnis=ergebnis;

        this.gridLength=ergebnis.length+1;// Anzahl der Spieler + 1(punktzustand)
        punktePanel= new JPanel[gridLength];// Array 
        mainPanel= new JPanel(new GridLayout(3,0));
        this.add(mainPanel);
        panel1= new JPanel();
        panel2= new JPanel(new GridLayout(gridLength,0));
        panel3= new JPanel(new GridLayout(2,0));

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        gewinnerLabel= new JLabel(gewinnerFilter());
      
        panel1.add(gewinnerLabel);

        JLabel punkteLabel= new JLabel("Punktestand:");
      
        punktePanel[0]= new JPanel();// punktePanel erstellen an der ersten Stelle 
        panel2.add(punktePanel[0]); // in panel 2 hinfügen 
        punktePanel[0].add(punkteLabel);// in Punktepanel Punktelabel hinfügen

        for(int i=1; i<gridLength;i++)// um Jpanel für die Spieler zu erstellen
        {
            punktePanel[i]= new JPanel();
            panel2.add(punktePanel[i]);
        }

        JLabel spielerLabel[]= new JLabel[gridLength];// Array für JLabel von Spieler 1und 2

        for(int i=1; i<gridLength;i++)// um spieler und Ergebnisse hinfügen
        {
            spielerLabel[i-1]=new JLabel("Spieler "+i+" : "+ergebnis[i-1]);
            punktePanel[i].add(spielerLabel[i-1]);

        }

        newGame = new JButton("New Game");
        beenden = new JButton("Beenden");

        panel3.add(newGame);
        panel3.add(beenden);

        beenden.addActionListener(this);
        newGame.addActionListener(this);

        setTitle("");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true); 
    }

    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource()==beenden)
        {
            System.exit(0);
        }
        if (e.getSource()==newGame)
        {
            
            new GameStarter("Start New Game");
            this.gameFrame.setVisible(false);
            this.setVisible(false);
        }

    }

    public String gewinnerFilter()// Methode um zu wissen wer hat den Spiel gewonnen
    {
        int [] gewinner= new int[gridLength-1];
        int [] score= new int[gridLength-1];
        int index=0;
        String gewinnerSatz="";
        for(int i=0; i<gridLength-1;i++)
        {
            if(score[gewinner[index]]==ergebnis[i])
            {
                gewinner[index]=i;
                score[gewinner[index]]=ergebnis[i];
                index++;
            }
            else if(score[gewinner[index]]<ergebnis[i])
            {
                while(index>0)
                {
                    index--;
                }
                gewinner[index]=i;
                score[gewinner[index]]=ergebnis[i];
                index++;
            }
        }
        if(index==1)
        {
            gewinnerSatz="Spieler "+(gewinner[index-1]+1)+" hat gewonnen!";
        }
        else
        {
            gewinnerSatz="Spieler ";
            for(int i=0;i<index;i++)
            {
                if(i==index-1)
                {
                    gewinnerSatz+=""+(gewinner[i]+1)+" haben gewonnen!";
                }
                else
                {
                    gewinnerSatz+=""+(gewinner[i]+1)+" und ";
                }

            }
        }
        return gewinnerSatz;

    }
}
