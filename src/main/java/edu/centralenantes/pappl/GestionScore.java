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

/**
 *
 * @author asjou
 */
public class GestionScore {
    private List<String> path;
    private List<Donnee> donnees;
    private Parametres p;
    
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
        this.path = new ArrayList<>();
        //Le chemin par défaut est celui du dossier téléchargements
        path.add("C:\\Downloads");
        this.donnees = new ArrayList<>();
        this.p= new Parametres();
    }
    
    public GestionScore(List<String> path) {
        this.path = path;
        this.donnees = new ArrayList<>();
        this.p= new Parametres();
    }    

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
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
    
     /**
     * Troisième méthode de calcul de score d'un fichier en se concentrant sur: ouverture, modification, taille (linéaire)
     * @param d 
     */
    public void calculScore4(Donnee d){
        
    }
}
