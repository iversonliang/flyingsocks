@echo off
title "flyingsocks-client-2.0"

if "%1%" == "-install" (
    md C:\ProgramData\flyingsocks-cli
    md C:\ProgramData\flyingsocks-cli\log
    exit
)

echo "Run flyingsocks-client..."
if "%1%" == "-debug" (
    start /b java -Xbootclasspath/a:../conf;../resources -jar ../lib/flyingsocks-cli-2.0.jar
) else (
    start /b javaw -Xbootclasspath/a:../conf;../resources -jar ../lib/flyingsocks-cli-2.0.jar
)

echo "Complete"
