package edu.ut.ece.social.hw1.dgs;

import com.google.common.base.Preconditions;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.Matching;
import edu.ut.ece.social.hw1.HwRunner;
import edu.ut.ece.social.hw1.km.KM;

import java.io.FileNotFoundException;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static edu.ut.ece.social.graph.BipartiteGraphFactory.emptyMatching;

/**
 * DGS maximum matching algorithm:
 * For all goods (right side nodes - rsn):
 *  - set price to 0
 *  - owner of good is null
 * Create Queue of left side nodes (lsn)
 * delta = 1/(number of right size nodes +1 )
 *
 * While Queue is not empty:
 *  pop lsn from queue
 *  find good that has greatest effectivePayoff = (edge_weight - price_rsn)
 *  if x >= 0:
 *    if rsn has owner
 *      queue.enqueue current owner
 *    owner = lsn
 *    price_rsn += delta
 * return value of all edges AND list of matches
 */
public class DGS {

    private static Integer findGreatestPayoff(Integer lsn, Map<Integer, Double> price, BipartiteGraph<Integer,Integer> graph) {
        Set<Integer> rsnSet = graph.successors(lsn);
        double maxValue = 0;
        Integer maxRsn = rsnSet.iterator().next();
        for (Integer rsn: rsnSet) {
            if ((graph.edgeValue(lsn,rsn).get() - price.get(rsn)) > maxValue) {
                maxValue = graph.edgeValue(lsn,rsn).get() - price.get(rsn);
                maxRsn = rsn;
            }
        }

        return maxRsn;
    }

    public static Matching<Integer> maxMatchingDGS (BipartiteGraph<Integer, Integer> graph) {
        Preconditions.checkArgument(graph.leftSideNodes().size() == graph.rightSideNodes().size(),
                "Right side nodes size does not equal left size node size");

        Queue<Integer> bidderQueue = new PriorityQueue();
        Map<Integer, Double> price = new HashMap<>();
        Map<Integer, Integer>  owner = new HashMap<>();

        for (Integer rsn: graph.rightSideNodes()) {
            price.put(rsn, 0.0);
            owner.put(rsn, null);
        }
        bidderQueue.addAll(graph.leftSideNodes());
        double delta = (double)  1 / (graph.nodes().size() + 1);

        if (delta == 0) {
            throw new IllegalStateException("Delta cannot be 0");
        }

        while (!bidderQueue.isEmpty()) {
            Integer lsn = bidderQueue.remove();
            Integer rsn = findGreatestPayoff(lsn, price, graph);
            double effectivePayoff = graph.edgeValue(lsn, rsn).get() - price.get(rsn);
            if (effectivePayoff >= 0) {
                if (owner.get(rsn) != null) {
                    bidderQueue.add(owner.get(rsn));
                }
                owner.put(rsn, lsn);
                price.put(rsn, price.get(rsn) + delta);
            }
        }

        Matching<Integer> m = emptyMatching();
        for (int o: owner.keySet()) {
            m.putEdge(owner.get(o), o);
        }
        return m;
    }

    public static void main(String args[]) throws FileNotFoundException {

        checkArgument(args.length==1, "Required filename as sole command line argument");

        String inputFileName = args[0];
        HwRunner.runMaximumMatchingProblem(inputFileName, DGS::maxMatchingDGS);
    }
}
