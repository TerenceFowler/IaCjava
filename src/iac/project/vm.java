/*
 * This is a vm object and contains all the elements that will 
 * eventually be passed to terraform
 */
package iac.project;

/**
 *
 * @author terencefowler
 */
public class vm 
{
    private String instance_type;
    private String name;
    private String user_data;
    private String security_groups;
    private boolean built;
    
    
    //create default vm for testing
    vm()
    {
        built = false;
        instance_type = "t2.micro";
        name = "micro";
        security_groups = "MainWebPage";
        user_data="${file(\"miniServer.sh\")}";
        System.out.println("vm created with default method... not advised...");
        
    }
    
    //create vm
    vm(String nameStr, String typeStr, String securityGrp,boolean isBuilt)
    {
        built = isBuilt;
        System.out.println("vm building..."+nameStr+" "+ typeStr);
        name = nameStr;
        security_groups = securityGrp;
        
        switch (typeStr)
        {
            
            case "Micro" :
            instance_type = "t2.micro";
            user_data="${file(\"miniServer.sh\")}";
            break;
            
            case "Small" :
            instance_type = "t2.small";
            user_data="${file(\"smallServer.sh\")}";
            break;
            
            case "Medium":
            instance_type = "t2.medium";
            user_data="${file(\"mediumServer.sh\")}";
            break;
            
            case "Large":
            instance_type = "t2.large";
            user_data="${file(\"largeServer.sh\")}";
            break;
            
        }
    }
        
    @Override
    public String toString()
    { 
        String str=
                "resource \"aws_instance\" \""+ name +"\" {\n" +
                "  ami = \"ami-a0cfeed8\"\n" +
                "  instance_type = \"" + instance_type +"\"\n" +
                "  security_groups = [\""+security_groups+"\"]\n" +
                "  key_name = \"IaC-security\"\n" +
                "  user_data=\""+user_data+"\"\n" +
                " }\n";
        
        return str;
    }
    public String getName()
    {
        //System.out.println("getName returned name...");
        return name;
    }
    public String getNameType()
    {
        //System.out.println("getName returned nameType...");
        String builtStr;
        if(built){
            builtStr="Running";
        }
        else
        {
            builtStr="Staged";
        }
        return name + "      " + instance_type +"      "+ builtStr;
    }
}
