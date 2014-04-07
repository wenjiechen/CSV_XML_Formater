Assumptions and design trade-off
---
1. In test case #3 ¡°Generate XML output from XML input¡±, assume that it should be ¡°Generate XML output from CSV input¡±.
2. Assume the fields in input files would not be change easily, the header of the ouput CSV file is hard code in XMLFormater.
3. The names of Fields are different between CSV file and XML file. The descriptions of Field enum class can¡¯t be used effectively.
4. Assume input xml file is validated. 
5. Using Dom4j to parse xml file instead of using JAXB xjc to generate JAXB classes from an XML schema file.
6. Each class and public methods have Javadoc.

Configuration
---
1. The project is built on eclipse, and use JUnit Library and Dom4j Library
2. Add dom4j-2.0.0-ALPHA-2.jar into project build path.
3. It might need to add JUnit 4 Library into project build path. But eclipse has this library, it should be no problem.
4. If you don¡¯t have JRE 1.7, you can change it to JRE 1.6 in project build path

Compile and Run
---
1. In testsrc folder, XMLFormaterTest has test case 1, 2 and other test cases whose method names are self- explaining. It generates CSV output files in ¡°testOutput¡± directory. 
2. 1. In testsrc folder, CSVFormaterTest has test case 3 and other test cases whose method names are self- explaining. It generates XML output files in ¡°testOutput¡± directory. 


3th party Libraries
---
1. dom4j-2.0.0-ALPHA-2.jar
2. JUnit 4
