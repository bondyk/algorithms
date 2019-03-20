import helper.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {

    /**
     * Merge lists by pairs using Divide and Conquer
     */
    private static ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private static ListNode mergeKLists(ListNode[] lists, int fromIdx, int toIdx) {
        int n = toIdx - fromIdx + 1;
        if (n == 0)  return null;

        if (n == 1) return lists[fromIdx];

        if (n == 2) return merge(lists[fromIdx], lists[toIdx]);

        int middle = (fromIdx + toIdx) / 2;
        ListNode n1 = mergeKLists(lists, fromIdx, middle);
        ListNode n2 = mergeKLists(lists, middle + 1, toIdx);
        return merge(n1, n2);
    }

    private static ListNode merge(ListNode n1, ListNode n2) {

        ListNode head;
        ListNode n;
        // Find a list which start with smaller value and use it as head of resulting list
        if (n2 == null || (n1 != null && n1.val <= n2.val)) {
            head = n1;
            n = n2;
        } else {
            head = n2;
            n = n1;
        }

        ListNode mPrev = null;
        ListNode m = head;

        while(n != null) {
            while (m != null && m.val <= n.val) {
                mPrev = m;
                m = m.next;
            }

            if (mPrev != null) {
                mPrev.next = n;
            }

            mPrev = n;
            ListNode nNext = n.next;
            n.next = m;
            n = nNext;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode n11 = new ListNode(1);
        ListNode n12 = new ListNode(4);
        ListNode n13 = new ListNode(5);
        n11.next = n12;
        n12.next = n13;

        ListNode n21 = new ListNode(1);
        ListNode n22 = new ListNode(3);
        ListNode n23 = new ListNode(4);
        n21.next = n22;
        n22.next = n23;

        ListNode n31 = new ListNode(2);
        ListNode n32 = new ListNode(6);
        n31.next = n32;

        ListNode m = mergeKLists(new ListNode[] {n11, n21, n31});

        while (m != null) {
            System.out.print(m.val);
            System.out.print(" -> ");
            m = m.next;
        }
        System.out.println();
        System.out.println("=====");

        m = mergeKLists(new ListNode[] {null, new ListNode(1)});

        while (m != null) {
            System.out.print(m.val);
            System.out.print(" -> ");
            m = m.next;
        }
        System.out.println();
        System.out.println("=====");

        m = mergeKLists(new ListNode[] {new ListNode(1), new ListNode(0)});

        while (m != null) {
            System.out.print(m.val);
            System.out.print(" -> ");
            m = m.next;
        }
    }
}
