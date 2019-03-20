import helper.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        reorder(head, head);

        print(head);
    }

    private static ListNode reorder(ListNode slow, ListNode fast) {

//        System.out.println("slow = " + slow + ", fast = " + fast);
        ListNode newFast = fast.next.next;
        ListNode newSlow = slow.next;

        ListNode middle = slow.next;
        ListNode next;
        if (newFast == null) {
            next = slow;
        } else if (newFast.next == null) {
            next = slow.next;
        } else {
            middle = reorder(newSlow, newFast);
            next = middle;
        }

        ListNode swapNode = next.next;
//        System.out.println("middle = " + middle + ", swapNode = " + swapNode);
        // ListNode ret = next;
//        System.out.println("next = " + next + ", swapNode = " + swapNode);
        next.next = swapNode.next;
        swapNode.next = slow.next;
        slow.next = swapNode;
        return middle;
    }

    private static void print(ListNode head) {
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
