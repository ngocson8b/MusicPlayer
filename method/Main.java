/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method;

import gui.MP3GUI;
import javax.swing.UIManager;


/**
 *
 * @author Dat Pham
 */

public class Main {
    
    public static void main(String[] args) {
       
        // set system look and feel
        try {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        //creat and show GUi
        MP3GUI mp4 = new MP3GUI();
        mp4.setVisible(true);
        //System.out.println("Hello");
   
    }
}