package edu.ut.ece.social.hw2;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Implementation of the Gayle-Shapley stable marriage algorithm.
 */
public class GS {

    public static BiMap<Integer, Integer> stableMarriage(MarriageProblem problem, boolean manOptimal) {

        // create free list of proposers
        Deque<Integer> freeList = new ArrayDeque<>();
        for (int i = 0; i < problem.getNumberOfMen(); i++) {
            freeList.add(i);
        }

        BiMap<Integer, Integer> matching = HashBiMap.create(problem.getNumberOfMen());


        BiFunction<Integer, Integer, Integer>
                getRank = manOptimal ? problem::getWomanRankingOfMan : problem::getManRankingOfWoman;

        // For the man-optimal scenario, the proposer is a man, and the target is his next choice woman
        // for woman-optimal, roles are switched.
        while (!freeList.isEmpty()) {
            int nextProposer = freeList.poll();
            int target = problem.getNextProposal(nextProposer, manOptimal);
            if (isTargetFree(matching, target)) {
                matching.put(nextProposer, target);
            } else {
                int targetsCurrentMatch = matching.inverse().get(target);
                int targetsRankOfCurrentMatch = getRank.apply(target, targetsCurrentMatch);
                int targetsRankOfNextProposer = getRank.apply(target, nextProposer);
                int rejectedPerson = (targetsRankOfNextProposer < targetsRankOfCurrentMatch) ? targetsCurrentMatch : nextProposer;

                // if the new man is more desirable to the woman, make the new match
                if (nextProposer != rejectedPerson) {
                    makeMatch(matching, nextProposer, target);
                }

                // update the next choice of the rejected man and put him back in the freelist
                problem.rejectProposer(rejectedPerson);
                freeList.add(rejectedPerson);
            }
        }

        return matching;
    }

    private static boolean isTargetFree(BiMap<Integer, Integer> matching, int targetIndex) {
        return !matching.values().contains(targetIndex);
    }

    private static void makeMatch(
            BiMap<Integer, Integer> matching, int proposerIndex, int targetIndex) {

        // if the woman already has a man, delete that entry
        if (matching.values().contains(targetIndex)) {
            matching.inverse().remove(targetIndex);
        }

        // make the new match
        matching.put(proposerIndex, targetIndex);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "./src/test/resources/hw2/prefs1.txt";
        MarriageProblem problem = MarriageProblem.fromFile(filePath);

        // man optimal execution
        GS.stableMarriage(problem, /* manOptimal= */ true);

        System.out.println("\n");

        problem.reset();

        // woman optimal execution
        GS.stableMarriage(problem, /* manOptimal= */ false);

    }
}
