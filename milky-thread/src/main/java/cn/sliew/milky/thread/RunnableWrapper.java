package cn.sliew.milky.thread;

public interface RunnableWrapper extends Runnable {

    @Override
    default void run() {
        try {
            doRun();
        } catch (Exception t) {
            onFailure(t);
        } finally {
            onAfter();
        }
    }

    /**
     * This method has the same semantics as {@link Runnable#run()}
     *
     * @throws InterruptedException if the run method throws an InterruptedException
     */
    void doRun() throws Exception;

    /**
     * This method is called in a finally block after successful execution
     * or on a rejection.
     *
     * @see MilkyThreadPoolExecutor#execute(java.lang.Runnable)
     */
    default void onAfter() {
        // nothing by default
    }

    /**
     * This method is invoked for all exception thrown by {@link #doRun()}
     */
    void onFailure(Exception e);

    /**
     * This should be executed if the thread-pool executing this action rejected the execution.
     * The default implementation forwards to {@link #onFailure(Exception)}
     *
     * @see MilkyThreadPoolExecutor#execute(java.lang.Runnable)
     */
    default void onRejection(Exception e) {
        onFailure(e);
    }

    /**
     * Should the runnable force its execution in case it gets rejected?
     */
    default boolean isForceExecution() {
        return false;
    }

}
