/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bluediamond.utilities;

import sun.misc.Regexp;



/**
 *
 * @author tiziano
 */
public abstract class StringUtils {
   public static boolean isEmpty(String s) {
       return s==null || s.length()==0;
   }
   
   public static String NoSpaces(String s)
   {
       return s.replaceAll("[\\t+,\\s+,\\n,\\r]", "");
   }
   

}
