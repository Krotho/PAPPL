/*
*PAPPL outil de conseil à l'archivage et à la suppression de fichiers sur une machine personnelle
*Copyright (C) 2021  Boulanger & Jourlin, Ecole Centrale de Nantes
*
*This library is free software; you can redistribute it and/or
*modify it under the terms of the GNU Lesser General Public
*License as published by the Free Software Foundation; either
*version 2.1 of the License, or (at your option) any later version.
*
*This library is distributed in the hope that it will be useful,
*but WITHOUT ANY WARRANTY; without even the implied warranty of
*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
*Lesser General Public License for more details.
*
*You should have received a copy of the GNU Lesser General Public
*License along with this library; if not, write to the Free Software
*Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
*USA
 */
package edu.centralenantes.pappl;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
     * @throws java.io.IOException
     */
    public InterfaceGraphiquePrincipale() throws IOException{
        
        //Creation de la frame Initiale
        super("Application PAPPL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.addWindowListener(this);
        //Creation d'Interface
        IT = new Interface(150); 
        
        //Charger Fichier Sauvegarde si il existe
        try{
            ChargementApplication ca = new ChargementApplication("Config.txt");
            ca.chargerApplication(this);
        }
        catch (Exception e){
            //Fichier Config non lu ou trouvé
        }
        
        //Creation du panel de la frame
        contentPanel=(JPanel)this.getContentPane();
        contentPanel.setLayout(new CardLayout());
        
        firstPanel= new JPanel(new BorderLayout());
        secondPanel = new JPanel(new BorderLayout());
        
        //Creation de la première frame (toolbar + bouton lancer)
        
        jtoolBar = createToolBar();
        BufferedImage image = ImageIO.read(new File("IconT.png"));
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        firstPanel.add(imageLabel,BorderLayout.CENTER);
        firstPanel.add(firstButton(),BorderLayout.SOUTH);
        firstPanel.add(jtoolBar, BorderLayout.NORTH);
        
        contentPanel.add(firstPanel);
        
        //Creation de la deuxième frame (toolbar, JListe, et boutons d'actions)
        
        secondPanel.add(secondButtons(),BorderLayout.SOUTH);
        //secondPanel.add(jtoolBar,BorderLayout.NORTH);
        Vector v = new Vector(IT.gScore.getDonneesPallier(IT.pallier));
        liste = new JList(v);
        liste.setCellRenderer(new TestCellRenderer());
        
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
            liste.setListData(IT.gScore.getDonneesPallier(IT.pallier).toArray());
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
        
        //Bouton de modification des extensions
        JButton gestionExtensions = new JButton("Extensions traitées");
        gestionExtensions.addActionListener((e)->defExtensions());
        toolBar.add(gestionExtensions);
        
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
        
        //Bouton de définition des dossiers dans lequel l'application ne doit pas se lancer
        JButton gestionIgnoredPaths = new JButton("Dossiers exclus pour l'application");
        gestionIgnoredPaths.addActionListener((e)->defPaths());
        toolBar.add(gestionIgnoredPaths);
        
        return toolBar;
    }
    
    
    
    /**
     * Lance une nouvelle frame qui va permettre de gérer les paramètres
     */
    private void afficheParam(){
            InterfaceGraphiqueParametres IGP = new InterfaceGraphiqueParametres(this);
    } 

    /**
     * Lance une nouvelle frame qui va permettre de gérer les paramètres
     */
    private void defExtensions(){
            InterfaceGraphiqueExtensions IGE = new InterfaceGraphiqueExtensions(this);
    } 
    
    
    /**
     * Permet de définir les archive en lançant un JFileChooser
     */
    private void defArchive(){
        JFileChooser archiveFileChooser = new JFileChooser(pathArchive);
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
        JFileChooser corbeilleFileChooser = new JFileChooser(pathCorbeille);
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
     * Permet de choisir quels paths ne vont PAS être parcourus lors du lancement de l'application
     */
    private void defIgnoredPaths(){
        InterfaceGraphiqueIgnoredPaths IGP  = new InterfaceGraphiqueIgnoredPaths();
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
                try {
                    Relancer();
                } catch (Exception ex1) {
                    Logger.getLogger(InterfaceGraphiquePrincipale.class.getName()).log(Level.SEVERE, null, ex1);
                }
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
            liste.setListData(IT.gScore.getDonneesPallier(IT.pallier).toArray());  
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
                     EA.enregistrerApplication(this);//On enregistre
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
