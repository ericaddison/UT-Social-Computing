package edu.ut.ece.social.hw1.km;

import com.google.common.collect.ImmutableList;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Matching;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class KMTest {

    static BipartiteGraph<Character, Integer> sampleGraph;

    @BeforeClass
    public static void setUpBeforeClass() {
        sampleGraph = BipartiteGraphFactory.emptyBipartiteGraph();
        sampleGraph.putEdge('a', 'w', 0);
        sampleGraph.putEdge('a', 'x', 0);
        sampleGraph.putEdge('b', 'x', 0);
        sampleGraph.putEdge('b', 'y', 0);
        sampleGraph.putEdge('c', 'y', 0);
        sampleGraph.putEdge('c', 'z', 0);
        sampleGraph.putEdge('d', 'z', 0);
    }

    @Test
    public void augmentingPathExampleFromBook_shouldComputeCorrectly() {
        Matching<Character> m = BipartiteGraphFactory.emptyMatching();
        m.putEdge('a', 'x');
        m.putEdge('b', 'y');
        m.putEdge('c', 'z');

        Optional<ImmutableList<Character>> augmentingPath = KM.findAugmentingPath(m, sampleGraph);

        assertThat(augmentingPath.isPresent()).isTrue();
        assertThat(augmentingPath.get()).containsExactly('d', 'z', 'c', 'y', 'b', 'x', 'a', 'w');
    }

    @Test
    public void whenNoAugmentingPath_shouldReturnEmptyOptional() {
        Matching<Character> m = BipartiteGraphFactory.emptyMatching();
        m.putEdge('a', 'w');
        m.putEdge('b', 'x');
        m.putEdge('c', 'y');
        m.putEdge('d', 'z');

        Optional<ImmutableList<Character>> augmentingPath = KM.findAugmentingPath(m, sampleGraph);

        assertThat(augmentingPath.isPresent()).isFalse();
    }

}
