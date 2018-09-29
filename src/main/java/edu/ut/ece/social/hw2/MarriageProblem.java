package edu.ut.ece.social.hw2;

import com.google.common.collect.ImmutableList;

import java.io.FileNotFoundException;
import java.util.List;

public class MarriageProblem {

    private int numberOfPeople;
    private ImmutableList<ImmutableList<Integer>> menPreferences;
    private ImmutableList<ImmutableList<Integer>> womanPreferences;

    MarriageProblem(int numberOfPeople, List<List<Integer>> menPreferences, List<List<Integer>> womanPreferences) {
        this.numberOfPeople = numberOfPeople;
        this.menPreferences = listOfLists(menPreferences);
        this.womanPreferences = listOfLists(womanPreferences);
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

    public int getNumberOfPeople() {
        return numberOfPeople;
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
     * <p> Ranking is returned as a value between 1 and N, where a lower number indicates a higher preference. </p>
     */
    public int getManRankingForWoman(int manIndex, int womanIndex) {
        return 1 + menPreferences.get(manIndex).indexOf(womanIndex);
    }

    /**
     * Returns the ranking that woman[womanIndex] assigns to man[manIndex].
     *
     * <p> Ranking is returned as a value between 1 and N, where a lower number indicates a higher preference. </p>
     */
    public int getWomanRankingForMan(int womanIndex, int manIndex) {
        return 1 + womanPreferences.get(womanIndex).indexOf(manIndex);
    }
}
