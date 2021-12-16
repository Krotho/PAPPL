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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Classe permettant de gérer la définition des différents paths auxquels l'application ne doit jamais accéder
 * @author asjou
 */
public class InterfaceGraphiqueIgnoredPaths extends JFrame {
    
    private JList listeIgnoredPath;
    
    /**
     * Constructeur de la classe InterfaceGraphiquePaths initialisant la frame
     */
    public InterfaceGraphiqueIgnoredPaths(){
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
        listeIgnoredPath= new JList(Interface.gScore.getIgnoredPaths().toArray());
        JScrollPane jspp= new JScrollPane(listeIgnoredPath); 
        
        //Bouton des path à ajouter
        JButton ajoutPath =new JButton("Ajouter");
        ajoutPath.addActionListener((e)->AjouterPath());
        boutonpanelPath.add(ajoutPath);
        
        
        //Bouton des paths à supprimer
        JButton suppPath = new JButton("Supprimer");
        suppPath.addActionListener((e)->SupprimerPath());
        boutonpanelPath.add(suppPath);
                
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
            Interface.gScore.getIgnoredPaths().add(chooserPath.getSelectedFile().getAbsolutePath());
        }
        listeIgnoredPath.setListData(Interface.gScore.getIgnoredPaths().toArray());
    }
    
    
    /**
     * Méthode lancée lors de l'appuie du bouton supprimer
     * Si des chemins d'accès sont sélection dans la JListe des paths alors ils sont supprimés
     */
    private void SupprimerPath(){
        int i=0;
        int[] indexListe = listeIgnoredPath.getSelectedIndices();
        for(int index : indexListe ){
            Interface.gScore.getIgnoredPaths().remove(index-i);
            i++;
        }
        listeIgnoredPath.setListData(new Vector(Interface.gScore.getIgnoredPaths()));
    }
}
