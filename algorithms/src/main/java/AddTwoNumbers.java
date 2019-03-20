
import helper.ListNode;

/**
 * Leetcode
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(3);
        ListNode n2 = new ListNode(5);
        n2.next = new ListNode(6);
        n2.next.next = new ListNode(4);
        AddTwoNumbers c = new AddTwoNumbers();
        ListNode r = c.addTwoNumbers(n1, n2);

        while(r != null) {
            System.out.print(r.val);
            r = r.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    private ListNode add(ListNode l1, ListNode l2, int shift) {
        int v1 = l1 == null ? 0 : l1.val;
        int v2 = l2 == null ? 0 : l2.val;
        int value = v1 + v2 + shift;
        int nextShift = 0;
        if (value > 9) {
            nextShift = value / 10;
            value = value % 10;
        }
        ListNode r = new ListNode(value);
        if ((l1 != null && l1.next != null)
            || (l2 != null && l2.next != null)
            || nextShift > 0) {
            r.next = add(l1 == null ? null : l1.next, l2 == null ? null : l2.next, nextShift);
        }
        return r;
    }
}


