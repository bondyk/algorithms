import helper.ListNode;
import helper.ListNodePrinter;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class MergeSortLinkedList {

    public static void main(String[] args) {
        ListNodePrinter.print(sortList(new ListNode(4,2,1,3,7,5,6)));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode preSlow = null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;

            // System.out.println("slow = " + slow.val);
        }

        // System.out.println(slow.val);
        preSlow.next = null;

        ListNode n1 = sortList(head);
        ListNode n2 = sortList(slow);

        return merge(n1, n2);
    }

    /*
    //This method is much slower. It basically implements insertion sort
    private ListNode mergeSort(ListNode n) {
        if (n == null || n.next == null) return n;

        ListNode nNext = n.next;
        n.next = null;
        ListNode n1 = mergeSort(n);
        ListNode n2 = mergeSort(nNext);

        return merge(n1, n2);
    }*/

    private static ListNode merge(ListNode n1, ListNode n2) {
        if (n1 == null) return n2;
        if (n2 == null) return n1;

        ListNode head = new ListNode(0); // fake node
        ListNode n = head;
        while (n1 != null && n2 != null) {
            if (n1.val <= n2.val) {
                n.next = n1;
                n1 = n1.next;
            } else {
                n.next = n2;
                n2 = n2.next;
            }
            n = n.next;
        }

        if (n1 != null) n.next = n1;
        if (n2 != null) n.next = n2;

        return head.next;
    }
}
