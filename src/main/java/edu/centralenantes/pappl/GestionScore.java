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
    private Parametres p=new Parametres(); //Paramètres pour les calculs de scores
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
            B=1;
            C=1.0e-30;
            D=1;
            E=1.0e-10;
            F=1;
            G=1.0e-6;
        }
        
        public Parametres(ArrayList<String> s){
            //this();
            if(s.size()>6){
                A=s.indexOf(0);
                B=s.indexOf(1);
                C=s.indexOf(2);
                D=s.indexOf(3);
                E=s.indexOf(4);
                F=s.indexOf(5);
                G=s.indexOf(6);
            }
        }
    }
    
    /**
     *
     */
    public GestionScore() {
        this.paths = new ArrayList();
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
    
    public void setP(ArrayList<String> s){
        if(s.size()>6){
            p.setA(Double.parseDouble(s.get(0)));
            p.setB(Double.parseDouble(s.get(1)));
            p.setC(Double.parseDouble(s.get(2)));
            p.setD(Double.parseDouble(s.get(3)));
            p.setE(Double.parseDouble(s.get(4)));
            p.setF(Double.parseDouble(s.get(5)));
            p.setG(Double.parseDouble(s.get(6)));
        }
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
    
    public void ajoutDonnee(Donnee d){
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
        Collections.sort(this.donnees,Collections.reverseOrder());
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
        s=p.C*Math.exp(p.B+p.A*((d.getDateOuvert()-d.getDateCrea())/(1+p.D*d.getFreqOuvert())));
        System.out.println(s);
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
        File[] files = new File[]{};
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
    
    public ArrayList<Donnee> getDonneesPallier(int pallier){
        ArrayList<Donnee> alDonnees = new ArrayList();
        for(Donnee d : donnees){
            if(d.getScore()<pallier){
                return alDonnees;
            }
            else{alDonnees.add(d);}
        }
        return alDonnees;
    }
}
