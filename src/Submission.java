public class Submission {
private String studentId;
private String fileName;
private int sizeKb;
private long timestampMs;
private int version;
private boolean flagged;
public Submission(String studentId, String fileName, int sizeKb, long timestampMs)
    {
    this.studentId = studentId;
    this.fileName = fileName;
    this.sizeKb = sizeKb;
    this.timestampMs= timestampMs;
    this.version = 1;
    this.flagged = false;
    }
    public String getStudentId()
    {
        return studentId;
    }
    public String getFileName()
    {
        return fileName;
    }
    public int getSizeKb()
    {
        return sizeKb;
    }
    public int getVersion()
    {
        return version;
    }
    public boolean isFlagged()
    {
        return flagged;
    }
    public void setFlagged(boolean flagged)
    {
        this.flagged = flagged;
    }
    public void replaceFile(String fileName, int sizeKb, long timestampMs)
    {
    this.fileName = fileName;
    this.sizeKb = sizeKb;
    this.timestampMs = timestampMs;
    this.version++;
    }
}
