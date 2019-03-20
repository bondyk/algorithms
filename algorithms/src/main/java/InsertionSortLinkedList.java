import helper.ListNode;
import helper.ListNodePrinter;

/**
 * Algorithm of Insertion Sort:
 *
 * 1. Insertion sort iterates, consuming one input element each repetition,
 * and growing a sorted output list.
 * 2. At each iteration, insertion sort removes one element from the input data,
 * finds the location it belongs within the sorted list, and inserts it there.
 * 3. It repeats until no input elements remain.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class InsertionSortLinkedList {

    public static void main(String[] args) {
        ListNodePrinter.print(insertionSortList(new ListNode(4,2,1,3,7,5,6)));
    }

    public static ListNode insertionSortList(ListNode n) {
        if (n == null || n.next == null) return n;

        ListNode sortedHead = insertionSortList(n.next);
        n.next = sortedHead;

        ListNode node = sortedHead;
        ListNode prevNode = null;
        while(node != null && n.val > node.val) {
            prevNode = node;
            node = node.next;
        }

        if (prevNode == null) {
            // n is smallest so it should be a new head
            return n;
        }

        // n should be placed between prevNode and node
        prevNode.next = n;
        n.next = node;
        return sortedHead;
    }
}
