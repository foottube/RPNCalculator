# TEST
mvn clean assembly:assembly
if [ $? -eq 0 ]; then
    echo
    echo
    echo "Starting RPNCalculator"
    echo "--------------------------------------"
    echo "RPN calculator (Press Ctrl+C to exit)."
    echo "--------------------------------------"
    java -jar target/RPNCalculator-1.0-SNAPSHOT.jar
fi
