@rem Gradle wrapper startup script for Windows
@if "%DEBUG%"=="" @echo off
setlocal
set DIRNAME=%~dp0
java -Xmx2048m -jar "%DIRNAME%gradle\wrapper\gradle-wrapper.jar" %*
