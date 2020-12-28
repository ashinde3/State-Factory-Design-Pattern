package channelpopularity.state.factory;

import channelpopularity.context.ChannelContext;
import channelpopularity.state.UnpopularState;
import channelpopularity.state.MildlyPopularState;
import channelpopularity.state.HighlyPopularState;
import channelpopularity.state.UltraPopularState;
import channelpopularity.state.StateName;
import channelpopularity.state.StateI;
import channelpopularity.util.Results;

public class SimpleStateFactory implements SimpleStateFactoryI {

    /**
     * Method to create instances of state using simple
     * factory design pattern
     * @param stateE
     * @param context
     * @param result
     * @return state instance of state interface
     */
    @Override
    public StateI create(StateName stateE, ChannelContext context, Results result) {
        StateI state = null;
        //System.out.println("In "+stateE);
        if(stateE.equals(StateName.UNPOPULAR)){
            state = new UnpopularState(context,result);
        }
        else if(stateE.equals(StateName.MILDLY_POPULAR)) {
            state = new MildlyPopularState(context,result);
        }
        else if(stateE.equals(StateName.HIGHLY_POPULAR)) {
            state = new HighlyPopularState(context,result);
        }
        else if(stateE.equals(StateName.ULTRA_POPULAR)) {
            state = new UltraPopularState(context,result);
        }
        return state;
    }

    /**
     * toString() method for debugging purposes
     * @return class name of return type double
     */
    public String toString()
    {
        return "CHANNEL CONTEXT CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

