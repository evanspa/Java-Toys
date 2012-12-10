@echo off

REM This script is for convenience - it will do the job of creating 2
REM passphrase-enabled keystores - 1 for the client, and 1 for the
REM server.

REM The client's keystore will hold the client's private/public
REM key pair as well as hold the server's certificate (containing the
REM server's public key only of course).

REM The server's keystore will hold the server's private/public key pair as well
REM as hold the client's certificate (yes, containing the client's public key
REM only of course).

REM Author: Paul R Evans
REM Version: $Id$

REM Global variable definitions...
set BASE_FOLDER=C:\var\data\SampleCodeProjects\SecureWebService
set KEYSTORES_BASE_FOLDER=%BASE_FOLDER%\keystores
set EXPORTEDCERTS_BASE_FOLDER=%BASE_FOLDER%\exportedCertificates

REM Variables associated with the server's keystore...
set SERVER_KEYSTORE_LOCATION=%KEYSTORES_BASE_FOLDER%\serverKeyStore.jks
set SERVER_KEYSTORE_PASSPHRASE=changeit
set SERVER_ENTRY_COMMONNAME=Mr. John Q Server
set SERVER_ENTRY_ORGUNIT=Samples
set SERVER_ENTRY_ORG=paulevans.name
set SERVER_ENTRY_COUNTRY=US
set SERVER_ENTRY_ALIAS=serverkey
set SERVER_ENTRY_KEYPASSPHRASE=changeit
set SERVER_ENTRY_EXPORTEDCERT_LOCATION=%EXPORTEDCERTS_BASE_FOLDER%\server.pub.cer
set SERVER_KEYSTORE_TRUSTED_CLIENTCERT_ALIAS=trusted_clientkey

REM Variables associated with the client's keystore...
set CLIENT_KEYSTORE_LOCATION=%KEYSTORES_BASE_FOLDER%\clientKeyStore.jks
set CLIENT_KEYSTORE_PASSPHRASE=changeit
set CLIENT_ENTRY_COMMONNAME=Mrs. Susan R Client
set CLIENT_ENTRY_ORGUNIT=Samples
set CLIENT_ENTRY_ORG=paulevans.name
set CLIENT_ENTRY_COUNTRY=US
set CLIENT_ENTRY_ALIAS=clientkey
set CLIENT_ENTRY_KEYPASSPHRASE=changeit
set CLIENT_ENTRY_EXPORTEDCERT_LOCATION=%EXPORTEDCERTS_BASE_FOLDER%\client.pub.cer
set CLIENT_KEYSTORE_TRUSTED_SERVERCERT_ALIAS=trusted_serverkey

REM Make sure we start out with fresh folders / keystores...
if exist "%BASE_FOLDER%" rd /S /Q %BASE_FOLDER%
if exist "%KEYSTORES_BASE_FOLDER%" rd /S /Q %KEYSTORES_BASE_FOLDER%
if exist "%EXPORTEDCERTS_BASE_FOLDER%" rd /S /Q %EXPORTEDCERTS_BASE_FOLDER%
mkdir %BASE_FOLDER%
mkdir %KEYSTORES_BASE_FOLDER%
mkdir %EXPORTEDCERTS_BASE_FOLDER%

REM The following 'keytool' command will do ALL of the following in one fell
REM swoop:
REM		(+) Create the keystore itself (passphrase-protected)
REM		(+) Create the server's key (which consists of the private key along
REM 		with the certificate chain for the associated public key)
REM		(+) the server's key will be added to the keystore
REM The server's key will be aliased as 'serverkey' and will be valid for
REM 36,000 days
keytool -genkeypair -keyalg RSA -sigalg MD5withRSA -keysize 1024 -dname "cn=%SERVER_ENTRY_COMMONNAME%, ou=%SERVER_ENTRY_ORGUNIT%, o=%SERVER_ENTRY_ORG%, c=%SERVER_ENTRY_COUNTRY%" -alias %SERVER_ENTRY_ALIAS% -keypass %SERVER_ENTRY_KEYPASSPHRASE% -keystore %SERVER_KEYSTORE_LOCATION% -storepass %SERVER_KEYSTORE_PASSPHRASE% -validity 36000

REM Export the server's certificate (so that it can be imported into the
REM client's keystore as a trusted certificate)...
keytool -exportcert -alias %SERVER_ENTRY_ALIAS% -keystore %SERVER_KEYSTORE_LOCATION% -storepass %SERVER_KEYSTORE_PASSPHRASE% -file %SERVER_ENTRY_EXPORTEDCERT_LOCATION%

REM Creates a new client keystore (to hold the client's key and any trusted
REM server certificates) with a single key entry for the client.

REM Just like we did with the server, the following 'keytool' command will
REM create the client's keystore, create the client's private/public key
REM pair (and will add it to the store) in one fell swoop.  The client's key
REM will be aliased as 'clientkey' and will be valid for 36,000 days
keytool -genkeypair -keyalg RSA -sigalg MD5withRSA -keysize 1024 -dname "cn=%CLIENT_ENTRY_COMMONNAME%, ou=%CLIENT_ENTRY_ORGUNIT%, o=%CLIENT_ENTRY_ORG%, c=%CLIENT_ENTRY_COUNTRY%" -alias %CLIENT_ENTRY_ALIAS% -keypass %CLIENT_ENTRY_KEYPASSPHRASE% -keystore %CLIENT_KEYSTORE_LOCATION% -storepass %CLIENT_KEYSTORE_PASSPHRASE% -validity 36000

REM Export the client's certificate (so that it can be imported into the
REM server's key store as a trusted certificate)...
keytool -exportcert -alias %CLIENT_ENTRY_ALIAS% -keystore %CLIENT_KEYSTORE_LOCATION% -storepass %CLIENT_KEYSTORE_PASSPHRASE% -file %CLIENT_ENTRY_EXPORTEDCERT_LOCATION%

REM Import the client's certificate into the server's keystore (as a trusted
REM certificate entry)...
keytool -importcert -keystore %SERVER_KEYSTORE_LOCATION% -alias %SERVER_KEYSTORE_TRUSTED_CLIENTCERT_ALIAS% -file %CLIENT_ENTRY_EXPORTEDCERT_LOCATION% -storepass %SERVER_KEYSTORE_PASSPHRASE% -noprompt

REM Import the server's certificate into the client's keystore (as a trusted
REM certificate entry)...
keytool -importcert -keystore %CLIENT_KEYSTORE_LOCATION% -alias %CLIENT_KEYSTORE_TRUSTED_SERVERCERT_ALIAS% -file %SERVER_ENTRY_EXPORTEDCERT_LOCATION% -storepass %CLIENT_KEYSTORE_PASSPHRASE% -noprompt