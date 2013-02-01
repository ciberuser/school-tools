@echo off

set SOURCE_PATH=%1
set DEST_PATH=%2

echo copying all files....

if not exist %DEST_PATH% (
	echo going to create directory path : %DEST_PATH%
	mkdir %DEST_PATH%
	echo %ERRORLEVEL%
	if ERRORLEVEL not 0 (
		echo failed to create dir exist
		exit  
	)
)

xcopy %SOURCE_PATH%\*.* %DEST_PATH%\*.* /y /e /c



