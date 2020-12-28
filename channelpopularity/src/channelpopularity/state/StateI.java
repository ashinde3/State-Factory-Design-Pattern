package channelpopularity.state;

import channelpopularity.util.Results;

public interface StateI {
    /**
     * Declared actions or methods such as add video,remove video,calculate metrics
     * and ad requests
     * @param str
     * @param result
     */
    public void addVideo(String str, Results result);
    public void removeVideo(String str,Results result);
    public void calculateMetrics(String str,Results result);
    public void requestAd(String str,Results result);
}

