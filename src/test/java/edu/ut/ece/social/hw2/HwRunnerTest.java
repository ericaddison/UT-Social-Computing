package edu.ut.ece.social.hw2;

import com.google.common.collect.ImmutableList;
import edu.ut.ece.social.graph.BipartiteGraphFactory;
import edu.ut.ece.social.graph.Matching;
import edu.ut.ece.social.hw2.HwRunner;
import edu.ut.ece.social.hw2.MarriageProblem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class HwRunnerTest {

    @Test
    public void justRunItMan_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/prefs1.txt", true);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,2)");
        assertThat(testOutputArray[1]).contains("(2,3)");
        assertThat(testOutputArray[2]).contains("(3,4)");
        assertThat(testOutputArray[3]).contains("(4,1)");
    }

    @Test
    public void justRunItWoman_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/prefs1.txt", false);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,4)");
        assertThat(testOutputArray[1]).contains("(2,1)");
        assertThat(testOutputArray[2]).contains("(3,3)");
        assertThat(testOutputArray[3]).contains("(4,2)");
    }

    @Test
    public void test1m_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test1.txt", true);

        String[] testOutputArray = testOutput.split("\n");
        assertThat(testOutputArray[0]).contains("(1,1)");
        assertThat(testOutputArray[1]).contains("(2,7)");
        assertThat(testOutputArray[2]).contains("(3,9)");
        assertThat(testOutputArray[3]).contains("(4,5)");
        assertThat(testOutputArray[4]).contains("(5,3)");
        assertThat(testOutputArray[5]).contains("(6,8)");
        assertThat(testOutputArray[6]).contains("(7,10)");
        assertThat(testOutputArray[7]).contains("(8,4)");
        assertThat(testOutputArray[8]).contains("(9,2)");
        assertThat(testOutputArray[9]).contains("(10,6)");
    }

    @Test
    public void test1w_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test1.txt", false);

        String[] testOutputArray = testOutput.split("\n");
        assertThat(testOutputArray[0]).contains("(1,1)");
        assertThat(testOutputArray[1]).contains("(2,2)");
        assertThat(testOutputArray[2]).contains("(3,5)");
        assertThat(testOutputArray[3]).contains("(4,8)");
        assertThat(testOutputArray[4]).contains("(5,7)");
        assertThat(testOutputArray[5]).contains("(6,9)");
        assertThat(testOutputArray[6]).contains("(7,4)");
        assertThat(testOutputArray[7]).contains("(8,6)");
        assertThat(testOutputArray[8]).contains("(9,3)");
        assertThat(testOutputArray[9]).contains("(10,10)");
    }

    @Test
    public void test2m_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test2.txt", true);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,3)");
        assertThat(testOutputArray[1]).contains("(2,31)");
        assertThat(testOutputArray[2]).contains("(3,22)");
        assertThat(testOutputArray[3]).contains("(4,5)");
        assertThat(testOutputArray[4]).contains("(5,2)");
        assertThat(testOutputArray[5]).contains("(6,46)");
        assertThat(testOutputArray[6]).contains("(7,14)");
        assertThat(testOutputArray[7]).contains("(8,9)");
        assertThat(testOutputArray[8]).contains("(9,23)");
        assertThat(testOutputArray[9]).contains("(10,47)");
        assertThat(testOutputArray[10]).contains("(11,34)");
        assertThat(testOutputArray[11]).contains("(12,15)");
        assertThat(testOutputArray[12]).contains("(13,37)");
        assertThat(testOutputArray[13]).contains("(14,13)");
        assertThat(testOutputArray[14]).contains("(15,44)");
        assertThat(testOutputArray[15]).contains("(16,7)");
        assertThat(testOutputArray[16]).contains("(17,29)");
        assertThat(testOutputArray[17]).contains("(18,27)");
        assertThat(testOutputArray[18]).contains("(19,33)");
        assertThat(testOutputArray[19]).contains("(20,36)");
        assertThat(testOutputArray[20]).contains("(21,21)");
        assertThat(testOutputArray[21]).contains("(22,45)");
        assertThat(testOutputArray[22]).contains("(23,38)");
        assertThat(testOutputArray[23]).contains("(24,40)");
        assertThat(testOutputArray[24]).contains("(25,12)");
        assertThat(testOutputArray[25]).contains("(26,50)");
        assertThat(testOutputArray[26]).contains("(27,11)");
        assertThat(testOutputArray[27]).contains("(28,20)");
        assertThat(testOutputArray[28]).contains("(29,1)");
        assertThat(testOutputArray[29]).contains("(30,48)");
        assertThat(testOutputArray[30]).contains("(31,32)");
        assertThat(testOutputArray[31]).contains("(32,24)");
        assertThat(testOutputArray[32]).contains("(33,49)");
        assertThat(testOutputArray[33]).contains("(34,26)");
        assertThat(testOutputArray[34]).contains("(35,6)");
        assertThat(testOutputArray[35]).contains("(36,43)");
        assertThat(testOutputArray[36]).contains("(37,18)");
        assertThat(testOutputArray[37]).contains("(38,16)");
        assertThat(testOutputArray[38]).contains("(39,17)");
        assertThat(testOutputArray[39]).contains("(40,10)");
        assertThat(testOutputArray[40]).contains("(41,8)");
        assertThat(testOutputArray[41]).contains("(42,35)");
        assertThat(testOutputArray[42]).contains("(43,25)");
        assertThat(testOutputArray[43]).contains("(44,39)");
        assertThat(testOutputArray[44]).contains("(45,19)");
        assertThat(testOutputArray[45]).contains("(46,28)");
        assertThat(testOutputArray[46]).contains("(47,30)");
        assertThat(testOutputArray[47]).contains("(48,42)");
        assertThat(testOutputArray[48]).contains("(49,4)");
        assertThat(testOutputArray[49]).contains("(50,41)");
    }

    @Test
    public void test2w_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test2.txt", false);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,29)");
        assertThat(testOutputArray[1]).contains("(2,50)");
        assertThat(testOutputArray[2]).contains("(3,10)");
        assertThat(testOutputArray[3]).contains("(4,32)");
        assertThat(testOutputArray[4]).contains("(5,4)");
        assertThat(testOutputArray[5]).contains("(6,20)");
        assertThat(testOutputArray[6]).contains("(7,43)");
        assertThat(testOutputArray[7]).contains("(8,18)");
        assertThat(testOutputArray[8]).contains("(9,8)");
        assertThat(testOutputArray[9]).contains("(10,1)");
        assertThat(testOutputArray[10]).contains("(11,30)");
        assertThat(testOutputArray[11]).contains("(12,34)");
        assertThat(testOutputArray[12]).contains("(13,24)");
        assertThat(testOutputArray[13]).contains("(14,26)");
        assertThat(testOutputArray[14]).contains("(15,31)");
        assertThat(testOutputArray[15]).contains("(16,28)");
        assertThat(testOutputArray[16]).contains("(17,17)");
        assertThat(testOutputArray[17]).contains("(18,37)");
        assertThat(testOutputArray[18]).contains("(19,5)");
        assertThat(testOutputArray[19]).contains("(20,22)");
        assertThat(testOutputArray[20]).contains("(21,12)");
        assertThat(testOutputArray[21]).contains("(22,23)");
        assertThat(testOutputArray[22]).contains("(23,9)");
        assertThat(testOutputArray[23]).contains("(24,27)");
        assertThat(testOutputArray[24]).contains("(25,25)");
        assertThat(testOutputArray[25]).contains("(26,21)");
        assertThat(testOutputArray[26]).contains("(27,46)");
        assertThat(testOutputArray[27]).contains("(28,16)");
        assertThat(testOutputArray[28]).contains("(29,49)");
        assertThat(testOutputArray[29]).contains("(30,47)");
        assertThat(testOutputArray[30]).contains("(31,2)");
        assertThat(testOutputArray[31]).contains("(32,3)");
        assertThat(testOutputArray[32]).contains("(33,6)");
        assertThat(testOutputArray[33]).contains("(34,19)");
        assertThat(testOutputArray[34]).contains("(35,42)");
        assertThat(testOutputArray[35]).contains("(36,11)");
        assertThat(testOutputArray[36]).contains("(37,45)");
        assertThat(testOutputArray[37]).contains("(38,48)");
        assertThat(testOutputArray[38]).contains("(39,35)");
        assertThat(testOutputArray[39]).contains("(40,40)");
        assertThat(testOutputArray[40]).contains("(41,44)");
        assertThat(testOutputArray[41]).contains("(42,7)");
        assertThat(testOutputArray[42]).contains("(43,39)");
        assertThat(testOutputArray[43]).contains("(44,41)");
        assertThat(testOutputArray[44]).contains("(45,36)");
        assertThat(testOutputArray[45]).contains("(46,15)");
        assertThat(testOutputArray[46]).contains("(47,14)");
        assertThat(testOutputArray[47]).contains("(48,38)");
        assertThat(testOutputArray[48]).contains("(49,33)");
        assertThat(testOutputArray[49]).contains("(50,13)");

    }

    @Test
    public void test3m_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test3.txt", true);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,69)");
        assertThat(testOutputArray[1]).contains("(2,57)");
        assertThat(testOutputArray[2]).contains("(3,92)");
        assertThat(testOutputArray[3]).contains("(4,8)");
        assertThat(testOutputArray[4]).contains("(5,75)");
        assertThat(testOutputArray[5]).contains("(6,17)");
        assertThat(testOutputArray[6]).contains("(7,91)");
        assertThat(testOutputArray[7]).contains("(8,60)");
        assertThat(testOutputArray[8]).contains("(9,40)");
        assertThat(testOutputArray[9]).contains("(10,22)");
        assertThat(testOutputArray[10]).contains("(11,3)");
        assertThat(testOutputArray[11]).contains("(12,50)");
        assertThat(testOutputArray[12]).contains("(13,58)");
        assertThat(testOutputArray[13]).contains("(14,35)");
        assertThat(testOutputArray[14]).contains("(15,1)");
        assertThat(testOutputArray[15]).contains("(16,12)");
        assertThat(testOutputArray[16]).contains("(17,20)");
        assertThat(testOutputArray[17]).contains("(18,80)");
        assertThat(testOutputArray[18]).contains("(19,71)");
        assertThat(testOutputArray[19]).contains("(20,44)");
        assertThat(testOutputArray[20]).contains("(21,41)");
        assertThat(testOutputArray[21]).contains("(22,4)");
        assertThat(testOutputArray[22]).contains("(23,79)");
        assertThat(testOutputArray[23]).contains("(24,27)");
        assertThat(testOutputArray[24]).contains("(25,38)");
        assertThat(testOutputArray[25]).contains("(26,43)");
        assertThat(testOutputArray[26]).contains("(27,31)");
        assertThat(testOutputArray[27]).contains("(28,98)");
        assertThat(testOutputArray[28]).contains("(29,68)");
        assertThat(testOutputArray[29]).contains("(30,99)");
        assertThat(testOutputArray[30]).contains("(31,59)");
        assertThat(testOutputArray[31]).contains("(32,42)");
        assertThat(testOutputArray[32]).contains("(33,65)");
        assertThat(testOutputArray[33]).contains("(34,46)");
        assertThat(testOutputArray[34]).contains("(35,95)");
        assertThat(testOutputArray[35]).contains("(36,83)");
        assertThat(testOutputArray[36]).contains("(37,33)");
        assertThat(testOutputArray[37]).contains("(38,21)");
        assertThat(testOutputArray[38]).contains("(39,47)");
        assertThat(testOutputArray[39]).contains("(40,86)");
        assertThat(testOutputArray[40]).contains("(41,73)");
        assertThat(testOutputArray[41]).contains("(42,14)");
        assertThat(testOutputArray[42]).contains("(43,82)");
        assertThat(testOutputArray[43]).contains("(44,11)");
        assertThat(testOutputArray[44]).contains("(45,29)");
        assertThat(testOutputArray[45]).contains("(46,16)");
        assertThat(testOutputArray[46]).contains("(47,25)");
        assertThat(testOutputArray[47]).contains("(48,81)");
        assertThat(testOutputArray[48]).contains("(49,37)");
        assertThat(testOutputArray[49]).contains("(50,93)");
        assertThat(testOutputArray[50]).contains("(51,5)");
        assertThat(testOutputArray[51]).contains("(52,9)");
        assertThat(testOutputArray[52]).contains("(53,87)");
        assertThat(testOutputArray[53]).contains("(54,76)");
        assertThat(testOutputArray[54]).contains("(55,6)");
        assertThat(testOutputArray[55]).contains("(56,49)");
        assertThat(testOutputArray[56]).contains("(57,96)");
        assertThat(testOutputArray[57]).contains("(58,56)");
        assertThat(testOutputArray[58]).contains("(59,61)");
        assertThat(testOutputArray[59]).contains("(60,15)");
        assertThat(testOutputArray[60]).contains("(61,72)");
        assertThat(testOutputArray[61]).contains("(62,26)");
        assertThat(testOutputArray[62]).contains("(63,19)");
        assertThat(testOutputArray[63]).contains("(64,7)");
        assertThat(testOutputArray[64]).contains("(65,30)");
        assertThat(testOutputArray[65]).contains("(66,89)");
        assertThat(testOutputArray[66]).contains("(67,32)");
        assertThat(testOutputArray[67]).contains("(68,13)");
        assertThat(testOutputArray[68]).contains("(69,48)");
        assertThat(testOutputArray[69]).contains("(70,18)");
        assertThat(testOutputArray[70]).contains("(71,84)");
        assertThat(testOutputArray[71]).contains("(72,100)");
        assertThat(testOutputArray[72]).contains("(73,45)");
        assertThat(testOutputArray[73]).contains("(74,64)");
        assertThat(testOutputArray[74]).contains("(75,77)");
        assertThat(testOutputArray[75]).contains("(76,55)");
        assertThat(testOutputArray[76]).contains("(77,67)");
        assertThat(testOutputArray[77]).contains("(78,90)");
        assertThat(testOutputArray[78]).contains("(79,97)");
        assertThat(testOutputArray[79]).contains("(80,10)");
        assertThat(testOutputArray[80]).contains("(81,54)");
        assertThat(testOutputArray[81]).contains("(82,53)");
        assertThat(testOutputArray[82]).contains("(83,70)");
        assertThat(testOutputArray[83]).contains("(84,24)");
        assertThat(testOutputArray[84]).contains("(85,2)");
        assertThat(testOutputArray[85]).contains("(86,39)");
        assertThat(testOutputArray[86]).contains("(87,51)");
        assertThat(testOutputArray[87]).contains("(88,34)");
        assertThat(testOutputArray[88]).contains("(89,66)");
        assertThat(testOutputArray[89]).contains("(90,28)");
        assertThat(testOutputArray[90]).contains("(91,23)");
        assertThat(testOutputArray[91]).contains("(92,52)");
        assertThat(testOutputArray[92]).contains("(93,78)");
        assertThat(testOutputArray[93]).contains("(94,62)");
        assertThat(testOutputArray[94]).contains("(95,63)");
        assertThat(testOutputArray[95]).contains("(96,88)");
        assertThat(testOutputArray[96]).contains("(97,94)");
        assertThat(testOutputArray[97]).contains("(98,74)");
        assertThat(testOutputArray[98]).contains("(99,36)");
        assertThat(testOutputArray[99]).contains("(100,85)");

    }

    @Test
    public void test3w_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test3.txt", false);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,89)");
        assertThat(testOutputArray[1]).contains("(2,68)");
        assertThat(testOutputArray[2]).contains("(3,73)");
        assertThat(testOutputArray[3]).contains("(4,22)");
        assertThat(testOutputArray[4]).contains("(5,95)");
        assertThat(testOutputArray[5]).contains("(6,55)");
        assertThat(testOutputArray[6]).contains("(7,87)");
        assertThat(testOutputArray[7]).contains("(8,4)");
        assertThat(testOutputArray[8]).contains("(9,19)");
        assertThat(testOutputArray[9]).contains("(10,85)");
        assertThat(testOutputArray[10]).contains("(11,66)");
        assertThat(testOutputArray[11]).contains("(12,8)");
        assertThat(testOutputArray[12]).contains("(13,56)");
        assertThat(testOutputArray[13]).contains("(14,42)");
        assertThat(testOutputArray[14]).contains("(15,60)");
        assertThat(testOutputArray[15]).contains("(16,69)");
        assertThat(testOutputArray[16]).contains("(17,37)");
        assertThat(testOutputArray[17]).contains("(18,18)");
        assertThat(testOutputArray[18]).contains("(19,31)");
        assertThat(testOutputArray[19]).contains("(20,44)");
        assertThat(testOutputArray[20]).contains("(21,38)");
        assertThat(testOutputArray[21]).contains("(22,10)");
        assertThat(testOutputArray[22]).contains("(23,40)");
        assertThat(testOutputArray[23]).contains("(24,47)");
        assertThat(testOutputArray[24]).contains("(25,3)");
        assertThat(testOutputArray[25]).contains("(26,77)");
        assertThat(testOutputArray[26]).contains("(27,57)");
        assertThat(testOutputArray[27]).contains("(28,92)");
        assertThat(testOutputArray[28]).contains("(29,45)");
        assertThat(testOutputArray[29]).contains("(30,65)");
        assertThat(testOutputArray[30]).contains("(31,17)");
        assertThat(testOutputArray[31]).contains("(32,75)");
        assertThat(testOutputArray[32]).contains("(33,74)");
        assertThat(testOutputArray[33]).contains("(34,88)");
        assertThat(testOutputArray[34]).contains("(35,27)");
        assertThat(testOutputArray[35]).contains("(36,78)");
        assertThat(testOutputArray[36]).contains("(37,49)");
        assertThat(testOutputArray[37]).contains("(38,1)");
        assertThat(testOutputArray[38]).contains("(39,86)");
        assertThat(testOutputArray[39]).contains("(40,76)");
        assertThat(testOutputArray[40]).contains("(41,33)");
        assertThat(testOutputArray[41]).contains("(42,32)");
        assertThat(testOutputArray[42]).contains("(43,59)");
        assertThat(testOutputArray[43]).contains("(44,20)");
        assertThat(testOutputArray[44]).contains("(45,35)");
        assertThat(testOutputArray[45]).contains("(46,46)");
        assertThat(testOutputArray[46]).contains("(47,53)");
        assertThat(testOutputArray[47]).contains("(48,63)");
        assertThat(testOutputArray[48]).contains("(49,12)");
        assertThat(testOutputArray[49]).contains("(50,6)");
        assertThat(testOutputArray[50]).contains("(51,99)");
        assertThat(testOutputArray[51]).contains("(52,93)");
        assertThat(testOutputArray[52]).contains("(53,72)");
        assertThat(testOutputArray[53]).contains("(54,81)");
        assertThat(testOutputArray[54]).contains("(55,16)");
        assertThat(testOutputArray[55]).contains("(56,58)");
        assertThat(testOutputArray[56]).contains("(57,2)");
        assertThat(testOutputArray[57]).contains("(58,34)");
        assertThat(testOutputArray[58]).contains("(59,91)");
        assertThat(testOutputArray[59]).contains("(60,7)");
        assertThat(testOutputArray[60]).contains("(61,13)");
        assertThat(testOutputArray[61]).contains("(62,94)");
        assertThat(testOutputArray[62]).contains("(63,9)");
        assertThat(testOutputArray[63]).contains("(64,41)");
        assertThat(testOutputArray[64]).contains("(65,26)");
        assertThat(testOutputArray[65]).contains("(66,67)");
        assertThat(testOutputArray[66]).contains("(67,79)");
        assertThat(testOutputArray[67]).contains("(68,29)");
        assertThat(testOutputArray[68]).contains("(69,24)");
        assertThat(testOutputArray[69]).contains("(70,80)");
        assertThat(testOutputArray[70]).contains("(71,43)");
        assertThat(testOutputArray[71]).contains("(72,61)");
        assertThat(testOutputArray[72]).contains("(73,96)");
        assertThat(testOutputArray[73]).contains("(74,98)");
        assertThat(testOutputArray[74]).contains("(75,52)");
        assertThat(testOutputArray[75]).contains("(76,54)");
        assertThat(testOutputArray[76]).contains("(77,64)");
        assertThat(testOutputArray[77]).contains("(78,90)");
        assertThat(testOutputArray[78]).contains("(79,23)");
        assertThat(testOutputArray[79]).contains("(80,62)");
        assertThat(testOutputArray[80]).contains("(81,48)");
        assertThat(testOutputArray[81]).contains("(82,14)");
        assertThat(testOutputArray[82]).contains("(83,36)");
        assertThat(testOutputArray[83]).contains("(84,5)");
        assertThat(testOutputArray[84]).contains("(85,100)");
        assertThat(testOutputArray[85]).contains("(86,15)");
        assertThat(testOutputArray[86]).contains("(87,83)");
        assertThat(testOutputArray[87]).contains("(88,39)");
        assertThat(testOutputArray[88]).contains("(89,82)");
        assertThat(testOutputArray[89]).contains("(90,71)");
        assertThat(testOutputArray[90]).contains("(91,21)");
        assertThat(testOutputArray[91]).contains("(92,28)");
        assertThat(testOutputArray[92]).contains("(93,50)");
        assertThat(testOutputArray[93]).contains("(94,97)");
        assertThat(testOutputArray[94]).contains("(95,11)");
        assertThat(testOutputArray[95]).contains("(96,51)");
        assertThat(testOutputArray[96]).contains("(97,25)");
        assertThat(testOutputArray[97]).contains("(98,84)");
        assertThat(testOutputArray[98]).contains("(99,30)");
        assertThat(testOutputArray[99]).contains("(100,70)");
    }

    @Test
    public void test4m_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test4.txt", true);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,27)");
        assertThat(testOutputArray[1]).contains("(2,37)");
        assertThat(testOutputArray[2]).contains("(3,12)");
        assertThat(testOutputArray[3]).contains("(4,62)");
        assertThat(testOutputArray[4]).contains("(5,46)");
        assertThat(testOutputArray[5]).contains("(6,3)");
        assertThat(testOutputArray[6]).contains("(7,45)");
        assertThat(testOutputArray[7]).contains("(8,38)");
        assertThat(testOutputArray[8]).contains("(9,15)");
        assertThat(testOutputArray[9]).contains("(10,53)");
        assertThat(testOutputArray[10]).contains("(11,34)");
        assertThat(testOutputArray[11]).contains("(12,25)");
        assertThat(testOutputArray[12]).contains("(13,29)");
        assertThat(testOutputArray[13]).contains("(14,54)");
        assertThat(testOutputArray[14]).contains("(15,19)");
        assertThat(testOutputArray[15]).contains("(16,40)");
        assertThat(testOutputArray[16]).contains("(17,42)");
        assertThat(testOutputArray[17]).contains("(18,51)");
        assertThat(testOutputArray[18]).contains("(19,58)");
        assertThat(testOutputArray[19]).contains("(20,43)");
        assertThat(testOutputArray[20]).contains("(21,21)");
        assertThat(testOutputArray[21]).contains("(22,39)");
        assertThat(testOutputArray[22]).contains("(23,16)");
        assertThat(testOutputArray[23]).contains("(24,10)");
        assertThat(testOutputArray[24]).contains("(25,36)");
        assertThat(testOutputArray[25]).contains("(26,64)");
        assertThat(testOutputArray[26]).contains("(27,55)");
        assertThat(testOutputArray[27]).contains("(28,17)");
        assertThat(testOutputArray[28]).contains("(29,35)");
        assertThat(testOutputArray[29]).contains("(30,18)");
        assertThat(testOutputArray[30]).contains("(31,50)");
        assertThat(testOutputArray[31]).contains("(32,9)");
        assertThat(testOutputArray[32]).contains("(33,61)");
        assertThat(testOutputArray[33]).contains("(34,1)");
        assertThat(testOutputArray[34]).contains("(35,23)");
        assertThat(testOutputArray[35]).contains("(36,22)");
        assertThat(testOutputArray[36]).contains("(37,28)");
        assertThat(testOutputArray[37]).contains("(38,4)");
        assertThat(testOutputArray[38]).contains("(39,47)");
        assertThat(testOutputArray[39]).contains("(40,11)");
        assertThat(testOutputArray[40]).contains("(41,56)");
        assertThat(testOutputArray[41]).contains("(42,8)");
        assertThat(testOutputArray[42]).contains("(43,24)");
        assertThat(testOutputArray[43]).contains("(44,33)");
        assertThat(testOutputArray[44]).contains("(45,6)");
        assertThat(testOutputArray[45]).contains("(46,30)");
        assertThat(testOutputArray[46]).contains("(47,63)");
        assertThat(testOutputArray[47]).contains("(48,48)");
        assertThat(testOutputArray[48]).contains("(49,5)");
        assertThat(testOutputArray[49]).contains("(50,60)");
        assertThat(testOutputArray[50]).contains("(51,32)");
        assertThat(testOutputArray[51]).contains("(52,41)");
        assertThat(testOutputArray[52]).contains("(53,49)");
        assertThat(testOutputArray[53]).contains("(54,13)");
        assertThat(testOutputArray[54]).contains("(55,66)");
        assertThat(testOutputArray[55]).contains("(56,44)");
        assertThat(testOutputArray[56]).contains("(57,2)");
        assertThat(testOutputArray[57]).contains("(58,31)");
        assertThat(testOutputArray[58]).contains("(59,26)");
        assertThat(testOutputArray[59]).contains("(60,52)");
        assertThat(testOutputArray[60]).contains("(61,57)");
        assertThat(testOutputArray[61]).contains("(62,59)");
        assertThat(testOutputArray[62]).contains("(63,7)");
        assertThat(testOutputArray[63]).contains("(64,14)");
        assertThat(testOutputArray[64]).contains("(65,20)");
        assertThat(testOutputArray[65]).contains("(66,65)");

    }

    @Test
    public void test4w_shouldNotThrowAnyExceptions() throws FileNotFoundException {
        String testOutput = HwRunner.runStableMarriageProblemWithOutput("./src/test/resources/hw2/test4.txt", false);

        String[] testOutputArray = testOutput.split("\n");

        assertThat(testOutputArray[0]).contains("(1,34)");
        assertThat(testOutputArray[1]).contains("(2,57)");
        assertThat(testOutputArray[2]).contains("(3,65)");
        assertThat(testOutputArray[3]).contains("(4,64)");
        assertThat(testOutputArray[4]).contains("(5,49)");
        assertThat(testOutputArray[5]).contains("(6,45)");
        assertThat(testOutputArray[6]).contains("(7,28)");
        assertThat(testOutputArray[7]).contains("(8,42)");
        assertThat(testOutputArray[8]).contains("(9,40)");
        assertThat(testOutputArray[9]).contains("(10,24)");
        assertThat(testOutputArray[10]).contains("(11,62)");
        assertThat(testOutputArray[11]).contains("(12,36)");
        assertThat(testOutputArray[12]).contains("(13,54)");
        assertThat(testOutputArray[13]).contains("(14,51)");
        assertThat(testOutputArray[14]).contains("(15,9)");
        assertThat(testOutputArray[15]).contains("(16,11)");
        assertThat(testOutputArray[16]).contains("(17,27)");
        assertThat(testOutputArray[17]).contains("(18,3)");
        assertThat(testOutputArray[18]).contains("(19,15)");
        assertThat(testOutputArray[19]).contains("(20,7)");
        assertThat(testOutputArray[20]).contains("(21,10)");
        assertThat(testOutputArray[21]).contains("(22,30)");
        assertThat(testOutputArray[22]).contains("(23,13)");
        assertThat(testOutputArray[23]).contains("(24,43)");
        assertThat(testOutputArray[24]).contains("(25,41)");
        assertThat(testOutputArray[25]).contains("(26,50)");
        assertThat(testOutputArray[26]).contains("(27,22)");
        assertThat(testOutputArray[27]).contains("(28,5)");
        assertThat(testOutputArray[28]).contains("(29,38)");
        assertThat(testOutputArray[29]).contains("(30,46)");
        assertThat(testOutputArray[30]).contains("(31,58)");
        assertThat(testOutputArray[31]).contains("(32,56)");
        assertThat(testOutputArray[32]).contains("(33,60)");
        assertThat(testOutputArray[33]).contains("(34,25)");
        assertThat(testOutputArray[34]).contains("(35,29)");
        assertThat(testOutputArray[35]).contains("(36,47)");
        assertThat(testOutputArray[36]).contains("(37,2)");
        assertThat(testOutputArray[37]).contains("(38,8)");
        assertThat(testOutputArray[38]).contains("(39,66)");
        assertThat(testOutputArray[39]).contains("(40,16)");
        assertThat(testOutputArray[40]).contains("(41,52)");
        assertThat(testOutputArray[41]).contains("(42,17)");
        assertThat(testOutputArray[42]).contains("(43,20)");
        assertThat(testOutputArray[43]).contains("(44,59)");
        assertThat(testOutputArray[44]).contains("(45,6)");
        assertThat(testOutputArray[45]).contains("(46,44)");
        assertThat(testOutputArray[46]).contains("(47,39)");
        assertThat(testOutputArray[47]).contains("(48,18)");
        assertThat(testOutputArray[48]).contains("(49,53)");
        assertThat(testOutputArray[49]).contains("(50,21)");
        assertThat(testOutputArray[50]).contains("(51,12)");
        assertThat(testOutputArray[51]).contains("(52,48)");
        assertThat(testOutputArray[52]).contains("(53,23)");
        assertThat(testOutputArray[53]).contains("(54,14)");
        assertThat(testOutputArray[54]).contains("(55,63)");
        assertThat(testOutputArray[55]).contains("(56,31)");
        assertThat(testOutputArray[56]).contains("(57,61)");
        assertThat(testOutputArray[57]).contains("(58,19)");
        assertThat(testOutputArray[58]).contains("(59,1)");
        assertThat(testOutputArray[59]).contains("(60,35)");
        assertThat(testOutputArray[60]).contains("(61,33)");
        assertThat(testOutputArray[61]).contains("(62,4)");
        assertThat(testOutputArray[62]).contains("(63,32)");
        assertThat(testOutputArray[63]).contains("(64,26)");
        assertThat(testOutputArray[64]).contains("(65,37)");
        assertThat(testOutputArray[65]).contains("(66,55)");
    }

}
