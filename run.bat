@echo off
call mvn clean assembly:assembly
if "%errorlevel%"=="0" (
    echo.
    echo.
    echo "Starting RPNCalculator"
    echo "--------------------------------------"
    echo "RPN calculator (Press Ctrl+C to exit)."
    echo "--------------------------------------"
    java -jar target/RPNCalculator-1.0-SNAPSHOT.jar
)