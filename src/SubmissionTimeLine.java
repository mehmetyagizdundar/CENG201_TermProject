public class SubmissionTimeLine {

    public static class Node {
        public Submission data;
        public Node left;
        public Node right;
        public int height;
        Node(Submission data) {
            this.data = data;
            this.height = 1;
        }
    }
    private Node root;
    private int size;

    public SubmissionTimeLine() {
        this.root = null;
        this.size = 0;
    }
    public void insert(Submission s) {
        if (s == null) return;
        this.root = insertRec(this.root, s);
        this.size++;
    }
    private Node insertRec(Node node, Submission s) {
        if (node == null) return new Node(s);

        if (s.getTimestampMs() < node.data.getTimestampMs()) {
            node.left = insertRec(node.left, s);
        } else if (s.getTimestampMs() > node.data.getTimestampMs()) {
            node.right = insertRec(node.right, s);
        } else {
            if (s.getStudentId().compareTo(node.data.getStudentId()) < 0) {
                node.left = insertRec(node.left, s);
            } else {
                node.right = insertRec(node.right, s);
            }
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && s.getTimestampMs() < node.left.data.getTimestampMs()) {
            return rotateRight(node);
        }
        if (balance < -1 && s.getTimestampMs() > node.right.data.getTimestampMs()) {
            return rotateLeft(node);
        }
        if (balance > 1 && s.getTimestampMs() > node.left.data.getTimestampMs()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && s.getTimestampMs() < node.right.data.getTimestampMs()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }
    public Submission[] rangeQuery(long startMs, long endMs) {
        Submission[] temp = new Submission[size];
        int[] count = new int[1];
        rangeRec(root, startMs, endMs, temp, count);

        Submission[] result = new Submission[count[0]];
        for (int i = 0; i < count[0]; i++) result[i] = temp[i];
        return result;
    }
    private void rangeRec(Node node, long startMs, long endMs, Submission[] temp, int[] count) {
        if (node == null) return;

        if (startMs < node.data.getTimestampMs()) {
            rangeRec(node.left, startMs, endMs, temp, count);
        }
        if (node.data.getTimestampMs() >= startMs && node.data.getTimestampMs() <= endMs) {
            temp[count[0]++] = node.data;
        }
        if (endMs > node.data.getTimestampMs()) {
            rangeRec(node.right, startMs, endMs, temp, count);
        }
    }
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }
    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }
    public int size() {
        return size;
    }
}
