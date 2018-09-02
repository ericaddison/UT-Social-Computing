package edu.ut.ece.social.graph;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a matching of a given Bipartite graph, which is represented as a collection of edges
 * between "left" and "right" side nodes.
 */
public class Matching<N> {

    /**
     * the BiMap that represents the edges of the Matching, where the keys represent the left-hand
     * nodes and the values represent the right-hand nodes.
     */
    private final BiMap<N, N> backingMap = HashBiMap.create();

    /**
     * Check if this matching is a perfect matching for the given graph. Ensure this matching is a matching for the
     * given graph, and that it contains all of the left and right side nodes (e.g. all nodes have matches)
     */
    public boolean isPerfectMatchingOn(BipartiteGraph<N, ?> graph) {
        return isMatchingOn(graph)
                && leftSideNodes().size() == graph.leftSideNodes().size()
                && rightSideNodes().size() == graph.rightSideNodes().size();
    }

    /**
     * Check whether this matching is valid for the given graph. since this is a valid matching, just need to
     * check if all of the matching nodes are nodes in the given graph.
     */
    public boolean isMatchingOn(BipartiteGraph<N, ?> graph) {
        boolean validLeftSideNodes = graph.leftSideNodes().containsAll(this.leftSideNodes());

        boolean validRightSideNodes = graph.rightSideNodes().containsAll(this.rightSideNodes());

        return validLeftSideNodes && validRightSideNodes;
    }

    /**
     * Finds an augmenting path for this Matching on the given graph. If no augmenting path is found, returns empty
     * {@link Optional}.
     *
     * @return an ordered list of nodes representing the augmenting path.
     */
    public Optional<ImmutableList<N>> findAugmentingPathOn(BipartiteGraph<N, ?> graph) {
        //TODO: implement findAugmentingPath
        return Optional.empty();
    }

    /**
     * Updates this matching such that the provided augmenting path is flipped.
     */
    public void flipAugmentingPath(ImmutableList<N> augmentingPath) {
        //TODO: implement flipAugmentingPath
    }

    /**
     * Attempt to insert a new edge in the matching, ensuring that neither node is already contained
     * in the other side.
     * If either supplied node already has a connection, operation fails.
     */
    public void putEdge(N leftSideNode, N rightSideNode) {
        checkArgument(!leftSideNodes().contains(rightSideNode),
                "The given rightSideNode already exists as a leftSideNode");
        checkArgument(!rightSideNodes().contains(leftSideNode),
                "The given leftSideNode already exists as a rightSideNode");
        checkArgument(!leftSideNodes().contains(leftSideNode),
                "The given leftSideNode already has a connection. Consider using forcePutEdge() instead");
        checkArgument(!rightSideNodes().contains(rightSideNode),
                "The given rightSideNode already has a connection. Consider using forcePutEdge() instead");

        backingMap.put(leftSideNode, rightSideNode);
    }

    /**
     * Insert the new edge value, removing existing edges on the given nodes if any exist.
     */
    public void forcePutEdge(N leftSideNode, N rightSideNode) {
        backingMap.forcePut(leftSideNode, rightSideNode);
    }

    /**
     * Checks whether this Matching contains an edge between the given left and right side nodes.
     */
    public boolean hasEdgeConnecting(N leftSideNode, N rightSideNode) {
        return backingMap.containsKey(leftSideNode) && backingMap.get(leftSideNode).equals(rightSideNode);
    }

    /**
     * Returns the match for the given node, if present.
     */
    public Optional<N> getMatch(N leftSideNode) {
        return Optional.ofNullable(backingMap.get(leftSideNode));
    }

    /**
     * Returns a set of the matches in this Matching.
     */
    public ImmutableSet<Entry<N, N>> getAllMatches() {
        return ImmutableSet.copyOf(backingMap.entrySet());
    }

    /**
     * Returns the "left-side" nodes from this matching.
     */
    public Set<N> leftSideNodes() {
        return backingMap.keySet();
    }

    /**
     * Returns the "right-side" nodes from this matching.
     */
    public Set<N> rightSideNodes() {
        return backingMap.values();
    }
}
