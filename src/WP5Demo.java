public class WP5Demo {
    public static void main(String[] args) {
        System.out.println("=== WP5: TIMELINE (AVL vs BST) DEMO ===");

        SubmissionTimeline avlTree = new SubmissionTimeline();
        PlainSubmissionBST bstTree = new PlainSubmissionBST();

        int testSize = 1000;
        for (int i = 0; i < testSize; i++) {
            Submission s = new Submission("S-" + i, "f.zip", 100, 1000L + i);
            avlTree.insert(s);
            bstTree.insert(s);
        }

        System.out.println(testSize + " adet sirali eleman eklendi.");
        System.out.println("Plain BST Max Derinlik (O(N) oldu!): " + bstTree.getMaxDepth());

        System.out.println("\n--- Range Query Testi ---");
        Submission[] rangeResult = avlTree.rangeQuery(1500L, 1505L);
        System.out.println("1500ms - 1505ms arasindaki odevler:");

        for (Submission s : rangeResult) {
            System.out.println(" -> " + s.getStudentId() + " | Zaman: " + s.getTimestampMs() + "ms");
        }
    }
}
