public class NaiveUploadQueue {
//WP2
    private Submission[] data;
    private int size;
    private int capacity;

    public NaiveUploadQueue(int capacity) {
        this.capacity = capacity;
        this.data = new Submission[capacity];
        this.size = 0;
    }
    public boolean enqueue(Submission s) {
        if (size == capacity || s == null) return false;
        data[size++] = s;
        return true;
    }
    public Submission dequeue() {
        if (size == 0) return null;
        Submission first = data[0];
        for (int i = 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        data[--size] = null;
        return first;
    }
    public int size() {
        return size;
    }
}
