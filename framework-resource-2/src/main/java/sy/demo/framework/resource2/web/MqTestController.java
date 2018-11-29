package sy.demo.framework.resource2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2018/11/28.
 * @author dell
 */
@RestController
@RequestMapping("/mq/test")
@Slf4j
public class MqTestController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping
    public RP mqTest() throws Exception{
        for (int i=0;i<5;i++) {
            amqpTemplate.convertAndSend("hello", "this is msg oo");
        }
        return RP.buildSuccess("success");
    }


}
