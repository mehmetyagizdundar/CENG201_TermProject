public class HeapDispatcher {
    private Submission[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 64;
    //WP3
    public HeapDispatcher() {
        this(DEFAULT_CAPACITY);
    }
    public HeapDispatcher(int capacity) {
        this.heap = new Submission[capacity];
        this.size = 0;
    }
    public int compare(Submission a, Submission b) {
        if (a == b) return 0;
        if (a == null) return -1;
        if (b == null) return 1;

        // Kriter 1: Flagged (Özel durumu olanlar her zaman önce)
        if (a.isFlagged() && !b.isFlagged()) return 1;
        if (!a.isFlagged() && b.isFlagged()) return -1;

        // Kriter 2: Erken teslim eden (Timestamp küçük olan kazanır)
        if (a.getTimestampMs() < b.getTimestampMs()) return 1;
        if (a.getTimestampMs() > b.getTimestampMs()) return -1;

        // Kriter 3: Alfabetik ID sırası (Tie-break)
        return b.getStudentId().compareTo(a.getStudentId());
    }

    // =========================================================================
    // 2. INSERT (O(log N))
    // =========================================================================
    public void insert(Submission s) {
        if (s == null) return;
        if (size == heap.length) {
            resize();
        }

        heap[size] = s;
        siftUp(size);
        size++;
    }
    public Submission poll() {
        if (size == 0) return null;

        Submission root = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0) {
            siftDown(0);
        }
        return root;
    }
    public void buildHeap(Submission[] items) {
        if (items == null) return;
        this.heap = new Submission[Math.max(DEFAULT_CAPACITY, items.length * 2)];
        this.size = items.length;
        for (int i = 0; i < items.length; i++) {
            this.heap[i] = items[i];
        }
        for (int i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap[index], heap[parent]) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }
    private void siftDown(int index) {
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;
            if (leftChild < size && compare(heap[leftChild], heap[largest]) > 0) {
                largest = leftChild;
            }
            if (rightChild < size && compare(heap[rightChild], heap[largest]) > 0) {
                largest = rightChild;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }
    private void swap(int i, int j) {
        Submission temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private void resize() {
        Submission[] newHeap = new Submission[heap.length * 2];
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        this.heap = newHeap;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
}
