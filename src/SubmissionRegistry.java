public class SubmissionRegistry {
    public static class Node
    {
        Submission data;
        Node next;
        Node (Submission data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    }
    private Node[]table; //başlangıç verisi
    private int size;
    private static final int initialCapacity = 16;
    private static final double loadFactor = 0.75; //O(1) da çalışsın collusion olmasın

    public SubmissionRegistry()
    {
    this.table = new Node[initialCapacity];
    this.size = 0;
    }
    private int hash(String key)
    {

    }
}
