public class WP4Demo {
    public static void main(String[] args) {
        System.out.println("=== WP4: VERSION ROLLBACK DEMO ===");

        SubmissionRegistry registry = new SubmissionRegistry();
        RollbackService rollbackService = new RollbackService(registry);

        Submission s1 = new Submission("S-001", "hw1_v1.zip", 100, 1000L);
        registry.put(s1);
        System.out.println("Orijinal Yukleme: " + s1.getFileName() + " (V" + s1.getVersion() + ")");

        VersionRecord v1Record = new VersionRecord(s1.getFileName(), s1.getSizeKb(), s1.getTimestampMs(), s1.getVersion());
        rollbackService.saveVersion(s1.getStudentId(), v1Record);

        registry.updateVersion("S-001", "hw1_v2_broken.zip", 200, 2000L);
        Submission current = registry.lookup("S-001");
        System.out.println("Hatali Guncelleme Sonrasi: " + current.getFileName() + " (V" + current.getVersion() + ")");

        System.out.println("Rollback Islemi Uygulaniyor...");
        rollbackService.rollback("S-001");

        System.out.println("Rollback Sonrasi Mevcut Durum: " + current.getFileName() + " (V" + current.getVersion() + ")");
    }
}
