package edu.ut.ece.social.hw2;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.BipartiteGraphFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class InputReader {

    static MarriageProblem readMarriageProblemFromFile(String filePath) throws FileNotFoundException {
        File file = Paths.get(filePath).toFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return readProblemFromReader(reader);
    }

    /**
     * Assumes file structure:
     *
     * N       // number of men/women (assumed equal)
     * a b c d // list of integers denoting man 1's preference of women
     * e f g h // man 2
     * ...
     * w x y z // man N
     * a b c d // woman 1
     * ...
     * w x y z // woman N
     */
    static MarriageProblem readProblemFromReader(BufferedReader reader) {

        // get the prefs as a list of lists of integers
        List<List<Integer>> prefs = reader.lines()
                .map(s -> s.replaceAll("//.*",""))
                .map(String::trim)
                .map(Splitter.on(Pattern.compile("\\s+"))::splitToList)
                .filter(splitArray -> splitArray.size() > 1)
                .map(InputReader::stringListToIntList)
                .collect(Collectors.toList());

        // split the list of lists into two lists of lists
        int nMen = prefs.get(0).size();
        List<List<List<Integer>>> splitPrefs = Lists.partition(prefs, nMen);

        return new MarriageProblem(nMen, splitPrefs.get(0), splitPrefs.get(1));

    }

    private static ImmutableList<Integer> stringListToIntList(List<String> stringList) {
        return stringList.stream()
                .map(Integer::parseInt)
                .collect(ImmutableList.toImmutableList());
    }
}
