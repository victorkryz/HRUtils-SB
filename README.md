# HRUtils-SB
Spring Boot application working with [*HR-Utils PL/SQL package*](https://github.com/victorkryz/HR-Utils)

## About
The project uses spring *JdbcTemplate*, *SimpleJdbcCall*, *StoredProcedure* classes, plain JDBC API
to call *HR-Utils PL/SQL package* procedures and functions and processing of the execution results.

One of the features is an operating on PL/SQL packages user types that become available since
Oracle server version 12.1 (see topic [*About Using PL/SQL Types*](http://docs.oracle.com/database/122/JJDBC/JDBC-reference-information.htm#GUID-E77C2AE8-E22B-48BF-A4CB-010CBC8FE7C2) 
of *'Oracle Database JDBC Developer's Guide'*). <sup id="a1">[1](#f1)</sup>


## Prerequisites
- Java 8
- Oracle Server (version 12.1 or higher) with installed HR schema
- Installed  [*HR-Utils PL/SQL package*](https://github.com/victorkryz/HR-Utils)
- Maven 3.3.9 


## Building an testing:
The project's building is based on Maven configuration unit and can be built
and tested using Maven command line.

Use the next commands:

- to build project: `mvnw clean compile -o`
- to start unit tests: `mvnw test -o`

Before starting tests, do configure actual Oracle connection in file "src\main\resources\META-INF\spring\database.properties":

```properties
 database.url=jdbc:oracle:thin:@//host/service
 database.username=hr
 database.password=...
 ```

> There's some specific with getting oracle JDBC drivers from the Oracle Maven Repository 
<sup id="a2">[2](#f1)</sup>



#### Project tested on the follow configuration:

Application is on Windows 7 (64-bit) against to Oracle Linux Server 7.2 (64-bit) with
Oracle Database 12c Enterprise Edition Release 12.1.0.2.0.




---
><b id="f1">1</b> Consequently, some set of the database calls within the project does not work correctly with Oracle servers version lower 12.1. [↩](#a1)


><b id="f2">2</b> There's some specific with getting oracle JDBC drivers from the Oracle Maven Repository
>(see topic [*How to get Oracle JDBC drivers and UCP from Oracle Maven Repository*](https://blogs.oracle.com/dev2dev/entry/how_to_get_oracle_jdbc)).

> You might download Oracle JDBC Drivers directly [*here*](http://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html) and install them into maven repository using maven command line, e.g: [↩](#a2)

    mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle.jdbc -DartifactId=ojdbc6 -Dversion=11.2.0.4 -Dpackaging=jar
    mvn install:install-file -Dfile=ojdbc7_g.jar -DgroupId=com.oracle.jdbc -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar 

