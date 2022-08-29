package com.test.springboot.controller.kafka;

import com.test.springboot.bean.MessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
public class KafkaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @GetMapping("send/{message}")
//    public String send(@PathVariable String message) {
////        this.kafkaTemplate.send("test", message);
//        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("test", message);
//        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//            @Override
//            public void onSuccess(SendResult<String, String> result) {
//                try {
//                    TimeUnit.SECONDS.sleep(4);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                logger.info("成功发送消息：{}，offset=[{}]", message, result.getRecordMetadata().offset());
//            }
//
//            @Override
//            public void onFailure(Throwable ex) {
//                logger.error("消息：{} 发送失败，原因：{}", message, ex.getMessage());
//            }
//        });
//        String result = "";
//        try {
//            result = future.get().getRecordMetadata().topic() + "-"
//                    + future.get().getRecordMetadata().partition() + "-" + future.get().getRecordMetadata().offset();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


    @Autowired
    private KafkaTemplate<String, MessageBean> kafkaTemplate2;

    @GetMapping("sendmessage/{message}")
    public void sendMessage(@PathVariable String message) {
        System.out.println("sendmessage接受");
        this.kafkaTemplate2.send("test", new MessageBean("Kent", message));
    }


}
