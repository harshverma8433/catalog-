# Shamir's Secret Sharing Algorithm - Java Implementation

This project implements a simplified version of Shamir's Secret Sharing algorithm using Lagrange Interpolation. It reads encoded points from JSON files, decodes the values, and solves for the constant term of the polynomial, representing the secret.

### Prerequisites

- **Java Development Kit (JDK)**: Ensure that you have JDK 8 or higher installed on your system. You can verify your installation by running the following command in your terminal:
  ```bash
  java -version
JSON Simple Library: This project uses the JSON Simple library for parsing JSON files. You will need to download the JAR file for this library and include it in your classpath.

Download the library from the following link:

JSON Simple JAR
Project Files
The project contains the following files:

SecretSharing.java: The Java source code for implementing Shamir's Secret Sharing algorithm.
testCase1.json: A JSON file containing the first set of test inputs.
testCase2.json: A JSON file containing the second set of test inputs.
README.md: This file, which explains how to set up and run the project.
Steps to Run the Code
Download and Place Files:

Place the SecretSharing.java file, testCase1.json, testCase2.json, and the downloaded json-20240303.jar (or any version of JSON Simple JAR) in the same directory.
Compile the Code: Open a terminal and navigate to the directory where the files are located. Compile the Java code by running the following command:

For Windows:

bash
Copy code
javac -cp ".;json-20240303.jar" SecretSharing.java
For Linux/Mac:

bash
Copy code
javac -cp ".:json-20240303.jar" SecretSharing.java
This command compiles the SecretSharing.java file while including the json-20240303.jar in the classpath.

Run the Program: After compilation, run the program using the following command:

For Windows:

bash
Copy code
java -cp ".;json-20240303.jar" SecretSharing
For Linux/Mac:

bash
Copy code
java -cp ".:json-20240303.jar" SecretSharing
The program will read both JSON test case files (testCase1.json and testCase2.json), decode the values, and print the secret for each test case.

Expected Output
After running the program, the console will display the calculated secret for each test case, as follows:

php
Copy code
Secret for testcase1.json: <calculated_secret_1>
Secret for testcase2.json: <calculated_secret_2>
The secrets are the constant terms of the respective polynomials decoded from the test case data.

Error Handling
If any errors occur during the execution of the program (such as a non-invertible modular value), the program will skip the invalid term and continue calculating with the remaining terms. Warnings will be printed to the console to indicate any skipped terms.

Additional Notes
Ensure that the JSON test cases are properly formatted and that the values match the expected format.
The modulus used in this implementation is 
2
127
−
1
2 
127
 −1, which is a large Mersenne prime, commonly used in cryptographic applications for modular arithmetic.
