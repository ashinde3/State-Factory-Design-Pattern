package channelpopularity.state;


import channelpopularity.util.Results;

public abstract class AbstractState implements StateI {

    /**
     * Method for adding video called from result class
     * @param str
     * @param result
     */
    @Override
    public void addVideo(String str,Results result) {
        result.addVideo(str);
    }

    /**
     * Method for removing video called from result class
     * @param str
     * @param result
     */
    @Override
    public void removeVideo(String str,Results result) {
        result.removeVideo(str);
    }

    /**
     * Method for calculating metrics and getiing popularity score,
     * called from result class
     * @param str
     * @param result
     */
    @Override
    public void calculateMetrics(String str,Results result) {
        result.metrics(str);
    }

    /**
     * Method for generating ad requests called from result class
     * @param str
     * @param result
     */
    @Override
    public void requestAd(String str,Results result) {
        result.adRequest(str);
    }
}

