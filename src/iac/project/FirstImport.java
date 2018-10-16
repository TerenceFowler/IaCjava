/*
 * Here we import any vms already being controlled by terraform
 */
package iac.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * used to  pull in vms under terraform
 * @author terencefowler
 */
public class FirstImport 
{
    
    private final String pathToFile;
    
    FirstImport()
    {
        PathToTerraform path = new PathToTerraform();
        pathToFile = path.toString();
    }
    
    public ArrayList runImport()      
    {
        BufferedReader br;
        String line;
        String type="",name="",securityGrp="", ebsSize="";
        ArrayList output = new ArrayList<vm>();
        

		try // this helps the user know they killed the program if something goes wrong
		{
			br = new BufferedReader(new FileReader(pathToFile));
			while (br.ready()) // tests if there's still more to read from the file
			{	
				line = br.readLine(); 
				if(line.contains("resource"))
                                {
                                    name = line.substring(24);
                                    StringTokenizer stk = new StringTokenizer(name, "\"");
                                    name = stk.nextToken();
                                    System.out.println("string tokenizer name" +name);
                                }
                                
                                
                                
                                if(line.contains("instance_type"))
                                {
                                    type = line;
                                    
                                    StringTokenizer stk = new StringTokenizer(type, "\"");
                                    stk.nextToken();
                                    type = stk.nextToken();
                                    
                                    System.out.println("type string .." + type);
                                    switch (type)
                                    {

                                        case "t2.micro" :
                                        type = "Micro";
                                        break;

                                        case "t2.small" :
                                        type = "Small";
                                        break;

                                        case "t2.medium":
                                        type = "Medium";
                                        break;

                                        case "t2.large":
                                        type = "Large";
                                        break;


                                    }
                                    
                                    
                                    System.out.println("string tokenizer type" +type);
                                    
                                }
                                
                                if(line.contains("security_groups"))
                                {
                                    securityGrp = line;
                                    
                                    StringTokenizer stk = new StringTokenizer(securityGrp, "\"");
                                    stk.nextToken();
                                    securityGrp = stk.nextToken();
                                    output.add(new vm(name, type, securityGrp,false));
                                }
                               

                            
			}//end while
			br.close();//end of buffer reader

		}//end try

		catch (FileNotFoundException fnfe) // gives output for any errors caught in try 
		{
			System.out.println("File not found");
		}
		catch (IOException ioe)
		{
			System.out.println("No more records");
		}
		// ..... END Buffered Reader ...... 
    
        return output;

    
}}
