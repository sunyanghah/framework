package sy.demo.framework.resource2.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2018/12/28.
 * @author dell
 */
@Component
@Slf4j
public class DelayReceiver1 {

    @RabbitListener(queues = "REDIRECT_QUEUE1")
    @RabbitHandler
    public void redirect(String message) throws Exception {
        log.info("dead message  N长时间 后 消费消息----redirect--1----- {}",message);
    }

    @RabbitListener(queues = "REDIRECT_QUEUE2")
    @RabbitHandler
    public void redirect2(String message) throws Exception {
        log.info("dead message  N长时间 后 消费消息----redirect--2----- {}",message);
    }

}
