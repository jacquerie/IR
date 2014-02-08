import it.acubelab.tagme.config.TagmeConfig;
import it.acubelab.tagme.RelatednessMeasure;
import irproject.IRProjectExperiments;
     
public class Main {

    public static void main (String[] args) throws Exception {
        TagmeConfig.init("/home/irproject/config.xml");
        String groupName = "group3";
        String groupPw = "7dGHlSa";
        RelatednessMeasure rel = new Probability("en");

        // Comment the following line to run the program in production mode.
        // IRProjectExperiments.dumpRelatednessExperiment(groupName, rel);

        // Comment the following two lines to run the program in testing mode.
        IRProjectExperiments.launchTagMeExperiment(groupName, groupPw, rel);
        IRProjectExperiments.launchRelatednessExperiment(groupName, groupPw, rel);
    }

}

