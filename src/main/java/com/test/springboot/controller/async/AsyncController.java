package com.test.springboot.controller.async;

import com.test.springboot.service.async.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//开启异步需要在入口类上加上@EnableAsync注解
@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AsyncService asyncService;

    @GetMapping("async")
    public void testAsync() {
        long start = System.currentTimeMillis();
        logger.info("异步方法开始");

        asyncService.asyncMethod();

        logger.info("异步方法结束");
        long end = System.currentTimeMillis();
        logger.info("总耗时：{} ms", end - start);
    }

    @GetMapping("async2")
    public String  testAsync2() {
        long start = System.currentTimeMillis();
        logger.info("异步方法开始");

        Future<String> stringFuture =asyncService.asyncMethod2();

        logger.info("异步方法结束");
        long end = System.currentTimeMillis();
        logger.info("总耗时：{} ms", end - start+" STRINGFUTURE:"+stringFuture);
        String result = null;
        try {
            result = stringFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("sync")
    public void testSync() {
        long start = System.currentTimeMillis();
        logger.info("同步方法开始");

        asyncService.syncMethod();

        logger.info("同步方法结束");
        long end = System.currentTimeMillis();
        logger.info("总耗时：{} ms", end - start);
    }
}
