import com.google.common.collect.ImmutableSet;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class HelloWorld {

    public static void main(String[] args) {
        MutableGraph<Integer> G = GraphBuilder.undirected().build();

        G.addNode(1);
        G.addNode(2);
        G.addNode(3);

        G.putEdge(1, 2);
        G.putEdge(2, 3);


        System.out.println("Here is my graph!");
        System.out.println(G.incidentEdges(3));
    }


}
