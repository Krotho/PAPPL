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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author asjou
 */
public class GestionScore {

    private List<String> paths; //Liste des paths à traiter
    private List<String> ignoredPaths; //Liste des paths à ignorer, qu'il s'agisse de dossiers ou de fichiers
    private List<Donnee> donnees; //Liste des données des fichiers trouvés
    private Parametres p; //Paramètres pour les calculs de scores
    private List<String> extensionsTraites; //Liste des extensions que l'on va traiter
    
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
    
    /**
     *
     */
    public GestionScore() {
        this.paths = new ArrayList();
        //Le chemin par défaut est celui du dossier téléchargements
        paths.add("C:\\Users\\Boulanger\\Downloads");
        this.donnees = new ArrayList();
        this.extensionsTraites = new ArrayList();
        this.extensionsTraites.add(".pdf");
        this.p = new Parametres();
        this.ignoredPaths = new ArrayList();
    }
    
    /**
     *
     * @param path
     */
    public GestionScore(ArrayList<String> path) {
        this.paths = path;
        this.donnees = new ArrayList();
        
        this.extensionsTraites = new ArrayList();
        //Par défaut on traite l'extension pdf
        this.extensionsTraites.add(".pdf");
        this.p = new Parametres();
        this.ignoredPaths = new ArrayList();
    }
    
    /**
     *
     * @param paths
     * @param extensionsTraites
     */
    public GestionScore(ArrayList<String> paths, ArrayList<String> extensionsTraites) {
        this.paths = paths;
        this.donnees = new ArrayList();
        this.extensionsTraites = extensionsTraites;
        this.p = new Parametres();
        this.ignoredPaths = new ArrayList();
    }

    /**
     *
     * @return
     */
    public List<String> getPaths() {
        return paths;
    }

    /**
     *
     * @param paths
     */
    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    /**
     *
     * @return
     */
    public List<String> getIgnoredPaths() {
        return ignoredPaths;
    }

    /**
     *
     * @param ignoredPaths
     */
    public void setIgnoredPaths(List<String> ignoredPaths) {
        this.ignoredPaths = ignoredPaths;
    }

    public Parametres getP() {
        return p;
    }

    public void setP(Parametres p) {
        this.p = p;
    }
    
    
    
    
    /**
     *
     * @return
     */
    public List<Donnee> getDonnees() {
        return donnees;
    }

    /**
     *
     * @param donnees
     */
    public void setDonnees(List<Donnee> donnees) {
        this.donnees = donnees;
    }

    /**
     *
     * @param path
     * @throws IOException
     */
    public void ajoutDonnee(String path) throws IOException{
        Donnee d = new Donnee(path);
        donnees.add(d);
    }
    public void calculScore(int i){
        for(Donnee D : donnees){
            calculScore(D,i);
        }
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
        //System.out.println(d.getScore());
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

    /**
     *
     * @return
     */
    public List<String> getExtensionsTraites() {
        return extensionsTraites;
    }

    /**
     *
     * @param extensionsTraites
     */
    public void setExtensionsTraites(List<String> extensionsTraites) {
        this.extensionsTraites = extensionsTraites;
    }
    
    /**
     * Méthode qui renvoie l'expression logique associée à une extension de la forme ".ext" OU "ext"
     * @return
     */
    public String expressionLogiqueExtensionsTraites(){
        String s = "";
        for(String elt : this.extensionsTraites){
            if(!"".equals(s)){
                s+="|";
            }
                s+="\\b.*"+elt+"\\b";
        }
        return s;
    }
    
    /**
     * Méthode qui vérifie si la liste de l'attribut nommé donnees contient un fichier se trouvant à un path donné
     * @param path
     * @return
     */
    public boolean isInDonnees(String path){
        for (Donnee d : this.donnees){
            if(d.isEqual(path)){
                return true;
            }
        }
        return false;
    }
    
    /**
     *  Méthode qui parcourt un chemin et ses sous-dossiers si il y en a en ajoutant à la liste des données tous les fichiers de la forme adaptée
     * @param chemin
     * @throws IOException
     */
    public void parcours_path(String chemin) throws IOException{
        boolean bName;
        File repertoire = new File(chemin);
        File[] files;
        if(this.ignoredPaths.contains(repertoire.getPath())){
            return;
        }
        if(repertoire.isDirectory()){
            files=repertoire.listFiles();
        }
        else {
            if(repertoire.exists()){
                files = new File[]{repertoire};
            }
            else{
                System.out.println("Le chemin "+repertoire.getPath()+" n'existe pas.");
                files = new File[]{};
            }
        }
        for (File file : files) {
            String fileName = file.getName();
            //System.out.println(fileName);
            Pattern uName = Pattern.compile(this.expressionLogiqueExtensionsTraites());
            Matcher mUname = uName.matcher(fileName);
            bName = mUname.matches();
            if (bName && !this.isInDonnees(file.getPath()) &&!(this.ignoredPaths.contains(file.getPath()))) {
                donnees.add(new Donnee(file.getPath()));
                //donnees.get(0).afficheDonnee();
            }
            if(file.isDirectory()){
                parcours_path(file.getPath());
            }
        }
    }
    
    /**
     * Parcours de l'ensemble des chemins rentrés dans l'attribut paths
     * @throws IOException
     */
    public void parcours() throws IOException{
        for(String path : this.paths){
            parcours_path(path);
        }
    }
    
    
     /**
     * Quatrième méthode de calcul de score d'un fichier en se concentrant sur: ouverture, modification, taille (linéaire)
     * @param d 
     */
    public void calculScore4(Donnee d){
        
    }
    
    
     /**
     * Cette méthode sert à trouver une Donnee de GestionScore
     * @param path
     * @return 
     */
    public Donnee findDonnee(String path){
        for(Donnee d : donnees){
            if(path.equals(d.getPath())){
                return d;
            }
        }
        return null;
    }
}
