import helper.ListNode;
import helper.ListNodePrinter;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 * <p>
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        ListNodePrinter.print(rotate(head, 2));// 6 -> 7 -> 1 -> 2 -> 3 -> 4 -> 5
    }

    private static ListNode rotate(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        int listSize = 0;
        ListNode n = head;
        while(n != null) {
            n = n.next;
            listSize++;
        }

        k = k % listSize;

        ListNode first = head;
        ListNode kThFromFirst = first;
        ListNode preKThFromFirst = null;
        while (first.next != null) {
            if (k == 1) {
                preKThFromFirst = kThFromFirst;
                kThFromFirst = kThFromFirst.next;
            } else {
                k--;
            }
            first = first.next;
        }

        if (k != 1 || preKThFromFirst == null) return null;


        preKThFromFirst.next = null;
        first.next = head;
        return kThFromFirst;
    }
}
