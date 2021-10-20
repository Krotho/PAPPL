/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.centralenantes.pappl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<String> extensionsTraites;
    
    public GestionScore() {
        this.paths = new ArrayList();
        //Le chemin par défaut est celui du dossier téléchargements
        paths.add("C:\\Downloads");
        this.donnees = new ArrayList();
        this.extensionsTraites = new ArrayList();
    }
    
    public GestionScore(ArrayList<String> path) {
        this.paths = path;
        this.donnees = new ArrayList();
        
        this.extensionsTraites = new ArrayList();
        //Par défaut on traite l'extension pdf
        this.extensionsTraites.add(".pdf");
    }
    
    public GestionScore(ArrayList<String> paths, ArrayList<String> extensionsTraites) {
        this.paths = paths;
        this.donnees = new ArrayList();
        this.extensionsTraites = extensionsTraites;
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
    
    
    
    public void ajoutDonnee(String path) throws IOException{
        Donnee d = new Donnee(path);
        donnees.add(d);
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
                System.out.println(mUname.group());
                donnees.add(new Donnee(file.getPath()));
            }
        }
    }
    
    public void parcours() throws IOException{
        for(String path : this.paths){
            parcours_path(path);
        }
    }
    
}
