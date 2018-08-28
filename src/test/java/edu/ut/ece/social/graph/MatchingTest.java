package edu.ut.ece.social.graph;

import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Matching;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;
import static edu.ut.ece.social.graph.BipartiteGraphFactory.emptyMatching;

@RunWith(JUnit4.class)
public class MatchingTest {

    static BipartiteGraph<Integer, Integer> sampleGraph;

    @BeforeClass
    public static void setUpBeforeClass() {
        sampleGraph = BipartiteGraphFactory.emptyBipartiteGraph();
        sampleGraph.putEdge(1, 2, 0);
        sampleGraph.putEdge(1, 4, 0);
        sampleGraph.putEdge(3, 4, 0);
        sampleGraph.putEdge(3, 6, 0);
        sampleGraph.putEdge(5, 6, 0);
        sampleGraph.putEdge(5, 8, 0);
        sampleGraph.putEdge(7, 8, 0);
        sampleGraph.putEdge(7, 2, 0);
    }


    @Test
    public void instantiation_shouldNotFail() {
        Matching<Integer> matching = emptyMatching();
        assertThat(matching).isNotNull();
    }

    @Test
    public void puttingNewEdge_shouldSucceed() {
        Matching<Integer> matching = emptyMatching();

        matching.putEdge(1, 2);

        assertThat(matching.leftSideNodes()).contains(1);
        assertThat(matching.rightSideNodes()).contains(2);
        assertThat(matching.hasEdgeConnecting(1, 2)).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void puttingSecondEdgeOnOneNode_shouldFail() {
        Matching<Integer> matching = emptyMatching();
        matching.putEdge(1, 2);

        matching.putEdge(1, 3);
    }

    @Test
    public void forcePuttingSecondEdgeOnOneNode_shouldSuceed() {
        Matching<Integer> matching = emptyMatching();
        matching.putEdge(1, 2);

        matching.forcePutEdge(1, 3);

        assertThat(matching.rightSideNodes()).contains(2);
        assertThat(matching.rightSideNodes()).contains(3);
        assertThat(matching.hasEdgeConnecting(1, 3)).isTrue();
        assertThat(matching.hasEdgeConnecting(1, 2)).isFalse();
    }

    @Test
    public void isMatchingOnMatchedGraph_shouldReturnTrue() {
        Matching<Integer> matching = emptyMatching();

        matching.putEdge(1, 6);
        matching.putEdge(3, 2);
        matching.putEdge(5, 4);

        boolean isMatching = matching.isMatchingOn(sampleGraph);

        assertThat(isMatching).isTrue();
    }

    @Test
    public void matchWithAnEdgeWithRightNodeNotInGraph_shouldReturnFalse() {
        Matching<Integer> matching = emptyMatching();

        matching.putEdge(1, 16);
        matching.putEdge(3, 2);
        matching.putEdge(5, 4);

        boolean isMatching = matching.isMatchingOn(sampleGraph);

        assertThat(isMatching).isFalse();
    }

    @Test
    public void matchWithAnEdgeWithLeftNodeNotInGraph_shouldReturnFalse() {
        Matching<Integer> matching = emptyMatching();

        matching.putEdge(12, 6);
        matching.putEdge(3, 2);
        matching.putEdge(5, 4);

        boolean isMatching = matching.isMatchingOn(sampleGraph);

        assertThat(isMatching).isFalse();
    }

    @Test
    public void isPerfectMatchingOnPerfectlyMatchedGraph_shouldReturnTrue() {
        Matching<Integer> matching = emptyMatching();

        matching.putEdge(1, 6);
        matching.putEdge(3, 2);
        matching.putEdge(5, 4);
        matching.putEdge(7, 8);

        boolean isPerfectMatching = matching.isPerfectMatchingOn(sampleGraph);

        assertThat(isPerfectMatching).isTrue();
    }

    @Test
    public void isPerfectMatchingOnNotPerfectlyMatchedGraph_shouldReturnFalse() {
        Matching<Integer> matching = emptyMatching();

        matching.putEdge(1, 6);
        matching.putEdge(3, 2);
        matching.putEdge(5, 4);

        boolean isPerfectMatching = matching.isPerfectMatchingOn(sampleGraph);

        assertThat(isPerfectMatching).isFalse();
    }
}
