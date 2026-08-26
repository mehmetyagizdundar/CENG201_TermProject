public class ExamGateEngine {

    private CircularUploadQueue intakeQueue;
    private HeapDispatcher dispatcher;
    private SubmissionRegistry registry;
    private SubmissionTimeLine timeline;
    private RollbackService rollbackService;
    public ExamGateEngine(int queueCapacity, int heapCapacity) {
        this.intakeQueue = new CircularUploadQueue(queueCapacity);
        this.dispatcher = new HeapDispatcher(heapCapacity);
        this.registry = new SubmissionRegistry();
        this.timeline = new SubmissionTimeLine();
        this.rollbackService = new RollbackService(this.registry);
    }
    public boolean acceptUpload(Submission s) {
        if (s == null) return false;
        boolean accepted = intakeQueue.enqueue(s);
        if (accepted) {
            Submission existing = registry.lookup(s.getStudentId());
            if (existing == null) {
                registry.put(s);
            } else {
                VersionRecord oldRecord = new VersionRecord(
                        existing.getFileName(),
                        existing.getSizeKb(),
                        existing.getTimestampMs(),
                        existing.getVersion()
                );
                rollbackService.saveVersion(s.getStudentId(), oldRecord);
                registry.updateVersion(s.getStudentId(), s.getFileName(), s.getSizeKb(), s.getTimestampMs());
            }
            dispatcher.insert(s);
            timeline.insert(s);
        }
        return accepted;
    }
    public CircularUploadQueue getIntakeQueue() {
        return intakeQueue;
    }
    public HeapDispatcher getDispatcher() {
        return dispatcher;
    }
    public SubmissionRegistry getRegistry() {
        return registry;
    }
    public SubmissionTimeLine getTimeline() {
        return timeline;
    }
    public RollbackService getRollbackService() {
        return rollbackService;
    }
}
