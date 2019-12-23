package top.maluo.ms.us.conf;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @className: AsyncConf
 * @Description: TODO
 * @author: xieed
 * @date: 2019/12/23 17:08
 * @version: v1.0
 */
@Configuration
@EnableAsync
public class AsyncConf implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程
        taskExecutor.setCorePoolSize(2);
        // 最大线程数
        taskExecutor.setMaxPoolSize(4);
        // 队列大小
        taskExecutor.setQueueCapacity(10);
        // 前缀
        taskExecutor.setThreadNamePrefix( "async-service-" );
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);

        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        //直接返回 AsyncUncaughtExceptionHandler 对象
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
