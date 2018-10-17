package edu.ut.ece.social.hw2;

import com.google.common.collect.ImmutableList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Assumes number of men = number of women.
 */
public class MarriageProblem {

    private int numberOfProposers;
    private ImmutableList<ImmutableList<Integer>> menPreferences;
    private ImmutableList<ImmutableList<Integer>> womanPreferences;
    private List<Integer> nextProposalRank;

    MarriageProblem(int numberOfMen, List<List<Integer>> menPreferences, List<List<Integer>> womanPreferences) {
        this.numberOfProposers = numberOfMen;
        this.menPreferences = listOfListsWithDecrement(menPreferences);
        this.womanPreferences = listOfListsWithDecrement(womanPreferences);
        reset();
    }

    public void reset() {
        nextProposalRank = new ArrayList<>(numberOfProposers);
        for (int i = 0; i < numberOfProposers; i++) {
            nextProposalRank.add(0);
        }
    }

    private static ImmutableList<ImmutableList<Integer>> listOfListsWithDecrement(List<List<Integer>> listOfLists) {
        ImmutableList.Builder<ImmutableList<Integer>> listBuilder = ImmutableList.builder();
        for (List<Integer> list : listOfLists) {
            ImmutableList.Builder<Integer> builder = ImmutableList.builder();
            for(int i=0; i<list.size(); i++) {
                builder.add(list.get(i) - 1);
            }
            listBuilder.add(builder.build());
        }
        return listBuilder.build();
    }

    public static MarriageProblem fromFile(String filepath) throws FileNotFoundException {
        return InputReader.readMarriageProblemFromFile(filepath);
    }

    public int getNumberOfMen() {
        return numberOfProposers;
    }

    public ImmutableList<Integer> getManPrefs(int manIndex) {
        return menPreferences.get(manIndex);
    }

    public ImmutableList<Integer> getWomanPrefs(int womanIndex) {
        return womanPreferences.get(womanIndex);
    }

    /**
     * Returns the ranking that man[manIndex] assigns to woman[womanIndex].
     *
     * <p> Ranking is returned as a value between 0 and N-1, where a lower number indicates a higher preference. </p>
     */
    public int getManRankingOfWoman(int manIndex, int womanIndex) {
        return menPreferences.get(manIndex).indexOf(womanIndex);
    }

    /**
     * Returns the ranking that woman[womanIndex] assigns to man[manIndex].
     *
     * <p> Ranking is returned as a value between 0 and N-1, where a lower number indicates a higher preference. </p>
     */
    public int getWomanRankingOfMan(int womanIndex, int manIndex) {
        return womanPreferences.get(womanIndex).indexOf(manIndex);
    }

    /**
     * Returns the index of the next woman/man a man/woman chooses.
     */
    public int getNextProposal(int proposerIndex, boolean isProposerAMan) {
        // get the rank of the next woman/man in the man/woman's preference list
        int nextRank = nextProposalRank.get(proposerIndex);
        return isProposerAMan
                ? getManPrefs(proposerIndex).get(nextRank)
                : getWomanPrefs(proposerIndex).get(nextRank);
    }

    /**
     * Increments the rank of the proposer's next choice after being rejected by his/her current choice.
     */
    public void rejectProposer(int proposerIndex) {
        nextProposalRank.set(proposerIndex, nextProposalRank.get(proposerIndex) + 1);
    }
}
