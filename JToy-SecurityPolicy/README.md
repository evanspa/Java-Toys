## About this Project

This is a very simple project that demonstrates usage of Java's built-in security
policy functionality.  This project demonstrates how a custom security policy
can be associated with the JVM upon startup.  

## 'Business Value' of this Project

The business value of this project is that it simply raises awareness of this
capability of Java - specifically the idea that the security permissions of
a Java program can be completely control externally from the code itself.

## Other Notables

This project leverages Spring [http://www.springsource.org] in a very basic
way - it's used to provide some simple injection.  The project also leverages
Logj4 [http://logging.apache.org/log4j/1.2/]

## Caveats about this Project

There aren't any real unit tests, and of course there are no integration tests,
however the build script is setup to run any and measure the coverage.

## Building and Running

The default target builds the JAR; the 'release' target builds a complete ZIP
that bundles the application.  The unzip'd structure contains a bat
files that will run the application.

The bat file 'runit-RFILE.bat' will execute the application with a mode that
will cause the application to attempt to read the contents of the file 
'datafiles/file-to-read.txt', and output it to the console.  This bat file
passes arguments to the JVM to configure the security policy.

The bat file 'runit-WFILE.bat' will execute the application with a mode that
will cause the application to attempt to write some content to the
file 'datafiles/file-to-write.txt'.  This bat file passes arguments to the JVM
to configure the security policy.

You are invited to play around with the security policy files
('config/rfile.policy' and 'config/wfile.policy') and experiment what happens
when you change things around (i.e. - make changes that you think will cause
a policy violation, and see what happens when you run the application).

## Explanation of Folders and Files

+ /nonDeploy-lib - root folder containing JARs that are NOT part of build product
+ /nonDeploy-lib/test-lib - root folder containing test-related JARs that are NOT part of the build product
+ /nonDeploy-lib/test-lib/cobertura-lib - contains JARs that are needed by Cobertura tool 
+ /nonDeploy-lib/test-lib/unitTest-lib - contains JARs that are needed to build and run unit tests
+ /nonDeploy-lib/env-lib - contains JARs that are needed to compile, but will be provided by the runtime environment and thus do not need to be part of the build product
+ /nonDeploy-lib/staticAnalysis-lib - root folder containing JARs to be used for static analysis of the source code (and will NOT be part of the build product)
+ /nonDeploy-lib/staticAnalysis-lib/Checkstyle-lib - contains JARs that are needed by Checkstyle tool
+ /src - root folder of source
+ /src/main - root folder of non-test source
+ /src/main/java - root folder of non-test Java source (this is the only place where non-test Java source is sitting)
+ /src/main/config - root folder of non-test configuration (shared by both client and server) - contains log4j configuration file
+ /src/main/config/security - contains the security policy files
+ /src/main/config/spring - contains Spring beans configuration file
+ /src/main/config/static-analysis - contains configuration used to perform static analysis on Java source
+ /src/main/executable - root folder of non-test executable-related source (things like shell scripts)
+ /src/test - root folder of test-related source
+ /src/test/datafiles - contains data files used by this application
+ /src/test/unittest/java - contains unit test Java source
+ /build.xml - Ant build script
+ /build.properties - contains property definitions used by build script
