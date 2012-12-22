@echo off 


set LST_PATH=%1

if "%LST_PATH%"=="" (
	set LST_PATH=CopyBuild.lst
)

if not exist %LST_PATH% (
	echo error no lst file found ...
	goto end
)

for /f "eol=; tokens=1,2" %%i in (%LST_PATH%) do (
	echo copy %%i to %%j ...
	call :copyFile %%i %%j 
)
goto end

:copyFile 
setlocal
	set SRC=%1
	set DST=%2
	copy %SRC% %DST% /y 
	if NOT ERRORLEVEL 0 (
		goto error
	)
endlocal
exit /b


:error
echo Copy failed....
:end 

