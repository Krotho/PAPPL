/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

/**
 *
 * @author Boulanger
 */
public class Test {
    public static void main(String[] arg) throws IOException, URISyntaxException{
        /*
        Donnee D= new Donnee("D:\\Program Files\\Info\\NETBEANS\\PAPPL\\pom.xml");
        D.afficheDonnee();
        GestionScore gS = new GestionScore();
        for(int i =1;i<5;i++){
            gS.calculScore(D, i);
            System.out.println(D.getScore());
        }
        */
        

        Interface I = new Interface(150);
        I.gestionInterface();
        for(String s : Interface.gScore.getPaths()){
            System.out.println(s);
        }
        System.out.println(Interface.pallier);
        ChargementApplication ca = new ChargementApplication("testPAPPL2.txt");
        //ca.chargerApplication();
        System.out.println(Interface.pallier);
        
        File f = new File("testPAPPL3.txt");
        EnregistrerApplication e = new EnregistrerApplication("testPAPPL3.txt");
        //e.enregistrerApplication(I);
        
    }
}
