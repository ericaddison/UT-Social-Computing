package edu.ut.ece.social.hw1;

import com.google.common.graph.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

/**
 * A weighted, undirected bipartite graph.
 */
public final class BipartiteGraph<N, V> extends UnweightedBipartiteGraph<N> implements ValueGraph<N, V> {

    private final MutableValueGraph<N, V> underlyingGraph = ValueGraphBuilder.undirected().build();

    protected BipartiteGraph() {
        this(new HashSet<>(), new HashSet<>());
    }

    private BipartiteGraph(Set<N> leftSideNodes, Set<N> rightSideNodes) {
        super(leftSideNodes, rightSideNodes);
    }

    public Optional<V> putEdge(N leftSideNode, N rightSideNode, V value) {
        putEdge(leftSideNode, rightSideNode);
        V previousValue = underlyingGraph.putEdgeValue(leftSideNode, rightSideNode, value);
        return Optional.ofNullable(previousValue);
    }

    @Override
    public boolean removeEdge(N nodeU, N nodeV) {
        return underlyingGraph.removeEdge(nodeU, nodeV) != null;
    }

    @Override
    public Graph<N> asGraph() {
        return underlyingGraph.asGraph();
    }

    @Override
    @ParametersAreNonnullByDefault
    public Optional<V> edgeValue(N nodeU, N nodeV) {
        return underlyingGraph.edgeValue(nodeU, nodeV);
    }

    @Override
    @ParametersAreNonnullByDefault
    public V edgeValueOrDefault(N nodeU, N nodeV, @Nullable V defaultValue) {
        return underlyingGraph.edgeValueOrDefault(nodeU, nodeV, defaultValue);
    }
}


