import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.graph.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A weighted, undirected bipartite graph.
 */
public class BipartiteGraph<N, V> implements ValueGraph<N, V> {

    private final MutableValueGraph<N, V> underlyingGraph;
    private final Set<N> leftSideNodes;
    private final Set<N> rightSideNodes;

    protected BipartiteGraph() {
        this(new HashSet<>(), new HashSet<>());
    }

    private BipartiteGraph(Set<N> leftSideNodes, Set<N> rightSideNodes) {
        this.underlyingGraph = ValueGraphBuilder.undirected().build();
        this.leftSideNodes = leftSideNodes;
        this.rightSideNodes = rightSideNodes;
        leftSideNodes.forEach(this::addLeftSideNode);
        rightSideNodes.forEach(this::addRightSideNode);
    }

    public static <N, V> BipartiteGraph<N, V> emptyGraph() {
        return new BipartiteGraph<>();
    }

    public boolean addLeftSideNode(N node) {
        return addNode(node, leftSideNodes);
    }

    public boolean addRightSideNode(N node) {
        return addNode(node, rightSideNodes);
    }

    private boolean addNode(N node, Set<N> nodeSet) {
        if(leftSideNodes.contains(node) || rightSideNodes.contains(node)) {
            return false;
        }

        nodeSet.add(node);
        underlyingGraph.addNode(node);
        return true;
    }

    public Optional<V> putEdgeValue(N leftSideNode, N rightSideNode, V value) {
        checkArgument(!rightSideNodes.contains(leftSideNode),
                "Provided left side node is already a right side node");
        checkArgument(!leftSideNodes.contains(rightSideNode),
                "Provided right side node is already a left side node");

        leftSideNodes.add(leftSideNode);
        rightSideNodes.add(rightSideNode);
        V previousValue = underlyingGraph.putEdgeValue(leftSideNode, rightSideNode, value);
        return Optional.ofNullable(previousValue);
    }

    public boolean removeNode(N node) {
        leftSideNodes.remove(node);
        rightSideNodes.remove(node);
        return underlyingGraph.removeNode(node);
    }

    public Optional<V> removeEdge(N nodeU, N nodeV) {
        return Optional.ofNullable(underlyingGraph.removeEdge(nodeU, nodeV));
    }

    public ImmutableSet<N> leftSideNodes() {
        return ImmutableSet.copyOf(leftSideNodes);
    }

    public ImmutableSet<N> rightSideNodes() {
        return ImmutableSet.copyOf(rightSideNodes);
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
    public Graph<N> asGraph() {
        return underlyingGraph.asGraph();
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
    public Set<N> adjacentNodes(N node) {
        return underlyingGraph.adjacentNodes(node);
    }

    @Override
    public Set<N> predecessors(N node) {
        return underlyingGraph.predecessors(node);
    }

    @Override
    public Set<N> successors(N node) {
        return underlyingGraph.successors(node);
    }

    @Override
    public Set<EndpointPair<N>> incidentEdges(N node) {
        return underlyingGraph.incidentEdges(node);
    }

    @Override
    public int degree(N node) {
        return underlyingGraph.degree(node);
    }

    @Override
    public int inDegree(N node) {
        return underlyingGraph.degree(node);
    }

    @Override
    public int outDegree(N node) {
        return underlyingGraph.degree(node);
    }

    @Override
    public boolean hasEdgeConnecting(N nodeU, N nodeV) {
        return underlyingGraph.hasEdgeConnecting(nodeU, nodeV);
    }

    @Override
    public Optional<V> edgeValue(N nodeU, N nodeV) {
        return underlyingGraph.edgeValue(nodeU, nodeV);
    }

    @Override
    public V edgeValueOrDefault(N nodeU, N nodeV, @Nullable V defaultValue) {
        return underlyingGraph.edgeValueOrDefault(nodeU, nodeV, defaultValue);
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


