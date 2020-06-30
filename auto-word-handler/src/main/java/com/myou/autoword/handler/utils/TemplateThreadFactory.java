package com.myou.autoword.handler.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author myou
 * @Date 2020/6/30  11:08 上午
 * 模板线程工厂 to: 线程日志记录
 * poolNumber:线程池序列号
 * threadNumber:线程编号
 */
public class TemplateThreadFactory implements ThreadFactory {
    private static final AtomicLong threadNumber = new AtomicLong(1);
    private static final AtomicLong poolNumber = new AtomicLong(1);
    private final String prefix;
    private final boolean isDaemon;
    private final ThreadGroup threadGroup;

    public TemplateThreadFactory(String prefix, boolean isDaemon) {
        this.prefix = prefix;
        this.isDaemon = isDaemon;
        this.threadGroup = System.getSecurityManager() == null ? Thread.currentThread().getThreadGroup() : System.getSecurityManager().getThreadGroup();
    }

    public TemplateThreadFactory(String prefix) {
        this(prefix, false);
    }

    public TemplateThreadFactory() {
        this(String.format("dispute-mediation-template-pool-%d", poolNumber.getAndIncrement()));
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(this.threadGroup, r, String.format("%s-thread-number-%d", this.prefix, threadNumber.getAndIncrement()));
        thread.setDaemon(this.isDaemon);
        return thread;
    }
}
