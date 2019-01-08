package sy.demo.framework.resource2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.resource2.amqp.RabbitConfig;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by dell on 2018/11/28.
 * @author dell
 */
@RestController
@RequestMapping("/mq/test")
@Slf4j
public class MqTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public RP mqTest() throws Exception{
        for (int i=0;i<5;i++) {
            rabbitTemplate.convertAndSend("hello", "this is msg oo");
        }
        return RP.buildSuccess("success");
    }

    @GetMapping("/1")
    public RP test1() throws Exception{
        String msg1 = "I am topic.mesaage msg======";
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
        return RP.buildSuccess("");
    }

    @GetMapping("/deadLetter/1")
    public RP deadLetter1(@RequestParam("p")String p) throws Exception{

        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
            messageProperties.setExpiration("5000");
            return message;
        };
//         向DL_QUEUE 发送消息  10*1000毫秒后过期 形成死信
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY.1", p, messagePostProcessor);
        log.info("[deadLetter 1 发送时间] - [{}]", LocalDateTime.now());
        return RP.buildSuccess("");
    }

    @GetMapping("/deadLetter/2")
    public RP deadLetter2(@RequestParam("p")String p) throws Exception{

        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
//         向DL_QUEUE 发送消息  10*1000毫秒后过期 形成死信
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY.2", p, messagePostProcessor);
        log.info("[deadLetter 2 发送时间] - [{}]", LocalDateTime.now());
        return RP.buildSuccess("");
    }


    @GetMapping("/3")
    public RP test3() throws Exception{
        rabbitTemplate.convertAndSend("REDIRECT_QUEUE", "this is msg osddfsddfsdfdsdfsdfds");
        return RP.buildSuccess("success");
    }

    @GetMapping("/4")
    public RP test4() throws Exception{
//        MessagePostProcessor messagePostProcessor = message -> {
//            MessageProperties messageProperties = message.getMessageProperties();
////            设置编码
//            messageProperties.setContentEncoding("utf-8");
////            设置过期时间10*1000毫秒
//            messageProperties.setExpiration("5000");
//            return message;
//        };
        rabbitTemplate.convertAndSend("hello_exchange","hello_key","this is msg osddfsddfsdfdsdfsdfds",new CorrelationData("4"));
        return RP.buildSuccess("success");
    }


//    @PostMapping("/pro")
//    public RP test5(@RequestBody String name) throws Exception{
//
//        return RP.buildSuccess("","你传进来的是"+name);
//    }

}
