public class RollbackService {
    private SubmissionRegistry registry;
    private static class StackNode {
        String studentId;
        VersionStack stack;
        StackNode next;
        StackNode(String studentId, VersionStack stack, StackNode next) {
            this.studentId = studentId;
            this.stack = stack;
            this.next = next;
        }
    }
    private StackNode[] map;
    public RollbackService(SubmissionRegistry registry) {
        this.registry = registry;
        this.map = new StackNode[64];
    }
    private VersionStack getStack(String studentId) {
        int index = Math.abs(studentId.hashCode()) % map.length;
        StackNode current = map[index];
        while (current != null) {
            if (current.studentId.equals(studentId)) {
                return current.stack;
            }
            current = current.next;
        }
        VersionStack newStack = new VersionStack();
        map[index] = new StackNode(studentId, newStack, map[index]);
        return newStack;
    }
    public void saveVersion(String studentId, VersionRecord oldVersion) {
        getStack(studentId).push(oldVersion);
    }
    public void rollback(String studentId) {
        Submission current = registry.lookup(studentId);
        if (current == null) {
            System.out.println("Student not found!");
            return;
        }
        VersionStack stack = getStack(studentId);
        if (stack.isEmpty()) {
            System.out.println("There is no earlier version!");
            return;
        }
        VersionRecord oldVersion = stack.pop();
        current.restoreFile(
                oldVersion.getFileName(),
                oldVersion.getSizeKb(),
                oldVersion.getTimestampMs(),
                oldVersion.getVersion()
        );
    }
}
