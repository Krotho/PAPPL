/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author asjou
 */
public class EnregistrerApplication {
    String path;
    BufferedWriter bw;

    /**
     *
     * @param path
     */
    public EnregistrerApplication(String path) {
        this.path = path;
    }
    
    /**
     * Méthode pour enregistrer l'état de l'interface avant fermeture de l'application
     * @param i
     * @throws IOException
     */
    public void enregistrerApplication(Interface i) throws IOException{
        bw = new BufferedWriter(new FileWriter(this.path));
        bw.write("Interface;"+ i.pallier+";"+i.choixScore+";\n");
        
        //Enregistrement GestionScore de l'interface
        //Enregistrement de paths
        bw.write("GScorePaths");
        for(String s : i.gScore.getPaths()){
            bw.write(";" + s);
        }
        bw.write(";\n");
        
        //Enregistrement ignoredPaths
        bw.write("GScoreIgnoredPaths");
        for(String s : i.gScore.getIgnoredPaths()){
            bw.write(";" + s);
        }
        bw.write(";\n");
        
        //Enregistrement extensionsTraites
        bw.write("GScoreExtensionsTraites");
        for(String s : i.gScore.getExtensionsTraites()){
            bw.write(";" + s);
        }
        bw.write(";\n");
        
        //Enregistrement Parametres
        bw.write("GScoreParametres;" + i.gScore.getP().A + ";" + i.gScore.getP().B + ";" + i.gScore.getP().C + ";"
                + i.gScore.getP().D + ";" + i.gScore.getP().E + ";" + i.gScore.getP().F + ";" + i.gScore.getP().G + ";\n");
        
        //Enregistrement des donnees
        for(Donnee d : i.gScore.getDonnees()){
            bw.write("Donnee;" + d.getPath() + ";" + d.getNom() + ";" + d.getDateModif() + ";" + d.getDateCrea() + ";" + d.getDateOuvert() + ";"
            + d.getTaille() + ";" + d.getExtension() + ";" + d.getNbNom() + ";" + d.getFreqModif() + ";" + d.getFreqOuvert() + ";" + d.getScore() + ";\n");
        }
        bw.flush();
        bw.close();
    }
}
