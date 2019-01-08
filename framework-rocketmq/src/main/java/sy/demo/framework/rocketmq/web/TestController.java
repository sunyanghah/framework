package sy.demo.framework.rocketmq.web;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2019/1/4.
 */
@RestController
@RequestMapping("/test")
@Log4j2
public class TestController {

    @Autowired
    private DefaultMQProducer producer;

//    @Autowired
//    private TransactionMQProducer producer;

//    @Autowired
//    private TestTransactionListener testTransactionListener;

    @GetMapping("/test")
    public void test(String info) throws Exception {
        Message message = new Message("testTopic", "Tag1", "12345", "rocketmq测试成功".getBytes());
        // 这里用到了这个mq的异步处理，类似ajax，可以得到发送到mq的情况，并做相应的处理
        //不过要注意的是这个是异步的
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("传输成功");
                log.info(JSON.toJSONString(sendResult));
            }
            @Override
            public void onException(Throwable e) {
                log.error("传输失败", e);
            }
        });
    }


    @GetMapping("/redGrabToDb")
    public void redGrabToDb(@RequestParam("data")String data) throws Exception{
        Message message = new Message("testTopic","redGrabToDb","redGrabToDb",data.getBytes());
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("redGrabToDb发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("redGrabToDb发送失败");
            }
        });
    }

    @GetMapping("/redMoneyToUser")
    public void redMoneyToUser(@RequestParam("data")String data) throws Exception{
        Message message = new Message("testTopic","redMoneyToUser","redMoneyToUser",data.getBytes());
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("redMoneyToUser发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("redMoneyToUser发送失败");
            }
        });
    }

    @GetMapping("/myTopic")
    public void myTopic(@RequestParam("data")String data) throws Exception{
        Message message = new Message("myTopic","testTag","testTag",data.getBytes());
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("myTopic发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("myTopic发送失败");
            }
        });
    }

}
