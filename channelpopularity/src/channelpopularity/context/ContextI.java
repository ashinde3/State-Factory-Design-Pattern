package channelpopularity.context;

import channelpopularity.state.StateName;

/**
 * Declared setCurrentState method
 */
public interface ContextI {
    public void setCurrentState(StateName nextState);
}

