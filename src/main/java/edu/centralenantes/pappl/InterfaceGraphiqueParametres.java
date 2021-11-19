/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class InterfaceGraphiqueParametres extends JFrame implements WindowListener {
    
    private JSlider A;
    private JSlider B;
    private JSlider C;
    private JSlider D;
    private JSlider E;
    private JSlider F;
    private JSlider G;
    private JSlider H;
    
    
    /**
     * Constructeur de la classe InterfaceGraphiqueParametres
     */
    public InterfaceGraphiqueParametres(){             
        //Creation de la frame Initiale
        super("Application PAPPL");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.addWindowListener(this);
        JPanel contentPane = (JPanel)this.getContentPane();
        /////////////////////// \\\\\\\\\\\\\\\\\\\\\\\
        
        
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
        contentPane.add(boutonPannel, BorderLayout.SOUTH);
        contentPane.add(panneauParam,BorderLayout.CENTER);  
        this.setVisible(true);
    }
    
    
    private void ValidationParam(){
        Interface.gScore.getP().A=A.getValue();
        Interface.gScore.getP().B=B.getValue();
        Interface.gScore.getP().C=C.getValue();
        Interface.gScore.getP().D=D.getValue();
        Interface.gScore.getP().E=E.getValue();
        Interface.gScore.getP().F=F.getValue();
        Interface.gScore.getP().G=G.getValue();
        //Interface.gScore.getP().H=H.getValue();
        InterfaceGraphiquePrincipale.paramOpen=false;
        this.dispose();
    }
    
	@Override
	public void windowActivated(WindowEvent e) { }
 
	@Override
	public void windowClosed(WindowEvent e) {
	}
 
	@Override
	public void windowClosing(WindowEvent e) {
            //Lorsque la fenêtre est fermée autrement que par OK ou Annuler, on réinitialise paramOpen de l'interface graphiqeu
            InterfaceGraphiquePrincipale.paramOpen=false;
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
