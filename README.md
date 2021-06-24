# NETA-
Demo Video Link: https://youtu.be/c_8OJPvlagg

How to Run:
1. Download HSQLDB 2.6.0 from http://hsqldb.org/
2. Instead of in-memory mode I used server mode of hsql.
3. To open server database, do these:
  1.Add the server.properties in netaş_database_configuration file into the hsqldb/
  2.Go to the /hsqldb directory with command prompt for example \>cd C:\hsqldb-2.3.4\hsqldb
  3.Run the command java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 studentdb
  4.Do not close the command prompt otherwise database server will close.
  5.Afterwards go to the /hsqldb/bin directory (not with command prompt) and open runManagerSwing.bat 
  6.In the connect UI choose type as HSQL  Database Engine Server and url:jdbc:hsqldb:hsql://localhost/studentdb   , user:SA and leave password empty
  7.Go to File -> Open Script and choose create_table file in netaş_database_configuration and execute script.
  8.Now we have the STUDENT_TABLE table.
  9.You can close HyperSQL Database Manager but do not close the command prompt window that leaves the database server open.
4. To open java project I used Eclipse as my IDE.
-Download the project folder and run /project/src/main/java/com/netas/project/ProjectApplication.java
5. This starts the Server side and also connects to the studentdb database.
6. To create React file use the command npx create-react-app my-app
7. Inside my-app/  replace package.json file with the file that I provided in react_Files/. This way it will now how to connect to the spring boot service and also install npm packages that I used
8. Delete all files except .vs in my-app/src and add the files that I provided in react_Files/src
9. Now we are ready to start my-app, before that do not forget to open studentdb server and spring boot application
10. Go to my-app/ in command prompt and run npm start
12. It should open the website on localhost:3000

Requirements that are not satisfied:
1.React Hook is never used.
2.Some validations are bugy.
3.Junit test is not implemented. Could not figure it out how it worked and there was no time left.
4.There are no TDD or BDD.
5.Docker Machine is not used.
