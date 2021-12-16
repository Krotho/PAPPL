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
