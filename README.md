# Cinema

Fullstack app writtem with Java Spring. Compises following features:
* Cinema hall management (creating a hall object, listing all exisiting halls, etc)
* Movie management
* Session managent
* Live search session (using Ajax)
* Live chat (using web sockets)

## Build

### Prerequisites

To run this project you'll need following tools:
* Maven,
* Tomcat,
* Some database manager, for example PostgreSQL.

### 1. Setup database

Create a database.
Configure it by running SQL commands from `src/resources/sql/schema.sql`.

### 2. Configure the project

Set images directory and credentials for accessing database in `src/resources/application.properties`.

### 3. Build web archive

Use following command in the root the project to build the WAR:
``` shell
mvn clean install
```

### 4. Deploy web archive 

First, you need to launch Tomcat:
``` shell
cd [tomcat root]/bin/libexec
./catalina.sh start
```

Then deploy the archive by placing it in `webapps` directory:
``` shell
cp target/cinema.war [tomcat root]/libexec/webapps/ROOT.war 
```

## Resources

* https://www.tutorialspoint.com/spring/spring_web_mvc_framework.htm
* https://www.baeldung.com/freemarker-in-spring-mvc-tutorial
* https://www.javaguides.net/2018/11/spring-mvc-5-hibernate-5-jsp-mysql-crud-tutorial.html
* https://www.baeldung.com/websockets-spring
