package brp;

import basiclearner.BasicLearner;
import basiclearner.SocketSUL;
import com.google.common.collect.ImmutableSet;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

public class BrpLearnerExperiment {

    public static void main(String[] args) {
        SocketSUL sul = null;

        try {
            sul = new SocketSUL(InetAddress.getLoopbackAddress(), 0, true, "reset");

            // the input alphabet
            Collection<String> inputAlphabet = ImmutableSet.of("IACK", "IREQ000", "IREQ001", "IREQ010", "IREQ011", "IREQ100", "IREQ101", "IREQ110", "IREQ111", "ISENDFRAME", "ITIMEOUT");

            // runControlledExperiment for detailed statistics, runSimpleExperiment for just the result
            //BasicLearner.randomWalk_chanceOfResetting = 0;
            //BasicLearner.randomWalk_numberOfSymbols =
            BasicLearner.runControlledExperiment(sul, BasicLearner.LearningMethod.LStar, BasicLearner.TestingMethod.RandomWalk, inputAlphabet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sul != null) {
                try {
                    ((AutoCloseable) sul).close();
                } catch (Exception exception) {
                    // should not happen
                }
            }
        }

    }

}
