package com.example.Tutorial;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//shift command and p to initialize a new project in vs code
//cntrl + enter to create new package (macos)

// https://stackoverflow.com/questions/62650732/build-jar-and-run-spring-boot-from-cmd
//once we are done with the project we can run the project/Release the project 
//by first converting it to the JAR file and then running it
//Jar is basically all the classes compiled together into one file so we can prepare it for execution 
@SpringBootApplication
public class TutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

}

// cmd to create jar file : mvn install
// output jar file : Tutorial-0.0.1-SNAPSHOT.jar
//cmd to run jar file (Realese file) : java -jar Tutorial-0.0.1-SNAPSHOT.jar