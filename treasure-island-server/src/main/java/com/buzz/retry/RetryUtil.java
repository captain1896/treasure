package com.buzz.retry;


import java.util.concurrent.TimeUnit;

public class RetryUtil {
    public interface ExecuteFunction {
        void execute() throws Exception;
    }

    public static void retry(int retryCount, long interval, TimeUnit timeUnit, boolean throwIfFail, ExecuteFunction function) throws Exception {
        if (function == null) {
            return;
        }

        for (int i = 0; i < retryCount; i++) {
            try {
                function.execute();
                break;
            } catch (Exception e) {
                if (i == retryCount - 1) {
                    if (throwIfFail) {
                        throw e;
                    } else {
                        break;
                    }
                } else {
                    if (timeUnit != null && interval > 0L) {
                        try {
                            timeUnit.sleep(interval);
                        } catch (InterruptedException e1) {
                            System.out.println("exception occurs");
                        }
                    }
                }
            }

        }

    }

    public static void retry(int retryCount, long interval, TimeUnit timeUnit, ExecuteFunction handler) throws Exception {
        retry(retryCount, interval, timeUnit, false, handler);
    }

    public static void retry(int retryCount, ExecuteFunction function) throws Exception {
        retry(retryCount, -1, null, function);
    }
}
