package channelpopularity.state;

import channelpopularity.context.ChannelContext;
import channelpopularity.operation.Operation;
import channelpopularity.operation.OutputConstants;
import channelpopularity.util.Results;
import static channelpopularity.state.StateName.UNPOPULAR;
import static channelpopularity.state.StateName.MILDLY_POPULAR;
import static channelpopularity.state.StateName.HIGHLY_POPULAR;
import static channelpopularity.state.StateName.ULTRA_POPULAR;

public class HighlyPopularState extends AbstractState {

    /**
     * Create instances of context class and result class
     */
    private ChannelContext context;
    private Results result;

    /**
     * parameterized constructor with instances of context and result class
     * @param context
     * @param result
     */
    public HighlyPopularState(ChannelContext context, Results result) {
        this.context = context;
        this.result = result;
    }

    /**
     * Method to add videos in the channel and retrieve popularity score and
     * States transition
     * @param str
     * @param result
     */
    @Override
    public void addVideo(String str,Results result) {
        super.addVideo(str,result);
        result.writeToFile(HIGHLY_POPULAR+"__"+ OutputConstants.VIDEO_ADDED+"::"+str.split("::")[1]);
        result.writeToStdout(HIGHLY_POPULAR+"__"+ OutputConstants.VIDEO_ADDED+"::"+str.split("::")[1]);

        if((result.getPopularityScore() >= 0) && (result.getPopularityScore() <= 1000)) {
            context.setCurrentState(UNPOPULAR);
        }
        else if((result.getPopularityScore() > 1000) && (result.getPopularityScore() <= 10000)) {
            context.setCurrentState(MILDLY_POPULAR);
        }
        else if((result.getPopularityScore() > 10000) && (result.getPopularityScore() <= 100000)) {
            context.setCurrentState(HIGHLY_POPULAR);
        }
        else if((result.getPopularityScore() > 100000) && (result.getPopularityScore() <= Integer.MAX_VALUE)) {
            context.setCurrentState(ULTRA_POPULAR);
        }

    }

    /**
     * Method to remove videos and set the next state based on the popularity score
     * @param str
     * @param result
     */
    @Override
    public void removeVideo(String str,Results result) {
        super.removeVideo(str,result);
        result.writeToFile(HIGHLY_POPULAR+"__"+ OutputConstants.VIDEO_REMOVED+"::"+str.split("::")[1]);
        result.writeToStdout(HIGHLY_POPULAR+"__"+ OutputConstants.VIDEO_REMOVED+"::"+str.split("::")[1]);

        if((result.getPopularityScore() >= 0) && (result.getPopularityScore() <= 1000)) {
            context.setCurrentState(UNPOPULAR);
        }
        else if((result.getPopularityScore() > 1000) && (result.getPopularityScore() <= 10000)) {
            context.setCurrentState(MILDLY_POPULAR);
        }
        else if((result.getPopularityScore() > 10000) && (result.getPopularityScore() <= 100000)) {
            context.setCurrentState(HIGHLY_POPULAR);
        }
        else if((result.getPopularityScore() > 100000) && (result.getPopularityScore() <= Integer.MAX_VALUE)) {
            context.setCurrentState(ULTRA_POPULAR);
        }
    }

    /**
     * Method to calculate the popularity score of the channel
     * @param str
     * @param result
     */
    @Override
    public void calculateMetrics(String str,Results result) {
        super.calculateMetrics(str,result);
        if((result.getPopularityScore() > 10000) && (result.getPopularityScore() <= 100000)) {
            result.writeToFile(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));
            result.writeToStdout(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));

        }
        else if((result.getPopularityScore() > 100000))
        {
            result.writeToFile(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));
            result.writeToStdout(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));
            context.setCurrentState(ULTRA_POPULAR);
        }
        else if((result.getPopularityScore() > 1000) && (result.getPopularityScore() <= 10000)) {
            result.writeToFile(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));
            result.writeToStdout(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));
            context.setCurrentState(MILDLY_POPULAR);
        }
        else if((result.getPopularityScore() >= 0) && (result.getPopularityScore() <= 1000)) {
            result.writeToFile(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));
            result.writeToStdout(HIGHLY_POPULAR+"__"+ OutputConstants.POPULARITY_SCORE_UPDATE+"::"+String.format("%.2f",result.getPopularityScore()));
            context.setCurrentState(UNPOPULAR);
        }
    }

    /**
     * Method to approve or reject ad requests based on the given range
     * @param str
     * @param result
     */
    @Override
    public void requestAd(String str,Results result) {
        super.requestAd(str,result);
        if((result.getAdLength() > 1) && (result.getAdLength() <= 30)) {
            result.writeToFile(HIGHLY_POPULAR+"__"+ Operation.AD_REQUEST+"::APPROVED");
            result.writeToStdout(HIGHLY_POPULAR+"__"+ Operation.AD_REQUEST+"::APPROVED");

        }
        else {
            result.writeToFile(HIGHLY_POPULAR+"__"+ Operation.AD_REQUEST+"::REJECTED");
            result.writeToStdout(HIGHLY_POPULAR+"__"+ Operation.AD_REQUEST+"::REJECTED");

        }
    }

    /**
     * toString() method for debugging purposes
     * @return class name of return type string
     */
    public String toString()
    {
        return "HIGHLY_POPULAR STATE CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

