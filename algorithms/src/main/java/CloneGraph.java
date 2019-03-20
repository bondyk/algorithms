import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph
 * contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors.
 * There is an edge between the given node and each of the nodes in its neighbors.
 * <p>
 * <p>
 * OJ's undirected graph serialization (so you can understand error output):
 * Nodes are labeled uniquely.
 * <p>
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * <p>
 * <p>
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * <p>
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * <p>
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * <p>
 * <p>
 * Visually, the graph looks like the following:
 * <p>
 * 1
 * / \
 * /   \
 * 0 --- 2
 * / \
 * \_/
 * Note: The information about the tree serialization is only meant so that you can understand
 * error output if you get a wrong answer. You don't need to understand the serialization to solve the problem.
 */
public class CloneGraph {

    public static void main(String[] args) {

    }

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<Integer, UndirectedGraphNode> processed = new HashMap<>();
        return clone(node, processed);
    }

    private static UndirectedGraphNode clone(
        UndirectedGraphNode node,
        Map<Integer, UndirectedGraphNode> processed) {
        UndirectedGraphNode clone = getOrClone(node.label, processed);
        processed.put(node.label, clone);
        for (UndirectedGraphNode n : node.neighbors) {
            if (!processed.containsKey(n.label)) {
                clone(n, processed);
            }
            clone.neighbors.add(getOrClone(n.label, processed));
        }

        return clone;
    }

    private static UndirectedGraphNode getOrClone(int label, Map<Integer, UndirectedGraphNode> processed) {
        if (processed.containsKey(label)) {
            return processed.get(label);
        }

        return new UndirectedGraphNode(label);
    }

    private static class UndirectedGraphNode {

        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
}
