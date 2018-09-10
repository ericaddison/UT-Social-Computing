package edu.ut.ece.social.graph;

/**
 * Factory methods for generating BipartiteGraphs.
 */
public class BipartiteGraphFactory {

    public static <N, V> BipartiteGraph<N, V> emptyBipartiteGraph() {
        return new BipartiteGraph<>();
    }

    public static <N> Matching<N> emptyMatching() {
        return new Matching<>();
    }
}
