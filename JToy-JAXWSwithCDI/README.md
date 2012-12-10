## About this Project:
This project presents a web service built using the standard JAX-WS
framework.  Specifically it uses the Provider API (see section 5.1 of the 
JAX-WS 2.0 specification for details) to allow fine grained control over
the processing of SOAP requests (in contrast to using the @WebService
annotation).

Various server-side components leverage the new CDI capability of Java EE 6.
Specifically the "endpoint" and server-centric "webservice" classes leverage the
@Inject annotation to enable the containing to manage the dependencies between
beans.

Presents a flexible strategy for modeling interactions between a presentation
tier (i.e. the 'client application' part of this project) and a business tier
(i.e. the web service) based on the GoF command design pattern (see 2nd bullet
point in the 'Notables' section down below).

I tried to keep things simple by not introducing any external frameworks (like
Spring), but it's funny, I believe I inadvertently reproduced essentially
Spring's Web Services framework (in this project you'll find abstractions
for marshaling and endpoints).

## "Business Value" of using the Provider API of JAX-WS:
In my opinion the business value of using the Provider API of JAX-WS is that it
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
marshaling component that can translate between the two, are all "foundational
elements" necessary in order to achieve loose coupling between systems and
flexible integration.  With this foundation in place it should be relatively
straight forward to create web service-based integrations (or message-based
integrations).  So, to tie this altogether, if you need to create web
service-based integrations, and you want to use JAX-WS, using the Provider API
will enable you to effectively use your "foundational elements."  If such
foundational elements are not necessary to meet your requirements, and you just
want to create basic or ad-hoc service endpoint implementations (SEIs), then you
can simply use the standard @WebService annotation.  In this mode you don't need
to deal with SOAP request payloads directly, and you don't need to plug-in your
own marshaler component.  Instead JAX-WS will simply use JAXB behind the scenes
to translate the request payload into your Java domain model. 

## "Business Value" of using Java EE 6 CDI:
There are many reasons - I won't try to reproduce here what simple Google
searches will yield.  Very briefly the business value of CDI is:
+ Simpler, easier-to-maintain code - code does not have to be cluttered with
boilerplate getters and setters.
+ Enables loose coupling between implementation classes.  This makes it
easy to create pluggable designs which are inherently easier to test and are
enablers of agility.

## Notables about this Project:
+ Operations exposed by the web service are modeled as commands (from the
GoF patterns) - new operations added to the web service can be accounted for
by simply creating a new command class (modifying behavior by adding code - a
principal effect of good object-oriented design)
+ The design of the "command" class hierarchy supports a distributed
architecture.  For each command there is a client-side and server-side
implementation.  The server-side implementation contains the actual
business logic where the client-side serves more as a proxy.  Client-side
command implementations leverage a "client command delegate" to contain the
actual plumbing to invoke its server-side command counterpart.  In this project
there is a single client command delegate implementation used to invoke
the web service - requests that hit the web service are un-marshaled into the
server-side command counterpart, and are then executed.  The beauty here is
that client command delegates are pluggable which enables agility.  If for
example the server-side command is to be exposed via a stateless session EJB,
all that would have to happen is that we'd need to create a new type of client
command delegate; NOTHING ELSE WOULD CHANGE.  If for example you wanted to use
this command design in another application; one in which all the components
where co-located and running within the same JVM - no problem!  All you would
need to do is create a client command delegate that simply held a reference
to the server-side command counterpart and invoke it as a simple method call!
If the architecture of the application changed to be distributed, again - no
problem - just use the appropriate command client delegate based on your 
needs.
+ Interface-based design allows for pluggability of components (marshaling,
endpoint, etc)
+ Default marshaler implementation based-on Castor
+ Ant used to build, run units, run Checkstyle, run Cobertura
+ Uses XMLUnit to unit test the marshaler component
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

## Caveats about this Project:
+ I only created a handful of unit tests - remember, this is only a sample so I
didn't feel like achieving 100% coverage (I just did enough to serve as a useful
example)
+ The Checkstyle report is not exactly clean as a whistle - but at least you
can see it in action
+ In-line code comments exist, but are a little on the light side
+ Although the project folder structure and build script account for
integration tests, there are none in actuality (this project doesn't integrate
with anything...all the components are co-located running w/in the same JVM)

## Other Miscellaneous Value of this Sample Code Project:
The value of this sample code project is it presents a strategy for designing
and building SOAP-based web services leveraging the command design pattern.  It
demonstrates how a service could be component-ized such that 'concerns' can be
leveraged in different contexts.  For example, the marshaler component is its
own component that can be used in other contexts (besides just a web
service-based one).

