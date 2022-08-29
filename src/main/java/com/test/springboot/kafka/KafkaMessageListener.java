package com.test.springboot.kafka;

import com.test.springboot.bean.MessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @KafkaListener(topics = "test", groupId = "test-consumer")
//    public void listen(String message) {
//        logger.info("接收到消息啦！！！！！listen: {}", message);
//    }
//
//    @KafkaListener(topics = "test", groupId = "test-consumer")
//    public void listen2(@Payload String message,
//                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        logger.info("接收消息listen2: {}，partition：{}", message, partition);
//    }



//    @KafkaListener(groupId = "test-consumer",
//            topicPartitions = @TopicPartition(topic = "test", partitions = { "0", "1" }))
//    @KafkaListener(groupId = "test-consumer", topicPartitions = @TopicPartition(topic = "test",
//            partitionOffsets = {
//                    @PartitionOffset(partition = "0", initialOffset = "0")
//            }))
//    public void listen3(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//        logger.info("接收消息listen3: {}，partition：{}", message, partition);
//    }


    @KafkaListener(topics = "test", groupId = "test-consumer")
    public void listen4(MessageBean message) {
        logger.info("接收消息MessageBean: {}", message);
    }


}