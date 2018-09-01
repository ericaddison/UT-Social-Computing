package edu.ut.ece.social.graph;

import com.google.common.graph.EndpointPair;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Labelling<N, V extends Number> {

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
