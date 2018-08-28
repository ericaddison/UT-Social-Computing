package edu.ut.ece.social.graph;

import com.google.common.base.Preconditions;

/**
 * Represents a matching of a given Bipartite graph, which is itself a Bipartite graph where every node has degree
 * of at most one.
 */
public class Matching<N> extends UnweightedBipartiteGraph<N> {

    /**
     * Check if this matching is a perfect matching for the given graph. Ensure this matching is a matching for the
     * given graph, and that it contains all of the left and right side nodes (e.g. all nodes have matches)
     */
    public <V> boolean isPerfectMatchingOn(BipartiteGraph<N, V> graph) {
        return isMatchingOn(graph)
                && leftSideNodes().size() == graph.leftSideNodes().size()
                && rightSideNodes().size() == graph.rightSideNodes().size();
    }

    /**
     * Check whether this matching is valid for the given graph. since this is a valid matching, just need to
     * check if all of the matching nodes are nodes in the given graph.
     */
    public <V> boolean isMatchingOn(BipartiteGraph<N, V> graph) {
        boolean validLeftSideNodes = graph.leftSideNodes().containsAll(this.leftSideNodes());

        boolean validRightSideNodes = graph.rightSideNodes().containsAll(this.rightSideNodes());

        return validLeftSideNodes && validRightSideNodes;
    }

    /**
     * Attempt to insert a new edge in the matching.
     * If either supplied node already has a connection, operation fails.
     */
    @Override
    public void putEdge(N leftSideNode, N rightSideNode) {
        Preconditions.checkArgument(
                !checkDegreeViolation(leftSideNode, rightSideNode),
                "A given node already has a match.");

        super.putEdge(leftSideNode, rightSideNode);
    }

    private boolean checkDegreeViolation(N leftSideNode, N rightSideNode) {
        return (leftSideNodes().contains(leftSideNode) && degree(leftSideNode) > 0) ||
                (rightSideNodes().contains(rightSideNode) && degree(rightSideNode) > 0);
    }

    /**
     * Insert the new edge value, removing existing edges on the given nodes if any exist.
     */
    public void forcePutEdge(N leftSideNode, N rightSideNode) {
        removeNode(leftSideNode);
        removeNode(rightSideNode);
        super.putEdge(leftSideNode, rightSideNode);
    }
}
