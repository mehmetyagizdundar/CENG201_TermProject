public class Submission {
private String studentId;
private String fileName;
private int sizeKb;
private long timestampMS;
private int version;
private boolean flagged;
public Submission(String studentId, String fileName, int sizeKb, long timestampMS, int version, boolean flagged)
    {
    this.studentId = studentId;
    this.fileName = fileName;
    this.sizeKb = sizeKb;
    this.timestampMS = timestampMS;
    this.version = 1;
    this.flagged = false;
    }
}
