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
public class UnweightedBipartiteGraph<N> implements Graph<N> {

    private final MutableGraph<N> underlyingGraph = GraphBuilder.undirected().build();
    private final Set<N> leftSideNodes;
    private final Set<N> rightSideNodes;

    protected UnweightedBipartiteGraph() {
        this(new HashSet<>(), new HashSet<>());
    }

    protected UnweightedBipartiteGraph(Set<N> leftSideNodes, Set<N> rightSideNodes) {
        this.leftSideNodes = leftSideNodes;
        this.rightSideNodes = rightSideNodes;
        leftSideNodes.forEach(this::addLeftSideNode);
        rightSideNodes.forEach(this::addRightSideNode);
    }

    public boolean addLeftSideNode(N node) {
        return addNode(node, leftSideNodes);
    }

    public boolean addRightSideNode(N node) {
        return addNode(node, rightSideNodes);
    }

    @SafeVarargs
    public final ImmutableList<Boolean> addLeftSideNodes(N... nodes) {
        return Arrays.stream(nodes).map(this::addLeftSideNode).collect(ImmutableList.toImmutableList());
    }

    @SafeVarargs
    public final ImmutableList<Boolean> addRightSideNodes(N... nodes) {
        return Arrays.stream(nodes).map(this::addRightSideNode).collect(ImmutableList.toImmutableList());
    }

    private boolean addNode(N node, Set<N> nodeSet) {
        if(leftSideNodes.contains(node) || rightSideNodes.contains(node)) {
            return false;
        }

        nodeSet.add(node);
        underlyingGraph.addNode(node);
        return true;
    }

    public boolean removeNode(N node) {
        leftSideNodes.remove(node);
        rightSideNodes.remove(node);
        return underlyingGraph.removeNode(node);
    }

    public ImmutableSet<N> leftSideNodes() {
        return ImmutableSet.copyOf(leftSideNodes);
    }

    public ImmutableSet<N> rightSideNodes() {
        return ImmutableSet.copyOf(rightSideNodes);
    }

    public void putEdge(N leftSideNode, N rightSideNode) {
        addEdgeSetup(leftSideNode, rightSideNode);
        underlyingGraph.putEdge(leftSideNode, rightSideNode);
    }

    protected void addEdgeSetup(N leftSideNode, N rightSideNode) {
        checkArgument(!rightSideNodes.contains(leftSideNode),
                "Provided left side node is already a right side node");
        checkArgument(!leftSideNodes.contains(rightSideNode),
                "Provided right side node is already a left side node");

        leftSideNodes.add(leftSideNode);
        rightSideNodes.add(rightSideNode);
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


