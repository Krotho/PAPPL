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
import java.util.ArrayList;
/**
 *
 * @author asjou
 */
public class TestAS {

    /**
     *
     * @param arg
     * @throws IOException
     */
    public static void main(String[] arg) throws IOException{        
        ArrayList<String> path = new ArrayList<>();
        path.add("C:\\Users\\asjou\\Downloads");
        //path.add("C:\\Users\\asjou\\Downloads\\TP1\\Interaction TP1.pdf");
        ArrayList<String> ext = new ArrayList<>();
        ext.add(".pdf");
//        ext.add("png");
        GestionScore gS = new GestionScore(path,ext);
        ArrayList<String> pathInterdit = new ArrayList<>();
        pathInterdit.add("C:\\Users\\asjou\\Downloads\\TP1\\Interaction TP1.pdf");
        pathInterdit.add("C:\\Users\\asjou\\Downloads\\Diego libre dans sa tête");
        gS.setIgnoredPaths(pathInterdit);
        gS.parcours();
        gS.getDonnees().forEach(d -> {
            System.out.println(d.getPath());
        });
    }
}
