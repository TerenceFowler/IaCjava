#!/bin/bash
yum update -y
yum install git -y
git clone https://github.com/TerenceFowler/iac-web.git
yum install httpd -y
cp -a /iac-web/. /var/www/html/
mv /var/www/html/mediumVM.html /var/www/html/index.html
service httpd start
