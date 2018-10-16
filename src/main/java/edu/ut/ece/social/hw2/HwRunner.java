package edu.ut.ece.social.hw2;

import com.google.common.collect.BiMap;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.hw1.MaximumMatchingAlgorithm;
import edu.ut.ece.social.hw2.InputReader;
import edu.ut.ece.social.hw2.GS;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class HwRunner {

    public static String runStableMarriageProblemWithOutput(String filePath, boolean manOptimal)
            throws FileNotFoundException {
        MarriageProblem problem = MarriageProblem.fromFile(filePath);

        StringBuilder output = new StringBuilder();
        BiMap<Integer, Integer> matching = GS.stableMarriage(problem, /* manOptimal= */ manOptimal);
        output.append(matching.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(match -> String.format("(%d,%d)", match.getKey()+1, match.getValue()+1))
                .collect(Collectors.joining("\n")));

        return output.toString();
    }
}
