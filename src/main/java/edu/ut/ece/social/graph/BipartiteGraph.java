package edu.ut.ece.social.graph;

import com.google.common.collect.ImmutableSet;
import com.google.common.graph.Graph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.google.common.graph.EndpointPair;
import com.google.common.graph.ElementOrder;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A weighted, undirected bipartite graph.
 */
public final class BipartiteGraph<N, V> implements ValueGraph<N, V> {

    private final MutableValueGraph<N, V> underlyingGraph = ValueGraphBuilder.undirected().build();
    private final Set<N> leftSideNodes;
    private final Set<N> rightSideNodes;

    BipartiteGraph() {
        this(new HashSet<>(), new HashSet<>());
    }

    private BipartiteGraph(Set<N> leftSideNodes, Set<N> rightSideNodes) {
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

    public boolean isLeftSideNode(N node) {
        return leftSideNodes.contains(node);
    }

    public boolean isRightSideNode(N node) {
        return rightSideNodes.contains(node);
    }

    public ImmutableSet<N> leftSideNodes() {
        return ImmutableSet.copyOf(leftSideNodes);
    }

    public ImmutableSet<N> rightSideNodes() {
        return ImmutableSet.copyOf(rightSideNodes);
    }

    boolean addNode(N node, Set<N> nodeSet) {
        if (isLeftSideNode(node) || isRightSideNode(node)) {
            return false;
        }

        nodeSet.add(node);
        return addNodeToUnderlyingGraph(node);
    }

    public boolean removeNode(N node) {
        leftSideNodes.remove(node);
        rightSideNodes.remove(node);
        return removeNodeFromUnderlyingGraph(node);
    }

    protected void addEdgeSetup(N leftSideNode, N rightSideNode) {
        checkArgument(!isRightSideNode(leftSideNode),
                "Provided left side node is already a right side node");
        checkArgument(!isLeftSideNode(rightSideNode),
                "Provided right side node is already a left side node");

        leftSideNodes.add(leftSideNode);
        rightSideNodes.add(rightSideNode);
    }

    protected boolean addNodeToUnderlyingGraph(N node) {
        return underlyingGraph.addNode(node);
    }

    protected boolean removeNodeFromUnderlyingGraph(N node) {
        return underlyingGraph.removeNode(node);
    }

    public Optional<V> putEdge(N leftSideNode, N rightSideNode, V value) {
        addEdgeSetup(leftSideNode, rightSideNode);
        V previousValue = underlyingGraph.putEdgeValue(leftSideNode, rightSideNode, value);
        return Optional.ofNullable(previousValue);
    }

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

    @Override
    public Set<N> nodes() {
        return ImmutableSet.copyOf(underlyingGraph.nodes());
    }

    @Override
    public Set<EndpointPair<N>> edges() {
        return underlyingGraph.edges().stream().map(this::orderEdge).collect(ImmutableSet.toImmutableSet());
    }

    private EndpointPair<N> orderEdge(EndpointPair<N> edge) {
        N nodeU = edge.nodeU();
        N nodeV = edge.nodeV();

        N leftSideNode = leftSideNodes().contains(nodeU) ? nodeU : nodeV;
        N rightSideNode = rightSideNodes().contains(nodeU) ? nodeU : nodeV;

        return EndpointPair.ordered(leftSideNode, rightSideNode);

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
