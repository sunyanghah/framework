package sy.demo.framework.rocketmq2.web;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2019/1/24.
 * @author dell
 */
@RestController
@RequestMapping("/rocketTest")
public class RocketmqTestController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/send/year")
    public RP test() throws Exception{

        Message message = MessageBuilder.withPayload("happy new year !").build();
        SendResult sendResult = rocketMQTemplate.syncSend("syTestTopic:newYearTag",message);
        System.out.println(sendResult.getSendStatus()+"----------------------");
        return RP.buildSuccess("");

    }

    @GetMapping("/send/birthday")
    public RP test2() throws Exception{
        SendResult sendResult = rocketMQTemplate.syncSend("syTestTopic:birthdayTag","happy birthday");
        System.out.println(sendResult.getSendStatus()+"-----------------------");
        return RP.buildSuccess("");
    }

}
