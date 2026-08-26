public class WP2Demo {
    public static void main(String[] args) {
        System.out.println("=== WP2: QUEUE PERFORMANCE DEMO ===");
        int testSize = 100000;
        CircularUploadQueue fastQueue = new CircularUploadQueue(testSize);
        NaiveUploadQueue slowQueue = new NaiveUploadQueue(testSize);
        for (int i = 0; i < testSize; i++) {
            Submission s = new Submission("S-" + i, "file.zip", 100, 1000L + i);
            fastQueue.enqueue(s);
            slowQueue.enqueue(s);
        }
        long startFast = System.currentTimeMillis();
        while (!fastQueue.isEmpty()) {
            fastQueue.dequeue();
        }
        long timeFast = System.currentTimeMillis() - startFast;
        long startSlow = System.currentTimeMillis();
        while (slowQueue.size() > 0) {
            slowQueue.dequeue();
        }
        long timeSlow = System.currentTimeMillis() - startSlow;
        System.out.println("Kapasite Testi Bitti. Islenen Eleman: " + testSize);
        System.out.println("Circular Queue Dequeue Suresi (O(1)): " + timeFast + " ms");
        System.out.println("Naive Queue Dequeue Suresi (O(N))   : " + timeSlow + " ms");
    }
}
