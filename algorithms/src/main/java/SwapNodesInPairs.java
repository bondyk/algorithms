import helper.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 *
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class SwapNodesInPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode n = head;
        ListNode result = head.next;
        ListNode nPrev = null;
        do {
            nPrev = swapWithNext(nPrev, n);
            n = nPrev.next;
        } while (n != null);

        return result;
    }

    private static ListNode swapWithNext(ListNode externalPrev, ListNode n) {
        ListNode n1 = n.next;
        if (n1 != null) {
            if (externalPrev != null) {
                externalPrev.next = n1;
            }
            ListNode externalNext = n1.next;
            n1.next = n;
            n.next = externalNext;
            return n;
        }

        return n;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode m = swapPairs(n1);

        while (m != null) {
            System.out.print(m.val);
            System.out.print(" -> ");
            m = m.next;
        }
        System.out.println();
        System.out.println("=====");
    }
}
