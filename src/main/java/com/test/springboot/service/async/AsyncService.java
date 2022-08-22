package com.test.springboot.service.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class AsyncService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("asyncThreadPoolTaskExecutor")
    public void asyncMethod() { //异步方法内部会新启一个线程来执行，这里线程名称为task - *
        sleep();
        logger.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
    }

    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> asyncMethod2() {
        sleep();
        logger.info("AsyncMethod2 异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    public void syncMethod() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
