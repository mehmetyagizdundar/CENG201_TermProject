public class NaiveDispatcher {
    //WP 3
    private Submission[] data;
    private int size;
    public NaiveDispatcher(int capacity) {
        this.data = new Submission[capacity];
        this.size = 0;
    }
    public void insert(Submission s) {
        if (size == data.length || s == null) return;
        data[size++] = s;
    }
    public Submission poll() {
        if (size == 0) return null;
        int bestIndex = 0;
        for (int i = 1; i < size; i++) {
            if (isHigherPriority(data[i], data[bestIndex])) {
                bestIndex = i;
            }
        }
        Submission best = data[bestIndex];
        for (int i = bestIndex; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return best;
    }
    private boolean isHigherPriority(Submission a, Submission b) {
        if (a.isFlagged() && !b.isFlagged()) return true;
        if (!a.isFlagged() && b.isFlagged()) return false;
        if (a.getTimestampMs() < b.getTimestampMs()) return true;
        if (a.getTimestampMs() > b.getTimestampMs()) return false;
        return a.getStudentId().compareTo(b.getStudentId()) < 0;
    }
    public int size() {
        return size;
    }
}