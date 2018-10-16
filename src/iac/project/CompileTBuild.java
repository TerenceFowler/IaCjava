/*
 * This writes all the vm objects to the appropraite file for terraform to apply from
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
    private final String pathToFile;
    
    //initiator
    CompileTBuild()
    {
        PathToTerraform path = new PathToTerraform();
        pathToFile = path.toString();
    }
    
    //recompile creates an output file for terraform
    public void recompile(ArrayList<vm> vms)
    {
        //compile the file body together
        String header = "provider \"aws\" {\n" +
                        "  access_key = \"${var.access_key}\"\n" +
                        "  secret_key = \"${var.secret_key}\"\n" +
                        "  region     = \"${var.region}\"\n" +
                        "}\n";
        
        
        String resources ="";
        
       for(int i=0; i<vms.size();i++)
       {
           resources = resources + vms.get(i).toString();
           System.out.println(vms.get(i).toString());
       }
        
       String fileBody = header + resources;
        
        //rewrite the file
        File writeFile = new File(pathToFile);
        FileWriter fr;
       
        try {
            
            fr = new FileWriter(writeFile, false); // false to overwrite file
            fr.write(fileBody);
            fr.close();
        
        } catch (IOException ex) {
            Logger.getLogger(CompileTBuild.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}