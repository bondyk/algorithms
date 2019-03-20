package helper;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, int... next) {
        this.val = x;
        ListNode n = this;
        for (final int nextVal : next) {
            n.next = new ListNode(nextVal);
            n = n.next;
        }
    }

    public String toString() {
        return String.valueOf(val);
    }
}
