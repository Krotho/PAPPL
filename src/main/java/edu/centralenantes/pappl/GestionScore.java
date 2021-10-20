/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author asjou
 */
public class GestionScore {

    private List<String> paths;
    private List<Donnee> donnees;
    private Parametres p;
    private List<String> extensionsTraites;
    
    class Parametres{
        public double A,B,C,D,E,F,G;

        public void setA(double A) {
            this.A = A;
        }

        public void setB(double B) {
            this.B = B;
        }

        public void setC(double C) {
            this.C = C;
        }

        public void setD(double D) {
            this.D = D;
        }

        public void setE(double E) {
            this.E = E;
        }

        public void setF(double F) {
            this.F = F;
        }

        public void setG(double G) {
            this.G = G;
        }


        public Parametres() {
            A=1.0e-8;
            B=0;
            C=1.0e-15;
            D=1;
            E=1.0e-10;
            F=1.0e-10;
            G=1.0e-3;
        }
        
        

    }
    
    

    
    
    public GestionScore() {
        this.paths = new ArrayList();
        //Le chemin par défaut est celui du dossier téléchargements
        paths.add("C:\\Downloads");
        this.donnees = new ArrayList();
        this.extensionsTraites = new ArrayList();
        this.p = new Parametres();

    }
    
    public GestionScore(ArrayList<String> path) {
        this.paths = path;
        this.donnees = new ArrayList();
        
        this.extensionsTraites = new ArrayList();
        //Par défaut on traite l'extension pdf
        this.extensionsTraites.add(".pdf");
        this.p = new Parametres();
    }
    
    public GestionScore(ArrayList<String> paths, ArrayList<String> extensionsTraites) {
        this.paths = paths;
        this.donnees = new ArrayList();
        this.extensionsTraites = extensionsTraites;
        this.p = new Parametres();
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;


    }

    public List<Donnee> getDonnees() {
        return donnees;
    }

    public void setDonnees(List<Donnee> donnees) {
        this.donnees = donnees;
    }

    
    public void ajoutDonnee(String path) throws IOException{
        Donnee d = new Donnee(path);
        donnees.add(d);
    }
    
    /** 
     * Méthode calculant le Score d'une Donnee en appelant d'autres fonctions pour les calculs
     * @param d 
     * @param i 
     */
    public void calculScore(Donnee d, int i){
        switch(i){
            case(1)->{
                calculScore1(d);// Calcul linéaire dépendant uniquement de la date création et derniere ouverture
            }
            case(2)->{
                calculScore2(d);
            }
            case(3)->{
                calculScore3(d);
            }
            case(4)->{
                calculScore4(d);
            }
        }
        
    }
    
    /**
     * Méthode qui va trier les différentes Données de la liste en fonction de leur score
     */
    public void triScore(){
        Collections.sort(this.donnees);
    }
    
    /**
     * Premiere méthode de calcul du score d'un fichier en se concentrant uniquement sur la date de derniere ouverture (linéaire)
     * @param d 
     */
    public void calculScore1(Donnee d){
        double s=0;
        s=p.A*(d.getDateOuvert()-d.getDateCrea())/(1+p.D*d.getFreqOuvert());
        d.setScore(s);
    }
    /**
     * Deuxième méthode de calcul du score d'un fichier en se concentrant uniquement sur la date de derniere ouverture (exponentielle)
     * @param d 
     */
    public void calculScore2(Donnee d){
        double s =0;
        s=p.A*Math.exp(p.B+p.C*((d.getDateOuvert()-d.getDateCrea())/(1+p.D*d.getFreqOuvert())));
        d.setScore((float)s);
    }
    
    /**
     * Troisième méthode de calcul de score d'un fichier en se concentrant sur: ouverture, modification, taille (linéaire)
     * @param d 
     */
    public void calculScore3(Donnee d){
        double s=0;
        s=p.A*(d.getDateOuvert()-d.getDateCrea())/(1+p.D*d.getFreqOuvert());
        s+=p.E*(d.getDateModif()-d.getDateCrea())/(1+p.F*d.getFreqModif());
        s*=(1+p.G*d.getTaille());
        d.setScore(s);     
    }  

    public List<String> getExtensionsTraites() {
        return extensionsTraites;
    }

    public void setExtensionsTraites(List<String> extensionsTraites) {
        this.extensionsTraites = extensionsTraites;
    }
    
    public String expressionLogiqueExtensionsTraites(){
        String s = "";
        for(String elt : this.extensionsTraites){
            if(!"".equals(s)){
                s+="|";
            }
            s+="[a-zA-Z0-9_.+-]+\\"+elt;
        }
        return s;
    }
    
    
    


    
    public void parcours_path(String chemin) throws IOException{
        boolean bName;
        File repertoire = new File(chemin);
        File[] files=repertoire.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            Pattern uName = Pattern.compile(this.expressionLogiqueExtensionsTraites());
            Matcher mUname = uName.matcher(fileName);
            bName = mUname.matches();
            if (bName) {
                donnees.add(new Donnee(file.getPath()));
            }
        }
    }
    
    public void parcours() throws IOException{
        for(String path : this.paths){
            parcours_path(path);
        }

    }
    
     /**
     * Troisième méthode de calcul de score d'un fichier en se concentrant sur: ouverture, modification, taille (linéaire)
     * @param d 
     */
    public void calculScore4(Donnee d){
        
    }
}
