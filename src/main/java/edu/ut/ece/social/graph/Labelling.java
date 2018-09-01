package edu.ut.ece.social.graph;

import com.google.common.graph.EndpointPair;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Labelling<N, V extends Number> {

    public static <N, V extends Number> Labelling<N, V> createFeasibleLabellingOn(
            BipartiteGraph<N, V> graph) {
        Labelling<N, V> labelling = new Labelling<>();

        for(N nodeU : graph.nodes()) {
            V maxWeight = graph.adjacentNodes(nodeU).stream()
                    .max((v1, v2) -> compareNodes(nodeU, v1, v2, graph))
                    .flatMap(maxV -> graph.edgeValue(nodeU, maxV))
                    .orElseThrow(() -> new IllegalStateException("Should never get here"));

            labelling.putLabel(nodeU, maxWeight);
        }
        return labelling;
    }

    private static <N, Z extends Number> int compareNodes(N nodeU, N nodeV1, N nodeV2, BipartiteGraph<N, Z> graph) {
        double edgeValue1 = graph.edgeValue(nodeU, nodeV1).get().doubleValue();
        double edgeValue2 = graph.edgeValue(nodeU, nodeV2).get().doubleValue();
        return Comparator.<Double>naturalOrder().compare(edgeValue1, edgeValue2);
    }

    Map<N, V> nodeLabelMap = new HashMap<>();

    public void putLabel(N node, V label) {
        nodeLabelMap.put(node, label);
    }

    public Optional<V> getLabel(N node) {
        return Optional.ofNullable(nodeLabelMap.get(node));
    }

    public <Z extends Number> boolean isFeasibleOn(BipartiteGraph<N, Z> graph) {
        return checkAllLabels(graph, (lU, lV, w) -> lU.doubleValue() + lV.doubleValue() >= w.doubleValue());
    }

    public <Z extends Number> boolean isTightOn(BipartiteGraph<N, Z> graph) {
        return checkAllLabels(graph, (lU, lV, w) -> lU.doubleValue() + lV.doubleValue() == w.doubleValue());
    }

    private <Z extends Number> boolean checkAllLabels(BipartiteGraph<N, Z> graph, LabelWeightComparator comparator) {
        // if graph contains nodes that this labelling does not, incompatible
        if(!nodeLabelMap.keySet().containsAll(graph.nodes())) {
            return false;
        }

        for(EndpointPair<N> pair : graph.edges()) {
            V labelU = nodeLabelMap.get(pair.nodeU());
            V labelV = nodeLabelMap.get(pair.nodeV());
            Z weight = graph.edgeValue(pair.nodeU(), pair.nodeV()).get();

            if (!comparator.compareLabelsAndWeight(labelU, labelV, weight)) {
                return false;
            }
        }

        return true;
    }

    private interface LabelWeightComparator {
        boolean compareLabelsAndWeight(Number labelU, Number labelV, Number weight);
    }

}
