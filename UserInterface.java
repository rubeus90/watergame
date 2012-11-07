import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener
{
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JButton bouton1;
    private JButton bouton2;
    private JButton bouton3;
    private JButton bouton4;
    private JButton bouton5;
    private JButton bouton6;
    private JButton bouton7;
    private JButton bouton8;
    private JButton bouton9;
    private JButton bouton10;
    private JButton bouton11;
    private JButton bouton12;
    private JButton bouton13;
   
   
    private JTextArea log;
    private JLabel image;


    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Gérer User Interface
     * La fenêtre du jeux comporte 2 panneaux, celui de gauche sert à afficher l'image, la partie texte et 2 boutons
     * Le panneau de droite comporte les boutons de commandes
     */
    private void createGUI()
    {
        myFrame = new JFrame("Water Game");
        myFrame.setResizable(false);
        
        //L'entrée de text
        entryField = new JTextField(34);
        log = new JTextArea();
        log.setEditable(false);
        
        //L'endroit ou affiche le texte du jeu
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(600, 280));
        listScroller.setMinimumSize(new Dimension(10,10));

        //Image des salles
        image = new JLabel();
        image.setPreferredSize(new Dimension(1200,600));
        
        
        bouton1 = new JButton();
        bouton2 = new JButton("Nord");
        bouton3 = new JButton("Haut");
        bouton4 = new JButton("Ouest");
        bouton5 = new JButton();
        bouton6 = new JButton("Est");
        bouton7 = new JButton("Back");
        bouton8 = new JButton("Sud");
        bouton9 = new JButton();
        bouton10 = new JButton("Manger");
        bouton11 = new JButton("Regarder");
        bouton12 = new JButton("Aide");
        bouton13 = new JButton("Test");
        
        
        
        //Panel image en haut de la fenêtre
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200,600));
        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.CENTER);
        
        //Panel corps qui comporte les boutons de navigation, la partie texte et les boutons de commandes
        JPanel panel2 = new JPanel();
        //Panel navigation
        JPanel sspanel1 = new JPanel();
        sspanel1.setPreferredSize(new Dimension(300,300));
        sspanel1.setLayout(new GridLayout(3,3));
        sspanel1.add(bouton1);
        sspanel1.add(bouton2);
        sspanel1.add(bouton3);
        sspanel1.add(bouton4);
        sspanel1.add(bouton5);
        sspanel1.add(bouton6);
        sspanel1.add(bouton7);
        sspanel1.add(bouton8);
        sspanel1.add(bouton9);
        
        //Panel texte
        JPanel sspanel2 = new JPanel();
        sspanel2.setLayout(new BorderLayout());
        sspanel2.setPreferredSize(new Dimension(600,300));
        sspanel2.add(listScroller, BorderLayout.NORTH);
        sspanel2.add(entryField, BorderLayout.SOUTH);
        //Panel boutons
        JPanel sspanel3 = new JPanel();
        sspanel3.setPreferredSize(new Dimension(300,300));
        sspanel3.setLayout(new GridLayout(2,2));
        sspanel3.add(bouton10);
        sspanel3.add(bouton11);
        sspanel3.add(bouton12);
        sspanel3.add(bouton13);
        
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
        panel2.add(sspanel1);
        panel2.add(sspanel2);
        panel2.add(sspanel3);

        
        //Panel box qui contient les 2 autres panels
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
        panel3.add(panel);
        panel3.add(panel2);
        
        
        
        
        
        
        myFrame.setContentPane(panel3);
       

//         myFrame.setLayout(new GridLayout(2,2));
//         myFrame.getContentPane().add(panel, BorderLayout.CENTER);
//         myFrame.getContentPane().add(panel, BorderLayout.WEST);
//         myFrame.getContentPane().add(panel2, BorderLayout.EAST);
//         myFrame.getContentPane().add(panel3, BorderLayout.SOUTH);


        
        
        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(this);
//         bouton1.addActionListener(this);
        bouton2.addActionListener(this);
        bouton3.addActionListener(this);
        bouton4.addActionListener(this);
//         bouton5.addActionListener(this);
        bouton6.addActionListener(this);
        bouton7.addActionListener(this);
        bouton8.addActionListener(this);
//         bouton9.addActionListener(this);
        bouton10.addActionListener(this);
        bouton11.addActionListener(this);
        bouton12.addActionListener(this);
        bouton13.addActionListener(this);
        
        
        
        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }

    
     public void eat()
    {
        System.out.println("Tu as déjà mangé, tu n'as plus faim");
    }
    
    
    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == bouton1) 
        {
         engine.interpretCommand("go");
        }
        else if(e.getSource() == bouton2) 
        {
         engine.interpretCommand("go nord");
        }       
        else if(e.getSource() == bouton3) 
        {
         engine.interpretCommand("go monter");
        }
        else if(e.getSource() == bouton4) 
        {
         engine.interpretCommand("go ouest");
        }
//         else if(e.getSource() == bouton5) 
//         {
//          engine.interpretCommand();
//         }
        else if(e.getSource() == bouton6) 
        {
         engine.interpretCommand("go est");
        }
        else if(e.getSource() == bouton7) 
        {
         engine.interpretCommand("back");
        }
        else if(e.getSource() == bouton8) 
        {
         engine.interpretCommand("go sud");
        }
//         else if(e.getSource() == bouton9) 
//         {
//          engine.interpretCommand();
//         }
        else if(e.getSource() == bouton10) 
        {
         engine.interpretCommand("eat");
        }
        else if(e.getSource() == bouton11) 
        {
         engine.interpretCommand("look");
        }
        else if(e.getSource() == bouton12) 
        {
         engine.interpretCommand("help");
        }
        else if(e.getSource() == bouton13) 
        {
         engine.test();
        }
        
        else
         processCommand();
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
}
