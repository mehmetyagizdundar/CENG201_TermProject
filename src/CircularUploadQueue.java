public class CircularUploadQueue {
//WP2
    private Submission[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public CircularUploadQueue(int capacity) {
        this.capacity = capacity;
        this.buffer = new Submission[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }


    public boolean enqueue(Submission s) {
        if (isFull() || s == null) {
            return false;
        }

        buffer[tail] = s;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }
    public Submission dequeue() {
        if (isEmpty()) {
            return null;
        }
        Submission item = buffer[head];
        buffer[head] = null;
        head = (head + 1) % capacity;
        size--;
        return item;
    }
    public Submission peek() {
        if (isEmpty()) {
            return null;
        }
        return buffer[head];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }
    public int size() {
        return size;
    }
    public int capacity() {
        return capacity;
    }
}