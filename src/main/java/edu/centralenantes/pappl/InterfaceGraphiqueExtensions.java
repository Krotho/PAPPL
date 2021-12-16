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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author asjou
 */
public class InterfaceGraphiqueExtensions extends JDialog {
    
    private JTextField listeExtensions;
    
    
    /**
     * Constructeur de la classe InterfaceGraphiqueExtensions
     */
    public InterfaceGraphiqueExtensions(InterfaceGraphiquePrincipale IGP){             
        //Creation de la frame Initiale
        super(IGP,"Application PAPPL",true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel)this.getContentPane();
        /////////////////////// \\\\\\\\\\\\\\\\\\\\\\\
        
        
        // CREATION DU SOUS PANNEAU DES PARAMETRES \\
        JPanel panneauParam = new JPanel();
        panneauParam.setLayout(new GridLayout(4,2));
        
        //Gestion des sliders \\
      
        listeExtensions = new JTextField(initText());
        JLabel Atxt= new JLabel("Entrer les extensions en les séparant par ; sans espace (ex : pdf;txt)",SwingConstants.CENTER);
        JPanel pA = new JPanel();
        pA.setLayout(new GridLayout(2,1));
        pA.add(Atxt);
        pA.add(listeExtensions);
        pA.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        
        //Ajout au panel
        panneauParam.add(pA);
        
        
        // CREATION DU PANNEAU de bouton au sud \\
        
        JButton valider = new JButton("Valider");
        valider.addActionListener((e)->ValidationListeExtensions());
        valider.setPreferredSize(new Dimension(100,50));
        
        JButton reset = new JButton("Reset");
        reset.addActionListener((e)->ResetListeExtensions());
        reset.setPreferredSize(new Dimension(100,50));
        
        JPanel boutonPannel = new JPanel(new FlowLayout());
        boutonPannel.add(valider);
        boutonPannel.add(reset);
        
        // FUSION DES DEUX DANS LE PANNEAU PRINCIPAL \\
        contentPane.add(boutonPannel, BorderLayout.SOUTH);
        contentPane.add(panneauParam,BorderLayout.CENTER);  
        this.setVisible(true);
    }
    
    /**
     * Méthode créant le texte initiale à partir de la config et des extensions traitées
     * @return 
     */
    private String initText(){
        String s ="";
        for(String ext : Interface.gScore.getExtensionsTraites()){
            s+= ext + ";";
        }
        return s;
    }
    /**
     * Méthode lancée lors de l'appuie sur le bouton valider, cela valider les extensions écrites ici dans gestionScore
     */
    private void ValidationListeExtensions(){
        //Interface.gScore.getP().A=A.getValue()*1.0e-9;
        ArrayList<String> l_ext = new ArrayList();
        l_ext.addAll(Arrays.asList(listeExtensions.getText().split(";")));
        Interface.gScore.setExtensionsTraites(l_ext);
        this.dispose();
    }
    
    /**
     * Méthode lancée lors de l'appuie sur le bouton Reset, cela reset les extensions avec juste les pdf comme base
     */
    private void ResetListeExtensions(){
        ArrayList<String> l = new ArrayList();
        l.add(".pdf");
        Interface.gScore.setExtensionsTraites(l);
        listeExtensions.setText(".pdf;");
    }
    
}
