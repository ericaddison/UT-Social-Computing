import java.util.*;

/**
 * Represents a matching of a given Bipartite graph, which is itself a Bipartite graph where every node has degree
 * of at most one.
 */
public class Matching<N, V> extends BipartiteGraph<N, V>{

    public static <N, V> Matching<N, V> emptyMatching() {
        return new Matching<>();
    }

    /**
     * Check if this matching is a perfect matching for the given graph. Ensure this matching is a matching for the
     * given graph, and that it contains all of the left and right side nodes (e.g. all nodes have matches)
     */
    public <N, V> boolean isPerfectMatching(BipartiteGraph<N, V> graph) {
        return checkMatching(graph)
                && leftSideNodes().size()==graph.leftSideNodes().size()
                && rightSideNodes().size()==graph.rightSideNodes().size();
    }

    /**
     * Check whether this matching is valid for the given graph. since this is a valid matching, just need to
     * check if all of the matching nodes are nodes in the given graph.
     */
    public <N, V> boolean checkMatching(BipartiteGraph<N, V> graph) {
        boolean validLeftSideNodes = graph.leftSideNodes().containsAll(this.leftSideNodes());

        boolean validRightSideNodes = graph.rightSideNodes().containsAll(this.rightSideNodes());

        return validLeftSideNodes && validRightSideNodes;
    }


    /**
     * Attempt to insert a new edge in the matching.
     * If either supplied node already has a connection, operation fails.
     */
    @Override
    public Optional<V> putEdgeValue(N leftSideNode, N rightSideNode, V value) {
        if(degree(leftSideNode) > 0 || degree(rightSideNode) > 0) {
            return Optional.empty();
        }

        return super.putEdgeValue(leftSideNode, rightSideNode, value);
    }


    /**
     * Insert the new edge value, removing existing edges on the given nodes if any exist.
     */
    public Optional<V> forcePutEdgeValue(N leftSideNode, N rightSideNode, V value) {
        removeNode(leftSideNode);
        removeNode(rightSideNode);
        return putEdgeValue(leftSideNode, rightSideNode, value);
    }
}
