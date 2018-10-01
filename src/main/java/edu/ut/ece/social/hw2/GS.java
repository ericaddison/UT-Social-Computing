package edu.ut.ece.social.hw2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implementation of the Gayle-Shapley stable marriage algorithm.
 */
public class GS {

    public static void stableMarriage(MarriageProblem problem) {

        // create free list of men
        Deque<Integer> freeMen = new ArrayDeque<>();
        for (int i = 1; i <= problem.getNumberOfMen(); i++) {
            freeMen.add(i);
        }

        BiMap<Integer, Integer> matching = HashBiMap.create(problem.getNumberOfMen());

        while (!freeMen.isEmpty()) {
            int nextMan = freeMen.poll();
            int targetWoman = problem.getNextManProposal(nextMan);
            if (isWomanFree(matching, targetWoman)) {
                matching.put(nextMan, targetWoman);
            } else {
                int womansCurrentMan = matching.inverse().get(targetWoman);
                int womansRankOfCurrentMan = problem.getWomanRankingForMan(targetWoman, womansCurrentMan);
                int womansRankOfNewMan = problem.getWomanRankingForMan(targetWoman, nextMan);
                int rejectedMan = (womansRankOfNewMan < womansRankOfCurrentMan) ? nextMan : womansCurrentMan;

                // if the new man is more desirable to the woman, make the new match
                if (nextMan != rejectedMan) {
                    makeMatch(matching, nextMan, targetWoman);
                }

                // update the next choice of the rejected man and put him back in the freelist
                problem.rejectMan(rejectedMan);
                freeMen.add(rejectedMan);
            }
        }

        matching.forEach((key, value) -> System.out.println(String.format("(%s,%s)", key, value)));

    }

    private static boolean isWomanFree(BiMap<Integer, Integer> matching, int womanIndex) {
        return !matching.values().contains(womanIndex);
    }

    private static void makeMatch(
            BiMap<Integer, Integer> matching, int manIndex, int womanIndex) {

        // if the woman already has a man, delete that entry
        if (matching.values().contains(womanIndex)) {
            matching.inverse().remove(womanIndex);
        }

        // make the new match
        matching.put(manIndex, womanIndex);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "./src/test/resources/hw2/prefs1.txt";
        MarriageProblem problem = MarriageProblem.fromFile(filePath);

        GS.stableMarriage(problem);

    }
}
