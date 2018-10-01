package edu.ut.ece.social.hw2;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class MarriageProblemTest {

    private static final int N = 3;

    private static final List<List<Integer>> MAN_PREFS = ImmutableList.of(
            ImmutableList.of(1, 2, 3),
            ImmutableList.of(3, 2, 1),
            ImmutableList.of(2, 1, 3));

    private static final List<List<Integer>> WOMAN_PREFS = ImmutableList.of(
            ImmutableList.of(3, 2, 1),
            ImmutableList.of(3, 1, 2),
            ImmutableList.of(2, 1, 3));

    @Test
    public void getNumberOfPeople_shouldReturnCorrectValue() {
        MarriageProblem problem = new MarriageProblem(3, MAN_PREFS, WOMAN_PREFS);

        assertThat(problem.getNumberOfMen()).isEqualTo(3);
    }

    @Test
    public void getManPrefs_shouldReturnCorrectValues() {
        MarriageProblem problem = new MarriageProblem(3, MAN_PREFS, WOMAN_PREFS);

        assertThat(problem.getManPrefs(1)).containsExactly(1, 2, 3).inOrder();
        assertThat(problem.getManPrefs(2)).containsExactly(3, 2, 1).inOrder();
        ;
        assertThat(problem.getManPrefs(3)).containsExactly(2, 1, 3).inOrder();
        ;
    }

    @Test
    public void getwomanPrefs_shouldReturnCorrectValues() {
        MarriageProblem problem = new MarriageProblem(3, MAN_PREFS, WOMAN_PREFS);

        assertThat(problem.getWomanPrefs(1)).containsExactly(3, 2, 1).inOrder();
        ;
        assertThat(problem.getWomanPrefs(2)).containsExactly(3, 1, 2).inOrder();
        ;
        assertThat(problem.getWomanPrefs(3)).containsExactly(2, 1, 3).inOrder();
        ;
    }

    @Test
    public void getManRankingOfWomen_shouldReturnCorrectValues() {
        MarriageProblem problem = new MarriageProblem(3, MAN_PREFS, WOMAN_PREFS);

        assertThat(problem.getManRankingOfWoman(1, 1)).isEqualTo(1);
        assertThat(problem.getManRankingOfWoman(1, 2)).isEqualTo(2);
        assertThat(problem.getManRankingOfWoman(1, 3)).isEqualTo(3);
        assertThat(problem.getManRankingOfWoman(2, 1)).isEqualTo(3);
        assertThat(problem.getManRankingOfWoman(2, 2)).isEqualTo(2);
        assertThat(problem.getManRankingOfWoman(2, 3)).isEqualTo(1);
        assertThat(problem.getManRankingOfWoman(3, 1)).isEqualTo(2);
        assertThat(problem.getManRankingOfWoman(3, 2)).isEqualTo(1);
        assertThat(problem.getManRankingOfWoman(3, 3)).isEqualTo(3);
    }

    @Test
    public void getwomanRankingOfMen_shouldReturnCorrectValues() {
        MarriageProblem problem = new MarriageProblem(3, MAN_PREFS, WOMAN_PREFS);

        assertThat(problem.getWomanRankingForMan(1, 1)).isEqualTo(3);
        assertThat(problem.getWomanRankingForMan(1, 2)).isEqualTo(2);
        assertThat(problem.getWomanRankingForMan(1, 3)).isEqualTo(1);
        assertThat(problem.getWomanRankingForMan(2, 1)).isEqualTo(2);
        assertThat(problem.getWomanRankingForMan(2, 2)).isEqualTo(3);
        assertThat(problem.getWomanRankingForMan(2, 3)).isEqualTo(1);
        assertThat(problem.getWomanRankingForMan(3, 1)).isEqualTo(2);
        assertThat(problem.getWomanRankingForMan(3, 2)).isEqualTo(1);
        assertThat(problem.getWomanRankingForMan(3, 3)).isEqualTo(3);
    }
}
