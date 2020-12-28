package channelpopularity.context;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.InvalidException;
import channelpopularity.util.Results;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ChannelContext implements ContextI {
    /**
     * Create instances of state interface and result class
     */
    private StateI currentState;
    private Results result;

    /**
     * create HashMap for available states
     */
    private Map<StateName, StateI> availableStates = new HashMap<>();
    private FileProcessor readInput;

    /**
     * parameterized constructor with simple state factory interface instance, list of states,
     * instance of file processor to read input file and result class instance and initialization
     * of current state
     * @param stateFactoryIn
     * @param stateNames
     * @param readInput
     * @param result
     */
    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames, FileProcessor readInput, Results result) {
        this.result = result;
        this.readInput = readInput;
        for(StateName states : stateNames) {
            //System.out.println("context "+states);
            availableStates.put(states,stateFactoryIn.create(states,this,result));
        }
        setCurrentState(StateName.UNPOPULAR);
    }

    /**
     * Method to set states
     * @param nextState
     */
    //@Override
    public void setCurrentState(StateName nextState) {
        if(availableStates.containsKey(nextState)) {
            currentState = availableStates.get(nextState);
        }
    }

    /**
     * Method to read input file line by line and process each line
     * based on the operations
     * @throws IOException
     */
    public void process() throws IOException {
        String line = null;
        boolean fileFlag = false;
        Pattern videoAdd = Pattern.compile("\\w+_\\w+::\\w+");
        Pattern videoRemove = Pattern.compile("\\w+_\\w+::\\w+");
        Pattern stringMetrics = Pattern.compile("\\w+__\\w+::\\[(\\w+=-?\\w+,)+\\w+=-?\\w+\\]");
        Pattern requestAd = Pattern.compile("\\w+_\\w+__\\w+::\\w+=-?\\w+");
        while (null != (line = readInput.poll())) {
            fileFlag = true;

            if (line.contains("ADD_VIDEO")) {
                //System.out.println("add context "+line);
                if(!videoAdd.matcher(line).matches()) {
                    try {
                        throw new InvalidException("Invalid add input");
                    } catch (InvalidException e) {
                        System.err.println("Invalid add input");
                        e.printStackTrace();
                        System.exit(0);
                    } finally {

                    }
                }
                currentState.addVideo(line, result);
            }
            else if (line.contains("REMOVE_VIDEO")) {
                if(!videoRemove.matcher(line).matches()) {
                    try {
                        throw new InvalidException("Invalid remove input");
                    } catch (InvalidException e) {
                        System.err.println("Invalid remove input");
                        e.printStackTrace();
                        System.exit(0);
                    } finally {

                    }
                }
                currentState.removeVideo(line, result);
            }
            else if (line.contains("METRICS")) {
                if(!stringMetrics.matcher(line).matches()) {
                    try {
                        throw new InvalidException("Invalid metrics input");
                    } catch (InvalidException e) {
                        System.err.println("Invalid metrics input");
                        e.printStackTrace();
                        System.exit(0);
                    } finally {

                    }
                }
                currentState.calculateMetrics(line, result);
            }
            else if (line.contains("AD_REQUEST")) {
                if(!requestAd.matcher(line).matches()) {
                    try {
                        throw new InvalidException("Invalid ad request input");
                    } catch (InvalidException e) {
                        System.err.println("Invalid ad request input");
                        e.printStackTrace();
                        System.exit(0);
                    } finally {

                    }
                }
                currentState.requestAd(line, result);
            }

        }
        if(fileFlag == false) {
            System.err.println("Empty file detected...exiting");
            System.exit(0);
        }
    }

    /**
     * toString method for debugging purposes
     * @return class name of return type string
     */
    public String toString()
    {
        return "CHANNEL CONTEXT CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

