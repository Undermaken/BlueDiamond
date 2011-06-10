/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bluediamond.ui;

import org.bluediamond.functionality.IFunctionality;
import org.bluediamond.BlueDiamond;
import org.bluediamond.functionality.TableFinder;

/**
 *
 * @author tiziano
 */
public class MainMenu {
    
    private IFunctionality[] functionality= {new TableFinder()};
    
    
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
        while(true)
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

            int num=-1;
            try            
            {
                Integer i=Integer.valueOf(line);
                num=i.intValue()-1;
            }
            catch(NumberFormatException e)
            {
                continue;
            }
            if(num>=0 && num<functionality.length)
                functionality[num].run();
        }
        
    }
}
