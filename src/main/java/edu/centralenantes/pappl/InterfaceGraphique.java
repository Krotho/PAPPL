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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
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
    public JButton DossierSuppression = new JButton("Def Suppression");
    
    
    //Paramètres
    
    public JSlider A;
    public JSlider B;
    public JSlider C;
    public JSlider D;
    public JSlider E;
    public JSlider F;
    public JSlider G;
    public JSlider H;
    
    public JFrame frameParam;
    
    //Archive   
    public JFileChooser chooserArchive;
    public String pathArchive;
    
    //Corbeille
    public JFileChooser chooserCorbeille;
    public String pathCorbeille;
    /**
     * Constructeur d'InterfaceGraphique
     * @throws Exception 
     */
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
    
    
    /**
     * Affichage de la page de modification de l'importance des paramètres
     */
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
      
        A = new JSlider(0,100);
        JLabel Atxt= new JLabel("A",SwingConstants.CENTER);
        JPanel pA = new JPanel();
        pA.setLayout(new GridLayout(2,1));
        pA.add(Atxt);
        pA.add(A);
        pA.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        B = new JSlider(0,100);
        JLabel Btxt= new JLabel("B",SwingConstants.CENTER);
        JPanel pB = new JPanel();
        pB.setLayout(new GridLayout(2,1));
        pB.add(Btxt);
        pB.add(B);        
        pB.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        C = new JSlider(0,100);
        JLabel Ctxt= new JLabel("C",SwingConstants.CENTER);
        JPanel pC = new JPanel();
        pC.setLayout(new GridLayout(2,1));
        pC.add(Ctxt);
        pC.add(C);
        pC.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        D = new JSlider(0,100);    
        JLabel Dtxt= new JLabel("D",SwingConstants.CENTER);
        JPanel pD = new JPanel();
        pD.setLayout(new GridLayout(2,1));
        pD.add(Dtxt);
        pD.add(D);
        pD.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        E = new JSlider(0,100);
        JLabel Etxt= new JLabel("E",SwingConstants.CENTER);
        JPanel pE = new JPanel();
        pE.setLayout(new GridLayout(2,1));
        pE.add(Etxt);
        pE.add(E);
        pE.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        F = new JSlider(0,100);
        JLabel Ftxt= new JLabel("F",SwingConstants.CENTER);
        JPanel pF = new JPanel();
        pF.setLayout(new GridLayout(2,1));
        pF.add(Ftxt);
        pF.add(F);
        pF.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        G = new JSlider(0,100);      
        JLabel Gtxt= new JLabel("G",SwingConstants.CENTER);
        JPanel pG = new JPanel();
        pG.setLayout(new GridLayout(2,1));
        pG.add(Gtxt);
        pG.add(G);
        pG.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        H = new JSlider(0,100);
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
    
    /**
     * Création du Panel au sud de la frame affichée
     * @return
     * @throws Exception 
     */
    private JPanel SouthFace()throws Exception{
        JPanel jp =new JPanel();
        jp.setLayout(new FlowLayout());
        jp.add(Supprimer);
        Supprimer.addActionListener((e)->{
            try {
                Supprimer();
            } catch (Exception ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Supprimer.setPreferredSize(new Dimension(150,40));
        
        jp.add(Archiver);
        Archiver.addActionListener((e)->{
            try {
                Archiver();
            } catch (Exception ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
    
    
    /**
     * Création du Panel au nord de la frame affichée (toolbar)
     * @return 
     */
    private JToolBar NorthFace(){
        JToolBar jt = new JToolBar();
        //Bouton paramètre
        GestionParametre.addActionListener((e)->AfficheParametre());
        jt.add(GestionParametre);
        
        //Bouton définition du dossier d'archive
        DossierArchive.addActionListener((e)->ajoutArchive());
        jt.add(DossierArchive);
        
        //Bouton définition du dossier de suppression
        DossierSuppression.addActionListener((e)->ajoutCorbeille());
        jt.add(DossierSuppression);
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
     * Méthode lancée lors de l'appuie sur le bouton Supprimer, elle déplace un/des fichier/s dans la corbeille si celle-ci est définie
     * cela relance aussi l'affichage des Données (peut être à améliorer)
     */
    private void Supprimer()throws Exception{
        //Dans le cas où le chemin d'accès est définit on déplace les fichiers choisis
        if(pathCorbeille!=null){
            int[] listeIndice = liste.getSelectedIndices();
            for(int i : listeIndice){
                Donnee d=IT.getGestionScore().getDonnees().get(i);
                Files.move(Paths.get(d.getPath()),Paths.get(pathCorbeille+"\\"+d.getNom()));
            }
            Relancer();
        }
        else{
            System.out.println("PAS DE DOSSIER DE SUPPRESSION DEFINI");
            // \\
        }
    }
    
    /**
     * Méthode lancée lors de l'appuie sur le bouton Relancer, cela remet à jour la liste des Données 
     * @throws Exception 
     */
    private void Relancer()throws Exception{
        if(A!=null){
            IT.gScore.getP().setA(A.getValue());
            IT.gScore.getP().setB(B.getValue());
            IT.gScore.getP().setC(C.getValue());
            IT.gScore.getP().setD(D.getValue());
            IT.gScore.getP().setE(E.getValue());
            IT.gScore.getP().setF(F.getValue());
            IT.gScore.getP().setG(G.getValue());
        }
        IT.actualisationInterface();
        liste.setListData(new Vector(IT.getGestionScore().getDonnees()));
    }
    
    /**
     * Méthode Archiver  lancée lors de l'appuie sur le bouton Archiver
     * lorsque des fichier sont sélectionnés sur la Jliste ils seront déplacés dans le dossier Archive si celui-ci est définit
     * @throws Exception 
     */
    private void Archiver()throws Exception{
        //Dans le cas où le chemin d'accès est définit on déplace les fichiers choisis
        if(pathArchive!=null){
            int[] listeIndice = liste.getSelectedIndices();
            for(int i : listeIndice){
                Donnee d=IT.getGestionScore().getDonnees().get(i);
                Files.move(Paths.get(d.getPath()),Paths.get(pathArchive+"\\"+d.getNom()));
            }
            Relancer();
        }
        else{
            System.out.println("PAS DE DOSSIER DARCHIVE DEFINI");
            // \\
        }
    }

    /**
     * Bouton Valider de la fenêtre Paramètre qui permet de la fermer (les données des paramètres sont récupérées plus tard (lors de relancer)
     */
    private void ValidationParam(){     
        frameParam.dispose();
    }
    
    
    /**
     * Lors de l'appuie sur la toolbar de "def archive" on ouvrir un JFileChooser permettant de choisir un dossier d'archive
     */
    private void ajoutArchive(){

        chooserArchive = new JFileChooser("Définition du dossier Archive");
        chooserArchive.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retour = chooserArchive.showOpenDialog(this);

        if(retour ==JFileChooser.APPROVE_OPTION){
            //Ajout du path du dossier d'archive
            pathArchive=chooserArchive.getSelectedFile().getAbsolutePath();
        }
    }
    
    /**
    * Lors de l'appuie sur la toolbar de "def corbeille" on ouvrir un JFileChooser permettant de choisir un dossier corbeille
    */
    private void ajoutCorbeille(){
        chooserCorbeille = new JFileChooser("Définition du dossier Corbeille");
        chooserCorbeille.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retour = chooserCorbeille.showOpenDialog(this);
        if(retour == JFileChooser.APPROVE_OPTION){
            //ajout du path du dossier corbeille
            pathCorbeille=chooserCorbeille.getSelectedFile().getAbsolutePath();
        }
    }
}
