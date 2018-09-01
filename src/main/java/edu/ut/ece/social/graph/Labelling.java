package edu.ut.ece.social.graph;

import com.google.common.graph.EndpointPair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Labelling<N, V extends Number> {

    @SuppressWarnings("unchecked") // for casting zero to V
    public static <N, V extends Number> Labelling<N, V> createFeasibleLabellingOn(
            BipartiteGraph<N, V> graph) {
        Labelling<N, V> labelling = new Labelling<>();

        for(N leftNode : graph.leftSideNodes()) {
            V maxWeight = graph.adjacentNodes(leftNode).stream()
                    .max((v1, v2) -> compareNodes(leftNode, v1, v2, graph))
                    .flatMap(maxV -> graph.edgeValue(leftNode, maxV))
                    .orElseThrow(() -> new IllegalStateException("Should never get here"));

            labelling.putLabel(leftNode, maxWeight);
        }

        graph.rightSideNodes().forEach(rightNode -> labelling.putLabel(rightNode, (V)Integer.valueOf(0)));

        return labelling;
    }

    private static <N, Z extends Number> int compareNodes(N nodeU, N nodeV1, N nodeV2, BipartiteGraph<N, Z> graph) {
        double edgeValue1 = graph.edgeValue(nodeU, nodeV1).get().doubleValue();
        double edgeValue2 = graph.edgeValue(nodeU, nodeV2).get().doubleValue();
        return Comparator.<Double>naturalOrder().compare(edgeValue1, edgeValue2);
    }

    private Map<N, V> nodeLabelMap = new HashMap<>();
    private static LabelWeightComparator tightComparator =
            (lU, lV, w) -> lU.doubleValue() + lV.doubleValue() == w.doubleValue();
    private static LabelWeightComparator feasibleComparator =
            (lU, lV, w) -> lU.doubleValue() + lV.doubleValue() >= w.doubleValue();


    public void putLabel(N node, V label) {
        nodeLabelMap.put(node, label);
    }

    public Optional<V> getLabel(N node) {
        return Optional.ofNullable(nodeLabelMap.get(node));
    }

    public <Z extends Number> boolean isFeasibleOn(BipartiteGraph<N, Z> graph) {
        return checkAllLabels(graph, feasibleComparator);
    }

    public <Z extends Number> boolean isTightOn(BipartiteGraph<N, Z> graph) {
        return checkAllLabels(graph, tightComparator);
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

    public <Z extends Number> BipartiteGraph<N, Z> getEqualityGraphOn(BipartiteGraph<N, Z> graph) {
        BipartiteGraph<N, Z> equalityGraph = BipartiteGraphFactory.emptyBipartiteGraph();
        graph.edges().stream()
                .filter(edge -> isTightEdge(graph, edge))
                .forEach(edge -> {
                    N nodeU = edge.source();
                    N nodeV = edge.target();
                    Z weight = graph.edgeValue(nodeU, nodeV).get();
                    equalityGraph.putEdge(nodeU, nodeV, weight);
                });
        return equalityGraph;
    }

    private <Z extends Number> boolean isTightEdge(BipartiteGraph<N, Z> graph, EndpointPair<N> edge) {
        V labelU = nodeLabelMap.get(edge.source());
        V labelV = nodeLabelMap.get(edge.target());
        return graph.edgeValue(edge.source(), edge.target())
                .map(weight -> tightComparator.compareLabelsAndWeight(labelU, labelV, weight))
                .orElse(false);
    }

}
