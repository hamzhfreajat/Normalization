# interview-task
This is an evaluation task that is handed to candidates to evaluate their coding and Object-Oriented 
design skills. 

# Task overview
The candidate should implement a normalization java utility to be used to normalize
numerical columns in a CSV file, the project will support two types of normalization and standardization:
* Z-Score
* Min-Max scaling

Interface [Normalizer](src/main/java/com/progressoft/tools/Normalizer.java) defines the contracts 
for both normalization techniques.

The candidate should follow the [unit tests](src/test/java/com/progressoft/tools/NormalizerTest.java)
in this project to identify what is the expected behavior to implement.

# Required setup
To work on this project, you need the following:
* Java 8+
* Maven 3.6+
* A Java IDE like Eclipse, NetBeans, or IntelliJ, IntelliJ is preferred, community versions should
suit your needs.
* Git to clone the project or use the download link to download the project as zip.

# Deliverables
**All requirements mentioned in this section is a must, the submission will be considered as failed
if one is missing**
* The candidate should clone this project then deliver a zip file or a git repository URL as a submission.
* All test cases in [unit tests](src/test/java/com/progressoft/tools/NormalizerTest.java) should be passed.
* Implemented production code should be written as clean as possible, the quality of the submitted code
 is going to be evaluated
* The project build using maven should success and should execute all test cases, if you execute below
build command through a terminal it should execute successfully
```shell script
mvn clean install
``` 
* Beside having a successful test cases, the submission should provide an executable jar file which
allows the evaluator to use the implemented utility through command line as below:
```shell script
java -jar iterview-task-1.0-SNAPSHOT.jar [SOURCE_PATH] [DEST_PATH] [COLUMN_TO_NORMALIZE] [NORMALIZATION_METHOD]
```
for example: if we want to read the ```/home/user/in/marks.csv``` file, apply a Min-Max normalization against
 ```mark``` column in it, then save the updated csv file to ```/home/user/out/marks_normalized.csv```, 
 the command should look like below:
```shell script
java -jar interview-task-1.0-SNAPSHOT.jar /home/user/out/marks.csv /home/user/out/marks_normalized.csv mark min-max
``` 
if z-score is what to apply, the command should look like below:
```shell script
java -jar interview-task-1.0-SNAPSHOT.jar /home/user/out/marks.csv /home/user/out/marks_normalized.csv mark z-score
``` 
* Your submission should have a NOTES.MD file, in which you should describe the following:
    * Describe in your own words what is z-score.
    * Describe in your own words what is Min-Max scaling.
    * Why would we need to use such normalization methods? describe by example, if possible.
    * In the unit tests, we used junit5 library, describe in your own words why would we use such library?
    * Mention down any difficulties or concerns you faced while working on the task. Your feedback is highly appreciated :D.

# Bonus points
To get additional points, you can apply one of the following:
* Extend the design to scale multiple columns separately, if you are going to do this, make sure
to keep the old test cases as is without breaking any code in it and keeping it successful
* Support another type of files, like XML or json, make sure to not break the old cases as well.
* Implement an HTML layout which allows a user to upload a file, choose the column to scale, 
then generate the result CSV file.