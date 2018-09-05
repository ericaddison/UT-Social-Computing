package edu.ut.ece.social.hw1.km;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Labelling;
import edu.ut.ece.social.graph.Matching;
import edu.ut.ece.social.hw1.HwRunner;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class KM {

    static Matching<Integer> maxMatchingKuhnMunkres(BipartiteGraph<Integer, Integer> graph) {

        Matching<Integer> m = BipartiteGraphFactory.emptyMatching();
        Labelling<Integer, Integer> l = Labelling.createFeasibleLabellingOn(graph);
        BipartiteGraph<Integer, Integer> El = l.getEqualityGraphOn(graph);

        while (!m.isPerfectMatchingOn(El)) {
            Optional<ImmutableList<Integer>> augmentingPath = findAugmentingPath(m, El);
            if (augmentingPath.isPresent()) {
                // flip path
            } else {
                //TODO: improve l to l'
            }
        }

        return m;
    }

    /**
     * Finds an augmenting path for this Matching on the given graph. If no augmenting path is found, returns empty
     * {@link Optional}.
     *
     * @return an ordered list of nodes representing the augmenting path.
     */
    static <N> Optional<ImmutableList<N>>
    findAugmentingPath(Matching<N> m, BipartiteGraph<N, ?> graph) {
        // find an exposed right-side node
        Optional<N> exposedNode = graph.rightSideNodes().stream()
                .filter(node -> !m.rightSideNodes().contains(node))
                .findAny();

        // if no exposed node, return empty optional
        if (!exposedNode.isPresent()) {
            return Optional.empty();
        }

        // perform modified BFS to find augmenting path
        Set<N> visited = new HashSet<>();
        Queue<N> rightSideNodesToVisit = new ArrayDeque<>();
        Queue<N> leftSideNodesToVisit = new ArrayDeque<>();
        Map<N, N> predecessors = new HashMap<>();

        rightSideNodesToVisit.add(exposedNode.get());
        while (!rightSideNodesToVisit.isEmpty()) {
            // step 1: find non-matching edges from a right side node
            ImmutableSet<N> nextLeftNodes =
                    performAugmentingPathBfsStep(
                            graph,
                            m,
                            rightSideNodesToVisit,
                            leftSideNodesToVisit,
                            visited,
                            predecessors,
                            /* onLeftSide= */ false);

            // check if we have found an exposed node
            Optional<N> exposedLeftNode = nextLeftNodes.stream()
                    .filter(node -> !m.leftSideNodes().contains(node))
                    .findAny();
            if (exposedLeftNode.isPresent()) {
                return Optional.of(makeAugmentingPathFromPredecessors(exposedLeftNode.get(), predecessors));
            }

            // step 2: find matching edges from a left-side node
            performAugmentingPathBfsStep(
                    graph,
                    m,
                    leftSideNodesToVisit,
                    rightSideNodesToVisit,
                    visited,
                    predecessors,
                    /* onLeftSide= */ true);
        }

        // only get here if explored the whole graph and found no augmenting path
        return Optional.empty();
    }

    private static <N> ImmutableSet<N> performAugmentingPathBfsStep(
            BipartiteGraph<N, ?> graph,
            Matching<N> matching,
            Queue<N> activeSideNodesToVisit,
            Queue<N> otherSideNodesToVisit,
            Set<N> visitedNodes,
            Map<N, N> predecessors,
            boolean onLeftSide) {

        N visitNode = checkNotNull(activeSideNodesToVisit.poll());

        // if on the left side, look for need to filter based on matched edges
        // otherwise, looking for non-matched edges
        Predicate<N> matchChecker = onLeftSide ?
                node -> matching.hasEdgeConnecting(visitNode, node) :
                node -> !matching.hasEdgeConnecting(visitNode, node);

        ImmutableSet<N> nextNodes =
                graph.adjacentNodes(visitNode).stream()
                        .filter(otherNode -> matchChecker.test(otherNode)
                                && !visitedNodes.contains(otherNode)
                                && !otherSideNodesToVisit.contains(otherNode))
                        .collect(ImmutableSet.toImmutableSet());
        visitedNodes.add(visitNode);

        // save the predecessors and add to visit queue
        nextNodes.forEach(node -> predecessors.put(node, visitNode));
        otherSideNodesToVisit.addAll(nextNodes);

        return nextNodes;
    }

    private static <N> ImmutableList<N>
    makeAugmentingPathFromPredecessors(N finalNode, Map<N, N> predecessors) {

        ImmutableList.Builder<N> builder = ImmutableList.builder();
        N node = finalNode;
        builder.add(finalNode);

        while ((node = predecessors.get(node)) != null) {
            builder.add(node);
        }

        return builder.build();
    }

    /**
     * Updates the given matching such that the provided augmenting path is flipped.
     *
     * Assumes that the path starts from the left side.
     */
    static <N> void flipAugmentingPath(Matching<N> m, ImmutableList<N> augmentingPath) {
        checkArgument(augmentingPath.size() % 2 == 0, "Length of augmentingPath is not even.");

        for (int i = 0; i < augmentingPath.size(); i += 2) {
            N leftNode = augmentingPath.get(i);
            N rightNode = augmentingPath.get(i + 1);
            m.forcePutEdge(leftNode, rightNode);
        }
    }


    public static void main(String args[]) throws FileNotFoundException {

        checkArgument(args.length == 1, "Required filename as sole command line argument");

        String inputFileName = args[0];
        HwRunner.runMaximumMatchingProblem(inputFileName, KM::maxMatchingKuhnMunkres);
    }

}
