/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
/**
 *
 * @author Boulanger
 */
public class Donnee implements Comparable<Donnee>  {
    private String nom; // Nom du Fichier
    private String path; // Path du fichier
    private String extension; // Extension du fichier (pdf, png,..)
    private long dateCrea; // date de creation du fichier
    private long dateOuvert; // date de dernière ouverture du fichier
    private long dateModif; // date de dernière modif du fichier
    private int freqModif; // fréquence de modif du fichier
    private int freqOuvert; // fréquence d'ouverture du fichier
    private int nbNom; // nombre de fois que le Nom est "répété"
    private long taille; // taille du fichier en byte
    private double score; // score total du fichier

    public long getDateModif() {
        return dateModif;
    }

    public void setDateModif(long dateModif) {
        this.dateModif = dateModif;
    }
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(long dateCrea) {
        this.dateCrea = dateCrea;
    }

    public long getDateOuvert() {
        return dateOuvert;
    }

    public void setDateOuvert(long dateOuvert) {
        this.dateOuvert = dateOuvert;
    }

    public int getFreqModif() {
        return freqModif;
    }

    public void setFreqModif(int freqModif) {
        this.freqModif = freqModif;
    }

    public int getFreqOuvert() {
        return freqOuvert;
    }

    public void setFreqOuvert(int freqOuvert) {
        this.freqOuvert = freqOuvert;
    }

    public int getNbNom() {
        return nbNom;
    }

    public void setNbNom(int nbNom) {
        this.nbNom = nbNom;
    }

    public long getTaille() {
        return taille;
    }

    public void setTaille(long taille) {
        this.taille = taille;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    /*
    * Constructeur de la Classe Donnee créant une Donnee à partir d'un path lié à un vrai fichier
    */
    public Donnee(String _path) throws IOException {
        this.path=_path;
        File file= new File(path);
        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        this.nom=file.getName();
        this.dateModif=attr.lastModifiedTime().toMillis();
        this.dateCrea=attr.creationTime().toMillis();
        this.dateOuvert=attr.lastAccessTime().toMillis();
        this.taille= attr.size();
        int pos = path.lastIndexOf(".");// On recherhce le dernier . dans le fichierqui va correspondre au début de l'extension
        if (pos > -1) {
            this.extension=path.substring(pos);// On supprime tous les éléments du String précédent le point trouvé
        }
        else{
            this.extension=null;
        }
        this.nbNom=0;
        this.freqModif=0;
        this.freqOuvert=0;
        this.score=0;
    }
    /**
    * Méthode affichant toutes les données d'un fichier correspondant à une Donnee
    */
    public void afficheDonnee(){
        Date d= new Date();
        System.out.println("Nom: " +this.nom);
        System.out.println("Taille: "+ this.taille +" Octets");
        System.out.println("Path: " +this.path);
        System.out.println("Extension: " +this.extension);
        System.out.println("Date de creation: " +(d.getTime()-this.dateCrea));
        System.out.println("Date de derniere modification: " +(d.getTime()-this.dateModif));
        System.out.println("Date de derniere ouverture: "+ (d.getTime()-this.dateOuvert));
        System.out.println("Reiteration du nom: " +this.nbNom);
        System.out.println("Frequence de modification: "+ this.freqModif );
        System.out.println("Frequence d'ouverture: "+ this.freqOuvert );
        System.out.println("Score: "+ this.score);

    }
    /**
     * Méthode permettant de comparer une Donnée avec une autre en utilisant leur score
     * @param d
     * @return 
     */
    @Override
    public int compareTo(Donnee d){
        return (int)(this.score - d.getScore());
    }
    
    /**
     * Compare le path de la donnée avec un path entré en String
     * @param path
     * @return
     */
    public boolean isEqual(String path){
        return (this.path.equals(path));
    }
}
