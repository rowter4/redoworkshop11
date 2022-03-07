package sg.edu.nus.iss.tryagain11;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tryagain11Application {

	// Step 1: Have a logger and import the logger package
	private static final Logger logger = LoggerFactory.getLogger(Tryagain11Application.class);

	// Step 2: Set the default port for the application
	private static final String DEFAULT_PORT = "3000";

	// All subsequent steps must be within the main class
	public static void main(String[] args) {
		logger.info("Main method Workshop11 Fired!");

		// Step 4: Initialise the Spring App
		SpringApplication app = new SpringApplication(Tryagain11Application.class);

		// Step 5: Need to decode the app arguments using the spring helper
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		// Step 6: Save the decoded arguments as a list of strings
		List<String> optsVal = appArgs.getOptionValues("port");
		logger.info("optsValues are as follows : " + optsVal);

		// Step 7: Need to create a variable to store the port number as a string
		String portNumber = null;

		/* Step 8: Would like to check if there are any existing port numbers and would like to
		   store the value inside the portNumber variable (this is for the else condition).

		   If there is not an existing port number given in the argument, we would want to get the 
		   port number from the existing environment variable. If this env variable do not have 
		   the port number, then we will set the port to the default value i.e. port 3000
		*/
		if (optsVal == null || optsVal.get(0) == null) {
			portNumber = System.getenv("PORT");
			if (portNumber == null) {
				portNumber = DEFAULT_PORT;
			}
		} else {
			portNumber = (String)optsVal.get(0); 
		}


		/* Step 9 : We will then use the determined portNumber from Step 8 and set it within springboot.
			We are mapping the server.port key to the determined portNumber value using singletonMap
		*/
		if (portNumber != null) {
			logger.info("APP RUNNING ON PORT " + portNumber);
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}

		app.run(args);
	}

}
