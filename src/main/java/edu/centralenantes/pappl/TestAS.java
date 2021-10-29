/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author asjou
 */
public class TestAS {

    /**
     *
     * @param arg
     * @throws IOException
     */
    public static void main(String[] arg) throws IOException{        
        ArrayList<String> path = new ArrayList<>();
        path.add("C:\\Users\\asjou\\Downloads");
        //path.add("C:\\Users\\asjou\\Downloads\\Einreiseanmeldung.pdf");
        path.add("C:\\Users\\asjou\\Downloads\\TP1\\Interaction TP1.pdf");
        ArrayList<String> ext = new ArrayList<>();
        ext.add(".pdf");
//        ext.add("png");
        GestionScore gS = new GestionScore(path,ext);
        gS.parcours();
        gS.getDonnees().forEach(d -> {
            System.out.println(d.getPath());
        });
    }
}
