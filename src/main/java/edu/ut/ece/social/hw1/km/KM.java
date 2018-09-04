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
        if(!exposedNode.isPresent()) {
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
            N visitRightNode = checkNotNull(rightSideNodesToVisit.poll());
            ImmutableSet<N> nextLeftNodes =
                    graph.adjacentNodes(visitRightNode).stream()
                        .filter(leftNode -> !m.hasEdgeConnecting(visitRightNode, leftNode)
                                && !visited.contains(leftNode)
                                && !leftSideNodesToVisit.contains(leftNode))
                        .collect(ImmutableSet.toImmutableSet());
            visited.add(visitRightNode);

            // save the predecessors and add to visit queue
            nextLeftNodes.forEach(node -> predecessors.put(node, visitRightNode));
            leftSideNodesToVisit.addAll(nextLeftNodes);

            // check if we have found an exposed node
            Optional<N> exposedLeftNode = nextLeftNodes.stream()
                    .filter(node -> !m.leftSideNodes().contains(node))
                    .findAny();
            if (exposedLeftNode.isPresent()) {
                return Optional.of(makeAugmentingPathFromPredecessors(exposedLeftNode.get(), predecessors));
            }

            // step 2: find matching edges from a left-side node
            N visitLeftNode = checkNotNull(leftSideNodesToVisit.poll());
            ImmutableSet<N> nextRightNodes =
                    graph.adjacentNodes(visitLeftNode).stream()
                            .filter(rightNode -> m.hasEdgeConnecting(visitLeftNode, rightNode)
                                    && !visited.contains(rightNode)
                                    && !rightSideNodesToVisit.contains(rightNode))
                            .collect(ImmutableSet.toImmutableSet());
            visited.add(visitLeftNode);

            // save the predecessors and add to visit queue
            nextRightNodes.forEach(node -> predecessors.put(node, visitLeftNode));
            rightSideNodesToVisit.addAll(nextRightNodes);
        }

        // only get here if explored the whole graph and found no augmenting path
        return Optional.empty();
    }

    private static <N> ImmutableList<N>
        makeAugmentingPathFromPredecessors(N finalNode, Map<N, N> predecessors) {

        ImmutableList.Builder<N> builder = ImmutableList.builder();
        N node = finalNode;
        builder.add(finalNode);

        while ( (node = predecessors.get(node)) != null) {
            builder.add(node);
        }

        return builder.build();
    }

    /**
     * Updates this matching such that the provided augmenting path is flipped.
     */
    static void flipAugmentingPath(Matching<Integer> m, ImmutableList<Integer> augmentingPath) {
        //TODO: implement flipAugmentingPath
    }


    public static void main(String args[]) throws FileNotFoundException {

        checkArgument(args.length == 1, "Required filename as sole command line argument");

        String inputFileName = args[0];
        HwRunner.runMaximumMatchingProblem(inputFileName, KM::maxMatchingKuhnMunkres);
    }

}
