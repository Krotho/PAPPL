/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asjou
 */
public class GestionScore {
    private List<String> path;
    private List<Donnee> donnees;
    
    public GestionScore() {
        this.path = new ArrayList<String>();
        //Le chemin par défaut est celui du dossier téléchargements
        path.add("C:\\Downloads");
    }
    
    public GestionScore(List<String> path) {
        this.path = path;
    }    

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
    
    
}
