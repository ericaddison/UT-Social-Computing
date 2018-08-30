package edu.ut.ece.social.hw1;

import edu.ut.ece.social.graph.BipartiteGraph;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static com.google.common.truth.Truth.assertThat;

public class InputReaderTest {

    private static final String INPUT_SQUARE = "3\n" +
            "12 2 4\n" +
            "8 7 6\n" +
            "7 5 2";

    private static final String INPUT_SQUARE_WITH_SPACES = "3\n" +
            "12 2 4 \n" +
            " 8 7 6\n" +
            " 7 5  \t2 ";

    private static final String INPUT_SQUARE_WITH_COMMENTS = "3\n" +
            "12 2 4 //number of rows and columns\n" +
            " 8 7 6\n" +
            " 7 5  \t2 ";


    @Test
    public void basicSquareMatrix_shouldParseToCorrectGraph() {
        BufferedReader reader = new BufferedReader(new StringReader(INPUT_SQUARE));

        BipartiteGraph<Integer, Integer> graph = InputReader.readGraphFromReader(reader);

        assertThat(graph.leftSideNodes()).containsExactly(1, 2, 3);
        assertThat(graph.rightSideNodes()).containsExactly(-1, -2, -3);
        assertThat(graph.edgeValue(1,-1).get()).isEqualTo(12);
        assertThat(graph.edgeValue(2,-3).get()).isEqualTo(6);
        assertThat(graph.edgeValue(3,-1).get()).isEqualTo(7);
    }

    @Test
    public void extraSpacesSquareMatrix_shouldParseToCorrectGraph() {
        BufferedReader reader = new BufferedReader(new StringReader(INPUT_SQUARE_WITH_SPACES));

        BipartiteGraph<Integer, Integer> graph = InputReader.readGraphFromReader(reader);

        assertThat(graph.leftSideNodes()).containsExactly(1, 2, 3);
        assertThat(graph.rightSideNodes()).containsExactly(-1, -2, -3);
        assertThat(graph.edgeValue(1,-1).get()).isEqualTo(12);
        assertThat(graph.edgeValue(2,-3).get()).isEqualTo(6);
        assertThat(graph.edgeValue(3,-1).get()).isEqualTo(7);
    }

    @Test
    public void extraCommentsSquareMatrix_shouldParseToCorrectGraph() {
        BufferedReader reader = new BufferedReader(new StringReader(INPUT_SQUARE_WITH_COMMENTS));

        BipartiteGraph<Integer, Integer> graph = InputReader.readGraphFromReader(reader);
        System.out.println(graph.toString());

        assertThat(graph.leftSideNodes()).containsExactly(1, 2, 3);
        assertThat(graph.rightSideNodes()).containsExactly(-1, -2, -3);
        assertThat(graph.edgeValue(1,-1).get()).isEqualTo(12);
        assertThat(graph.edgeValue(2,-3).get()).isEqualTo(6);
        assertThat(graph.edgeValue(3,-1).get()).isEqualTo(7);
    }}
