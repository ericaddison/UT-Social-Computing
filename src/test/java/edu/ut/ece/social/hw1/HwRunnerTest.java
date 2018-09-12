package edu.ut.ece.social.hw1;

import com.google.common.collect.ImmutableList;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Matching;
import edu.ut.ece.social.hw1.dgs.DGS;
import edu.ut.ece.social.hw1.km.KM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class HwRunnerTest {

    @Test
    public void justRunIt_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/testInput.dat", testMaxMatch);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("3");
        assertThat(testOutputArray[2]).contains("(1,1)");
        assertThat(testOutputArray[3]).contains("(2,2)");
        assertThat(testOutputArray[4]).contains("(3,3)");
    }

    private static MaximumMatchingAlgorithm testMaxMatch = graph -> {
        Matching<Integer> matching = BipartiteGraphFactory.emptyMatching();
        ImmutableList<Integer> leftSideNodes = graph.leftSideNodes().asList();
        ImmutableList<Integer> rightSideNodes = graph.rightSideNodes().asList();

        for(int i=0; i<leftSideNodes.size(); i++) {
            matching.putEdge(leftSideNodes.get(i), rightSideNodes.get(i));
        }
        return matching;
    };

}
