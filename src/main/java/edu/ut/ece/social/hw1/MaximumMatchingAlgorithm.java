package edu.ut.ece.social.hw1;

import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.Matching;

@FunctionalInterface
public interface MaximumMatchingAlgorithm {
    <N, V> Matching<N> findMaximumMatching(BipartiteGraph<N, V> graph);
}
