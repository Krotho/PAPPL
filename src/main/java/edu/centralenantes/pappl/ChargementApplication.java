/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author asjou
 */
public class ChargementApplication {
    private String path;
    private BufferedReader bf;

    /**
     * 
     * @param path
     */
    public ChargementApplication(String path) {
        this.path = path;
    }
    
    /**
     * Méthode à lancer pour récupérer les données de lancement précédents
     * @param IGP
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void chargerApplication(InterfaceGraphiquePrincipale IGP) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(path);
        bf = new BufferedReader(fr);
        
        String line = bf.readLine();// On récupère la ligne 1 (pallier interface)
        StringTokenizer sT = new StringTokenizer(line, ";");
        sT.nextToken(); //On supprime "Interface"
        String mot = sT.nextToken(); //On récupère le pallier
        Interface.pallier=Integer.parseInt(mot); //On crée le pallier de l'interface
        Interface.choixScore=Integer.parseInt(sT.nextToken()); //On enregistre le choix du score
        
        
        //On récupère les infos de l'interface graphique sur les premières lignes
        line = bf.readLine(); // On récupère la ligne suivante (path de corbeille)
        sT = new StringTokenizer(line, ";");
        sT.nextToken(); //On supprime "PathCorbeille"
        if(sT.hasMoreTokens()){
            IGP.setPathCorbeille(sT.nextToken());
        }
        line = bf.readLine(); // On récupère la ligne suivante (path d'archive)
        sT = new StringTokenizer(line, ";");
        sT.nextToken(); //On supprime "PathArchive"
        if(sT.hasMoreTokens()){
            IGP.setPathArchive(sT.nextToken());
        }
        
        //Creation GestionScore de l'interface
        Interface.gScore = new GestionScore();
        //Recuperation de paths
        line=bf.readLine();
        sT = new StringTokenizer(line, ";");
        ArrayList<String> listeMots = new ArrayList(); //Liste vide pour stockage
        sT.nextToken(); //On supprime le "paths"
        while(sT.hasMoreTokens()) {
             listeMots.add(sT.nextToken()); // On crée une liste de mots contenant toutes les infos de la liste paths
        }
        Interface.gScore.setPaths(listeMots);
        
        //Recuperation ignoredPaths
        line=bf.readLine();
        sT = new StringTokenizer(line, ";");
        listeMots = new ArrayList(); //Recréation liste vide
        sT.nextToken(); //On supprime le "ignoredPaths"
        while(sT.hasMoreTokens()) {
             listeMots.add(sT.nextToken()); // On crée une liste de mots contenant toutes les infos de la liste paths
        }
        Interface.gScore.setIgnoredPaths(listeMots);
        
        //Recuperation extensionsTraites
        line=bf.readLine();
        sT = new StringTokenizer(line, ";");
        listeMots = new ArrayList(); //Recréation liste vide
        sT.nextToken(); //On supprime le "extensionsTraites"
        while(sT.hasMoreTokens()) {
             listeMots.add(sT.nextToken()); // On crée une liste de mots contenant toutes les infos de la liste paths
        }
        Interface.gScore.setExtensionsTraites(listeMots);
        
        //Recuperation Parametres
        line=bf.readLine();
        sT = new StringTokenizer(line, ";");
        listeMots = new ArrayList(); //Recréation liste vide
        sT.nextToken(); //On supprime le "Parametres"
        while(sT.hasMoreTokens()) {
             listeMots.add(sT.nextToken()); // On crée une liste de mots contenant toutes les infos de la liste paths
        }
        Interface.gScore.setP(listeMots);
        
        //Creation des donnees
        line= bf.readLine();
        Donnee exDonnee;
        Donnee newDonnee;
        while(line!=null){
            // On va séparer les caracts de chaque ligne
            
            sT = new StringTokenizer(line, ";");
            listeMots= new ArrayList();
            sT.nextToken(); //On supprime le "Donnee"
            while(sT.hasMoreTokens()) {
                listeMots.add(sT.nextToken()); // On crée une liste de mots contenant toutes les infos d'une ancienne donnee
            }
            exDonnee = new Donnee(listeMots);
            try{
                //On crée la donnee actuelle si elle existe
                newDonnee = new Donnee(exDonnee.getPath());
                //Mise à jour des fréquences d'ouverture et de modification
                if(newDonnee.getDateOuvert()>exDonnee.getDateOuvert()){
                    newDonnee.setFreqOuvert(exDonnee.getFreqOuvert()+1);
                }
                else{
                    newDonnee.setFreqOuvert(exDonnee.getFreqOuvert());
                }
                if(newDonnee.getDateModif()>exDonnee.getDateModif()){
                    newDonnee.setFreqModif(exDonnee.getFreqModif()+1);
                }
                else{
                    newDonnee.setFreqModif(exDonnee.getFreqModif());
                }
                //On ne récupère pas l'ancien score de la Donnee enregistrée mais il faudrait le faire là si on le voulait
                
                //Ajout dans la liste de donnees de gScore de l'interface
                Interface.gScore.ajoutDonnee(newDonnee);
            }
            catch (IOException exception){
                System.out.println("exDonnee " + exDonnee.getNom() + " non trouvée au path " + exDonnee.getPath());
            }
            catch (InvalidPathException exception){
                System.out.println("exDonnee " + exDonnee.getNom() + " non trouvée au path " + exDonnee.getPath());
            }
            line= bf.readLine();
        }
        bf.close();
        fr.close();
        
    }
}