And of course the code itself can be used as a guide for building JAX-WS
web services using the Provider API.  The folder structure of the project itself
and the associated Ant build script are well thought-out enough that they make
for good examples to use on your own projects.

## Building, Installing and Running:
+ This project was built and tested using JDK 6 and Java EE 6
+ Download and install GlassFish version 3 [https://glassfish.dev.java.net/]
(this project was test using GlassFish 3.0.1 build 22)
+ Download and install soapUI [http://www.soapui.org/] (this project was
tested using soapUI v3.6-beta2, free version)
+ Download and install latest version of Ant [http://ant.apache.org] (this
project was tested using Ant 1.7.1)
+ Start your local GlassFish server
+ The log4j configuration file associated with the web service (it's sitting
in the webapp/WEB-INF/classes folder) has appenders configured to write log files
to /var/log/SampleCodeProjects/JaxWsProviderApi_withCDI - you will want to create
this folder structure on your machine
+ First step is to build the WAR file; the default target (dist-all) from the
Ant build script will build the WAR file - it will be in the "dist/server/" folder
+ Run DeployToGlassFish.bat (convenience script) to deploy the web service to
the GlassFish instance
+ Log into GlassFish admin console (you would have been prompted to setup
an admin account during the install) and confirm that the web service
(application) has been installed properly
+ Launch soapUI and import the project file found in the soapUI/ folder (before
testing the web service with our Java-based client application, we'll want to
do a smoke test using soapUI)
+ Drill into the 'CalculateOrderSubTotal' operation of the 'OrderSystemBinding'
binding - you'll see several sample requests available - execute each one and
ensure you get an appropriate response back.
+ Now that we know the web service is deployed properly and is working (based
on our soapUI test), we will test the web service by invoking it with our Java-based
client application...
+ Use the "releaseClient" Ant target to create the client application bundle ZIP
+ Unzip the bundle to anywhere on your machine; it will unzip to create a
folder: "JaxWsProviderApi-releaseClient/" - in this folder will be a script that
can be used to run the client application.  The client application simply
executes the web service (under-the-covers it's using the JAX-WS Dispatch API) a few
times with some fabricated order instances.

## How-To Create a New Command:
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
+ Update the client application Java class 
(name.paulevans.samplesprojects.clientapplication.ClientApplication) such that
it invokes your new command (it should be self-explanatory looking at the
existing ClientApplication.java source how you go about doing this)
+ That's it!  You're done!  I know, it was a bit of a hassle, but, the REALLY
COOL thing is that you DID NOT HAVE TO MODIFY ANY JAVA CODE.  Instead you had
to ADD Java code (creating the client and server-side command classes).  You
DID NOT have to modify any Java 'if/else' blocks and you DID NOT have to modify
any Java 'switch' statements.  For what it's worth, I believe a system in which
you're able to add or alter behavior by ADDING Java code (as opposed to modifying
Java code) is indicative of the application of good object-oriented design. 

## Explanation of folders and files:
+ /lib - root folder containing libraries we are depedenent on
+ /lib/client - contains JARs that are required by the client-part of this project
+ /lib/nonDeploy-lib - root folder containing JARs that are NOT part of build product
+ /lib/nonDeploy-lib/test-lib - root folder containing test-related JARs that are NOT part of the build product
+ /lib/nonDeploy-lib/test-lib/cobertura-lib - contains JARs that are needed by Cobertura tool 
+ /lib/nonDeploy-lib/test-lib/integrationTest-lib - contains JARs that are needed to build and run integration tests
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
+ /src/main/config/CDI - contains "beans.xml" files that will be bundled within the META-INF/ folder of each of the JARs produced by the build script.  A requirement of CDI is that beans intended to be managed by the container that are bundled within JARs need to have an empty "beans.xml" placed within the META-INF/ folder.  You'll notice there is a separate "beans.xml" file for each of the following JARs produced by the build script (except for the client JARs: client-application.jar, client-command.jar, ws-client.jar)
+ /src/main/executable - root folder of non-test executable-related source (things like shell scripts)
+ /src/main/executable/client - contains client-only executable scripts
+ /src/test - root folder of test-related source
+ /src/test/unittest/java - contains unit test Java source
+ /src/test/unittest/config - contains unit test-related configuration source
+ /src/test/integrationtest/java - contains integration test Java source
+ /src/test/integrationtest/config - contains integration test-related configuration source
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
+ /nonCDI-JavaSrc.zip - contains non-CDI version of Java source of "endpoint" and server-centrid "webservice" classes.  You can unzip this into your project and you'll still have a fully working project, but the Java source that contained the @Inject annotation will be overwritten with "traditional" code
