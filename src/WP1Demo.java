public class WP1Demo {
    public static void main(String[] args) {
        System.out.println("=== WP1: SUBMISSION REGISTRY DEMO ===");
        SubmissionRegistry registry = new SubmissionRegistry();
        Submission s1 = new Submission("S-001", "hw1.zip", 120, 1000L);
        registry.put(s1);
        System.out.println("İlk Yukleme: " + s1.getStudentId() + " | Versiyon: " + s1.getVersion() + " | Dosya: " + s1.getFileName());
        registry.updateVersion("S-001", "hw1_fixed.zip", 150, 2500L);
        Submission updated = registry.lookup("S-001");
        System.out.println("Guncelleme Sonrasi:");
        System.out.println("ID: " + updated.getStudentId() + " | Yeni Versiyon: " + updated.getVersion() + " | Yeni Dosya: " + updated.getFileName());
        Submission notFound = registry.lookup("S-999");
        System.out.println("Olmayan Ogrenci Sorgusu: " + (notFound == null ? "Bulunamadi (Null dondu)" : "Hata!"));
    }
}
