package channelpopularity.state.factory;

import channelpopularity.context.ChannelContext;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public interface SimpleStateFactoryI {
    /**
     * Declared create method of type interface
     * @param state
     * @param context
     * @param result
     * @return states instance of type StateI
     */
    public StateI create(StateName state, ChannelContext context, Results result);
}

