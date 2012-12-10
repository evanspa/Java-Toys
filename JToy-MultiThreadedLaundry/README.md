The purpose of this program is to demonstrate how to use wait() and notify() w/respect to multithreading. 

Typically you use wait() and notify() in a consumer/producer situation in which you have consumer threads and
producer threads.  The consumer threads will consume whatever it is they consume (in this program they consume
dryed-cloths from the dryer machine) until there is nothing left to consume, at which point they'll invoke "wait()".
The producer threads will produce whatever it is they produce (in this program, they produce wet-cloths and put them
into the dryer-machine), at which point they'll invoke "notify()" or "notifyAll()" to notify the consumer threads that
there is new stuff to consume.

Notables:

+ Built using JDK 6
+ Code is very well Javadoc'd
+ No unit testing
+ 100% automation using build.xml script (clean, build, dist, run, javadoc)
	(-) Javadoc API placed in generated/docs/javadoc
+ UML class diagrams and sequence diagrams provided (built using MagicDraw UML Community Edition 15.1)
+ Leverages Log4j (no System.out.println() calls)
	(-) Leverages a Timer to write a heartbeat-style log message to standard out every 3 seconds
	(-) Because this is a console application, the main system thread is told to wait() so that the program doesn't
immediately terminate
+ runit.bat script provided to easily run the program


Notes on the UML:

+ Pretty straight-forward creating the class diagram
+ Sequence diagrams are a little fuzzy...some questions I have are:
	- How many do you need?
		+ One per method? (this is what I ended up doing)
		+ One per use case? (a use case isn't always so clear cut regarding the implementation needed to design it)



About this Project:
-------------------
This is a very simple project that demonstrates usage of the javax.swing.Timer
class.  The Timer class generates action events on some configured interval.

The SimpleTimer class is the action listener, and it simply prints some
trivial output using Log4j.

'Business Value' of this Project:
---------------------------------
The business value of this project is that it gets you thinking about threads, 
and the value of threads.  The Pragmatic Programmer [http://en.wikipedia.org/wiki/Pragmatic_Programmer] 
admonishes us to always consider the dimension of time when it comes to
application design.

Caveats about this Project:
---------------------------
There aren't any real unit tests, and of course there are no integration tests,
however the build script is setup to run any and measure the coverage.

Building and Running:
---------------------
The default target builds the JAR; the 'release' target builds a complete ZIP
that bundles the application.  The unzip'd structure contains a bat
file that will run the application.

Explanation of Folders and Files:
---------------------------------
(+) /nonDeploy-lib - root folder containing JARs that are NOT part of build product
(+) /nonDeploy-lib/test-lib - root folder containing test-related JARs that are NOT part of the build product
(+) /nonDeploy-lib/test-lib/cobertura-lib - contains JARs that are needed by Cobertura tool 
(+) /nonDeploy-lib/test-lib/integrationTest-lib - contains JARs that are needed to build and run integration tests
(+) /nonDeploy-lib/test-lib/unitTest-lib - contains JARs that are needed to build and run unit tests
(+) /nonDeploy-lib/env-lib - contains JARs that are needed to compile, but will be provided by the runtime environment and thus do not need to be part of the build product
(+) /nonDeploy-lib/staticAnalysis-lib - root folder containing JARs to be used for static analysis of the source code (and will NOT be part of the build product)
(+) /nonDeploy-lib/staticAnalysis-lib/Checkstyle-lib - contains JARs that are needed by Checkstyle tool
(+) /src - root folder of source (there is some source that is sitting in /webapp/WEB-INF sub-folders)
(+) /src/main - root folder of non-test source (but again, there is some non-test source sitting in /webapp/WEB-INF sub-folders)
(+) /src/main/java - root folder of non-test Java source (this is the only place where non-test Java source is sitting)
(+) /src/main/config - root folder of non-test configuration (shared by both client and server)
(+) /src/main/config/static-analysis - contains configuration used to perform static analysis on Java source
(+) /src/main/executable - root folder of non-test executable-related source (things like shell scripts)
(+) /src/test - root folder of test-related source
(+) /src/test/unittest/java - contains unit test Java source
(+) /src/test/integrationtest/java - contains integration test Java source
(+) /build.xml - Ant build script
(+) /build.properties - contains property definitions used by build script
(+) /README.txt - you're reading this right now!!!