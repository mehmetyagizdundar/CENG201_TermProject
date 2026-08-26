public class VersionStack {
//WP4
    private static class Node {
        VersionRecord data;
        Node next;
        Node(VersionRecord data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node top;
    private int size;
    public VersionStack() {
        this.top = null;
        this.size = 0;
    }
    public void push(VersionRecord record) {
        if (record == null) return;
        this.top = new Node(record, this.top);
        this.size++;
    }
    public VersionRecord pop() {
        if (top == null) return null;
        VersionRecord item = top.data;
        top = top.next;
        size--;
        return item;
    }
    public VersionRecord peek() {
        if (top == null) return null;
        return top.data;
    }
    public boolean isEmpty() {
        return top == null;
    }
    public int size() {
        return size;
    }
}