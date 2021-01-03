
# A guild to Multi Threading vs. Multi Processing
Research paper on Multi Threading vs. Multi Processing apply on Operating System & Application 


# Automotive Customization System
Automotive Customization System is an individual project using Java. The application allow multiple users get access to the server, and modify the data (which is cars) in the server. The application also allow each user to configurate their car and upload it to the server (please refer to the picture). The server and client object was implemented using Object Oriented Programing to encapsulate the design and access. Link Hash Map is used as the main container for this project (instead of a real database). The update data is write to a text file to simplifier the process. The container can be replace with a database system (SQL Server or My SQL), and will perform the full function of an API. Project can be extend to a full e-commerce page if the following component is add: a better GUI for the clients, payment API (Paypal), and a database system.

![alt text](https://github.com/jackyhuynh/Java-multithreadingResearch/blob/main/Research%20on%20Multithreads%20vs.%20Multiprocess.pdf)

## Technology:
Java, Servlet, Object-Oriented Design, Encapsulation, Data Structures, Algorithm, Apache Tom Cat, Servelet, Client-Sever Implementation

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
What things you need to install the software and how to install them
- Eclipse IDE: The Eclipse IDE is famous for our Java Integrated Development Environment (IDE), but we have a number of pretty cool IDEs, including our C/C++ IDE, JavaScript/TypeScript IDE, PHP IDE, and more. 
- Java Runtime Enviroment and Java Virtual Machine: Java software for your computer, or the Java Runtime Environment, is also referred to as the Java Runtime, Runtime Environment, Runtime, JRE, Java Virtual Machine, Virtual Machine, Java VM, JVM, VM, Java plug-in, Java plugin, Java add-on or Java download.
- Apache Tomcat (called "Tomcat" for short) is an open-source implementation of the Java Servlet, JavaServer Pages, Java Expression Language and WebSocket technologies. Tomcat provides a "pure Java" HTTP web server environment in which Java code can run.

### Installing

A step by step series of examples that tell you how to get a development enviroment running.
Download and Install Java (Java Runtime Enviroment or Java Virtual Machine)
* [Java for Windows](https://java.com/en/download/) - we should download and install JVM before the Eclipse IDE 
Download and Install Eclipse IDE
* [Install Eclipse IDE](https://www.eclipse.org/ide/) - If you haven't downloaded and installed Eclipse IDE yet, here's how to get started.
* [Install Net Bean IDE](https://netbeans.org/features/index.html) - You can use either Eclipse IDE or Net Bean IDE, not need both. If you haven't downloaded and installed Eclipse IDE yet, here's how to get started.
* [Install Apache Tomcat](https://tomcat.apache.org/download-80.cgi) 
There is other IDE that can run Java code, but we must download the JVM and Apache Tomcat for this particular project.

## Running the tests
Explain how to run the automated tests for this system:
### Run Server and test if Server is function properly
- Start server Server/driver/Driver.java
```
package driver;

/*
 *
 * Author :	Truc Huynh
 * Program :	AutomotiveSystem
 */

import java.io.IOException;
import java.net.ServerSocket;
import server.CreateServerConnectionToClient;

public class Driver {
	
	public static void main(String args[])throws IOException 
        {
            CreateServerConnectionToClient server = new CreateServerConnectionToClient(new ServerSocket(5555));
        }
}
```
- Tested complete successfully.

### Simultaneously create three clients and test if they are function properly
- Start client Client/driver/Driver.java

```
package driver;

/*
 *
 * Author :	Truc Huynh
 * Program :	AutomotiveSystem
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import client.DefaultSocketClient;

public class Driver 
{	
	public static void main(String args[])
        {
            String LocalHost = "";
            try
            {
                //Get the LocalHost...
                 LocalHost =  InetAddress.getLocalHost().getHostName();
            }
            catch (UnknownHostException e){
                System.err.println ("Unable to find local host");
            }
            DefaultSocketClient d = new DefaultSocketClient(LocalHost, 5555);
            d.start();
 	 }            
}
```
- Tested complete successfully.

### Upload the data to Server (#1):
- Create multithreading Server that can handle multiple clients. In Image 1, I run one server and three clients. The Server will receive a properties file (data) which upload by the client (#1), parse it, and store it on the server. (Validate data is successfully upload)

![alt text](https://github.com/jackyhuynh/automotiveCustomizationSystem-Java/blob/main/picture/Capture%201.PNG)

- Tested complete successfully.
- After client_T (#1) post (send) the text file (properties file) to the server. Server receive the properties file and parsed the text file to create an instance of Automobile and store it in the Link Hash Map (which is the application database) (Refer to Image 2).

![alt text](https://github.com/jackyhuynh/automotiveCustomizationSystem-Java/blob/main/picture/Capture2.PNG)

### Client Send Request to Server (Client #2)
- Client-T (#2) send a request to the Server for cars configuration. Then Server will send the respond back (instance of Automobile). (Image 3)

![alt text](https://github.com/jackyhuynh/automotiveCustomizationSystem-Java/blob/main/picture/Capture3.PNG)

- Tested complete successfully.

### Client Received Request from Server (Client #2, Client #3)
- Client-T (#2) will get the choice from this instance of Automobile and store it within this Automobile. Picture below show successfully configurate the Car (Upload the data to server successfully) (Image 4)

![alt text](https://github.com/jackyhuynh/automotiveCustomizationSystem-Java/blob/main/picture/Capture4.PNG)

- Tested complete successfully.

### Send and Received Request from Server (Client #3)
- Call Client-T (#3) to configurate a Car after terminate Client-T (#2) to verify if the data is successful upload to the Server. (Image 5)

![alt text](https://github.com/jackyhuynh/automotiveCustomizationSystem-Java/blob/main/picture/Capture5.PNG)

- Tested complete successfully.

### Conclusion
The Server and Clients satisfy basic communication: send and receive request. Server receive upload data and store in its database (instance of Link Hash Map). The clients have ability to insert, update and delete an Automobile. Each test in the picture has been perform 30 to 40 times (depend on what tasks). There is crashing  during the test with the Implementation of Exception Handling(helper class).

## Deployment

Can be deploy as web client and server that can handle multiple clients using Apache Tomcat.

## Architecture UML Design

### Client Diagram:

![alt text](https://github.com/jackyhuynh/automotiveCustomizationSystem-Java/blob/main/picture/ClientUML.gif)

### Server Diagram:

![alt text](https://github.com/jackyhuynh/automotiveCustomizationSystem-Java/blob/main/picture/ServerUML.gif)

## Built With

* [Java Virtual Machine](https://java.com/en/download/)
* [Eclipse IDE for Windows](https://www.eclipse.org/ide/)
* [Net Bean IDE for Windows](https://netbeans.org/features/index.html)
* [Apache Tomcat](https://tomcat.apache.org/download-80.cgi) 

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Truc Huynh** - *Initial work* - [TrucDev](https://github.com/jackyhuynh)


## Format
my README.md format was retrieved from
* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)
See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

