package edu.ut.ece.social.hw1;

import com.google.common.collect.ImmutableList;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Matching;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class HwRunnerTest {

    static BipartiteGraph<Integer, Integer> sampleGraph;
    private CachingPrintStream myPrintStream;

    @Before
    public void beforeTest() {
        myPrintStream = new CachingPrintStream();
    }

    @Test
    public void justRunIt_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        System.setOut(myPrintStream);

        HwRunner.runMaximumMatchingProblem("./src/test/resources/testInput.dat", testMaxMatch);

        assertThat(myPrintStream.lines.get(0)).matches("Elapsed time:.*ms");
        assertThat(myPrintStream.lines).contains("3");
        assertThat(myPrintStream.lines).contains("(1,-1)");
        assertThat(myPrintStream.lines).contains("(2,-2)");
        assertThat(myPrintStream.lines).contains("(3,-3)");
    }

    private static class CachingPrintStream extends PrintStream {

        CachingPrintStream() {
            super(new ByteArrayOutputStream());
        }

        List<String> lines = new ArrayList<>();

        @Override
        public void println(String s) {
            lines.add(s);
        }
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
