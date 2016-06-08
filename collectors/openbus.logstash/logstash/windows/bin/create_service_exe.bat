@echo off
if "%OS%" == "Windows_NT" setlocal
rem Guess LS_HOME if not defined
set CURRENT_DIR=%cd%
if not "%LS_HOME%" == "" goto gotHome
set LS_HOME=%~dp0\..
if exist "%LS_HOME%\bin\LogStashService.exe" goto okHome
rem CD to the upper dir
cd ..
set LS_HOME=%cd%
:gotHome
if exist "%LS_HOME%\bin\LogStashService.exe" goto okHome
echo The LogStashService.exe was not found...
echo The LS_HOME environment variable is not defined correctly.
echo This environment variable is needed to run this program
goto end
:okHome
rem Make sure prerequisite environment variables are set
set JAVA_HOME=%LS_HOME%\jre7\
if not "%JAVA_HOME%" == "" goto gotBase
echo The JAVA_HOME environment variable is not defined
echo This environment variable is needed to run this program
goto end 
:gotBase
 
set EXECUTABLE=%LS_HOME%\bin\LogStashService.exe

rem Set default Service name
set SERVICE_NAME=LogStashService
set PR_DISPLAYNAME=LogStash

if "%1" == "" goto displayUsage
if "%2" == "" goto setServiceName
set SERVICE_NAME=%2
set PR_DISPLAYNAME=%2
:setServiceName
if %1 == install goto doInstall
if %1 == remove goto doRemove
if %1 == uninstall goto doRemove
echo Unknown parameter "%1"
:displayUsage
echo.
echo Usage: create_service_exe.bat install/remove [service_name]
goto end

:doRemove
rem Remove the service
"%EXECUTABLE%" //DS//%SERVICE_NAME%
echo The service '%SERVICE_NAME%' has been removed
goto end

:doInstall
rem Install the service
echo Installing the service '%SERVICE_NAME%' ...
echo Using   LS_HOME:        %LS_HOME%
echo Using JAVA_HOME:        %JAVA_HOME%

rem Use the environment variables as an example
rem Each command line option is prefixed with PR_

set PR_DESCRIPTION=Logstash
set PR_INSTALL=%EXECUTABLE%
set PR_LOGPATH=%LS_HOME%\logs
rem Set the server jvm from JAVA_HOME
set PR_JVM=%JAVA_HOME%\bin\java.exe
if exist "%PR_JVM%" goto foundJvm
set PR_JVM=auto
:foundJvm
echo Using JVM:              %PR_JVM%
"%EXECUTABLE%" //IS//%SERVICE_NAME% --StartMode exe --Startup auto --StdError auto --StdOutput auto --LogPath "%PR_LOGPATH%" --StartImage "%PR_JVM%" ++StartParams "-jar#%LS_HOME%\vendor\jar\jruby-complete-1.7.11.jar#%LS_HOME%\lib\logstash\runner.rb#agent#-f#conf/windows_events.conf" --StartPath "%LS_HOME%" --StopMode exe --StopImage "%PR_JVM%" --StopPath "%LS_HOME%" --StopTimeout 60 ++Environment "RUBYLIB=%LS_HOME%\lib#GEM_HOME=%LS_HOME%\vendor\bundle\jruby\1.9#GEM_PATH=%LS_HOME%\vendor\bundle\jruby\1.9"


if not errorlevel 1 goto installed
echo Failed installing '%SERVICE_NAME%' service
goto end
:installed
rem Clear the environment variables. They are not needed any more.
set PR_DISPLAYNAME=
set PR_DESCRIPTION=
set PR_INSTALL=
set PR_LOGPATH=
set PR_CLASSPATH=
set PR_JVM=

echo The service '%SERVICE_NAME%' has been installed.

:end
cd %CURRENT_DIR%
