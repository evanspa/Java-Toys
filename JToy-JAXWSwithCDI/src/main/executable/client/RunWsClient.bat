@echo off

REM Set the base path
set WSCLIENT_BASE_PATH=.

REM Set java endorsed dir property
set WSCLIENT_ENDORSED_DIRS=-Djava.endorsed.dirs=/%WSCLIENT_BASE_PATH%/lib

REM Set the location of the logj4 configuration file...
set WSCLIENT_LOGGING_CONFIG_FILE=-Dlog4j.configuration=file:%WSCLIENT_BASE_PATH%/config/log4j.client.properties

REM Create variable to contain main application class
set WSCLIENT_MAIN_APP_CLASS=name.paulevans.samplecodeprojects.clientapplication.ClientApplication

REM Launch the application...
java %WSCLIENT_LOGGING_CONFIG_FILE% %WSCLIENT_ENDORSED_DIRS% -cp %WSCLIENT_BASE_PATH%/lib/castor-1.3.1.jar;%WSCLIENT_BASE_PATH%/lib/castor-1.3.1-core.jar;%WSCLIENT_BASE_PATH%/lib/client-application.jar;%WSCLIENT_BASE_PATH%/lib/client-command.jar;%WSCLIENT_BASE_PATH%/lib/common-command.jar;%WSCLIENT_BASE_PATH%/lib/commons-io-1.4.jar;%WSCLIENT_BASE_PATH%/lib/commons-lang-2.4.jar;%WSCLIENT_BASE_PATH%/lib/commons-logging-1.1.1.jar;%WSCLIENT_BASE_PATH%/lib/common-util.jar;%WSCLIENT_BASE_PATH%/lib/log4j-1.2.15.jar;%WSCLIENT_BASE_PATH%/lib/marshaler-api.jar;%WSCLIENT_BASE_PATH%/lib/marshaler-impl-castor.jar;%WSCLIENT_BASE_PATH%/lib/model.jar;%WSCLIENT_BASE_PATH%/lib/ws-client.jar;%WSCLIENT_BASE_PATH%/config %WSCLIENT_MAIN_APP_CLASS%
 