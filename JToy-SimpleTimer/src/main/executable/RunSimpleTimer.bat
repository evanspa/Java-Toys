@echo off

REM Set the base path...
set BASE_PATH=.

REM Set the location of the logj4 configuration file...
set LOGGING_CONFIG_FILE=-Dlog4j.configuration=file:%BASE_PATH%/config/log4j.properties

REM Launch the application...
java %LOGGING_CONFIG_FILE% -cp %BASE_PATH%/lib/log4j-1.2.15.jar;%BASE_PATH%/lib/SimpleTimer.jar name.paulevans.samplecodeprojects.SimpleTimer



