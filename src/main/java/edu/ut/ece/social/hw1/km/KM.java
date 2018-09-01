package edu.ut.ece.social.hw1.km;

import com.google.common.base.Preconditions;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Labelling;
import edu.ut.ece.social.graph.Matching;
import edu.ut.ece.social.hw1.HwRunner;

import java.io.FileNotFoundException;
import java.time.Instant;

import static com.google.common.base.Preconditions.checkArgument;

public class KM {

    public static Matching<Integer> maxMatchingKuhnMunkres(BipartiteGraph<Integer, Integer> graph) {

        Matching<Integer> m = BipartiteGraphFactory.emptyMatching();
        Labelling<Integer, Integer> l = Labelling.createFeasibleLabellingOn(graph);
        BipartiteGraph<Integer, Integer> El = l.getEqualityGraphOn(graph);

        while (!m.isPerfectMatchingOn(graph)) {

        }

        return m;
    }

    public static void main(String args[]) throws FileNotFoundException {

        checkArgument(args.length==1, "Required filename as sole command line argument");

        String inputFileName = args[0];
        HwRunner.runMaximumMatchingProblem(inputFileName, KM::maxMatchingKuhnMunkres);
    }

}
