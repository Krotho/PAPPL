/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author asjou
 */
public class TestAS {
    public static void main(String[] arg) throws IOException{        
        ArrayList<String> path = new ArrayList<>();
        path.add("C:\\Users\\asjou\\Downloads");
//        ArrayList<String> ext = new ArrayList<>();
//        ext.add(".pdf");
        GestionScore gS = new GestionScore(path);
        gS.parcours();
        for (Donnee d : gS.getDonnees()){
            d.afficheDonnee();
        }
    }
}
