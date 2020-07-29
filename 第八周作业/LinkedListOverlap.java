import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LinkedListOverlap {
    public static boolean isOverlap (Node m, Node n) {
        if (m == null || n == null) return false;
        Map<Node, Node> nodeMap = new HashMap();
        int c = 0;
        while (true) {
            if (m == null && n == null) break;
            if (c++%2 == 0) {
                if (m != null) {
                    if (nodeMap.get(m) != null) return true;
                    else nodeMap.put(m, m);
                    m = m.next;
                }
            } else {
                if (n != null) {
                    if (nodeMap.get(n) != null) return true;
                    else nodeMap.put(n, n);
                    n = n.next;
                }
            }
        }
        return false;
    }

    static class Node {
        public Node next;
    }

    @Test
    public void test_normal() {
        //Given
        Node same = new Node();

        Node m = new Node();
        m.next = new Node();
        m.next.next = new Node();
        m.next.next.next = new Node();
        m.next.next.next.next = new Node();
        m.next.next.next.next.next = same;

        Node n = new Node();
        n.next = new Node();
        n.next.next = new Node();
        n.next.next.next = same;

        //When
        boolean result = LinkedListOverlap.isOverlap(m, n);

        //Then
        Assert.assertTrue(result);
    }
}
