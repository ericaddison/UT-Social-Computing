package edu.ut.ece.social.graph;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractBipartiteGraph<N> {

    private final Set<N> leftSideNodes;
    private final Set<N> rightSideNodes;

    protected AbstractBipartiteGraph(Set<N> leftSideNodes, Set<N> rightSideNodes) {
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

    @SafeVarargs
    public final ImmutableList<Boolean> addLeftSideNodes(N... nodes) {
        return Arrays.stream(nodes).map(this::addLeftSideNode).collect(ImmutableList.toImmutableList());
    }

    @SafeVarargs
    public final ImmutableList<Boolean> addRightSideNodes(N... nodes) {
        return Arrays.stream(nodes).map(this::addRightSideNode).collect(ImmutableList.toImmutableList());
    }

    boolean addNode(N node, Set<N> nodeSet) {
        if (isLeftSideNode(node) || isRightSideNode(node)) {
            return false;
        }

        nodeSet.add(node);
        return addNodeToUnderlyingGraph(node);
    }

    protected abstract boolean addNodeToUnderlyingGraph(N node);

    public boolean removeNode(N node) {
        leftSideNodes.remove(node);
        rightSideNodes.remove(node);
        return removeNodeFromUnderlyingGraph(node);
    }

    protected abstract boolean removeNodeFromUnderlyingGraph(N node);

    protected void addEdgeSetup(N leftSideNode, N rightSideNode) {
        checkArgument(!isRightSideNode(leftSideNode),
                "Provided left side node is already a right side node");
        checkArgument(!isLeftSideNode(rightSideNode),
                "Provided right side node is already a left side node");

        leftSideNodes.add(leftSideNode);
        rightSideNodes.add(rightSideNode);
    }

}
