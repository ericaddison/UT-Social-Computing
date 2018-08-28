package edu.ut.ece.social.hw1;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static edu.ut.ece.social.hw1.BipartiteGraphFactory.emptyBipartiteGraph;

@RunWith(JUnit4.class)
public class BipartiteGraphTest {

    @Test
    public void addAnEdgeBetweenLeftAndRightNode_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();

        Optional<Integer> putEdgeResult = g.putEdge(1, 2, 10);

        assertThat(putEdgeResult.isPresent()).isFalse();
        assertThat(g.hasEdgeConnecting(1, 2)).isTrue();
        assertThat(g.leftSideNodes()).contains(1);
        assertThat(g.rightSideNodes()).contains(2);
    }

    @Test
    public void updateAndEdge_shouldReturnPreviousWeight() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();
        g.putEdge(1, 2, 10);

        Optional<Integer> putEdgeResult = g.putEdge(1, 2, 20);

        assertThat(putEdgeResult.isPresent()).isTrue();
        assertThat(putEdgeResult.get()).isEqualTo(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAnEdgeBetweenTwoLeftNodes_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();
        g.addLeftSideNode(1);
        g.addLeftSideNode(2);

        Optional<Integer> putEdgeResult = g.putEdge(1, 2, 10);

        assertThat(putEdgeResult.isPresent()).isFalse();
        assertThat(g.hasEdgeConnecting(1, 2)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAnEdgeBetweenTwoRightNodes_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();
        g.addRightSideNode(1);
        g.addRightSideNode(2);

        g.putEdge(1, 2, 10);
    }

    @Test
    public void removeAnExistingEdge_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();
        g.putEdge(1, 2, 10);

        Optional<Integer> removeEdgeResult = g.removeEdge(1, 2);

        assertThat(removeEdgeResult.isPresent()).isTrue();
        assertThat(removeEdgeResult.get()).isEqualTo(10);
    }

    @Test
    public void removeANonexistingEdge_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();

        Optional<Integer> removeEdgeResult = g.removeEdge(1, 2);

        assertThat(removeEdgeResult.isPresent()).isFalse();
    }


}
