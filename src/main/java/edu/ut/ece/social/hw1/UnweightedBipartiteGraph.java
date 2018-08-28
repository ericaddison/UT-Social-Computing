package edu.ut.ece.social.hw1;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * An unweighted, undirected bipartite graph.
 */
public class UnweightedBipartiteGraph<N> extends AbstractBipartiteGraph<N> implements Graph<N> {

    private final MutableGraph<N> underlyingGraph = GraphBuilder.undirected().build();

    protected UnweightedBipartiteGraph() {
        this(new HashSet<>(), new HashSet<>());
    }

    protected UnweightedBipartiteGraph(Set<N> leftSideNodes, Set<N> rightSideNodes) {
        super(leftSideNodes, rightSideNodes);
    }

    @Override
    protected boolean addNodeToUnderlyingGraph(N node) {
        return underlyingGraph.addNode(node);
    }

    @Override
    public boolean removeNodeFromUnderlyingGraph(N node) {
        return underlyingGraph.removeNode(node);
    }

    public void putEdge(N leftSideNode, N rightSideNode) {
        addEdgeSetup(leftSideNode, rightSideNode);
        underlyingGraph.putEdge(leftSideNode, rightSideNode);
    }

    public boolean removeEdge(N nodeU, N nodeV) {
        return underlyingGraph.removeEdge(nodeU, nodeV);
    }

    @Override
    public Set<N> nodes() {
        return ImmutableSet.copyOf(underlyingGraph.nodes());
    }

    @Override
    public Set<EndpointPair<N>> edges() {
        return ImmutableSet.copyOf(underlyingGraph.edges());
    }

    @Override
    public boolean isDirected() {
        return underlyingGraph.isDirected();
    }

    @Override
    public boolean allowsSelfLoops() {
        return underlyingGraph.allowsSelfLoops();
    }

    @Override
    public ElementOrder<N> nodeOrder() {
        return underlyingGraph.nodeOrder();
    }

    @Override
    @ParametersAreNonnullByDefault
    public Set<N> adjacentNodes(N node) {
        return underlyingGraph.adjacentNodes(node);
    }

    @Override
    @ParametersAreNonnullByDefault
    public Set<N> predecessors(N node) {
        return underlyingGraph.predecessors(node);
    }

    @Override
    @ParametersAreNonnullByDefault
    public Set<N> successors(N node) {
        return underlyingGraph.successors(node);
    }

    @Override
    @ParametersAreNonnullByDefault
    public Set<EndpointPair<N>> incidentEdges(N node) {
        return underlyingGraph.incidentEdges(node);
    }

    @Override
    @ParametersAreNonnullByDefault
    public int degree(N node) {
        return underlyingGraph.degree(node);
    }

    @Override
    @ParametersAreNonnullByDefault
    public int inDegree(N node) {
        return underlyingGraph.degree(node);
    }

    @Override
    @ParametersAreNonnullByDefault
    public int outDegree(N node) {
        return underlyingGraph.degree(node);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return underlyingGraph.hasEdgeConnecting(nodeU, nodeV);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        return underlyingGraph.equals(object);
    }

    @Override
    public int hashCode() {
        return underlyingGraph.hashCode();
    }
}


