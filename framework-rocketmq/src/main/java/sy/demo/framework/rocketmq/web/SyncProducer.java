package sy.demo.framework.rocketmq.web;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2018/12/29.
 * @author dell
 */
@RestController
@RequestMapping("/sync")
public class SyncProducer {

    @GetMapping("/1")
    public RP send(@RequestParam("group") String group , @RequestParam("topic")String topic) throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer(group);
        // Specify name server addresses.
        producer.setNamesrvAddr("39.96.46.94:9876;39.105.65.204:9876;39.96.11.112:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 3; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("testTopic" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();

        return RP.buildSuccess("");
    }

}
