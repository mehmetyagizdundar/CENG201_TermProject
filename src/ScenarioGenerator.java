import java.util.Random;

public class ScenarioGenerator {
    public static final long DEADLINE_MS = 86_340_000L;
    private final Random rn;
    public ScenarioGenerator(long seed) {
        this.rn = new Random(seed);
    }
    public Submission generateSubmission(String studentId, long minTimeMs, long maxTimeMs) {
        String fileName = "solution_" + studentId + ".zip";
        int sizeKb = 100 + rn.nextInt(9901);
        long range = Math.max(1, maxTimeMs - minTimeMs);
        long timestampMs = minTimeMs + (Math.abs(rn.nextLong()) % range);
        boolean accommodation = rn.nextDouble() < 0.15;
        return new Submission(studentId, fileName, sizeKb, timestampMs, accommodation);
    }
    public Submission[] generateBatch(int count, long minTimeMs, long maxTimeMs) {
        Submission[] batch = new Submission[count];
        for (int i = 0; i < count; i++) {
            String studentId = String.format("S-%04d", rn.nextInt(2000));
            batch[i] = generateSubmission(studentId, minTimeMs, maxTimeMs);
        }
        return batch;
    }
    public Submission[] generateWP1EightStudents() {
        Submission[] students = new Submission[8];
        String[] ids = {
                "S-0010", "S-0025", "S-0042", "S-0077",
                "S-0105", "S-0120", "S-0200", "S-0350"
        };
        for (int i = 0; i < 8; i++) {
            int sizeKb = 500 + (i * 250);
            long timeMs = DEADLINE_MS - (3600_000L) + (i * 300_000L);
            boolean accommodation = (i % 3 == 0);
            students[i] = new Submission(
                    ids[i],
                    ids[i] + "_v1.zip",
                    sizeKb,
                    timeMs,
                    accommodation
            );
        }
        return students;
    }
    public String[] generateRandomLookupKeys(int count, int maxStudentIdNumber) {
        String[] keys = new String[count];
        for (int i = 0; i < count; i++) {
            keys[i] = String.format("S-%04d", rn.nextInt(maxStudentIdNumber));
        }
        return keys;
    }
}