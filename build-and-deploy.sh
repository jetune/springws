#!/bin/sh

mvn clean install

\cp -f /home/stephane/work/nki/dev/java/projects/springws/server/target/springws-server-1.0-SNAPSHOT.jar lib/;
\cp -f /home/stephane/work/nki/dev/java/projects/springws/client/target/springws-client-1.0-SNAPSHOT.jar lib/;
\cp -f /home/stephane/work/nki/dev/java/projects/springws/web/target/springws-web-1.0-SNAPSHOT.war ~/programs/apache-tomcat/webapps/springws.war;

