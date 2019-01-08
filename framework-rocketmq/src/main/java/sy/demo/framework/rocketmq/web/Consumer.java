package sy.demo.framework.rocketmq.web;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

import java.util.List;

/**
 * Created by dell on 2018/12/29.
 * @author dell
 */
@RestController
@RequestMapping("/consumer")
public class Consumer {

    @GetMapping("/1")
    public RP consumer(@RequestParam("group") String group , @RequestParam("topic")String topic) throws Exception{
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group);

        // Specify name server addresses.
        consumer.setNamesrvAddr("39.96.46.94:9876;39.105.65.204:9876;39.96.11.112:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("testTopic", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
//                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                System.out.println(new String(msgs.get(0).getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");

        return RP.buildSuccess("");
    }


}
