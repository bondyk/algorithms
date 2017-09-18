package icake;


import java.util.ArrayList;
import java.util.List;

/**
 * Given an undirected graph with maximum degree D, find a graph coloring using at most D+1 colors.
 */
public class GraphColoring {

    public static void main(String[] args) {
        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");

        a.neighbors.add(b);
        b.neighbors.add(a);
        b.neighbors.add(c);
        c.neighbors.add(b);

        List<GraphNode> graph = new ArrayList<>();
        graph.add(a);
        graph.add(b);
        graph.add(c);

        color(2, graph);
        System.out.println(graph);
    }

    private static void color(int degree, GraphNode node) {
        if (node.color == -1) {
            int colors = degree + 1;
            node.color = getAllowedColor(colors, node);
            color(degree, node.neighbors);
        }
    }

    private static void color(int degree, List<GraphNode> nodes) {
        for (GraphNode node : nodes) {
            color(degree, node);
        }
    }

    private static int getAllowedColor(int colors, GraphNode node) {
        boolean[] forbiddenColors = new boolean[colors];
        for (GraphNode neighbour : node.neighbors) {
            if (neighbour.color != -1) {
                forbiddenColors[neighbour.color] = true;
            }
        }

        for (int i = 0; i < forbiddenColors.length; i++) {
            boolean forbiddenColor = forbiddenColors[i];
            if (!forbiddenColor) return i;
        }

        throw new IllegalStateException("No available color");
    }

    private static class GraphNode {
        private final String label;
        private final List<GraphNode> neighbors = new ArrayList<>();;
        private int color = -1;

        public GraphNode(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "[" + label + "]=" + color;
        }
    }
}
