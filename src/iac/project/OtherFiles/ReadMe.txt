
This is a Java program for compatibility with multiple platrforms. 

This program assumes that the files in 'OtherFiles' are placed in a directory together. The path in PathToTerraform should be updated to reflect this directory. 

Secondly, a cronjob/crontab/launchd or other mechanism should be created to call the runme.sh scrip periodically. This speed will govern how quickly the staged vms will be launched in aws. 

An aws key and secret must also be added to the terraform.tfvars file. 

An appropriate security group with access to ssh/http/https should be created on aws. The default value for this is MainWebPage, but can be changed on the fly in the GUI.



