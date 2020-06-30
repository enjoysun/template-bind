package com.myou.autoword.handler.utils;

import java.util.concurrent.*;

/**
 * @Author myou
 * @Date 2020/6/30  11:09 上午
 */
public class TemplateThreadPool {
    private static class GeneratePool {
        public static ExecutorService executorService(int coreThreads, int queues) {
            return new ThreadPoolExecutor(coreThreads,
                    TemplateSystemConfig.SYSTEM_KERNEL,
                    TemplateSystemConfig.KEEP_ALIVE_TIME,
                    TimeUnit.MILLISECONDS,
                    queues == 0 ? new SynchronousQueue<Runnable>() : new LinkedBlockingQueue<Runnable>(),
                    new TemplateThreadFactory(),
                    new TemplateRejectedExecution()
            );
        }
    }

    public static ExecutorService getExecutorService() {
        return GeneratePool.executorService(TemplateSystemConfig.THREAD_POOL_THREAD_COUNT, TemplateSystemConfig.THREAD_POOL_QUEUE_ELEMENT);
    }
}
