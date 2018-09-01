package edu.ut.ece.social.hw1.dgs;

import com.google.common.base.Preconditions;
import edu.ut.ece.social.graph.BipartiteGraph;
import edu.ut.ece.social.graph.Matching;

import java.util.*;

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
public class DGS<N> {

    private Queue<N> bidderQueue = new PriorityQueue();
    private Hashtable<N, Integer> price = new Hashtable();
    private Hashtable<N, N>  owner = new Hashtable();

    private N findGreatestPayoff(N lsn, BipartiteGraph<N,Integer> graph) {
        Set<N> rsnSet = graph.successors(lsn);
        int maxValue = 0;
        N maxRsn = rsnSet.iterator().next();
        for (N rsn: rsnSet) {
            if ((graph.edgeValue(lsn,rsn).get() - price.get(rsn)) > maxValue) {
                maxValue = graph.edgeValue(lsn,rsn).get() - price.get(rsn);
                maxRsn = rsn;
            }
        }

        return maxRsn;
    }

    public BipartiteGraph DGSAlgorithm (BipartiteGraph<N, Integer> graph) {
        Preconditions.checkArgument(graph.leftSideNodes().size() == graph.rightSideNodes().size(),
                "Right side nodes size does not equal left size node size");

        for (N rsn: graph.rightSideNodes()) {
            price.put(rsn, 0);
            owner.put(rsn, null);
        }
        bidderQueue.addAll(graph.leftSideNodes());
        double delta = 1/ (graph.nodes().size() + 1);

        while (!bidderQueue.isEmpty()) {
            N lsn = bidderQueue.remove();
            N rsn = findGreatestPayoff(lsn, graph);
            int effectivePayoff = graph.edgeValue(lsn, rsn).get() - price.get(rsn);
            if (effectivePayoff >= 0) {
                if (owner.get(rsn) != null) {
                    bidderQueue.add(owner.get(rsn));
                }
                owner.put(rsn, lsn);
                price.put(rsn, (int) Math.ceil(price.get(rsn) + delta));
            }
        }

        Matching<Integer> matching = emptyMatching();
        //for (N o: owner.)
        matching.putEdge(1, 2);

        // TODO: Add time measurements
    }
}
