/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Boulanger
 */
public class InterfaceGraphique extends JFrame {
    
    private Interface IT;
    
    public JPanel monPanneau;
    public GridLayout layout;
    public JSlider slider;
    public JList liste;
    public JButton button1;
    
    // Gestion application
    public JButton Supprimer = new JButton("Supprimer");
    public JButton Archiver= new JButton("Archiver");
    public JButton Relancer = new JButton("Relancer");
    
    //Menu 
    public JButton GestionParametre = new JButton("Paramètres");
    public JButton DossierArchive = new JButton("Def Archive");
    public JButton DossierSuppresion = new JButton("Def Suppression");
    
    public JFrame frameParam;
    
    public InterfaceGraphique() throws Exception{

        // Instanciation de la fenêtre
        super("Application PAPPL ALPHA");
        IT = new Interface(350);
        IT.gestionInterface();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        
        
        Vector v = new Vector(IT.getGestionScore().getDonnees());
        liste = new JList(v);
        
        JScrollPane jsp=new JScrollPane(liste);
        //Ajout de composants
        
        JPanel contentPane =(JPanel)this.getContentPane();
        
        contentPane.setLayout(new BorderLayout());
        contentPane.add(NorthFace(), BorderLayout.NORTH);
        contentPane.add(SouthFace(), BorderLayout.SOUTH);
        contentPane.add(jsp);
        
        //Look de la fenêtre
        //AfficheParametre();
        
    }
    
    private void AfficheParametre(){
        
        JFrame windows = new JFrame("Paramètres"); 
        windows.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        windows.setSize(600,400);
        windows.setLocationRelativeTo(null);

        
        JPanel panneau = new JPanel();
        panneau.setLayout(new BorderLayout());
        
        
        // CREATION DU SOUS PANNEAU DES PARAMETRES \\
        JPanel panneauParam = new JPanel();
        panneauParam.setLayout(new GridLayout(4,2));
        
        //Gestion des sliders \\
      
        JSlider A = new JSlider(0,100);
        JLabel Atxt= new JLabel("A",SwingConstants.CENTER);
        JPanel pA = new JPanel();
        pA.setLayout(new GridLayout(2,1));
        pA.add(Atxt);
        pA.add(A);
        pA.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        JSlider B = new JSlider(0,100);
        JLabel Btxt= new JLabel("B",SwingConstants.CENTER);
        JPanel pB = new JPanel();
        pB.setLayout(new GridLayout(2,1));
        pB.add(Btxt);
        pB.add(B);        
        pB.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        JSlider C = new JSlider(0,100);
        JLabel Ctxt= new JLabel("C",SwingConstants.CENTER);
        JPanel pC = new JPanel();
        pC.setLayout(new GridLayout(2,1));
        pC.add(Ctxt);
        pC.add(C);
        pC.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        JSlider D = new JSlider(0,100);    
        JLabel Dtxt= new JLabel("D",SwingConstants.CENTER);
        JPanel pD = new JPanel();
        pD.setLayout(new GridLayout(2,1));
        pD.add(Dtxt);
        pD.add(D);
        pD.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        JSlider E = new JSlider(0,100);
        JLabel Etxt= new JLabel("E",SwingConstants.CENTER);
        JPanel pE = new JPanel();
        pE.setLayout(new GridLayout(2,1));
        pE.add(Etxt);
        pE.add(E);
        pE.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        JSlider F = new JSlider(0,100);
        JLabel Ftxt= new JLabel("F",SwingConstants.CENTER);
        JPanel pF = new JPanel();
        pF.setLayout(new GridLayout(2,1));
        pF.add(Ftxt);
        pF.add(F);
        pF.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        JSlider G = new JSlider(0,100);      
        JLabel Gtxt= new JLabel("G",SwingConstants.CENTER);
        JPanel pG = new JPanel();
        pG.setLayout(new GridLayout(2,1));
        pG.add(Gtxt);
        pG.add(G);
        pG.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        JSlider H = new JSlider(0,100);
        JLabel Htxt= new JLabel("H",SwingConstants.CENTER);
        JPanel pH = new JPanel();
        pH.setLayout(new GridLayout(2,1));
        pH.add(Htxt);
        pH.add(H);
        pH.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        //Ajout au panel
        panneauParam.add(pA);
        panneauParam.add(pB);
        panneauParam.add(pC);
        panneauParam.add(pD);
        panneauParam.add(pE);
        panneauParam.add(pF);
        panneauParam.add(pG);
        panneauParam.add(pH);
        
        
        // CREATION DU PANNEAU de bouton au sud \\
        
        JButton valider = new JButton("Valider");
        valider.addActionListener((e)->ValidationParam());
        valider.setPreferredSize(new Dimension(100,50));
        
        JButton reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100,50));
        
        JPanel boutonPannel = new JPanel(new FlowLayout());
        boutonPannel.add(valider);
        boutonPannel.add(reset);
        
        // FUSION DES DEUX DANS LE PANNEAU PRINCIPAL \\
        panneau.add(boutonPannel, BorderLayout.SOUTH);
        panneau.add(panneauParam,BorderLayout.CENTER);
        
        // AJOUT A LA FENETRE WINDOWS  \\
        
        windows.setContentPane(panneau);
        windows.pack();
        windows.setVisible(true);
        frameParam=windows;
    }
    

    private JPanel SouthFace()throws Exception{
        JPanel jp =new JPanel();
        jp.setLayout(new FlowLayout());
        jp.add(Supprimer);
        Supprimer.addActionListener((e)->Supprimer());
        Supprimer.setPreferredSize(new Dimension(150,40));
        
        jp.add(Archiver);
        Archiver.addActionListener((e)->Archiver());
        Archiver.setPreferredSize(new Dimension(150,40));
        
        jp.add(Relancer);
        Relancer.addActionListener((e)->{
            try {
                Relancer();
            } catch (Exception ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Relancer.setPreferredSize(new Dimension(150,40));
        return jp;
    }
    
    private JToolBar NorthFace(){
        JToolBar jt = new JToolBar();
        GestionParametre.addActionListener((e)->AfficheParametre());
        jt.add(GestionParametre);
        jt.add(DossierArchive);
        jt.add(DossierSuppresion);
        return jt;
    }
    
    public static void main(String[] args)throws Exception{
        // Ajout d'un look
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        
        //Lancement de la fenêtre
        InterfaceGraphique IG = new InterfaceGraphique();
        IG.setVisible(true);
    }
    /**
     * Méthode lancée lors de l'appuie sur le bouton Supprimer
     */
    private void Supprimer(){
        System.out.println("Suppression");
    }
    
    /**
     * Méthode lancée lors de l'appuie sur le bouton Relancer, cela remet à jour la liste des Données 
     * @throws Exception 
     */
    private void Relancer()throws Exception{
        IT.gestionInterface();
        liste = new JList(new Vector(IT.getGestionScore().getDonnees()));
    }
    
    private void Archiver(){
        System.out.println("Archiver");
    }

    private void ValidationParam(){
        frameParam.dispose();
    }
}
