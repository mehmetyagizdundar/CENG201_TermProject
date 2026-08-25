public class WP6Demo {
    public static void main(String[] args) {
        System.out.println("=== WP6: REPORT SERVICE DEMO ===");

        Submission[] data = {
                new Submission("S-001", "a.zip", 100, 1000L),
                new Submission("S-002", "b.zip", 100, 5000L),
                new Submission("S-003", "c.zip", 100, 2000L),
                new Submission("S-004", "d.zip", 100, 8000L),
                new Submission("S-005", "e.zip", 100, 3000L)
        };

        long deadline = 2500L;

        System.out.println("\n--- Top-K Late Submissions (K=2) ---");
        Submission[] top2Late = ReportService.findTopKLate(data, 2, deadline);
        for (Submission s : top2Late) {
            System.out.println("En Gec Kalan: " + s.getStudentId() + " | Zaman: " + s.getTimestampMs() + "ms");
        }

        System.out.println("\n--- QuickSort ve Binary Search Testi ---");
        ReportService.quickSort(data, 0, data.length - 1);

        System.out.print("Siralanmis Zamanlar: ");
        for (Submission s : data) {
            System.out.print(s.getTimestampMs() + " ");
        }
        System.out.println();

        int firstLateIdx = ReportService.findFirstLateIndex(data, deadline);
        if (firstLateIdx != -1) {
            System.out.println("Deadline (" + deadline + "ms) sonrasi İLK gec kalan: "
                    + data[firstLateIdx].getStudentId() + " (" + data[firstLateIdx].getTimestampMs() + "ms)");
        }
    }
}
