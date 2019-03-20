import helper.ListNode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 */
public class RemoveNthNode {

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
        ListNode n = removeNthFromEnd(n1, 2);
        while(n != null) {
            System.out.print(n.val);
            System.out.print(" -> ");
            n = n.next;
        }
        System.out.println();
        System.out.println("============");
        n = removeNthFromEnd(n4, 2);
        while(n != null) {
            System.out.print(n.val);
            System.out.print(" -> ");
            n = n.next;
        }
    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        return remove(head, head, n);
    }

    private static ListNode remove(ListNode lo, ListNode hi, int n) {
        if (n > 1) {
            return remove(lo, hi.next, n - 1);
        } else if (hi.next != null) {
            lo.next = remove(lo.next, hi.next, n);
            return lo;
        } else {
            // delete lo
            return lo.next;
        }
    }
}
