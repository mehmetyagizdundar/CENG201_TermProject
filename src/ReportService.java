public class ReportService {

    public static Submission[] findTopKLate(Submission[] allSubmissions, int k, long deadlineMs) {
        if (allSubmissions == null || k <= 0) return new Submission[0];
        Submission[] minHeap = new Submission[k];
        int heapSize = 0;
        for (Submission s : allSubmissions) {
            if (s.getTimestampMs() > deadlineMs) {
                if (heapSize < k) {
                    minHeap[heapSize] = s;
                    siftUpMin(minHeap, heapSize);
                    heapSize++;
                } else if (s.getTimestampMs() > minHeap[0].getTimestampMs()) {
                    minHeap[0] = s;
                    siftDownMin(minHeap, 0, heapSize);
                }
            }
        }
        Submission[] result = new Submission[heapSize];
        for (int i = 0; i < heapSize; i++) {
            result[i] = minHeap[i];
        }
        return result;
    }
    public static void quickSort(Submission[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Submission[] arr, int low, int high) {
        long pivot = arr[high].getTimestampMs();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].getTimestampMs() <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Submission[] arr, int i, int j) {
        Submission temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int findFirstLateIndex(Submission[] sortedArr, long deadlineMs) {
        if (sortedArr == null || sortedArr.length == 0) return -1;
        int low = 0;
        int high = sortedArr.length - 1;
        int firstLate = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sortedArr[mid].getTimestampMs() > deadlineMs) {
                firstLate = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return firstLate;
    }
    private static void siftUpMin(Submission[] heap, int idx) {
        while (idx > 0) {
            int parent = (idx - 1) / 2;
            if (heap[idx].getTimestampMs() < heap[parent].getTimestampMs()) {
                Submission temp = heap[idx];
                heap[idx] = heap[parent];
                heap[parent] = temp;
                idx = parent;
            } else {
                break;
            }
        }
    }
    private static void siftDownMin(Submission[] heap, int idx, int size) {
        while (idx < size) {
            int left = 2 * idx + 1;
            int right = 2 * idx + 2;
            int smallest = idx;
            if (left < size && heap[left].getTimestampMs() < heap[smallest].getTimestampMs()) {
                smallest = left;
            }
            if (right < size && heap[right].getTimestampMs() < heap[smallest].getTimestampMs()) {
                smallest = right;
            }
            if (smallest != idx) {
                Submission temp = heap[idx];
                heap[idx] = heap[smallest];
                heap[smallest] = temp;
                idx = smallest;
            } else {
                break;
            }
        }
    }
}
