package sy.demo.framework.resource2.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2018/12/27.
 * @author dell
 */
@Component
//@RabbitListener(queues = "topic.message")
public class TopicMessage1Receiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("topicMessageReceiver--------1  : " +msg);
    }

}
