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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe InterfaceGraphiqueParametres qui va afficher une frame permettant de modifier
 * les différents paramètres de l'application (pour les calculs) ainsi que de choisir la méthode de calcul
 * @author Boulanger
 */
public class InterfaceGraphiqueParametres extends JDialog {
    
    private JSlider A;
    private JSlider B;
    private JSlider C;
    private JSlider D;
    private JSlider E;
    private JSlider F;
    private JSlider G;
    private JSlider H;
    private JComboBox jcbScore;
    
    
    /**
     * Constructeur de la classe InterfaceGraphiqueParametres
     */
    public InterfaceGraphiqueParametres(InterfaceGraphiquePrincipale IGP){             
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
      
        A = new JSlider(0,100);
        JLabel Atxt= new JLabel("Facteur delta ouverture",SwingConstants.CENTER);
        JPanel pA = new JPanel();
        pA.setLayout(new GridLayout(2,1));
        pA.add(Atxt);
        pA.add(A);
        A.setValue((int)(Interface.gScore.getP().A/(1.0e-9)));
        pA.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        B = new JSlider(0,100);
        JLabel Btxt= new JLabel("constante expo ouverture",SwingConstants.CENTER);
        JPanel pB = new JPanel();
        pB.setLayout(new GridLayout(2,1));
        pB.add(Btxt);
        pB.add(B); 
        B.setValue((int)(Interface.gScore.getP().B/(1)));
        pB.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        C = new JSlider(0,100);
        JLabel Ctxt= new JLabel("facteur expo ouverture",SwingConstants.CENTER);
        JPanel pC = new JPanel();
        pC.setLayout(new GridLayout(2,1));
        pC.add(Ctxt);
        pC.add(C);
        C.setValue((int)(Interface.gScore.getP().C/(1.0e-30)));
        pC.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        D = new JSlider(0,100);    
        JLabel Dtxt= new JLabel("facteur freq ouverture",SwingConstants.CENTER);
        JPanel pD = new JPanel();
        pD.setLayout(new GridLayout(2,1));
        pD.add(Dtxt);
        pD.add(D);
        D.setValue((int)(Interface.gScore.getP().D/(1)));
        pD.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        E = new JSlider(0,100);
        JLabel Etxt= new JLabel("facteur delta modif",SwingConstants.CENTER);
        JPanel pE = new JPanel();
        pE.setLayout(new GridLayout(2,1));
        pE.add(Etxt);
        pE.add(E);
        E.setValue((int)(Interface.gScore.getP().E/(1.0e-11)));
        pE.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        F = new JSlider(0,100);
        JLabel Ftxt= new JLabel("facteur freq modif",SwingConstants.CENTER);
        JPanel pF = new JPanel();
        pF.setLayout(new GridLayout(2,1));
        pF.add(Ftxt);
        pF.add(F);
        F.setValue((int)(Interface.gScore.getP().F));
        pF.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        G = new JSlider(0,100);      
        JLabel Gtxt= new JLabel("facteur taille",SwingConstants.CENTER);
        JPanel pG = new JPanel();
        pG.setLayout(new GridLayout(2,1));
        pG.add(Gtxt);
        pG.add(G);
        G.setValue((int)(Interface.gScore.getP().G/(1.0e-7)));
        pG.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        H = new JSlider(0,100);
        JLabel Htxt= new JLabel("H",SwingConstants.CENTER);
        JPanel pH = new JPanel();
        pH.setLayout(new GridLayout(2,1));
        pH.add(Htxt);
        pH.add(H);
        //H.setValue((int)(Interface.gScore.getP().H/(1.0e-4)));
        pH.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        
        //Ajout au panel
        panneauParam.add(pA);
        panneauParam.add(pB);
        panneauParam.add(pC);
        panneauParam.add(pD);
        panneauParam.add(pE);
        panneauParam.add(pF);
        panneauParam.add(pG);
        //panneauParam.add(pH);
        
        // CREATION D UNE COMBO BOX POUR CHOISIR LE CALCUL DE SCORE \\
        String[] listeScore = {"Calcul 1", "Calcul 2", "Calcul 3", "Calcul 4"};
        jcbScore  = new JComboBox(listeScore);
        jcbScore.setSelectedIndex(Interface.choixScore-1);
        panneauParam.add(jcbScore);
        
        
        
        // CREATION DU PANNEAU de bouton au sud \\
        
        JButton valider = new JButton("Valider");
        valider.addActionListener((e)->ValidationParam());
        valider.setPreferredSize(new Dimension(100,50));
        
        JButton reset = new JButton("Reset");
        reset.addActionListener((e)->ResetParam());
        reset.setPreferredSize(new Dimension(100,50));
        
        JPanel boutonPannel = new JPanel(new FlowLayout());
        boutonPannel.add(valider);
        boutonPannel.add(reset);
        
        // FUSION DES DEUX DANS LE PANNEAU PRINCIPAL \\
        contentPane.add(boutonPannel, BorderLayout.SOUTH);
        contentPane.add(panneauParam,BorderLayout.CENTER);  
        this.setVisible(true);
    }
    
    private void ValidationParam(){
        Interface.gScore.getP().A=A.getValue()*1.0e-9;
        Interface.gScore.getP().B=B.getValue();
        Interface.gScore.getP().C=C.getValue()*1.0e-30;
        Interface.gScore.getP().D=D.getValue();
        Interface.gScore.getP().E=E.getValue()*1.0e-11;
        Interface.gScore.getP().F=F.getValue();
        Interface.gScore.getP().G=G.getValue()*1.0e-7;
        //Interface.gScore.getP().H=H.getValue();
        
        Interface.choixScore=jcbScore.getSelectedIndex()+1;
        this.dispose();
    }
    
    private void ResetParam(){
        A.setValue(10);
        B.setValue(0);
        C.setValue(10);
        D.setValue(0);
        E.setValue(10);
        F.setValue(10);
        G.setValue(10);
    }
}
