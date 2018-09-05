package edu.ut.ece.social.hw1;

import com.google.common.collect.ImmutableList;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Matching;
import edu.ut.ece.social.hw1.dgs.DGS;
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

    @Test
    public void justRunItDGS_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/testInput.dat", DGS::maxMatchingDGS);

        String[] testOutputArray = testOutput.split("\n");
        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("3");
        assertThat(testOutputArray[2]).contains("(1,-1)");
        assertThat(testOutputArray[3]).contains("(2,-2)");
        assertThat(testOutputArray[4]).contains("(3,-3)");
        assertThat(testOutputArray).hasLength(5);
    }

    @Test
    public void justRunItDGSTest1_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test1.txt", DGS::maxMatchingDGS);

        String[] testOutputArray = testOutput.split("\n");
        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("449");
        assertThat(testOutputArray[3]).contains("(1,-2)");
        assertThat(testOutputArray[5]).contains("(2,-4)");
        assertThat(testOutputArray[4]).contains("(3,-3)");
        assertThat(testOutputArray[2]).contains("(4,-1)");
        assertThat(testOutputArray[6]).contains("(5,-5)");
        assertThat(testOutputArray).hasLength(7);
    }

    @Test
    public void justRunItDGSTest2_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test2.txt", DGS::maxMatchingDGS);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("7");
        assertThat(testOutputArray[4]).contains("(1,-3)");
        assertThat(testOutputArray[5]).contains("(2,-4)");
        assertThat(testOutputArray[3]).contains("(3,-2)");
        assertThat(testOutputArray[7]).contains("(4,-6)");
        assertThat(testOutputArray[11]).contains("(5,-10)");
        assertThat(testOutputArray[8]).contains("(6,-7)");
        assertThat(testOutputArray[10]).contains("(7,-9)");
        assertThat(testOutputArray[2]).contains("(8,-1)");
        assertThat(testOutputArray[6]).contains("(9,-5)");
        assertThat(testOutputArray[9]).contains("(10,-8)");
        assertThat(testOutputArray).hasLength(12);
    }

    @Test
    public void justRunItDGSTest3_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test3.txt", DGS::maxMatchingDGS);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("17889");
    }

    @Test
    public void justRunItDGSTest4_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test4.txt", DGS::maxMatchingDGS);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("473");
    }

    @Test
    public void justRunItDGSTestM3_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/M3.txt", DGS::maxMatchingDGS);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("94");

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
