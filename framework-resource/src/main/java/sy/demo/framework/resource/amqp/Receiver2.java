package sy.demo.framework.resource.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2018/11/28.
 */
@Slf4j
@Component
@RabbitListener(queues = "hello")
public class Receiver2 {

    @RabbitHandler
    public void handle2(String msg) throws Exception{
        log.info("------this is receiver2------{}-----------",msg);
    }
}
