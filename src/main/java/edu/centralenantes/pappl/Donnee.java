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
public class Donnee {
    private String nom; // Nom du Fichier
    private String path; // Path du fichier
    private String extension; // Extension du fichier (pdf, png,..)
    private FileTime dateCrea; // date de creation du fichier
    private FileTime dateOuvert; // date de dernière ouverture du fichier
    private FileTime dateModif; // date de dernière modif du fichier
    private int freqModif; // fréquence de modif du fichier
    private int freqOuvert; // fréquence d'ouverture du fichier
    private int nbNom; // nombre de fois que le Nom est "répété"
    private long taille; // taille du fichier en byte
    private float score; // score total du fichier

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

    public FileTime getDateCrea() {
        return dateCrea;
    }

    public void setDateCrea(FileTime dateCrea) {
        this.dateCrea = dateCrea;
    }

    public FileTime getDateOuvert() {
        return dateOuvert;
    }

    public void setDateOuvert(FileTime dateOuvert) {
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
    /*
    * Constructeur de la Classe Donnee créant une Donnee à partir d'un path lié à un vrai fichier
    */
    public Donnee(String _path) throws IOException{
        this.path=_path;
        File file= new File(path);
        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        this.nom=file.getName();
        this.dateModif=attr.lastModifiedTime();
        this.dateCrea=attr.creationTime();
        this.dateOuvert=attr.lastAccessTime();
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
    
}
