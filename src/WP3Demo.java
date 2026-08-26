public class WP3Demo {
    public static void main(String[] args) {
        System.out.println("=== WP3: HEAP PRIORITY & PERFORMANCE DEMO ===");

        System.out.println("\n--- Öncelik Sıralaması Testi ---");
        HeapDispatcher heap = new HeapDispatcher(10);

        heap.insert(new Submission("S-0042", "a.zip", 100, 10000L, false));
        heap.insert(new Submission("S-0099", "b.zip", 100, 12000L, true));
        heap.insert(new Submission("S-0055", "c.zip", 100, 9000L, false));
        heap.insert(new Submission("S-0010", "d.zip", 100, 9000L, false));

        System.out.println("Beklenen: S-0099 -> S-0010 -> S-0055 -> S-0042");
        System.out.println("Gerceklesen:");
        while (!heap.isEmpty()) {
            Submission s = heap.poll();
            System.out.println(" -> ID: " + s.getStudentId() + " | Flagged: " + s.isFlagged() + " | Time: " + s.getTimestampMs());
        }

        System.out.println("\n--- Performans Testi (10.000 Eleman) ---");
        int testSize = 10000;

        HeapDispatcher fastHeap = new HeapDispatcher(testSize);
        NaiveDispatcher slowDispatcher = new NaiveDispatcher(testSize);

        for (int i = 0; i < testSize; i++) {
            Submission s = new Submission("S-" + i, "file.zip", 100, (long)(Math.random() * 50000), (Math.random() > 0.9));
            fastHeap.insert(s);
            slowDispatcher.insert(s);
        }

        long startFast = System.currentTimeMillis();
        while (!fastHeap.isEmpty()) {
            fastHeap.poll();
        }
        long timeFast = System.currentTimeMillis() - startFast;

        long startSlow = System.currentTimeMillis();
        while (slowDispatcher.size() > 0) {
            slowDispatcher.poll();
        }
        long timeSlow = System.currentTimeMillis() - startSlow;

        System.out.println("HeapDispatcher Poll Süresi (O(log N)): " + timeFast + " ms");
        System.out.println("NaiveDispatcher Poll Süresi (O(N))   : " + timeSlow + " ms");
    }
}