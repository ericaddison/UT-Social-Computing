package edu.ut.ece.social.hw1;

import com.google.common.collect.ImmutableList;
import edu.ut.ece.social.hw1.dgs.DGS;
import edu.ut.ece.social.hw1.km.KM;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.FileNotFoundException;
import java.util.Collection;

import static com.google.common.truth.Truth.assertThat;

@RunWith(Parameterized.class)
public class SampleAnswerTest {


    @Parameters
    public static Collection<MaximumMatchingAlgorithm> data() {
        return ImmutableList.of(DGS::maxMatchingDGS, KM::maxMatchingKuhnMunkres);
    }

    private final MaximumMatchingAlgorithm maximumMatchingAlgorithm;

    public SampleAnswerTest(MaximumMatchingAlgorithm maximumMatchingAlgorithm) {
        this.maximumMatchingAlgorithm = maximumMatchingAlgorithm;
    }


    @Test
    public void evaluateSample_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/testInput.dat", maximumMatchingAlgorithm);

        String[] testOutputArray = testOutput.split("\n");
        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("3");
        assertThat(testOutputArray[2]).contains("(1,1)");
        assertThat(testOutputArray[3]).contains("(2,2)");
        assertThat(testOutputArray[4]).contains("(3,3)");
        assertThat(testOutputArray).hasLength(5);
    }

    @Test
    public void evaluateSampleTest1_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test1.txt", maximumMatchingAlgorithm);

        String[] testOutputArray = testOutput.split("\n");
        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("449");
        assertThat(testOutputArray[2]).contains("(1,2)");
        assertThat(testOutputArray[3]).contains("(2,4)");
        assertThat(testOutputArray[4]).contains("(3,3)");
        assertThat(testOutputArray[5]).contains("(4,1)");
        assertThat(testOutputArray[6]).contains("(5,5)");
        assertThat(testOutputArray).hasLength(7);
    }

    @Test
    public void evaluateSampleTest2_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test2.txt", maximumMatchingAlgorithm);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("7");
        assertThat(testOutputArray[2]).contains("(1,3)");
        assertThat(testOutputArray[3]).contains("(2,4)");
        assertThat(testOutputArray[4]).contains("(3,2)");
        assertThat(testOutputArray[5]).contains("(4,6)");
        assertThat(testOutputArray[6]).contains("(5,10)");
        assertThat(testOutputArray[7]).contains("(6,7)");
        assertThat(testOutputArray[8]).contains("(7,9)");
        assertThat(testOutputArray[9]).contains("(8,1)");
        assertThat(testOutputArray[10]).contains("(9,5)");
        assertThat(testOutputArray[11]).contains("(10,8)");
        assertThat(testOutputArray).hasLength(12);
    }

    @Test
    public void evaluateSampleTest3_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test3.txt", maximumMatchingAlgorithm);

        String[] testOutputArray = testOutput.split("\n");
        String[] testOutputArray2 = testOutput.split("\n",2);

        assertThat(testOutputArray2[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray2[1]).contains("17889");
    }

    @Test
    public void evaluateSampleTest4_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/test4.txt", maximumMatchingAlgorithm);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("473");
    }

    @Test
    public void evaluateSampleTestM3_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runMaximumMatchingProblemWithOutput("./src/test/resources/M3.txt", maximumMatchingAlgorithm);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).matches("Elapsed time:.*ms");
        assertThat(testOutputArray[1]).contains("94");

    }
}
