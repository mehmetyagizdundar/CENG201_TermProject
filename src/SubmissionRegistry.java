public class SubmissionRegistry {
    public static class Node //Work Package 1
    {
        Submission data;
        Node next;
        Node (Submission data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    }
    private Node[] table; //başlangıç verisi
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75; //O(1) da çalışsın collusion olmasın

    public SubmissionRegistry()
    {
        this.table = new Node[INITIAL_CAPACITY];
        this.size = 0;
    }

    private int hash(String key)
    {
        if(key == null) return 0;
        int h = key.hashCode() % table.length;
        if(h < 0)
        {
            h += table.length; // negatif değer gelme durumu varsa pozitife döndür
        }
        return h;
    }

    public void put(Submission s)
    {
        if(s == null) return;

        int index = hash(s.getStudentId());
        Node current = table[index];

        while (current != null)
        {
            if (current.data.getStudentId().equals(s.getStudentId()))
            {
                return;
            }
            current = current.next;
        }

        table[index] = new Node(s, table[index]);
        size++;

        if ((double) size / table.length >= LOAD_FACTOR)
        {
            resize();
        }
    }

    public Submission lookup(String studentId)
    {
        if (studentId == null) return null;

        int index = hash(studentId);
        Node current = table[index];

        while (current != null)
        {
            if (current.data.getStudentId().equals(studentId))
            {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public int updateVersion(String studentId, String fileName, int sizeKb, long timestampMs)
    {
        Submission sub = lookup(studentId);
        if (sub == null)
        {
            return -1;
        }
        sub.replaceFile(fileName, sizeKb, timestampMs);
        return sub.getVersion();
    }

    public int size()
    {
        return this.size;
    }

    private void resize()
    {
        Node[] oldTable = table;
        table = new Node[oldTable.length * 2];
        size = 0;

        for (Node head : oldTable)
        {
            Node current = head;
            while (current != null)
            {
                put(current.data);
                current = current.next;
            }
        }
    }

    public void printCollisionReps()
    {
        System.out.println("\n--- Hash Table Bucket & Collision çıktısı ---");
        int collisionCount = 0;
        int maxChainLength = 0;
        int usedBuckets = 0;

        for (int i = 0; i < table.length; i++)
        {
            Node current = table[i];
            int chainLnt= 0;

            if (current != null)
            {
                usedBuckets++;
                System.out.print("Bucket [" + i + "]: ");
                while (current != null)
                {
                    System.out.print(current.data.getStudentId() + " -> ");
                    chainLnt++;
                    current = current.next;
                }
                System.out.println("null");

                if (chainLnt > 1)
                {
                    collisionCount += (chainLnt - 1);
                }
                if (chainLnt > maxChainLength)
                {
                    maxChainLength = chainLnt;
                }
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("Toplam Kapasite   : " + table.length);
        System.out.println("Kayıtlı Elemanlar  : " + size);
        System.out.println("Dolu Bucket'larımız : " + usedBuckets);
        System.out.println("Toplam Çakışma Sayısı  : " + collisionCount);
        System.out.println("Zincirin toplam uzunluğu   : " + maxChainLength);
        System.out.println("--------------------------------------------\n");
    }
}