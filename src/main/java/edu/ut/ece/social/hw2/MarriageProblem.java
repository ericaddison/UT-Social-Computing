package edu.ut.ece.social.hw2;

import com.google.common.collect.ImmutableList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The manIndex and womanIndex are 1-based indexing!
 * <p>
 * Assumes number of men = number of women.
 */
public class MarriageProblem {

    private int numberOfProposers;
    private ImmutableList<ImmutableList<Integer>> menPreferences;
    private ImmutableList<ImmutableList<Integer>> womanPreferences;
    private List<Integer> nextProposalRank;

    MarriageProblem(int numberOfMen, List<List<Integer>> menPreferences, List<List<Integer>> womanPreferences) {
        this.numberOfProposers = numberOfMen;
        this.menPreferences = listOfLists(menPreferences);
        this.womanPreferences = listOfLists(womanPreferences);
        reset();
    }

    public void reset() {
        nextProposalRank = new ArrayList<>(numberOfProposers);
        for (int i = 0; i < numberOfProposers; i++) {
            nextProposalRank.add(1);
        }
    }

    private static ImmutableList<ImmutableList<Integer>> listOfLists(List<List<Integer>> listOfLists) {
        ImmutableList.Builder<ImmutableList<Integer>> listBuilder = ImmutableList.builder();
        for (List<Integer> list : listOfLists) {
            listBuilder.add(ImmutableList.copyOf(list));
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
        return menPreferences.get(toInternalIndex(manIndex));
    }

    public ImmutableList<Integer> getWomanPrefs(int womanIndex) {
        return womanPreferences.get(toInternalIndex(womanIndex));
    }

    /**
     * Returns the ranking that man[manIndex] assigns to woman[womanIndex].
     *
     * <p> Ranking is returned as a value between 1 and N, where a lower number indicates a higher preference. </p>
     */
    public int getManRankingOfWoman(int manIndex, int womanIndex) {
        return toExternalIndex(menPreferences.get(toInternalIndex(manIndex)).indexOf(womanIndex));
    }

    /**
     * Returns the ranking that woman[womanIndex] assigns to man[manIndex].
     *
     * <p> Ranking is returned as a value between 1 and N, where a lower number indicates a higher preference. </p>
     */
    public int getWomanRankingOfMan(int womanIndex, int manIndex) {
        return toExternalIndex(womanPreferences.get(toInternalIndex(womanIndex)).indexOf(manIndex));
    }

    private static int toInternalIndex(int externalIndex) {
        return externalIndex - 1;
    }

    private static int toExternalIndex(int internalIndex) {
        return internalIndex + 1;
    }

    /**
     * Returns the index of the next woman/man a man/woman chooses.
     */
    public int getNextProposal(int proposerIndex, boolean isProposerAMan) {
        // get the rank of the next woman/man in the man/woman's preference list
        int nextRank = nextProposalRank.get(toInternalIndex(proposerIndex));
        return isProposerAMan
                ? getManPrefs(proposerIndex).get(toInternalIndex(nextRank))
                : getWomanPrefs(proposerIndex).get(toInternalIndex(nextRank));
    }

    /**
     * Increments the rank of the proposer's next choice after being rejected by his/her current choice.
     */
    public void rejectProposer(int proposerIndex, boolean isProposerAMan) {
        int currentProposalRank = getNextProposal(proposerIndex, isProposerAMan);
        nextProposalRank.set(toInternalIndex(proposerIndex), currentProposalRank + 1);
    }
}
