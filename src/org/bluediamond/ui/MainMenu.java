/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bluediamond.ui;

import org.bluediamond.functionality.IFunctionality;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.bluediamond.BlueDiamond;

/**
 *
 * @author tiziano
 */
public class MainMenu {
    
    private IFunctionality[] functionality=new IFunctionality[0];
    
    
    private static MainMenu instance;
    
    private MainMenu()
    {
    }
    
    public static MainMenu getInstance()
    {
        if(instance==null)
            instance=new MainMenu();
        
        return instance;
    }
    
    public void run()
    {
        System.out.println(String.format("---- BlueDiamond v%.2f ----\n", BlueDiamond.APP_VERSION));
        
        int count=1;
        for(IFunctionality f : functionality)
        {
            System.out.println(String.format("%d) %s",count++,f.getName()));
        }
        System.out.println("\nq) Quit");
        System.out.print("\nYour Choice: ");
        String line=BlueDiamond.getLine();
        
        if(line.equals("q"))
            System.exit(0);

        try            
        {
            Integer i=Integer.valueOf(line);
            int num=i.intValue()-1;
            if(num>=0 && num<functionality.length)
                functionality[num].run();
        }
        catch(NumberFormatException e)
        {}
        
        run();
        
    }
}
