/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;

/**
 * Classe permettant de gérer la définition des différents paths auxquels l'application accède
 * @author Boulanger
 */
public class InterfaceGraphiquePaths extends JFrame {
    
    private JList listePath;
    
    /**
     * Constructeur de la classe InterfaceGraphiquePaths initialisant la frame
     */
    public InterfaceGraphiquePaths(){        
        //Creation de la frame Initiale
        super("Application PAPPL");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        
        
        //Initialisation des Panel
        JPanel contentPane = (JPanel)this.getContentPane();
        contentPane.setLayout(new BorderLayout());      
        JPanel boutonpanelPath = new JPanel(new FlowLayout());
        
        //Affichage de la liste des paths avec JListe et JScrollPane       
        listePath= new JList(Interface.gScore.getPaths().toArray());
        JScrollPane jspp= new JScrollPane(listePath); 
        
        //Bouton des path à ajouter
        JButton ajoutPath =new JButton("Ajouter");
        ajoutPath.addActionListener((e)->AjouterPath());
        boutonpanelPath.add(ajoutPath);
        
        
        //Bouton des paths à supprimer
        JButton suppPath = new JButton("Supprimer");
        suppPath.addActionListener((e)->SupprimerPath());
        boutonpanelPath.add(suppPath);
                
        //Bouton permettant à l'utilisateur de valider puis de fermer la fenêtre
        JButton valider = new JButton("Valider");
        valider.addActionListener((e)->Valider());
        boutonpanelPath.add(valider);
        
        //Ajouter tout ça à la frame       
        contentPane.add(jspp);
        contentPane.add(boutonpanelPath, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
    
    /**
     * Méthode lancée lors de l'appuie du bouton Ajouter
     */
    private void AjouterPath(){
        JFileChooser chooserPath = new JFileChooser("Définition du dossier Archive");
        chooserPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retour = chooserPath.showOpenDialog(this);

        if(retour ==JFileChooser.APPROVE_OPTION){
            //Ajout du path du dossier d'archive
            Interface.gScore.getPaths().add(chooserPath.getSelectedFile().getAbsolutePath());
        }
        listePath.setListData(Interface.gScore.getPaths().toArray());
    }
    
    
    /**
     * Méthode lancée lors de l'appuie du bouton supprimer
     * Si des chemins d'accès sont sélection dans la JListe des paths alors ils sont supprimés
     */
    private void SupprimerPath(){
        int i=0;
        int[] indexListe = listePath.getSelectedIndices();
        for(int index : indexListe ){
            Interface.gScore.getPaths().remove(index-i);
            i++;
        }
        listePath.setListData(new Vector(Interface.gScore.getPaths()));
    }
    
    /**
     * Méthode permettant à l'utilisateur de valider et donc de fermer la fenêtre
    */
    private void Valider(){
        this.dispose();
    }
}
