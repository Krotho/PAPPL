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
     * @param IGP
     * @throws IOException
     */
    public void enregistrerApplication(InterfaceGraphiquePrincipale IGP) throws IOException{
        bw = new BufferedWriter(new FileWriter(this.path));
        bw.write("Interface;"+ Interface.pallier+";"+Interface.choixScore+";\n");
        
        //Enregistrement paths dossiers d'archive et de corbeille
        bw.write("PathCorbeille;"+ IGP.getPathCorbeille()+";\n");
        bw.write("PathCorbeille;"+ IGP.getPathArchive()+";\n");
        
        //Enregistrement GestionScore de l'interface
        //Enregistrement de paths
        bw.write("GScorePaths");
        for(String s : Interface.gScore.getPaths()){
            bw.write(";" + s);
        }
        bw.write(";\n");
        
        //Enregistrement ignoredPaths
        bw.write("GScoreIgnoredPaths");
        for(String s : Interface.gScore.getIgnoredPaths()){
            bw.write(";" + s);
        }
        bw.write(";\n");
        
        //Enregistrement extensionsTraites
        bw.write("GScoreExtensionsTraites");
        for(String s : Interface.gScore.getExtensionsTraites()){
            bw.write(";" + s);
        }
        bw.write(";\n");
        
        //Enregistrement Parametres
        bw.write("GScoreParametres;" + Interface.gScore.getP().A + ";" + Interface.gScore.getP().B + ";" + Interface.gScore.getP().C + ";"
                + Interface.gScore.getP().D + ";" + Interface.gScore.getP().E + ";" + Interface.gScore.getP().F + ";" + Interface.gScore.getP().G + ";\n");
        
        //Enregistrement des donnees
        for(Donnee d : Interface.gScore.getDonnees()){
            bw.write("Donnee;" + d.getPath() + ";" + d.getNom() + ";" + d.getDateModif() + ";" + d.getDateCrea() + ";" + d.getDateOuvert() + ";"
            + d.getTaille() + ";" + d.getExtension() + ";" + d.getNbNom() + ";" + d.getFreqModif() + ";" + d.getFreqOuvert() + ";" + d.getScore() + ";\n");
        }
        bw.flush();
        bw.close();
    }
}
