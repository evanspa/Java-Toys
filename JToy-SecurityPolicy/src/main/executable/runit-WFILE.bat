@echo off

REM Set the base path...
set BASE_PATH=.

REM Install the default security manager...
set JAVA_OPTS=-Djava.security.manager

REM Specify our own policy file to be used in addition to the system policy file...
set JAVA_OPTS=%JAVA_OPTS% -Djava.security.policy=%BASE_PATH%/config/wfile.policy

REM Launch the application...
java %JAVA_OPTS% -cp %BASE_PATH%/config;%BASE_PATH%/lib/log4j-1.2.15.jar;%BASE_PATH%/lib/commons-logging-1.1.1.jar;%BASE_PATH%/lib/spring-context-2.5.5.jar;%BASE_PATH%/lib/spring-beans-2.5.5.jar;%BASE_PATH%/lib/spring-core-2.5.5.jar;%BASE_PATH%/lib/commons-cli-1.1.jar;%BASE_PATH%/lib/commons-io-1.4.jar;%BASE_PATH%/dist/JavaPolicySample.jar name.paulevans.samplecodeprojects.policytesting.SimpleApp -type WFILE
