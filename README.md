empl-reg-app
============

Solution for last SWE 645 assignment

Swe645 homework 4 Instructions (November 26, 2012)

WSDL: http://localhost:8080/jhu5_session/EmployeeWS/EmployeeSearchImpl?wsdl
Ws client: http://localhost:8080/jhu5_ws_client/SweMvc/Employees
Web app (w/o web service): http://localhost:8080/jhu5/SweMvc/ShowEmployees


Assumptions:
-  Familiarity with JBoss application server (i.e. start/stop server and deploy *.ear files)
-	Familiarity with MySQL server (i.e. start/stop server and run *.sql scripts)
-	Familiarity with creating a data source by installing a JDBC driver as a module 

Instruction:
1.	To set up the database for this application, run setup_db.sql in MySQL server, which will set up tables and wire them together. Also it will populate these tables with data. The following properties are: 
-	DB name: swe645
-	DB username/password: sweuser/sweuser
-	Table names: employee, supervisor, location

2.	To set up the data source with its JNDI name as ‘java:/Swe645DS’:
a.	Include the jdbc jar file, mysql-connector-java-5.1.17-bin.jar, in {jboss_home} /modules/com/mysql/main/ directory.
b.	Edit module.xml to use this mysql jar file (I have provided this file).
c.	Copy standalone.xml into {jboss_home} /standalone/ directory.

Once tables are set up and the data source is set up as Swe645DS, deploy the jhu5.ear in the JBoss AS.

Then go to this link: http://localhost:8080/jhu5/SweMvc/ShowEmployees
