package edu.ut.ece.social.hw1;

import com.google.common.base.Preconditions;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.Matching;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class HwRunner {

    public static void runMaximumMatchingProblem(String filename, MaximumMatchingAlgorithm matchingAlgorithm)
            throws FileNotFoundException {
        System.out.println(runMaximumMatchingProblemWithOutput(filename, matchingAlgorithm));
    }

    public static String runMaximumMatchingProblemWithOutput(String filename, MaximumMatchingAlgorithm matchingAlgorithm)
            throws FileNotFoundException {
        BipartiteGraph<Integer, Integer> graph = InputReader.readGraphFromFile(Paths.get(filename));

        Instant startInstant = Instant.now();
        Matching<Integer> maxMatching = matchingAlgorithm.findMaximumMatching(graph);
        Instant endInstant = Instant.now();

        Duration elapsedTime = Duration.between(startInstant, endInstant);

        StringBuilder output = new StringBuilder();
        output.append("Elapsed time: " + elapsedTime.toMillis() + "ms\n");
        output.append(Integer.toString(computeMatchingWeight(graph, maxMatching)) + "\n");

        output.append(maxMatching.getAllMatches().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(match -> String.format("(%d,%d)", match.getKey(), match.getValue()*-1))
                .collect(Collectors.joining("\n")));

        return output.toString();
    }

    private static int computeMatchingWeight(BipartiteGraph<Integer, Integer> graph, Matching<Integer> matching) {
        Preconditions.checkArgument(matching.isMatchingOn(graph), "Matching is not valid on graph");

        int weightSum = 0;
        for (int leftSideNode : matching.leftSideNodes()) {
            int edgeWeight = matching.getMatch(leftSideNode)
                    .flatMap(rightSideNode -> graph.edgeValue(leftSideNode, rightSideNode))
                    .orElseThrow(() -> new IllegalStateException("Should not be possible"));
            weightSum += edgeWeight;
        }

        return weightSum;
    }

}
