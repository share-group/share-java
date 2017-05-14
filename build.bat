@echo off

:开启gradle daemon模式
del /s/q %USERPROFILE%\.gradle\gradle.properties
echo org.gradle.daemon=false > %USERPROFILE%\.gradle\gradle.properties
rd /s/q %cd%\.gradle\

:生成pb文件
rd /s /q %cd%\share-core\src\java\com\share\core\protocol\protobuf
%cd%\protobuf\protoc\protoc.exe --java_out=%cd%\share-core\src\java\ --proto_path=%cd%\protobuf\pb\ %cd%\protobuf\pb\demo.proto
:--js_out=import_style=commonjs,binary:E:\code\pb\pb

cd %cd%
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && del /s /q %cd%\%%a\.gitignore || cd %cd%
)
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && del /s /q %cd%\%%a\.classpath || cd %cd%
)
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && del /s /q %cd%\%%a\.project || cd %cd%
)
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && rd /s /q %cd%\%%a\.settings || cd %cd%
)
for /f "tokens=*" %%a in ('dir /b') do (
   echo %%a | findstr "\-" && rd /s /q %cd%\%%a\bin || cd %cd%
)

gradle clean eclipse && rd /s/q %cd%\.gradle\