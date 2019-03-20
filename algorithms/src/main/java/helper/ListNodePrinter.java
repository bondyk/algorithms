package helper;

public class ListNodePrinter {

    public static void print(ListNode head) {
        while(head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
