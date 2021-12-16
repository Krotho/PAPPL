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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

/**
 *
 * @author Boulanger
 */
public class Test {
    public static void main(String[] arg) throws IOException, URISyntaxException{
            /*
            Donnee D= new Donnee("D:\\Program Files\\Info\\NETBEANS\\PAPPL\\pom.xml");
            D.afficheDonnee();
            GestionScore gS = new GestionScore();
            for(int i =1;i<5;i++){
                gS.calculScore(D, i);
                System.out.println(D.getScore());
            }
            */


            Interface I = new Interface(150);
            I.gestionInterface();
            for(String s : Interface.gScore.getPaths()){
                System.out.println(s);
            }
            System.out.println(Interface.pallier);
            ChargementApplication ca = new ChargementApplication("testPAPPL2.txt");
            //ca.chargerApplication();
            System.out.println(Interface.pallier);

            File f = new File("testPAPPL3.txt");
            EnregistrerApplication e = new EnregistrerApplication("testPAPPL3.txt");
            //e.enregistrerApplication(I);
        
    }
}
