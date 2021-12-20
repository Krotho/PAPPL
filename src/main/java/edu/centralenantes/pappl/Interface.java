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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Boulanger
 */
public class Interface {

 

    /**
     * Gestion de score qu'on relie à l'interface par une variable statique
     */
    public static GestionScore gScore;

    /**
     * Pallier pour le choix des scores trop hauts
     */
    public static int pallier;
    
    /**
     * Choix de la méthode de calcul de score qu'on emploie
     */
    public static int choixScore;
    
    /**
     *
     * @param _pallier
     */

    public Interface(int _pallier){
        Interface.pallier=_pallier;
        Interface.gScore = new GestionScore();
        Interface.choixScore = 1;
    }
    
    public void actualisationInterface() throws Exception{
        gScore.setDonnees(new ArrayList());
        gScore.parcours();
        gScore.calculScore(choixScore);
        gScore.triScore();
    }
    /**
     * Cette fonction va gérer l'interface dans sa globalité avec les demande à l'utilisateur et les résultats
     * @throws java.io.IOException
     * @throws java.net.URISyntaxException
     */
    public void gestionInterface() throws IOException, URISyntaxException{
        //On lance la recherche des fichiers de gScore        
        gScore.parcours();       
        //On calcul le score de chaque donnee
        gScore.calculScore(choixScore);   
        //On tri ensuite la liste       
        gScore.triScore();   
    }
    
    /**
     * La méthode affiche va afficher à chaque ligne le path, le nom du fichier et son score si celui-ci est supérieur au pallier
     */
    public void afficheDonnee(){
        for(Donnee D: gScore.getDonnees()){
            if(D.getScore()>pallier){
                System.out.println("Nom : " + D.getNom()+" Chemin d'accès : " +D.getPath() +" Score : "+D.getScore() );
            }
        }
    }
    
    /**
     * Cette méthode va tenter de supprimer un fichier 
     * @param path
     */
    public void deleteFichier(String path){

            File file = new File(path);

            if(file.delete()){
             System.out.println(file.getName() + " est supprimé.");
            }
            else{
             System.out.println("Opération de suppression echouée");
            }

        

    }
}
