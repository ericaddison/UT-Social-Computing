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
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class KM {

    public static Matching<Integer> maxMatchingKuhnMunkres(BipartiteGraph<Integer, Integer> graph) {

        Matching<Integer> m = BipartiteGraphFactory.emptyMatching();
        Labelling<Integer, Integer> l = Labelling.createFeasibleLabellingOn(graph);
        BipartiteGraph<Integer, Integer> El = l.getEqualityGraphOn(graph);

        while (!m.isPerfectMatchingOn(El)) {
            Optional<ImmutableList<Integer>> augmentingPath = findAugmentingPath(m, El);
            if (augmentingPath.isPresent()) {
                flipAugmentingPath(m, augmentingPath.get());
            } else {
                l = improveLabelling(l, m, El, graph);
                El = l.getEqualityGraphOn(graph);
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
    static <N> Optional<ImmutableList<N>> findAugmentingPath(
            Matching<N> m, BipartiteGraph<N, ?> graph) {
        // find an exposed right-side node
        Optional<N> exposedNode = graph.rightSideNodes().stream()
                .filter(node -> graph.degree(node) > 0)
                .filter(node -> !m.rightSideNodes().contains(node))
                .findFirst();

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
                    .findFirst();
            if (exposedLeftNode.isPresent()) {
                return Optional.of(makeAugmentingPathFromPredecessors(exposedLeftNode.get(), predecessors));
            }

            // step 2: find matching edges from a left-side node
            if (!leftSideNodesToVisit.isEmpty()) {
                performAugmentingPathBfsStep(
                        graph,
                        m,
                        leftSideNodesToVisit,
                        rightSideNodesToVisit,
                        visited,
                        predecessors,
                        /* onLeftSide= */ true);
            }
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

    private static <N> ImmutableList<N> makeAugmentingPathFromPredecessors(
            N finalNode, Map<N, N> predecessors) {

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
     * <p>
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

    /**
     * Improve the given labelling to expand the associated equality graph.
     */
    static <N> Labelling<N, Integer> improveLabelling(
            Labelling<N, Integer> labelling,
            Matching<N> M,
            BipartiteGraph<N, Integer> El,
            BipartiteGraph<N, Integer> graph) {

        boolean repeatStep2 = true;
        while (repeatStep2) {
            // Step 2: pick exposed vertex u in X
            // find an exposed left-side node
            N u = El.leftSideNodes().stream()
                    .filter(node -> !M.leftSideNodes().contains(node))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Unable to find exposed left-side vertex"));

            // let S = {u}, T = empty
            Set<N> S = new HashSet<>();
            Set<N> T = new HashSet<>();
            S.add(u);

            boolean repeatStep3 = true;

            while (repeatStep3) {
                Set<N> N_l_S = S.stream().flatMap(s -> El.adjacentNodes(s).stream()).collect(Collectors.toSet());
                if (N_l_S.equals(T)) {
                    // Step 3: if N_l(S) = T, update labels
                    labelling = updateLabels(labelling, graph, S, T);
                    repeatStep3 = false;
                    repeatStep2 = false;
                } else {
                    // Step 4: if N_l(S) != T, pick y in N_l(S)-T
                    N_l_S.removeAll(T);
                    N y = N_l_S.stream().findFirst().get();

                    Optional<N> z = M.getMatch(y);
                    if (!z.isPresent()) {
                        // if y is free, then u->y is an augmenting path
                        // augment the path and goto step 2
                        ImmutableList<N> augmentingPath = findAugmentingPath(M, El).orElse(ImmutableList.of(u, y));
                        flipAugmentingPath(M, augmentingPath);
                        repeatStep3 = false;
                    } else {
                        // else if y is matched to some z in X
                        // S <- S U {z}
                        S.add(z.get());
                        // T <- T U {y}
                        T.add(y);
                        // goto step 3
                    }
                }
            }

        }
        return labelling;

    }

    private static <N> Labelling<N, Integer> updateLabels(
            Labelling<N, Integer> labelling,
            BipartiteGraph<N, Integer> graph,
            Set<N> S,
            Set<N> T) {
        // find alpha based on S and T
        // alpha = min_{x in S, y in T}(l(x) + l(y) - w(x,y))
        int alpha = Integer.MAX_VALUE;
        Set<N> notT = new HashSet<>(graph.rightSideNodes());
        notT.removeAll(T);
        for (N x : S) {
            for (N y : notT) {
                Optional<Integer> lx = labelling.getLabel(x);
                Optional<Integer> ly = labelling.getLabel(y);
                Optional<Integer> w = graph.edgeValue(x, y);
                int nextVal = lx.get() + ly.get() - w.get();
                alpha = (nextVal < alpha) ? nextVal : alpha;
            }
        }

        // create new labelling with updated labels
        Labelling<N, Integer> newLabelling = new Labelling<>();
        for (N node : labelling.getNodes()) {
            int oldLabel = labelling.getLabel(node).get();
            if (S.contains(node)) {
                newLabelling.putLabel(node, oldLabel - alpha);
            } else if (T.contains(node)) {
                newLabelling.putLabel(node, oldLabel + alpha);
            } else {
                newLabelling.putLabel(node, oldLabel);
            }
        }

        // return new labelling
        return newLabelling;
    }

    public static void main(String args[]) throws FileNotFoundException {

        checkArgument(args.length == 1, "Required filename as sole command line argument");

        String inputFileName = args[0];
        HwRunner.runMaximumMatchingProblem(inputFileName, KM::maxMatchingKuhnMunkres);
    }

}
