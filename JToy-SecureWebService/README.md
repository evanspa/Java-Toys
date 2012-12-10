## About this Project

This project presents a web service and client application both built using
the Spring framework (specifically Spring-WS).  

Interesting there is no security-related Java code; all security is handled
by the Spring framework and is driven through configuration.  The configuration
leverages Apaches WSS4J.

All web service messaging (requests and responses) are digitally signed and
encrypted using X.509 certificates.  Contained in this sample project is a
convenient Windows bat script that will generate the necessary keystores
and setup the necessary keys and certificate entries.

Although just a sample, this project presents a flexible design based on the
GoF command design pattern.  Requests are modeled as commands and are
un-marshaled by the remote web service into commands and executed on the server.
Each 'command' has client-side and server-side counterparts.  The 'execute()'
method of client-side commands is where the web service invocation occurs.  The
'execute()' method of server-side commands contains the actual business logic.
The internal design of the system is layered and interface-based allowing for
pluggability and loose coupling.


## 'Business Value' of WS-Security

The business value of WS-Security is easily apparent - the ability to incorporate
security axioms of confidentiality (through encryption) and integrity (through
signatures) into web service messaging is very powerful.


## Notables about this Project

+ Confidentiality through encryption and integrity through signatures are
provided by Apache's WSS4J library (which implements the OASIS Web Services
Security suite).
+ Encryption and digital signatures are performed using X.509 certificates
+ The client has its own keystore (type=JKS, provider=SUN) that contains the
client's key as well as the server's public certificate.  Conversely the server
has its own keystore (type=JKS, provider=SUN) that contains the server's key as
well as the client's public certificate.
+ The web service uses Spring's SoapActionEndpointMapping endpoint mapping
strategy (normally I prefer to use Spring's PayloadRootQnameEndpointMapping, but
because our payload is ENCRYPTED, we can't use it here).  Therefore each
request message needs to have the SOAP action header set - every request,
regardless of command, uses the following value for this header (w/out the
brackets): [http://name.paulevans/samples/action/AnyCommand].  The service
is designed such that the payload root WILL DETERMINE how the request
is processed; it's just that at initial receipt of the request, Spring will use
the SOAP action to know that the request should be serviced by our web service,
regardless of the command being issued.
+ Interface-based design allows for pluggability of components (client command
delegates, endpoints, etc)
+ Default marshaler implementation based-on Castor
+ Ant used to build, run units, run Checkstyle, run Cobertura
+ WSDL follows document/literal 'wrapped' conventionl; although the 'wrapped'
convention requires you to do some "unnatural" things in your WSDL, I feel the
benefits out-weight the negatives.  The benefits are: SOAP request payloads can
be completely schema-validated, internal processing/dispatching of request
payloads are simplified as the "operation" is included in the payment (its the
root element).  For more on the 'wrapped' convention, feel free to Google.
+ Canonical schemas (XSDs) externalized from the WSDL and defined with
their own files (found in webapp/WEB-INF/wsdl/canonicals)
+ Canonical schemas broken down into 2 categories: commands and types
+ Places in which checked exceptions are thrown but in which callers are
unlikely to be able to do anything meaningful with them have been written to
catch and re-throw the exception as a runtime exception.
+ The build script enforces a strict separation of "client" and "server" code.  Clients
should NOT have access to server-side "command" classes and of course the web service
should NOT have access to the associated client-side "command" classes.  The build script
is setup such that this rule is enforced.  You'll notice that the product of the "dist-ws"
build target (the WAR file) contains no client-side code and the product of the "releaseClient"
build target contains no server-side code.


## 'Business Value' of using Spring-WS

In my opinion the business value of using Spring-WS is that it
ultimately forces you down a path in which you're more likely to think through
the following:
	+ The design of the input and output messages (i.e. XML messages); you're
		more apt to create a canonical data model (such models would be used in
		other contexts - not just a web service context).  Follow this link
		[http://www.enterpriseintegrationpatterns.com/CanonicalDataModel.html]
		to read on canonical data models from the standpoint of integration.
	+ Now that you have XSD-based canonicals, you can create a marshaler
		that can translate XML instances to-and-from a Java object model.  So
		now you have canonical XSDs, a marshaler, and a Java object model - all
		this and we don't even have a web service yet!  But having these 3 things
		are necessary building blocks to achieve ANY sort of integration between
		systems; it just so happens in this case we're creating a web service,
		however, if you were building a message listener (to listen on a 
		queue/channel), you would need these same components.
		
To clarify, this notion that an organization would have XML-based canonical
data formats (manifesting as XSDs), along with a Java object domain, and a
context-unaware marshaling component that can translate between the two, are all "foundational
elements" necessary in order to achieve loose coupling between systems and
flexible integration.  With this foundation in place it should be relatively
straight forward to create web service-based integrations (or message-based
integrations).  So, to tie this altogether, if you need to create web
service-based integrations, Spring-WS is a good choice as it will enable you to
effectively use your "foundational elements."

## Other Miscellaneous Value of this Sample Code Project

It gets developers thinking about security!

## Caveats about this Project

+ Very limited unit testing
+ Checkstyle report isn't the cleanest
+ In-line code and configuration comments do exist, but in some cases are a
bit light
+ Some aspects of this project are not suitable for implementing in a production
environment.  Some of those aspects being:
	+ the keystore passwords are sitting in plain text in the Spring
		configuration file - obviously this is unacceptable and not suitable for
		production scenarios.
	+ Sun keystores are very basic and file-based structures for storing
		cryptographic artifacts (such as private keys and certificates) and only
		provide a command-line interface for managing.  Sun keystores may be
		okay in small environments, but, in large enterprises a more
		"enterprise-grade" implementation should be sought.
	+ The keystore passwords and private key passphrases are all the same - 
		'changeit'.  This is done for simplicity and is obviously not recommended
		for a production environment.
+ As mentioned in the 'Notables' section above, the web service uses the
SOAP action header to know that a request should be processed by it.  Using the
SOAP action HTTP header is not ideal because it creates an annoying coupling
to the HTTP protocol (there is absolutely nothing inherent about the design and
implementation of our service that couples it to HTTP - that is why this coupling
to HTTP in the context of routing is an annoyance).  A superior alternative
solution would be to use WS-Addressing.


## The 'SetupKeystores.bat' script

+ Located in the project's root folder, this bat file is for convenience and
will create the client's and server's keystores.
+ The script will also create a key-pair for the client (a public key and
associated passphrase-enabled private key).  The public key will be wrapped
within an X.509 V3 self-signed certificate, stored as a single-element
certificate chain.  The client's certificate chain and private key are stored
as a single-entry in the client's keystore aliased as 'clientkey'.
+ The script will also create a key-pair for the server and store it in the
server's keystore aliased as 'serverkey'.
+ Next the script will export out of the client's keystore the client's
certificate chain (which contains the client's public key).  This certificate
chain (which I'll from here on I'll simply refer to as 'certificate') will
then be imported into the server's keystore as a 'trusted certificate'.  
+ The script will export out of the server's keystore the server's certificate
(which contains the server's public key).  This certificate will then be
imported into the client's keystore as a 'trusted certificate'.
+ You'll note from the script file that both the client's and server's
keystore are password-enabled; the password for each keystore is 'changeit'.
Both the client's and server's private keys are also passphrase-enabled; the
passphrase for each of them is 'changeit'   
+ The script is largely driven by variables that are defined at the top
of the file.  Things like the location of where keystores are to be written,
passwords and passphrases, alias names, etc are defined here (and can be changed
if you wish).

At this point the client's and server's keystores are initialized to a state
such that secure communications can be performed.  If the server's keystore
did not have the client's certificate installed, then secure web service
messaging between them would not be possible.  If the client's keystore did not
have the server's certificate installed, secure communications would not be
possible as well.

## Building, Installing and Running

+ This project was built and tested using JDK 6 and Java EE 6
+ Download and install GlassFish version 3 [https://glassfish.dev.java.net/]
(this project was test using GlassFish 3.0.1 build 22)
+ Download and install soapUI [http://www.soapui.org/] (this project was
tested using soapUI v3.6-beta2, free version)
+ Download and install latest version of Ant [http://ant.apache.org] (this
project was tested using Ant 1.7.1)
+ Download and install Paros proxy security tool [http://www.parosproxy.org/]
(this project was tested using Paros version 3.2.13)
+ Create and initialize the keystores by running the 'SetupKeystores.bat'
script
+ The log4j configuration file associated with the web service (it's sitting
in the webapp/WEB-INF/classes folder) has appenders configured to write log files
to /var/log/SampleCodeProjects/SecureSpringWebService - you will want to create
this folder structure on your machine
+ Next you'll want to build the WAR file; the default target (dist-all) from the
Ant build script will build the WAR file - it will be in the "dist/server/" folder
+ Run DeployToGlassFish.bat (convenience script) to deploy the web service to
the GlassFish instance
+ Log into GlassFish admin console (you would have been prompted to setup
an admin account during the install) and confirm that the web service
(application) has been installed properly
+ Launch soapUI and import the project file found in the soapUI/ folder (before
testing the web service with our Java-based client application, we'll want to
do a smoke test using soapUI)
+ Right-click the project icon and select 'Show Project View' - you'll notice
on the 'Security Configurations' tab that a configuration has already been
defined.  There's an outgoing configuration called 'SignAndEncrypt' that is
configured to sign outgoing messages using the client's private key from the
client's keystore, and is configured to encrypt messages using the server's
public key.  What this really means is that a symmetric key will be created and
encrypted using the server's public key and the request message payload will
be encrypted using this symmetric key.  The encrypted symmetric key will
be part of the request message.  The server will decrypt the message
payload by first decrypting the symmetric key using its own private key and will
then be able to decrypt the message payload using the symmetric key.  There is
also an incoming configuration called 'DefaultIncoming' that will be used to
validate the signature of response messages as well as decrypt the included
symmetric key and then decrypt the message payload. 
+ Drill into the 'CalculateOrderSubTotal' operation of the 'OrderSystemBinding'
binding - you'll see several sample requests available - execute each one and
ensure you get an appropriate response back.  You'll notice that each request
has the 'Authentication and Security-related settings' filled-out appropriately
to use the 'SignAndEncrypt' and 'DefaultIncoming' security configurations.
+ Now that we know the web service is deployed properly and is working (based
on our soapUI testing), we will test the web service by invoking it with our Java-based
client application...
+ Use the "releaseClient" Ant target to create the client application bundle ZIP
+ Unzip the bundle to anywhere on your machine; it will unzip to create a
folder: "SecureWebService-releaseClient/" - in this folder will be a script that
can be used to run the client application.
+ Don't run the client application yet...the client application is configured
to use an http proxy (see bean 'hostConfig' in config/spring.xml) - you can simply
comment-out the <property> element that is setting the 'proxyHostPlusPort'
property to disable the use http proxying).  Fire-up the Paros proxy tool
and configure it to behave as a local proxy listening on localhost on port 8081.
+ Now you can go ahead and run the client application it simply invokes the 
web service (under-the-covers it's using Spring's WebServiceTemplate 
abstraction) a few times with some fabricated order instances.
+ You should play around with the Paros tool - specifically try trapping
the request/response messages and alter the encrypted payload - verify that
the receiver of the message throws an error due to the signature not validating.


## How to Create New Commands

+ Create server-side command class to contain business logic (the new command
class should go within: name.paulevans.sampleprojects.command.server.servercmds
package)
+ Create client-side "marker" command class (I call this a marker because
it effectively has no implementation; it simply exists so that it can be
mapped and marshaled to XML) - this class should be created in the
name.paulevans.sampleprojects.command.clientcmds package.
+ Update 'commands' canonical (webapp/WEB-INF/wsdl/canonicals/OrderSystem-commands.xsd)
+ Update client-centric (castor-mapping_clientCommands.xml) and server-
centric (castor-mapping_serverCommands.xml) Castor mapping configuration files
+ Update abstract (webapp/WEB-INF/wsdl/OrderSystem-abstract.wsdl) and concrete 
(webapp/WEB-INF/wsdl/OrderSystem-concrete-soap.wsdl) WSDL definitions
+ In order to invoke the command from the client application, you'll need to:
	+ Update the client application's Spring configuration (located at
		src/main/config/client/spring.xml) - at line 21 you'll see the 
		'calculate order sub-total' client command bean defined; you'll basically
		want to copy/paste this bean definition for your new command.
	+ Update the client application Java class
		(name.paulevans.samplesprojects.clientapplication.ClientApplication)
		such that it invokes your new command (it should be self-explanatory
		looking at the existing ClientApplication.java source how you go about
		doing this)
+ That's it!  You're done!  I know, it was a bit of a hassle, but, the REALLY
COOL thing is that you DID NOT HAVE TO MODIFY ANY JAVA CODE.  Instead you had
to ADD Java code (creating the client and server-side command classes).  You
DID NOT have to modify any Java 'if/else' blocks and you DID NOT have to modify
any Java 'switch' statements.  For what it's worth, I believe a system in which
you're able to add or alter behavior by ADDING Java code (as opposed to modifying
Java code) is indicative of the application of good object-oriented design. 


## Explanation of Folders and Files

+ /lib - root folder containing libraries we are depedenent on
+ /lib/client - contains JARs that are required by the client-part of this project
+ /lib/nonDeploy-lib - root folder containing JARs that are NOT part of build product
+ /lib/nonDeploy-lib/test-lib - root folder containing test-related JARs that are NOT part of the build product
+ /lib/nonDeploy-lib/test-lib/cobertura-lib - contains JARs that are needed by Cobertura tool 
+ /lib/nonDeploy-lib/test-lib/unitTest-lib - contains JARs that are needed to build and run unit tests
+ /lib/nonDeploy-lib/env-lib - contains JARs that are needed to compile, but will be provided by the runtime environment and thus do not need to be part of the build product
+ /lib/nonDeploy-lib/staticAnalysis-lib - root folder containing JARs to be used for static analysis of the source code (and will NOT be part of the build product)
+ /lib/nonDeploy-lib/staticAnalysis-lib/Checkstyle-lib - contains JARs that are needed by Checkstyle tool
+ /src - root folder of source (there is some source that is sitting in /webapp/WEB-INF sub-folders)
+ /src/main - root folder of non-test source (but again, there is some non-test source sitting in /webapp/WEB-INF sub-folders)
+ /src/main/java - root folder of non-test Java source (this is the only place where non-test Java source is sitting)
+ /src/main/config - root folder of non-test configuration (shared by both client and server)
+ /src/main/config/client - contains client-only related configuration
+ /src/main/config/server - contains server-only related configuration
+ /src/main/config/static-analysis - contains configuration used to perform static analysis on Java source
+ /src/main/executable - root folder of non-test executable-related source (things like shell scripts)
+ /src/main/executable/client - contains client-only executable scripts
+ /src/test - root folder of test-related source
+ /src/test/unittest/java - contains unit test Java source
+ /src/test/unittest/config - contains unit test-related configuration source
+ /src/test/integrationtest/java - contains integration test Java source
+ /webapp - root folder contain web-specific content
+ /webapp/WEB-INF - standard WAR folder
+ /webapp/WEB-INF/lib - contains JARs needed at runtime by the web service
+ /webapp/WEB-INF/classes - contains compiled Java source as well as log4j configuration file to be used by web service
+ /webapp/WEB-INF/wsdl - contains WSDL descriptor (it is separated into 2 files: abstract and concrete definitions)
+ /webapp/WEB-INF/wsdl/canonicals - contains XML-based canonical definitions of types and commands
+ /soapUI - contains a soapUI project file that can be used to test the web service commands - it provides sample requests exercising various scenarios
+ /build.xml - Ant build script
+ /build.properties - contains property definitions used by build script
+ /DeployToGlassFish.bat - convenience script to deploy the web service WAR to your locally running GlassFish server
+ /UndeployFromGlassFish.bat - convenience script that undeploys your web service application
+ /SetupKeystores.bat - convenience script to create and initialize client and server keystores

