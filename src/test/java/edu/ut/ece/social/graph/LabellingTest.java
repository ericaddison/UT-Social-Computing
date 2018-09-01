package edu.ut.ece.social.graph;

import com.google.common.truth.Truth;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class LabellingTest {

    static BipartiteGraph<Integer, Integer> sampleGraph;

    @BeforeClass
    public static void setUpBeforeClass() {
        sampleGraph = BipartiteGraphFactory.emptyBipartiteGraph();
        sampleGraph.putEdge(1, 2, 3);
        sampleGraph.putEdge(1, 4, 5);
        sampleGraph.putEdge(3, 4, 7);
        sampleGraph.putEdge(3, 2, 5);
    }

    @Test
    public void feasibleLabelling_shouldIdentifyCorrectly() {
        Labelling<Integer, Integer> labelling = new Labelling<>();
        labelling.putLabel(1, 10);
        labelling.putLabel(2, 10);
        labelling.putLabel(3, 10);
        labelling.putLabel(4, 10);

        boolean isFeasible = labelling.isFeasibleOn(sampleGraph);
        boolean isTight = labelling.isTightOn(sampleGraph);

        assertThat(isFeasible).isTrue();
        assertThat(isTight).isFalse();
    }

    @Test
    public void infeasibleLabelling_shouldIdentifyCorrectly() {
        Labelling<Integer, Integer> labelling = new Labelling<>();
        labelling.putLabel(1, 0);
        labelling.putLabel(2, 0);
        labelling.putLabel(3, 0);
        labelling.putLabel(4, 0);

        boolean isFeasible = labelling.isFeasibleOn(sampleGraph);
        boolean isTight = labelling.isTightOn(sampleGraph);

        assertThat(isFeasible).isFalse();
        assertThat(isTight).isFalse();
    }

    @Test
    public void tightLabelling_shouldIdentifyCorrectly() {
        Labelling<Integer, Integer> labelling = new Labelling<>();
        labelling.putLabel(1, 1);
        labelling.putLabel(2, 2);
        labelling.putLabel(3, 3);
        labelling.putLabel(4, 4);

        boolean isFeasible = labelling.isFeasibleOn(sampleGraph);
        boolean isTight = labelling.isTightOn(sampleGraph);

        assertThat(isFeasible).isTrue();
        assertThat(isTight).isTrue();
    }


}
