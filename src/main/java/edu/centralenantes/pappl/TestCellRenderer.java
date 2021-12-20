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

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Boulanger
 */
public class TestCellRenderer extends DefaultListCellRenderer  {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Donnee d =Interface.gScore.getDonnees().get(index);
            double score =d.getScore();
            if( score <0 ){
                setBackground(Color.BLUE);
            }
            else if( score>=0 && score <300 ){
                setBackground(Color.cyan);
            }
            else if(score >=300 && score<1000){
                setBackground(Color.green);
            }
            else if(score>=1000 && score <2000){
                setBackground(Color.yellow);
            }
            else if(score>=2000 && score <3000 ){
                setBackground(Color.orange);
            }
            else{
                setBackground(Color.red);
            }
            return c ;
        }
    
}
