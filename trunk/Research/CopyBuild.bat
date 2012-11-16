@echo off 

set BUILD_FILE=TraitsFinderCLI.jar
set OUTPUT_BUILD_MAIN_PATH=C:\Users\oshapira\Dropbox\TraitsFinder
Set BUILD_DEFAULT_VERSION=%OUTPUT_BUILD_MAIN_PATH%


call :copyBuild

:copyBuild

if exist %BUILD_FILE% (
echo copy build file...
copy %BUILD_FILE% %BUILD_DEFAULT_VERSION%\%BUILD_FILE% /y 
if NOT ERRORLEVEL 0 (
	goto error
)
echo copy success.
goto end

)

:error
echo Copy failed....
:end 

