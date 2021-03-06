# Technical Assignment on MAS API

This is the technical assignment to call MAS API to retrieve the bank and financial companies interest rates.  

## Requirements Overview

The requirement is for user to compare rates and check the trend.  For this a UI is almost a must as it will be more user friendly.  Ideally a graphs or charts is needed (open source chart like chart.js can be used).  However, since the business requirements specifically mention no requirement to plot graphs or chart, it is not included as the assumption is user are evaluating other graphs or charts engine hence just want to see the raw data as of now.

This project is created using Spring MVC + Freemarker.  It follows the web MVC approach with the usage of JQuery in the markup.  I can't make the backend work to call the API, hence I use JQuery Ajax and send the response over to the server for processing.  

The application layer will be MVC plus a business layer (tsk.techassignment.process).  This is to maintain codes at different layer and can be split or package if needed.  Iterator design pattern is mostly used for calculating the average.  

**Updated (1 May 2018)**
After a good night rest, I re-think and was not happy with the business layer implemented.  Hence I perform some rework on it.  Mainly,
- Refactor the processor classes such that it uses Chain-of-responsibility pattern as the original processing is actually broken down into 3 steps (Convert Json String to Java Object, calculate Average, analyze trend)
- Create a Factory class to initialize and set up the processor classes

### Requirements Met

* Specify Dates in format to get the data
Since retrieving of data is via Ajax, date format validation is only performed in the front end.  Furthermore, with the usage of JQuery UI Datepicker, incorrect entry by the user is minimise.  For the response data send to the server, there is minimum check and mostly relies on can the Json be parse to Java object.

* Compare financial companies rates against bank rates by months
In the Detail section, all the data are group by month, from latest to earliest.  The financial companies rate that is higher will be highlight in **bold** and *italic*.  Once user review, it can be further fine tune to have other ways of highlighting it at UI level.

* Compare overall average of financial companies rate against bank rates
This is performed by averaging the rates of a particular fix deposit or saving deposit for the period specify by user.  It is display at the Summary/Average Rate section.

* To find out if interest rates slopes are on an upward or downward trend
This is the part where a graph should be use.  It is quite vague on how a upward or downward trend should be. For now, I assume this is done by comparing the rates of the earliest date user choose against the rates of the latest date.  There will be wording mentioning it is an upward or downward trend (Up / Down / No Change / NA).  For example, Bank Fix Deposit for 3 Month on Jan 2017 is 0.15, on Dec 2017 is 0.14.  Hence it is consider a downward trend.  NA is used when the value is not available.  This is shown at Summary/Trend section.

## Getting Started

The project is develop on Eclipse using maven as the build tool.  A war file is generated once build and will needs to be deploy in Tomcat for it to run.

### Prerequisites

To run this software, the following must be installed,

```
Apache Maven 3.5.3 - for build and compilation
Apache Tomcat 7.0.86 - as the Java container
Any modern browser 
JDK 1.8
```

### Installing

Step 1 - ensure Maven and JDK 1.8 are installed and can be run

```
JAVA_HOME needs to be setup for Maven to execute

Download the project to a local directory

Open a command prompt and navigate to the folder where the pom.xml exist, and type mvn clean install
```

Step 2 - deploying to tomcat (Updated 1 May 2018)

```
Ensure the tomcat installed is working.

Once compile finish, you should see a success message and also a line mentioning where is the war file located. For example,

[INFO] --- maven-install-plugin:2.4:install (default-install) @ tsk-ta-mas ---
[INFO] Installing E:\personal\Interview\Interview_20180428\technical\Code\tsk-techassignment-mas\target\tsk-ta-mas-0.0.2-SNAPSHOT.war to C:\Users\Tan\.m2\repository\tsk\007\techassignment\tsk-ta-mas\0.0.1-SNAPSHOT\tsk-ta-mas-0.0.2-SNAPSHOT.war
[INFO] Installing E:\personal\Interview\Interview_20180428\technical\Code\tsk-techassignment-mas\pom.xml to C:\Users\Tan\.m2\repository\tsk\007\techassignment\tsk-ta-mas\0.0.1-SNAPSHOT\tsk-ta-mas-0.0.2-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.914 s
[INFO] Finished at: 2018-04-30T03:04:36+08:00
[INFO] ------------------------------------------------------------------------

Copy the war file from the directory and paste into tomcat/webapps folder. Once copied, start up tomcat.
```

Step 3 - Verifying deployment 

Once tomcat is started, open up a browser and navigate to http://localhost:8080/tsk-ta-mas-0.0.2-SNAPSHOT/

Ensure you can connect to MAS API site and try entering a date range and hit the *Retrieve Interest Rate* button.

**Take note the URL has changed to reflect the latest amendment**

## Running the tests

JUnit test will be triggered whenever *mvn clean install* is run.  You can also trigger the test only by using *mvn test* command.

### Brief Overview of the tests

JUnit Test are mainly performed on classes that has business logic like averaging the rates, determine the trend or comparing Bank or Financial Companies provide the better rate.

```
For example, for AggregateRecordsTest.java, 2 tests are written;
1 to test that the record values will not be clear when retrieving it for Average calculation.

The other test is to test the correctness of the calculation with a couple of null values throw in to ensure the logic can handle it.
```

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
