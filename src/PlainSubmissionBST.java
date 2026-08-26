public class PlainSubmissionBST {

    public static class Node {
        Submission data;
        Node left;
        Node right;

        public Node(Submission data) {
            this.data = data;
        }
    }
    private Node root;
    private int size;
    private int maxDepth;

    public PlainSubmissionBST() {
        this.root = null;
        this.size = 0;
        this.maxDepth = 0;
    }
    public void insert(Submission s) {
        if (s == null) return;
        root = insertRec(root, s, 1);
        size++;
    }
    private Node insertRec(Node root, Submission s, int depth) {
        if (root == null) {
            if (depth > maxDepth) maxDepth = depth;
            return new Node(s);
        }

        if (s.getTimestampMs() < root.data.getTimestampMs()) {
            root.left = insertRec(root.left, s, depth + 1);
        } else if (s.getTimestampMs() > root.data.getTimestampMs()) {
            root.right = insertRec(root.right, s, depth + 1);
        } else {
            if (s.getStudentId().compareTo(root.data.getStudentId()) < 0) {
                root.left = insertRec(root.left, s, depth + 1);
            } else {
                root.right = insertRec(root.right, s, depth + 1);
            }
        }
        return root;
    }
    public int getMaxDepth() {
        return maxDepth;
    }
    public int size() {
        return size;
    }
}
