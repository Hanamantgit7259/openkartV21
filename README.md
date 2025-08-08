**OpenKartV21 - Selenium Automation Framework**
📌 Overview
OpenKartV21 is a Selenium WebDriver-based automation testing framework designed to validate the OpenKart web application.
It supports functional, regression, and cross-browser testing with parallel execution capabilities.

**🚀 Features**
Automated test scripts for multiple OpenKart modules.
Cross-browser testing (Chrome, Firefox, Edge).
Parallel execution with TestNG.
Data-driven testing using Apache POI.
Maven for build & dependency management.
HTML & Extent Reports for results.

**🛠 Tech Stack**
Programming Language: Java
Automation Tool: Selenium WebDriver
Test Framework: TestNG
Build Tool: Maven
Reporting: TestNG HTML Reports, Extent Reports
Data Handling: Apache POI (Excel)

**Folder Structure**
├── src/main/java        # Core framework code (Base classes, utilities, helpers)
├── src/test/java        # Test cases for OpenKart
├── pom.xml              # Maven dependencies & build configuration
├── testng.xml           # TestNG suite configuration
├── grid-docker.xml      # Selenium Grid configuration
├── parallelTesting.xml  # Parallel execution config
├── run.bat              # Batch file to trigger tests
└── README.md            # Project documentation

**How to Run Tests**
1️⃣ **Clone the Repository**
git clone https://github.com/Hanamantgit7259/openkartV21.git
cd openkartV21

2️⃣ **Run Tests with Maven**
mvn clean test

3️⃣ **Run Tests with TestNG XML**
mvn clean test -DsuiteXmlFile=master.xml

**📊 Test Reports**
TestNG HTML Report: test-output/index.html
Extent Report: Generated after execution in Reports/

**👨‍💻 Author: **
Hanamant Gulaganjikoppa
Quality Engineering Analyst | Automation Tester @infosys Technologies Limited

**📜 License & Community Note**
This project was created out of my love for automation, my passion for problem-solving, and my interest in helping the testing community grow.
I’ve made it public so anyone can pull, explore, and enhance it — whether you’re learning automation or want to contribute with your own ideas.
💬 Let’s connect, share knowledge, and keep making the world of automation better — one test script at a time!


