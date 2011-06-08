/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bluediamond;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.bluediamond.ui.MainMenu;

/**
 *
 * @author tiziano
 */
public class BlueDiamond {

    public static final double APP_VERSION = 0.1;
    
    private static InputStreamReader converter;
    private static BufferedReader in;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        converter = new InputStreamReader(System.in);
        in = new BufferedReader(converter);
        MainMenu.getInstance().run();
    }
    
    public static String getLine() 
    {
        try {
            return in.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
