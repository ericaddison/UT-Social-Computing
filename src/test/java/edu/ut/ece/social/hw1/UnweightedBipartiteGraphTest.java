package edu.ut.ece.social.hw1;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;
import static edu.ut.ece.social.hw1.BipartiteGraphFactory.emptyBipartiteGraph;

@RunWith(JUnit4.class)
public class UnweightedBipartiteGraphTest {

    @Test
    public void createATwoNodeGraph_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();

        g.addLeftSideNode(1);
        g.addRightSideNode(2);
        assertThat(g.leftSideNodes()).contains(1);
        assertThat(g.leftSideNodes()).doesNotContain(2);
        assertThat(g.rightSideNodes()).contains(2);
        assertThat(g.rightSideNodes()).doesNotContain(1);
    }

    @Test
    public void addingMultipleDistinctLeftNodes_shouldReturnAllTrue() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();

        ImmutableList<Boolean> addResults = g.addLeftSideNodes(1, 2, 3, 4);

        assertThat(addResults).doesNotContain(false);
    }

    @Test
    public void addingMultipleLeftNodesWithRepeat_shouldReturnAFalse() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();

        ImmutableList<Boolean> addResults = g.addLeftSideNodes(1, 2, 3, 1);

        assertThat(addResults).containsExactly(true, true, true, false);
    }

    @Test
    public void addingMultipleDistinctRightNodes_shouldReturnAllTrue() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();

        ImmutableList<Boolean> addResults = g.addRightSideNodes(1, 2, 3, 4);

        assertThat(addResults).doesNotContain(false);
    }

    @Test
    public void addingMultipleRightNodesWithRepeat_shouldReturnAFalse() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();

        ImmutableList<Boolean> addResults = g.addRightSideNodes(1, 2, 3, 1);

        assertThat(addResults).containsExactly(true, true, true, false);
    }


    @Test
    public void addingSameNodeToBothSides_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();
        g.addLeftSideNode(1);

        boolean addNodeToOtherSideResult = g.addRightSideNode(1);

        assertThat(addNodeToOtherSideResult).isFalse();
        assertThat(g.leftSideNodes()).contains(1);
        assertThat(g.rightSideNodes()).doesNotContain(1);
    }


    @Test
    public void removeAnExistingNode_shouldSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();
        g.addLeftSideNode(1);
        g.addRightSideNode(2);

        boolean removeNodeResult = g.removeNode(1);

        assertThat(removeNodeResult).isTrue();
        assertThat(g.leftSideNodes()).doesNotContain(1);
    }

    @Test
    public void removeANonexistingNode_shouldNotSucceed() {
        BipartiteGraph<Integer, Integer> g = emptyBipartiteGraph();
        g.addRightSideNode(2);

        boolean removeNodeResult = g.removeNode(1);

        assertThat(removeNodeResult).isFalse();
    }


}
