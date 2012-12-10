## Multi-threaded Laundry Program

This is a rather silly program that demonstrates rudimentary usage of Java's wait() and notify() methods in order to achieve coordination among multiple threads.  The code is silly because of the domain: a laundry with a washer and dryer machine.  There are several threads whose job it is to dry the wet clothes.  There are other threads whose job it is to produce the wet clothes.  Yes, very silly.

Typically wait() and notify() are used in situations in which there are "consumer" threads and "producer" threads. Consumer threads will consume whatever it is they consume (in this program they consume dried cloths from a laundromat dryer machine) until there is nothing left to consume, at which point they'll invoke wait(). The producer threads will produce whatever it is they produce (in this program they produce wet cloths and put them into the laundromat's dryer machine), at which point they'll invoke notify() to notify the consumer threads that there is new stuff to consume.

The value of this code is that it gets you thinking about threads, and the value of threads.  The Pragmatic Programmer [http://en.wikipedia.org/wiki/Pragmatic_Programmer] admonishes us to always consider the dimension of time when it comes to application design.

## Caveats about this Project

There aren't any real unit tests, and of course there are no integration tests,
however the build script is setup to run any and measure the coverage.

## Building and Running
The default target builds the JAR; the 'release' target builds a complete ZIP that bundles the application.  The unzip'd structure contains a bat file that will run the application.

## Explanation of Folders and Files:

+ /nonDeploy-lib - root folder containing JARs that are NOT part of build product
+ /nonDeploy-lib/test-lib - root folder containing test-related JARs that are NOT part of the build product
+ /nonDeploy-lib/test-lib/cobertura-lib - contains JARs that are needed by Cobertura tool 
+ /nonDeploy-lib/test-lib/integrationTest-lib - contains JARs that are needed to build and run integration tests
+ /nonDeploy-lib/test-lib/unitTest-lib - contains JARs that are needed to build and run unit tests
+ /nonDeploy-lib/env-lib - contains JARs that are needed to compile, but will be provided by the runtime environment and thus do not need to be part of the build product
+ /nonDeploy-lib/staticAnalysis-lib - root folder containing JARs to be used for static analysis of the source code (and will NOT be part of the build product)
+ /nonDeploy-lib/staticAnalysis-lib/Checkstyle-lib - contains JARs that are needed by Checkstyle tool
+ /src - root folder of source (there is some source that is sitting in /webapp/WEB-INF sub-folders)
+ /src/main - root folder of non-test source (but again, there is some non-test source sitting in /webapp/WEB-INF sub-folders)
+ /src/main/java - root folder of non-test Java source (this is the only place where non-test Java source is sitting)
+ /src/main/config - root folder of non-test configuration (shared by both client and server)
+ /src/main/config/static-analysis - contains configuration used to perform static analysis on Java source
+ /src/main/executable - root folder of non-test executable-related source (things like shell scripts)
+ /src/test - root folder of test-related source
+ /src/test/unittest/java - contains unit test Java source
+ /src/test/integrationtest/java - contains integration test Java source
+ /build.xml - Ant build script
+ /build.properties - contains property definitions used by build script
+ /README.txt - you're reading this right now!!!
