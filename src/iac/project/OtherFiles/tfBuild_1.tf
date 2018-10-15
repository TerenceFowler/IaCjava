provider "aws" {
  access_key = "${var.access_key}"
  secret_key = "${var.secret_key}"
  region     = "${var.region}"
}
resource "aws_instance" "test" {
  ami = "ami-a0cfeed8"
  instance_type = "t2.micro"
  security_groups = ["MainWebPage"]
  key_name = "IaC-security"
  user_data="${file("miniServer.sh")}"
 }
