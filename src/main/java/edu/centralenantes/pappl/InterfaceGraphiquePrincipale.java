/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author Boulanger
 */
public class InterfaceGraphiquePrincipale extends JFrame implements WindowListener{
    private JPanel contentPanel;
    
    private JPanel firstPanel;
    private JPanel secondPanel;
    
    //Interface
    private Interface IT;
    
    //Archivage
    private String pathArchive;
    
    //Corbeille
    private String pathCorbeille;
    

    
    //ToolBar
    private JToolBar jtoolBar;
    
    //JListe
    private JList liste;
    
    //Param
    public static boolean paramOpen;

    
    
    public String getPathArchive() {
        return pathArchive;
    }

    public void setPathArchive(String pathArchive) {
        this.pathArchive = pathArchive;
    }

    public String getPathCorbeille() {
        return pathCorbeille;
    }

    public void setPathCorbeille(String pathCorbeille) {
        this.pathCorbeille = pathCorbeille;
    }
    
    
    /**
     * Constructeur d'InterfaceGraphiquePrincipale
     */
    public InterfaceGraphiquePrincipale(){
        
        //Creation de la frame Initiale
        super("Application PAPPL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.addWindowListener(this);
        //Creation d'Interface
        IT = new Interface(150); 
        
        //Charger Fichier Sauvegarde si il existe
        
        paramOpen=false;
        //Creation du panel de la frame
        contentPanel=(JPanel)this.getContentPane();
        contentPanel.setLayout(new CardLayout());
        
        firstPanel= new JPanel(new BorderLayout());
        secondPanel = new JPanel(new BorderLayout());
        
        //Creation de la première frame (toolbar + bouton lancer)
        
        jtoolBar = createToolBar();

        firstPanel.add(firstButton(),BorderLayout.SOUTH);
        firstPanel.add(jtoolBar, BorderLayout.NORTH);
        
        contentPanel.add(firstPanel);
        
        //Creation de la deuxième frame (toolbar, JListe, et boutons d'actions)
        
        secondPanel.add(secondButtons(),BorderLayout.SOUTH);
        //secondPanel.add(jtoolBar,BorderLayout.NORTH);
        Vector v = new Vector(IT.gScore.getDonnees());
        liste = new JList(v);     
        JScrollPane jsp=new JScrollPane(liste);
        secondPanel.add(jsp,BorderLayout.CENTER);
        
        contentPanel.add(secondPanel);
        this.setVisible(true);
        
    }
    
    
    
    /**
     * Méthode Lancer lancée lors de l'appuie sur le bouton Lance de la premiere interface
     * Permet de lancer l'application et de switch la frame sur la frame suivante
     * @throws Exception 
     */
    private void Lancer()throws Exception{
        //Dans le cas  où la liste de parcour est vide, on indique une erreur à l'utilisateur
        if(IT.gScore.getPaths().size()<1){
            JOptionPane.showMessageDialog(contentPanel, "Attention aucun chemin d'accès n'est défini !","Erreur", JOptionPane.ERROR_MESSAGE);
        }
        else{
            IT.gestionInterface();
            liste.setListData(IT.gScore.getDonnees().toArray());
            secondPanel.add(jtoolBar,BorderLayout.NORTH);
           ((CardLayout)contentPanel.getLayout()).next(contentPanel);
        }
       
    }
    
    
    /**
     * Creation d'une barre de menu dans laquelle on peut déf les parametres, les dossisers d'archive/corbeille 
     * et les paths visités par l'application
     * @return 
     */
    private JToolBar createToolBar(){
        JToolBar toolBar = new JToolBar();
        
        //Bouton de modification des paramètres
        JButton gestionParam = new JButton("Paramètres");
        gestionParam.addActionListener((e)->afficheParam());
        toolBar.add(gestionParam);
        
        //Bouton de définition du dossier d'archive
        JButton gestionArchive=new JButton("Dossier Archive");
        gestionArchive.addActionListener((e)->defArchive());
        toolBar.add(gestionArchive);
        
        //Bouton de définition du dossier de corbeille
        JButton gestionCorbeille = new JButton("Dossier Corbeille");
        gestionCorbeille.addActionListener((e)->defCorbeille());
        toolBar.add(gestionCorbeille);
        
        //Bouton de définition des dossiers dans lequel l'application se lance
        JButton gestionPaths = new JButton("Gestion des accès");
        gestionPaths.addActionListener((e)->defPaths());
        toolBar.add(gestionPaths);
        
        return toolBar;
    }
    
    
    
    /**
     * Lance une nouvelle frame qui va permettre de gérer les paramètres
     */
    private void afficheParam(){
        if(!paramOpen){
            InterfaceGraphiqueParametres IGP = new InterfaceGraphiqueParametres();
            paramOpen=true;
        }
        else{
            JOptionPane.showMessageDialog(contentPanel, "Une fenêtre Paramètre est déjà ouverte!","Erreur", JOptionPane.ERROR_MESSAGE);          
        }
    }
    
    
    
    /**
     * Permet de définir les archive en lançant un JFileChooser
     */
    private void defArchive(){
        JFileChooser archiveFileChooser = new JFileChooser();
        archiveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retour = archiveFileChooser.showOpenDialog(this);

        if(retour ==JFileChooser.APPROVE_OPTION){
            //Ajout du path du dossier d'archive
            pathArchive=archiveFileChooser.getSelectedFile().getAbsolutePath();
        }
    }
    
    
    /**
     * Permet de définir le dossier de Corbeille en lançant un JFileChooser
     */
    private void defCorbeille(){
        JFileChooser corbeilleFileChooser = new JFileChooser();
        corbeilleFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retour = corbeilleFileChooser.showOpenDialog(this);

        if(retour ==JFileChooser.APPROVE_OPTION){
            //Ajout du path du dossier d'archive
            pathCorbeille=corbeilleFileChooser.getSelectedFile().getAbsolutePath();
        }
    }
    
    
    
    /**
     * Permet de choisir quels paths vont être parcourus lors du lancement de l'application
     */
    private void defPaths(){
        InterfaceGraphiquePaths IGP  = new InterfaceGraphiquePaths();
    }

    
    
    
    /**
     * Création d'un Panel contenant le bouton Lancer pour lancer l'application
     * @return 
     */
    private JPanel firstButton(){
        JPanel jp = new JPanel();
        JButton Lancer = new JButton("Lancer");
        jp.add(Lancer);
        
        Lancer.addActionListener((e)->{
            try {
                Lancer();
            } catch (Exception ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return jp;
    }
    
    
    
    
    /**
     * Création d'un Panel contenant les boutons une fois que l'application a été lancée une fois
     * Bouton (Supprimer, Archiver, Relancer)
     * @return 
     */
    private JPanel secondButtons(){
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        
        
        //Gestion du bouton supprimer
        JButton Supprimer = new JButton("Supprimer");
        jp.add(Supprimer);        
        Supprimer.addActionListener((e)->{
            try {
                Supprimer();
            } catch (Exception ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Supprimer.setPreferredSize(new Dimension(150,40));
        
        
        //Gestion du bouton Archiver
        JButton Archiver = new JButton("Archiver");
        jp.add(Archiver);
        Archiver.addActionListener((e)->{
            try {
                Archiver();
            } catch (Exception ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Archiver.setPreferredSize(new Dimension(150,40));
        
        
        //Gestion du bouton Relancer
        JButton Relancer = new JButton("Relancer");
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
     * Méthode lancée lors de l'appuie sur le bouton Supprimer,
     * Elle supprime un ou des fichiers choisis
     */
    private void Supprimer() throws Exception{
        //Dans le cas où le chemin d'accès est définit on déplace les fichiers choisis        
        if(pathCorbeille!=null ){
            //On demande à l'utilisateur s'il est sûr de vouloir supprimer les fichiers sélectionnés
            if(JOptionPane.showConfirmDialog(contentPanel, "êtes vous sûr de supprimer ces fichiers?","Confirmation",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                int[] listeIndice = liste.getSelectedIndices();
                for(int i : listeIndice){
                    Donnee d=IT.gScore.getDonnees().get(i);
                    Files.move(Paths.get(d.getPath()),Paths.get(pathCorbeille+"\\"+d.getNom()));
                }
                Relancer();//On "relance" l'application pour mettre à jour la liste
            }
        }
        else{
            //Sinon on indique via une fenêtre qu'aucun dossier corbeille n'est défini !
            JOptionPane.showMessageDialog(contentPanel, "Attention aucun dossier corbeille n'est défini !","Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    /**
     * Méthode lancée lors de l'appuie sur le bouton Archive,
     * Elle archive un ou des fichiers choisis
     */
    private void Archiver() throws Exception{
        //Dans le cas où le chemin d'accès est définit on déplace les fichiers choisis
        if(pathArchive!=null){
            //On vérifie d'abbord si l'utilisateur veut archiver ces fichiers
            if(JOptionPane.showConfirmDialog(contentPanel, "êtes vous sûr d'archiver ces fichiers?","Confirmation",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                int[] listeIndice = liste.getSelectedIndices();
                for(int i : listeIndice){
                    Donnee d=IT.gScore.getDonnees().get(i);
                    Files.move(Paths.get(d.getPath()),Paths.get(pathArchive+"\\"+d.getNom()));
                }
                Relancer();//On "relance" l'appliccation pour mettre à jour la liste
            }
        }
        else{
            //Sinon on indique via une fenêtre qu'aucun dossier archive n'est défini !
            JOptionPane.showMessageDialog(contentPanel, "Attention aucun dossier archive n'est défini !","Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    /**
     * Méthode lancée lors de l'appuie sur la bouton Relancer,
     * La méthode relance le programme principale pour afficher une nouvelle liste de Donnée
     */
    private void Relancer() throws Exception{
        if(IT.gScore.getPaths().size()<1){
            JOptionPane.showMessageDialog(contentPanel, "Attention aucun chemin d'accès n'est défini !","Erreur", JOptionPane.ERROR_MESSAGE);
        }
        else{
            IT.actualisationInterface();
            liste.setListData(IT.gScore.getDonnees().toArray());  
        }
    }
    
    
    
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        InterfaceGraphiquePrincipale IGP= new InterfaceGraphiquePrincipale();
        
    }
    
    
    
    
    
    
    	@Override
	public void windowActivated(WindowEvent e) { }
 
	@Override
	public void windowClosed(WindowEvent e) {
	}
 
	@Override
	public void windowClosing(WindowEvent e){
            //On fait apparaître une fenêtre pour savoir si l'utilisateur veut sauvegarder ou non
             if(JOptionPane.showConfirmDialog(contentPanel, "Voulez-vous sauvegarder la configuration ?","Confirmation",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.YES_OPTION){
                 EnregistrerApplication EA = new EnregistrerApplication("Config.txt");//On crée l'objet pour enregistrer 
                 try {
                     EA.enregistrerApplication(IT);//On enregistre
                 } catch (IOException ex) {
                     Logger.getLogger(InterfaceGraphiquePrincipale.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        }
	
 
	@Override
	public void windowDeactivated(WindowEvent e) { }
 
	@Override
	public void windowDeiconified(WindowEvent e) { }
 
	@Override
	public void windowIconified(WindowEvent e) { }
 
	@Override
	public void windowOpened(WindowEvent e) { }
}
