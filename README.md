# NETA-

How to Run:
-Download HSQLDB 2.6.0 from http://hsqldb.org/
-Instead of in-memory mode I used server mode of hsql.
-To open server database, do these:
  1.Add the server.properties in netaş_database_configuration file into the hsqldb/
  2.Go to the /hsqldb directory with command prompt for example \>cd C:\hsqldb-2.3.4\hsqldb
  3.Run the command java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 studentdb
  4.Do not close the command prompt otherwise database server will close.
  5.Afterwards go to the /hsqldb/bin directory (not with command prompt) and open runManagerSwing.bat 
  6.In the connect UI choose type as HSQL  Database Engine Server and url:jdbc:hsqldb:hsql://localhost/studentdb   , user:SA and leave password empty
  7.Go to File -> Open Script and choose create_table file in netaş_database_configuration and execute script.
  8.Now we have the STUDENT_TABLE table.
  9.You can close HyperSQL Database Manager but do not close the command prompt window that leaves the database server open.
-To open java project I used Eclipse as my IDE.
-Download the project folder and run /project/src/main/java/com/netas/project/ProjectApplication.java
-This starts the Server side and also connects to the studentdb database.
-To create React file use the command npx create-react-app my-app
- Inside my-app/  replace package.json file with the file that I provided in react_Files/ or just add the line "proxy": "http://localhost:8080". Without it we can not connect to our spring rest service that is running on port 8080.
- Delete all files except .vs in my-app/src and add the files that I provided in react_Files/src
- Before we start my-app we need to install some node modules.
   1. npm install --save bootstrap@4.1.3 react-cookie@3.0.4 react-router-dom@4.3.1 reactstrap@6.5.0
   2. npm install react-input-mask --save
   3. npm i react-select-me --save
   4. npm install primereact --save
   5. npm install primeicons --save
   I may have forgot some of the modules, if so plase detect them and install them.
-Now we are ready to start my-app, before that do not forget to open studentdb server and spring boot application
-go to my-app/ in command prompt and run npm start

Requirements that are not satisfied:
1.React Hook is never used.
2.Some validations are bugy.
3.Junit test is not implemented. Could not figure it out how it worked and there was no time left.
4.There are no TDD or BDD.
5.Docker Machine is not used.
