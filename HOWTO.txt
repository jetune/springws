Build with Maven
mvn clean install

Deploy on Tomcat
cp /home/stephane/work/nki/dev/java/projects/springws/web/target/springws-web-1.0-SNAPSHOT.war ~/programs/apache-tomcat/webapps/springws.war

Client request
send-client-request.sh dev
send-client-request.sh test
