package sy.demo.framework.rocketmq2.consume;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2019/1/24.
 * @author dell
 */
@RocketMQMessageListener(topic = "syTestTopic",consumerGroup = "${spring.application.name}",selectorExpression = "*")
@Component
public class TestConsume implements RocketMQListener<MessageExt>,RocketMQPushConsumerLifecycleListener {

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {

    }

    @Override
    public void onMessage(MessageExt messageExt) {
        String tag = messageExt.getTags();
        if (tag.equals("birthdayTag")){
            String message = new String(messageExt.getBody());
            System.out.println("this is birthdayConsume ------------"+message+"-------------");
        }
        if (tag.equals("newYearTag")){
            String message = new String(messageExt.getBody());
            System.out.println("this is newYearTag ------------"+message+"-------------");
        }

    }
}
