package edu.ut.ece.social.hw1;

/**
 * Factory methods for generating BipartiteGraphs.
 */
public class BipartiteGraphFactory {

    public static <N> UnweightedBipartiteGraph<N> emptyUnweightedGraph() {
        return new UnweightedBipartiteGraph<>();
    }

    public static <N, V> BipartiteGraph<N, V> emptyBipartiteGraph() {
        return new BipartiteGraph<>();
    }

    public static <N> Matching<N> emptyMatching() {
        return new Matching<>();
    }
}
