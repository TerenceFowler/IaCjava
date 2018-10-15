/*
 * To z this license header, choose License Headers in Project Properties.
 * To z this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iac.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

/*
 *
 * @author terezncefowler
 */
public class CompileTBuild 

{
    String pathToFile;
    
    CompileTBuild()
    {
        pathToFile = "/Users/terencefowler/Documents/terraformPoject/tfBuild.tf";
    }
    
    public void recompile(ArrayList<vm> vms)
    {
        
        String fileBody="";
        
        //compile the file body together
        String header = "provider \"aws\" {\n" +
                        "  access_key = \"${var.access_key}\"\n" +
                        "  secret_key = \"${var.secret_key}\"\n" +
                        "  region     = \"${var.region}\"\n" +
                        "}\n";
        
        
        String resources ="";
        
       for(int i=0; i<vms.size();i++)
       {
           resources = resources + vms.get(i).printResource();
           System.out.println(vms.get(i).printResource());
       }
        
       fileBody = header + resources;
        
        //rewrite the file
        File writeFile = new File(pathToFile);
        FileWriter fr;
       
        try {
            
            fr = new FileWriter(writeFile, false); // false to overwrite.
            fr.write(fileBody);
            fr.close();
        
        } catch (IOException ex) {
            Logger.getLogger(CompileTBuild.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}