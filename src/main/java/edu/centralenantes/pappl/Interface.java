/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public GestionScore gScore;
    public int pallier;
    
    public GestionScore getGestionScore(){
        return this.gScore;
    }
    public Interface(int _pallier){
        this.pallier=_pallier;
        this.gScore = new GestionScore();
    }
    
    public void actualisationInterface() throws Exception{
        gScore.setDonnees(new ArrayList());
        gScore.parcours();
        gScore.calculScore(1);
        gScore.triScore();
    }
    /**
     * Cette fonction va gérer l'interface dans sa globalité avec les demande à l'utilisateur et les résultats
     */
    public void gestionInterface() throws IOException, URISyntaxException{
        //On commence par demande à l'utilisateur les chemins d'accès qu'il veut
        gScore = new GestionScore();
        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<String> paths = new ArrayList();
        
        
        System.out.println("Veuillez indiquer le chemin d'accès: ");
        /*
        input =sc.nextLine();
        paths.add(input);
        */
        paths.add("C:\\Users\\Boulanger\\Downloads");
        gScore.setPaths(paths);
        
        
        //On lance la recherche des fichiers de gScore
        
        gScore.parcours();
        
        //On calcul le score de chaque donnee
        gScore.calculScore(1);
    
        //On tri ensuite la liste
        
        gScore.triScore();
        /*
        
        //Maintenant, on va afficher tous les fichiers ayant un score supérieur au pallier
        
        afficheDonnee();
        
        //Suppression d'un fichier
        System.out.println("Voulez vous supprimer un fichier de la liste?");
        input=sc.nextLine();
        while("O".equals(input)){
            
            System.out.println("Veuillez indiquer le chemin d'accès");
            input = sc.nextLine();
            deleteFichier(input);
            System.out.println("Voulez vous supprimer un fichier de la liste?");
            input=sc.nextLine();
        }
        
        
        //Archivage d'un fichier 
        
        System.out.println("Voulez vous archiver un fichier de la liste?");
        input=sc.nextLine();
        while("O".equals(input)){
            
            System.out.println("Veuillez indiquer le chemin d'accès du fichier");
            String oldPath = sc.nextLine();
            Donnee donnee=gScore.findDonnee(oldPath);
            if(donnee!=null){
                System.out.println("Veuillez indiquer le nom du Dossier dans lequel vous voulez placer le fichier");
                String folderPath=sc.nextLine();
                folderPath=folderPath+donnee.getNom();
               
                //of.renameTo(nf);
                Path tmp = Files.move(Paths.get(oldPath), Paths.get(folderPath)); 
                System.out.println(folderPath);
                if(tmp != null) 
                { 
                    System.out.println("Fichier déplacé avec succès"); 
                } 
                else
                {
                    System.out.println("Impossible de déplacer le fichier"); 
                }     
            }
            else{
                System.out.println("Le fichier spécifié n'existe pas"); 
            }
            System.out.println("Voulez vous archiver un fichier de la liste?");
            input =sc.nextLine();
        }
        */
    
        
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
