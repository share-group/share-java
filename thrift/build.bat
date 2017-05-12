@echo off
set thrift=%cd%\thrift-0.9.3.exe

rd /s /q %cd%\..\share-soa\src\java\com\share\soa\thrift\protocol

cd %cd%\share
for /f "tokens=*" %%a in ('dir /b') do (
	echo %%a | findstr ".thrift" && %thrift% -r --gen java -out ..\..\share-soa\src\java %cd%\%%a || cd %cd%
)
