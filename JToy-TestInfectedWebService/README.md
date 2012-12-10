## About this Project

This project presents a simple SOAP-based web service for generating transaction identifier values, developed in Java.  What?  You heard me.  A web service service that generates transaction identifier values.  What's a transaction identifier value you ask?  Simple - it's simply a random string of characters and numbers.  Why would I need to invoke such a service you ask?  Simple - let's say for example you have a complex enterprise Java web-based application that is designed and built across a set of tiers: client, web, business, data.  Let's say a user clicks a button on one of the pages.  The first thing that might happen is that a servlet filter might do something with the request (decorate it or something; or check for the presence of an SSO token), then the request flows to a servlet, the servlet gets a handle to a business delegate and invokes a method on it, the business delegate is just serving as a remote proxy to a stateless session EJB (SLSB) and so now the SLSB is invoked; the SLSB invokes some methods on a DAO, the DAO invokes some database stored procedures and then the thread of control unwinds and a response is generated and sent back to the user's browser.  There's a lot happening there.  Each component mentioned will probably do some form of logging (ad-hoc and/or deliberate).  Can you imagine being the operations person in charge of trying to find all the log statements that are associated with a particular button click?  What a nightmare.  There will be nothing to meaningfully group the log statements together, and, they'll probably be scattered across different physical machines (the servlet container, the application server, the database server).

Enter the transaction identifier service.  At the start of the chain of processing for that button click - the first component in the chain will invoke the transaction identifier service to get a unique transaction identifier value.  This value will be passed to each component down the chain.  Every component that writes to the logging system will pass this transaction identifier value.  The transaction identifier should be written as part of the log statement.  Voila!  Now you have a way to group a collection of log statements, and you now have the ability to effectively review the audit trail of a request (aka transaction) - from start to finish.  To deal with the pain of having log statements spread across different physical machines is still an issue - one that can be solved by created a logging web service that can centralize where log statements are physically persisted...but that's another story.

Okay, back to the topic at hand.  Now you know what a transaction identifier service is.  It's a pretty boring service.  Essential.  But boring.  Good thing it's not what this sample code project is about.

This sample code project is about being <a href="http://junit.sourceforge.net/doc/testinfected/testing.htm">test infected</a>.  This sample code project is about demonstrating how you can deeply test a web service (in this case one built using Spring) - testing each component in complete isolation and testing components when they are assembled.  Not only is the Java source tested, but so is the Castor marshaling configuration files, and the Spring configuration files.  There are also automated tests to ensure the canonical XSDs are indeed well-formatted and schema-valids.  There is essentially nothing about the artifacts that constitute this web service that are not unit or integration tested.

## 'Business Value' of Testing

