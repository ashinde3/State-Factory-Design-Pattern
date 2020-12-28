package channelpopularity.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    /**
     * Initialization of data members
     */
    private Map<String,String> videoStore = new HashMap<>();
    private int likes = 0;
    private int dislikes = 0;
    private int views = 0;
    private int adLength = 0;
    private double popularityScore = 0.0;
    private FileWriter outputFile;

    /**
     * Results constructor for creating new instance of file
     * @param fileName
     */
    public Results(String fileName) {
        try {
            outputFile = new FileWriter(new File(fileName));
        } catch (IOException e) {
            System.err.println("No file");
            e.printStackTrace();
        } finally {

        }
    }


    /**
     * Method to return popularity score
     * @return popularity score of return type double
     */
    public double getPopularityScore() {
        return popularityScore;
    }

    /**
     * Method to add video and store in HashMap
     * @param addVideo
     */
    public void addVideo(String addVideo) {
        String add[] = addVideo.split("::");
        if(videoStore.containsKey(add[1])) {
            try {
                throw new InvalidException("Video already present.");
            } catch (InvalidException e) {
                System.err.println("Video already present.");
                e.printStackTrace();
                System.exit(0);
            } finally {

            }
        }
        videoStore.put(add[1],"VIEWS=0,LIKES=0,DISLIKES=0");
        popularityScore= views+(2 *(likes-dislikes));
        popularityScore/=videoStore.size();
        if(popularityScore < 0) {
            popularityScore = 0;
        }
    }

    /**
     * Method to remove videos from channel.
     * @param removeVideo
     * @throws NumberFormatException
     */
    public void removeVideo(String removeVideo) throws NumberFormatException{
        String remove[] = removeVideo.split("::");
        if(!videoStore.containsKey(remove[1])){
            try {
                throw new InvalidException("Video is removed already or not added");
            } catch (InvalidException e) {
                System.err.println("Video is removed already or not added");
                e.printStackTrace();
                System.exit(0);
            } finally {

            }
        }
        String key=remove[1];
        String val= videoStore.get(key);
        String[] old = val.split(",");
        int views_Old=Integer.parseInt( old[0].split("=")[1]);//"VIEWS=1000";
        if(views_Old < 0) {
            try {
                throw new InvalidException("Views can't be negative.");
            } catch (InvalidException e) {
                System.err.println("Views can't be negative.");
                e.printStackTrace();
                System.exit(0);
            } finally {

            }
        }
        int likes_Old=Integer.parseInt(old[1].split("=")[1]);
        int dislikes_Old=Integer.parseInt(old[2].split("=")[1]);

        likes-=likes_Old;
        views-=views_Old;
        dislikes-=dislikes_Old;

        //REMOVE_VIDEO::video2
        videoStore.remove(remove[1]);

        /**
         * Calculating popularity score
         */
        popularityScore= views+(2 *(likes-dislikes));
        popularityScore/=videoStore.size();
        if(popularityScore < 0) {
            popularityScore = 0;
        }

    }

    /**
     * Method to calculate popularity score based on metrics such as
     * views,likes and dislikes
     * @param metrics
     * @throws NumberFormatException
     */
    public void metrics(String metrics) throws NumberFormatException {

        //METRICS__video1::[VIEWS=1000,LIKES=20,DISLIKES=20];
        String  strSplit = metrics.split("::")[1];
        String key=metrics.split("::")[0].split("__")[1];
        String val= videoStore.get(key);
        String[] old = val.split(",");

        int views_Old=Integer.parseInt( old[0].split("=")[1]);//"VIEWS=1000";
        if(views_Old < 0) {
            try {
                throw new InvalidException("Views can't be negative.");
            } catch (InvalidException e) {
                System.err.println("Views can't be negative.");
                e.printStackTrace();
                System.exit(0);
            } finally {

            }
        }
        int likes_Old=Integer.parseInt(old[1].split("=")[1]);
        int dislikes_Old=Integer.parseInt(old[2].split("=")[1]);

        String metricString= strSplit.substring(1,strSplit.length()-1);
        String[] metricsString1=metricString.split(",");
        int views_New=Integer.parseInt( metricsString1[0].split("=")[1]);//"VIEWS=1000";
        if(views_New < 0) {
            try {
                throw new InvalidException("Views can't be negative.");
            } catch (InvalidException e) {
                System.err.println("Views can't be negative.");
                e.printStackTrace();
                System.exit(0);
            } finally {

            }
        }
        /**
         * Calculate total views, likes, dislikes
         */
        int likes_New=Integer.parseInt(metricsString1[1].split("=")[1]);
        int dislikes_New=Integer.parseInt(metricsString1[2].split("=")[1]);
        views+=views_New;
        likes+=likes_New;
        dislikes+=dislikes_New;
        if(likes < 0 || dislikes < 0) {
            System.err.println("likes or dislikes negative"+"\nLIKES = "+likes+", DISLIKES = "+dislikes);
            System.exit(0);
        }
        /**
         * Calculating popularity score
         */
        popularityScore= views+(2 *(likes-dislikes));
        popularityScore/=videoStore.size();
        if(popularityScore < 0) {
            popularityScore = 0;
        }
        views_Old+=views_New;
        likes_Old+=likes_New;
        dislikes_Old+=dislikes_New;
        if(likes_Old < 0 || dislikes_Old < 0) {
            System.err.println("likes or dislikes negative__LIKES = "+likes_Old+", DISLIKES = "+dislikes_Old);
            System.exit(0);
        }
        val="VIEWS="+views_Old+",LIKES="+likes_Old+",DISLIKES="+dislikes_Old;
        videoStore.put(key,val);

    }

    /**
     * Method to generate ad requests and get adLength
     * @param request
     * @throws NumberFormatException
     */
    public void adRequest(String request) throws NumberFormatException{
        //AD_REQUEST__video1::LEN=8
        String req = request.split("::")[0].split("__")[1];
        if(!videoStore.containsKey(req)){
            try {
                throw new InvalidException("No videos found for AdRequest");
            } catch (InvalidException e) {
                System.err.println("No videos found for AdRequest");
                e.printStackTrace();
                System.exit(0);
            } finally {

            }

        }
        String strReq = request.split("::")[1].split("=")[1];
        adLength = Integer.parseInt(strReq);
        if(adLength < 0) {
            try {
                throw new InvalidException("Ad length can't be negative");
            } catch (InvalidException e) {
                System.err.println("Ad length can't be negative");
                e.printStackTrace();
                System.exit(0);
            } finally {

            }
        }
    }

    public int getAdLength() {
        return adLength;
    }

    /**
     * Method to write output to the file
     * @param sentence
     */
    @Override
    public void writeToFile(String sentence) {
        try {
            outputFile.write(sentence+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to write output to the stdout
     * @param sentence
     */
    @Override
    public void writeToStdout(String sentence) {
        System.out.println(sentence);
    }

    /**
     * Close file connection
     */
    public void close() {
        try {
            outputFile.close();
        } catch (IOException e) {
            System.err.println("could not close file");
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * toString() method for debugging purposes
     * @return class name of return type string
     */
    public String toString()
    {
        return "RESULTS CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}
