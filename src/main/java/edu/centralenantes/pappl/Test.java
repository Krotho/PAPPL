/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Boulanger
 */
public class Test {
    public static void main(String[] arg) throws IOException{
        Donnee D= new Donnee("D:\\Program Files\\Info\\NETBEANS\\PAPPL\\pom.xml");
        D.afficheDonnee();
        GestionScore gS = new GestionScore();
        for(int i =1;i<5;i++){
            gS.calculScore(D, i);
            System.out.println(D.getScore());
        }
    }
}