This is a no brainer.  The later in the cycle defects are found, the more expensive they are to fix.  Everybody knows this.  What I hope to achieve with this sample code project is to provide an example for unit and integration testing a web service, and to demonstrate to what extent the artifacts of a system can be tested (i.e. there's more to test than just the pure Java source; you need to test your configuration-related artifacts as well).

## Unit Testing vs Integration Testing

If you're not asking what the difference is between a "unit test" and an "integration test" - you should be.  Try Googling it and coming back with a definitive, unambiguous demarcation.  Without question, if you Google "integration test" - you're going to come across articles and blogs that discuss "system integration testing."  I.e. this is when you assemble your application, and you deploy it into the system integration environment, and you make sure your application works properly and plays nicely amid all the other applications that are deployed, running and consuming competing resources.  Yes, this is a valid definition.  But this definition of integration testing is not what I'm talking about here.  I'm talking about software-level integration testing; not system-level.  You see - I told you this can be very confusing.  The other obvious question is "what is a unit test?"  If you go ask 10 of your co-workers to define "unit testing" - don't be surprised if you get 10 different answers.  The frustrating part is not nobody is technically wrong because there is no industry standard definition of "unit testing" that I am aware of.

So, at the end of the day, unit testing and integration testing are inherently ambiguous when spoken-about in absence of context.  My goal is that this sample code project provides the context, or, at least one concrete example of context, necessary to speak unambiguously about unit testing and integration testing.

## Unit Testing and Integration Testing Defined (by me, well, not really)

Okay - so I'm going to cheat.  I'm going to steal, er, borrow what J.B. Rainsberger states in his presentation: "Integration Tests are a Scam." [http://www.infoq.com/presentations/integration-tests-scam]  To paraphrase Joe, he states that a unit is: "...something that is interesting enough on its own that you would want to test it in isolation..."  Got that?  Somewhat vague, I agree.  I view this definition as more of a guide than anything.  Most of the time I find that a "unit" is usually a method in a class.  If the method in question uses collaborating objects to get its job done, you'll want to mock or stub those out so that you're not testing the collaborator; you only want to test the method (aka unit) at hand.  

In contrast, integration tests are meant to test units assembled (integrated) into components, and ensure they work correctly, as intended.  In this sample code project, there are the following assembled, integrated components:

+ The web service (generates transaction identifier; provides SOAP/HTTP interface)
+ Web service client (provides API for clients to easily invoke web service)

My integration tests look and go as follows:
	
	(1) Compile and build web service WAR file
	(2) Start Java EE container and deploy WAR file
	(3) Assemble web service client objects (which leverage client-side Spring WebServiceTemplate)
	(4) Execute JUnit-style test case methods; within these test methods the web service client objects are being invoked (which in turn will invoke the web service using SOAP over HTTP) and assertions are performed
	(5) Stop Java EE container
	
All of my unit tests look like traditional unit tests - I'm simply using: JUnit, XMLUnit, EasyMock, Spring-WS-test.  The unit tests run in an automated fashion.  They can be launched from your IDE, or from the Ant build script.  If launched from Ant, you get the added benefit that an HTML-based unit test report is created, as well as a Cobertura coverage being generated (which are nice when your build is incorporated within a continuous integration system and you can have the reports published to a web server where project managers and developers alike are afforded deep knowledge into the progress of the code construction and quality).

## Notables about this Project

+ I am using Cargo (from Codehaus) as a means of starting a Java EE container before running integration tests, and then stopping the container.
+ I am using Spring-WS-test to unit test my client-side and server-side Spring configuration files
+ I am using EasyMock to achieve complete unit testing coverage of my Java source
+ I am using XMLUnit to unit test components that produce XML as part of their contract
+ Of course I'm using JUnit as the foundation of both the unit tests and integration tests
+ The execution of my unit and integration tests are driven from Ant (completely automated; no manual human involvement whatsoever)
+ The execution of the unit tests and integration tests produce reports in their own, separate folders (see 'Explanation of folders and files' below)
+ If you want, you can functionally test the web service using soapUI using the configuration file found in /soapUI/ folder
+ Unit tests that are tests for Java code, the tests reside in mirror'd package structure within the /src/test/java folder.  However, unit tests for non-Java targets (like Castor and Spring configuration files) simply reside in a "unittest" package within the /src/test/java folder.
+ The integration tests leverage JUnit's @BeforeClass and @AfterClass annotations.  Within concrete integration test classes, i.e. those that test the web service deployment within a specific JEE container, a method will be annotated with @BeforeClass to perform the expensive, and one-time-only task of starting the JEE container.  The container will be stopped in the method annotated with @AfterClass.
+ The /docs/ folder contains a convenience HTML file to make it easy to access the reports that are generated by some of the Ant targets
+ The /docs/ folder contains a cut at an FMEA [http://en.wikipedia.org/wiki/Failure_mode_and_effects_analysis] that I used as a conceptual inform for creating my unit and integration tests.

## Testing to the Interface

I always attempt to "test to the interface" - just as one codes to an interface, you should test to the interface as well.  In such cases, I following the typical pattern of having test methods sitting in abstract classes, the "setUp()" method invoking an abstract method that returns a concrete instance of the SUT (system-under-test).  Concrete test sub-classes are then created for each implementation of the interface being tested; each concrete test sub-class extends the abstract test class, and implements the abstract method that returns an instance of itself.  JUnit will run each of the test methods defined in the abstract class for each of the concrete sub-classes.  This is a fairly standard pattern in the testing world, and I follow it here in this sample code project.  

I also follow this idiom for my integration tests as well.  The test methods of the integration test are defined once in an abstract class.  There exists a concrete sub-class for each JEE container in which we want to test the web service in.  Each sub-class simply exists to report the location of the locally installed JEE container and define any necessary parameters for the container (like the servlet port it should listen on).

## Apprehension with Interaction-based (Mock) Testing

The first thing that jumped out at me as I researched interaction-based testing using mock objects was that the tests would be coupled to the implementation of the unit-under-test (UUT).  I don't like this one bit.  I don't like knowing that my tests could break after refactoring the implementation but left the contract of the unit alone.  This is crazy and goes against everything I know and believe in good object oriented design (coding to interfaces and all that jazz).  I was happy after reading Martin Fowler's article: "Mocks Aren't Stubs" [http://martinfowler.com/articles/mocksArentStubs.html].  I was happy because he affirmed my own angst with mock-based testing (see the section "Coupling Tests to Implementations" in his article).  However with that said, I understand that interaction-based testing has its uses.  There are cases where traditional (Fowler calls it "classical"), state-based unit tests can fall short and interaction-based testing is the appropriate choice.  For example, there might be behavior that requires testing but there may not be any observable state change within the UUT.  This would be a case in which an interaction-based test would work better.  Fowler basically agrees with this too - that there are cases where interaction-based testing is appropriate compared to state-based.  In this project, I have generally used interaction-based (mock-based) testing for those units that leverage "complex" collaborators.  I.e. collaborators that would be onerous to stub.  In other cases I have leveraged classical testing and stubbed out the collaborators.

## Castor Configuration:

The Castor configuration is broken down into 4 files (within /src/main/config/castor):
+ castor-commonTypes.xml - defines XML/Java mapping for types common (used-by) to both client and server command objects
+ commands/gentxnidcmd/castor-GenTxnIDCmd.xml - defines XML/Java mapping that is shared between both client and server command objects
+ commands/gentxnidcmd/castor-GenTxnIDClientCmd.xml - empty definition that extends definition from castor-GenTxnIDCmd.xml so that we can marshal/unmarshal to and from client command objects
+ commands/gentxnidcmd/castor-GenTxnIDServerCmd.xml - empty definition that extends definition from castor-GenTxnIDCmd.xml so that we can marshal/unmarshal to and from server command objects
	
This elaborate set of mapping files are needed to stay DRY (Don't Repeat Yourself) - here's what happens when a client application creates a client command object and calls its 'execute()' method:	

(1) Client creates client command object and sets some data on it (via its setters)
(2) Client invokes 'execute()' method on client command
(3) The implementation of the 'execute()' method is to invoke the delegate instance
(4) The delegate instance marshals its "owning" client command into XML using the castor-GenTxnIDClientCmd.xml (which extends from the castor-GenTxnIDCmd.xml config) config and invokes the remote web service (using Spring-WS's WebServiceTemplate)
(5) The web service endpoint receives the request and unmarshals it using the castor-GenTxnIDServerCmd.xml (which also extends from the castor-GenTxnIDCmd.xml config) into a server command instance; the server command's 'execute()' is invoked and the response marshaled and sent back to the client

Client applications only require castor-GenTxnIDCmd.xml, castor-commonTypes.xml and castor-GenTxnClientCmd.xml and the web service only requires castor-GenTxnIDCmd.xml, castor-commonTypes.xml and castor-GenTxnServerCmd.xml.  Most (well, ALL really) mapping information for commands is in the common castor-GenTxnIDCmd.xml - this is great since our client and server mapping files simply exist to extend it and contain the mapping to concrete client and server command classes respectively.  There is no duplication of castor mapping configuration whatsoever (yeh, and there was much rejoicing).  

## Distributed Command Framework:

Encapsulated within this little transaction identifier service is a generic little framework (I hesitate to call it that, but oh well) that I've dubbed the "Distributed Command Framework."  The code that encapsulates this framework is located within the /src/main/java folder within the name.paulevans.distcmdfrmwk package.  In short, it enables the execution of commands to be remotely distributed.  

The purpose of the framework is to enable the power of the GoF command design pattern, yet, enable its usage within a remotely distributed architecture, yet provide local method invocation semantics.  In short, it enables use of the GoF command design pattern within a distributed architecture yet completing hiding that complexity from consumers.

If you as a developer wanted to leverage this framework, you would essentially do the following for each command:

(1) Create a command interface (it would extend the framework's "Command" interface), and you would declare any getter, setters, etc that you need
(2) Create an abstract class that implements the command interface; specifically this command class would implement the getter and setter methods; the 'execute()' method should not be implemented
(3) Create a concrete "server" command class that extends the support class; the 'execute()' method should be implemented; the logic of the command resides here
(4) Create a concrete "client" command class that extends the support class; this class should also implement the framework's "ClientCommand" interface.  This interface contains a single method: 'setClientCommandDelegate()' that must be implemented.  This "client" command class uses a "client command delegate" object to invoke the server command object.  Using a separate "delegate" object to do this provides a layer of indirection that allows for a pluggable delegation strategy.  My framework comes with 2 delegate strategies: local and SOAP web service based.  A local delegate simply holds a reference to the server command instance, and invokes its 'execute()' method - all within the same JVM.  The web service based delegate will invoke a web service; the implementation of the web service is to construct a server command instance, execute its 'execute()' method, and the response will be sent back in the response.  My framework comes with a Spring-WS based endpoint that is used to expose server commands via a web service.
(5) That's it; once you know how you want to connect your client and server command instances, it is just a matter of assembling your application accordingly.

This service, my transaction identifier service, is basically just using my framework.  Specifically it consists of a server command instance that is exposed as a SOAP web service and is invoked using a client command instance configured with a delegate that knows how to invoke the web service (which the framework provides).

And let me again that I hesitate to call this a "framework" - the reality is that is just supplies some basic abstractions in which one can extend to achieve the aforementioned-stated goals.  There is no support for some of the things one might expect from a framework centered around the GoF command pattern.  E.g., there is no support for undo-able or redo-able functionality.

## Caveats about this Project 

+ The Checkstyle report is not exactly clean as a whistle - but at least you can see it in action
+ Yes, I'm proud of the depth of the testing done with this web service, but I fully acknowledge there are whole categories of additional testing that is not covered by this sample code project.  Some of those categories being: performance/load testing, security testing (penetration testing, fuzz testing, gorilla testing, etc), etc.  Hopefully I'll get to those in different sample code projects.
+ You'll notice in the integration test coverage report that the coverage indicated for package: name.paulevans.distcmdfrmwk.server.cmdendpoint.ws is nill.  This is expected because this package is encapsulated within a WAR file that is deployed to a JEE container, and as such, is outside the scope so-to-speak of the coverage measurement.  

## Building, Installing and Running

+ This project was built and tested using JDK 6
+ The integration tests assume that GlassFish and Tomcat are both locally installed.  Specifically GlassFish version 3.0.5 Build 22 and Tomcat 7.0.5.  The integration tests assume the GlassFish and Tomcat containers are installed at the following locations:
	(-) C:\JEEContainers\Tomcat\apache-tomcat-7.0.5
	(-) C:\JEEContainers\Glassfish\GlassFish-3.0.1-B22
+ Download and install soapUI [http://www.soapui.org/] (this project was tested using soapUI v3.6-beta2, free version) - this step is optional - it is only if you want to manually test the web service
+ Download and install latest version of Ant [http://ant.apache.org] (this project was tested using Ant 1.7.1)
+ To run the unit tests, execute the "unitTest" Ant target (a JUnit test report will be written to /generated/reports/unitTest/formatted/index.html and a Cobertura coverage report will be written to /generated/reports/unitTest/coverage/index.html)
+ To run the integration tests, make sure you have at least the 2 JEE containers installed above as indicated (in the correct folder locations on your machine) and execute the "integrationTest" Ant target (a JUnit test report will be written to /generated/reports/integrationTest/formatted/index.html and a Cobertura coverage report will be written to /generated/reports/integrationTest/coverage/index.html)

## Explanation of folders and files

+ /lib - root folder containing libraries we are depedenent on
+ /lib/client - contains JARs that are required by the client-part of this project (i.e. the client command and the SOAP-web-service-invoking client command delegate)
+ /lib/nonDeploy-lib - root folder containing JARs that are not part of build product and are not required at runtime
+ /lib/nonDeploy-lib/test-lib - root folder containing test-related JARs (unit and integration) that are not part of the build product and are not required at runtime
+ /lib/nonDeploy-lib/test-lib/Cobertura - contains Cobertura JAR(s) and its dependent JARs
+ /lib/nonDeploy-lib/test-lib/Cargo - contains Cargo JAR(s) and its dependent JARs (Cargo is for starting/stopping JEE containers)
+ /lib/nonDeploy-lib/test-lib/EasyMock - contains EasyMock JAR(s) and its dependent JARs
+ /lib/nonDeploy-lib/test-lib/JUnit - contains JUnit JAR(s) and its dependent JARs
+ /lib/nonDeploy-lib/test-lib/Spring-test - contains JARs that comprise the Spring-test framework
+ /lib/nonDeploy-lib/test-lib/Spring-WS-test - contains JARs that comprise the Spring-WS-test framework and its dependent JARs
+ /lib/nonDeploy-lib/test-lib/XMLUnit - containsn XMLUnit JAR(s) and its dependent JARs
+ /lib/nonDeploy-lib/env-lib - contains JARs that are needed to compile, but will be provided by the runtime environment and thus do not need to be part of the build product (this would things like Servlet-API.jar)
+ /lib/nonDeploy-lib/staticAnalysis-lib - root folder containing JARs to be used for static analysis of the source code (and will not be part of the build product)
+ /lib/nonDeploy-lib/staticAnalysis-lib/Checkstyle - contains Checkstyle JAR(s) and its dependent JARs
+ /src - root folder of source (there is also some source that is sitting in /webapp/WEB-INF sub-folders)
+ /src/main - root folder of non-test source (but again, there is some non-test source sitting in /webapp/WEB-INF sub-folders)
+ /src/main/java - root folder of non-test Java source (this is the only place where non-test Java source is sitting)
+ /src/main/config - root folder of non-test configuration
+ /src/main/config/castor - contains all Castor XML mapping configuration
+ /src/main/config/spring - contains Spring configuration for client command configured with the web service based client command delegate
+ /src/main/config/static-analysis - contains configuration files for static analysis tools
+ /src/test - root folder of test-related source
+ /src/test/unittest - root folder containing unit tests
+ /src/test/unittest/java - contains JUnit-based unit tests of main Java source
+ /src/test/unittest/canonical_schemas - contains JUnit-based unit tests of canonical XSDs (just ensures they are well-formed and schema-valid)
+ /src/test/unittest/castor_configuration - contains JUnit-based unit tests of Castor mapping files
+ /src/test/unittest/spring_configuration - contains JUnit-based unit tests to ensure correctness of Spring configuration files (both client configuration file that configures a client command and its delegate as well as the server configuration file which defines the server command object exposed as a SOAP web service based endpoint)
+ /src/test/unittest/config - contains unit test-related configuration source
+ /src/test/integrationTest - root folder containing integration tests
+ /webapp - root folder that defines the web service that will be used to expose our server command instance
+ /webapp/WEB-INF - standard WAR folder - contains web.xml and Spring-WS configuration file
+ /webapp/WEB-INF/lib - contains JARs needed at runtime by the web service
+ /webapp/WEB-INF/classes - contains compiled Java source as well as log4j configuration file to be used by web service
+ /webapp/WEB-INF/wsdl - contains WSDL descriptor (it is separated into 2 files: abstract and concrete definitions)
+ /webapp/WEB-INF/wsdl/canonicals - contains XML-based canonical definitions of types and commands
+ /soapUI - contains a soapUI project file that can be used to test the web service commands - it provides sample requests exercising various scenarios
+ /build.xml - Ant build script
+ /build.properties - contains property definitions used by build script
+ /DeployToGlassFish.bat - convenience script to deploy the web service WAR to your locally running GlassFish server for manual functional testing using soapUI
+ /UndeployFromGlassFish.bat - convenience script that undeploys your web service

