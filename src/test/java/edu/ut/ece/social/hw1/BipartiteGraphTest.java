package edu.ut.ece.social.hw1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class BipartiteGraphTest {

    @Test
    public void createATwoNodeGraph_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();

        g.addLeftSideNode(1);
        g.addRightSideNode(2);
        assertThat(g.leftSideNodes()).contains(1);
        assertThat(g.leftSideNodes()).doesNotContain(2);
        assertThat(g.rightSideNodes()).contains(2);
        assertThat(g.rightSideNodes()).doesNotContain(1);
    }

    @Test
    public void addingSameNodeToBothSides_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();
        g.addLeftSideNode(1);

        boolean addNodeToOtherSideResult = g.addRightSideNode(1);

        assertThat(addNodeToOtherSideResult).isFalse();
        assertThat(g.leftSideNodes()).contains(1);
        assertThat(g.rightSideNodes()).doesNotContain(1);
    }

    @Test
    public void addAnEdgeBetweenLeftAndRightNode_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();

        Optional<Integer> putEdgeResult = g.putEdgeValue(1, 2, 10);

        assertThat(putEdgeResult.isPresent()).isFalse();
        assertThat(g.hasEdgeConnecting(1, 2)).isTrue();
        assertThat(g.leftSideNodes()).contains(1);
        assertThat(g.rightSideNodes()).contains(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAnEdgeBetweenTwoLeftNodes_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();
        g.addLeftSideNode(1);
        g.addLeftSideNode(2);

        Optional<Integer> putEdgeResult = g.putEdgeValue(1, 2, 10);

        assertThat(putEdgeResult.isPresent()).isFalse();
        assertThat(g.hasEdgeConnecting(1, 2)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAnEdgeBetweenTwoRightNodes_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();
        g.addRightSideNode(1);
        g.addRightSideNode(2);

        g.putEdgeValue(1, 2, 10);
    }

    @Test
    public void removeAnExistingNode_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();
        g.addLeftSideNode(1);
        g.addRightSideNode(2);

        boolean removeNodeResult = g.removeNode(1);

        assertThat(removeNodeResult).isTrue();
        assertThat(g.leftSideNodes()).doesNotContain(1);
    }

    @Test
    public void removeANonexistingNode_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();
        g.addRightSideNode(2);

        boolean removeNodeResult = g.removeNode(1);

        assertThat(removeNodeResult).isFalse();
    }

    @Test
    public void removeAnExistingEdge_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();
        g.putEdgeValue(1, 2, 10);

        Optional<Integer> removeEdgeResult = g.removeEdge(1, 2);

        assertThat(removeEdgeResult.isPresent()).isTrue();
        assertThat(removeEdgeResult.get()).isEqualTo(10);
    }

    @Test
    public void removeANonexistingEdge_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = BipartiteGraph.emptyGraph();

        Optional<Integer> removeEdgeResult = g.removeEdge(1, 2);

        assertThat(removeEdgeResult.isPresent()).isFalse();
    }


}
