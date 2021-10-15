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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
/**
 *
 * @author Boulanger
 */
public class Donnee {
    private String nom;
    private String path;
    private String extension;
    private long dateCrea;
    private long dateOuvert;
    private int freqModif;
    private int freqOuvert;
    private int nbNom;
    private float taille;
    private float score;

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

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
    
    public Donnee(String _path) throws IOException{
        this.path=_path;
        File file= new File(path);
        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        this.nom=file.getName();
        this.dateCrea=file.lastModified();
        this.dateCrea=file.creationTime();
    }
}
