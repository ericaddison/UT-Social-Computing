package edu.ut.ece.social.hw2;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.StringReader;

@RunWith(JUnit4.class)
public class InputReaderTest {

    private static final String INPUT_3x3 =
            "3 // number of men/women\n" +
                    "1 2 3 // prefs of man 1\n" +
                    "3 2 1\n" +
                    "2 3 1\n" +
                    "1 3 2\n" +
                    "2 3 1 // prefs of woman 1\n" +
                    "2 1 3\n" +
                    "1 2 3\n";

    @Test
    public void readingTestString_shouldReturnValidMarriageProblem() {
        BufferedReader reader = new BufferedReader(new StringReader(INPUT_3x3));

        MarriageProblem problem = InputReader.readProblemFromReader(reader);

        assertThat(problem.getNumberOfMen()).isEqualTo(3);
        assertThat(problem.getManPrefs(1)).containsExactly(1, 2, 3);
        assertThat(problem.getManPrefs(2)).containsExactly(3, 2, 1);
        assertThat(problem.getManPrefs(3)).containsExactly(2, 3, 1);
        assertThat(problem.getWomanPrefs(1)).containsExactly(2, 3, 1);
        assertThat(problem.getWomanPrefs(2)).containsExactly(2, 1, 3);
        assertThat(problem.getWomanPrefs(3)).containsExactly(1, 2, 3);
    }

}
