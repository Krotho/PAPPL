/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
