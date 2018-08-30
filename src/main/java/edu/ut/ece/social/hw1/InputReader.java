package edu.ut.ece.social.hw1;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputReader {

    public static BipartiteGraph<Integer, Integer> readGraphFromFile(Path filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
        return readGraphFromReader(reader);
    }

    public static BipartiteGraph<Integer, Integer> readGraphFromReader(BufferedReader reader) {

        // get the values as a list of lists of integers
        List<List<Integer>> values = reader.lines()
                .map(s -> s.replaceAll("//.*",""))
                .map(String::trim)
                .map(Splitter.on(Pattern.compile("\\s+"))::splitToList)
                .filter(splitArray -> splitArray.size() > 1)
                .map(InputReader::stringListToIntList)
                .collect(Collectors.toList());

        // fill in a BipartiteGraph
        BipartiteGraph<Integer, Integer> newGraph = BipartiteGraphFactory.emptyBipartiteGraph();
        for(int i=0; i<values.size(); i++) {
            List<Integer> row = values.get(i);
            for(int j=0; j<row.size(); j++) {
                newGraph.putEdge(i+1, -1*(j+1), row.get(j));
            }
        }

        return newGraph;
    }

    private static List<Integer> stringListToIntList(List<String> stringList) {
        return stringList.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
