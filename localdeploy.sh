/Applications/apache-tomcat-6.0.35/bin/shutdown.sh; 
rm -rf /Applications/apache-tomcat-6.0.35/webapps/temulatester*;
mvn clean package;
mv target/temula_tester-0.0.2-SNAPSHOT.war target/temulatester.war;
cp target/temulatester.war /Applications/apache-tomcat-6.0.35/webapps/;
/Applications/apache-tomcat-6.0.35/bin/startup.sh;
