package channelpopularity.driver;

import channelpopularity.context.ChannelContext;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.InvalidException;
import channelpopularity.util.Results;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import static channelpopularity.state.StateName.*;

/**
 * @author Akshay Shinde
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws IllegalArgumentException, InvalidException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		try {
			if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
				System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
				System.exit(0);
			}
			System.out.println("Hello World! Lets get started with the assignment");
			/**
			 * File processor instance to read input file argument
			 */
			FileProcessor readInput = new FileProcessor(args[0]);
			/**
			 * Results instance to get output file argument
			 * and store the result in it
			 */
			Results outputFile = new Results(args[1]);
			/**
			 * Creating an array list and storing state names in the list
			 */
			List<StateName> stateName = new ArrayList<>();
			stateName.add(UNPOPULAR);
			stateName.add(MILDLY_POPULAR);
			stateName.add(HIGHLY_POPULAR);
			stateName.add(ULTRA_POPULAR);

			SimpleStateFactory simpleStateFactory = new SimpleStateFactory();
			ChannelContext context = new ChannelContext(simpleStateFactory, stateName, readInput, outputFile);
			context.process();
			outputFile.close();
		}
		/**
		 * Exceptions handling
		 */
		catch (NumberFormatException number) {
			System.err.println("Invalid number found.Check format...Exiting");
			number.printStackTrace();
			System.exit(0);
		}
		catch(NullPointerException nullPtr) {
			System.err.println("Null Pointer exception check...Exiting");
			nullPtr.printStackTrace();
			System.exit(0);
		}
		catch(FileNotFoundException file) {
			System.err.println("File not found...Exiting");
			file.printStackTrace();
			System.exit(0);
		}
		catch (SecurityException security) {
			System.err.println("Security violation...Exiting");
			security.printStackTrace();
			System.exit(0);
		}
		catch(IOException io) {
			System.err.println("Invalid Or No Input File...Exiting");
			io.printStackTrace();
			System.exit(0);
		}
		catch(InvalidPathException path) {
			System.err.println("Invalid Path...Exiting");
			path.printStackTrace();
			System.exit(0);
		}
		catch(IllegalArgumentException arg) {
			System.err.println("Invalid Arguments...Exiting");
			arg.printStackTrace();
			System.exit(0);
		}
		finally {

		}
	}
}

