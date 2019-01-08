package sy.demo.framework.resource2.amqp;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2018/11/28.
 */
@Slf4j
@Component
public class Receiver1 {

    @RabbitListener(queues = "hello")
    public void handle(Message message, Channel channel) throws Exception{
        log.info("------this is receiver1------{}-----------",new String(message.getBody()));
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
