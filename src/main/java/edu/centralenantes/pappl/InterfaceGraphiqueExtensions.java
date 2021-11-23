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
     * Constructeur de la classe InterfaceGraphiqueParametres
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
    
    private String initText(){
        String s ="";
        for(String ext : Interface.gScore.getExtensionsTraites()){
            s+= ext + ";";
        }
        return s;
    }
    
    private void ValidationListeExtensions(){
        //Interface.gScore.getP().A=A.getValue()*1.0e-9;
        ArrayList<String> l_ext = new ArrayList();
        l_ext.addAll(Arrays.asList(listeExtensions.getText().split(";")));
        Interface.gScore.setExtensionsTraites(l_ext);
        this.dispose();
    }
    
    private void ResetListeExtensions(){
        ArrayList<String> l = new ArrayList();
        l.add(".pdf");
        Interface.gScore.setExtensionsTraites(l);
        listeExtensions.setText(".pdf;");
    }
    
}
