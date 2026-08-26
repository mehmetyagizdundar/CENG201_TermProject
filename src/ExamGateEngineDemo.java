public class ExamGateEngineDemo {
    public static void main(String[] args) {
        System.out.println("=== WP7: EXAM GATE ENGINE DEMO ===");

        ExamGateEngine engine = new ExamGateEngine(10, 10);

        Submission s1 = new Submission("S-100", "hw_final.zip", 150, 1000L, false);
        Submission s2 = new Submission("S-101", "hw_final.zip", 200, 1500L, true);
        Submission s3 = new Submission("S-102", "hw_final.zip", 120, 2000L, false);

        System.out.println("Odevler Sisteme Yukleniyor...");
        engine.acceptUpload(s1);
        engine.acceptUpload(s2);
        engine.acceptUpload(s3);

        System.out.println("\n1. Queue Durumu (WP2):");
        System.out.println("Kuyruktaki Ödev Sayısı: " + engine.getIntakeQueue().size());

        System.out.println("\n2. Registry (Hash Table) Durumu (WP1):");
        System.out.println("S-101 Kaydi: " + engine.getRegistry().lookup("S-101").getFileName());

        System.out.println("\n3. Versiyon Güncelleme ve Rollback (WP4):");
        Submission s1_v2 = new Submission("S-100", "hw_final_v2_fixed.zip", 160, 2500L, false);
        engine.acceptUpload(s1_v2);

        System.out.println("S-100 Güncel Versiyon: " + engine.getRegistry().lookup("S-100").getVersion()
                + " | Dosya: " + engine.getRegistry().lookup("S-100").getFileName());

        engine.getRollbackService().rollback("S-100");
        System.out.println("S-100 Rollback Sonrasi Dosya: " + engine.getRegistry().lookup("S-100").getFileName());

        System.out.println("\n4. Heap Dispatcher (WP3) - Öncelikli İşleme:");
        while (!engine.getDispatcher().isEmpty()) {
            Submission processed = engine.getDispatcher().poll();
            System.out.println("Isleniyor -> ID: " + processed.getStudentId()
                    + " | Flagged: " + processed.isFlagged());
        }

        System.out.println("\n5. Timeline (AVL Tree) Durumu (WP5):");
        System.out.println("Ağactaki Toplam Ödev Sayisi: " + engine.getTimeline().size());

        System.out.println("\nSistem başarıyla test edildi! Bütün modüller birbiriyle entegre çalışıyor.");
    }
}