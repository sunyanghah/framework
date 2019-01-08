package sy.demo.framework.resource2.amqp;

import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by dell on 2018/12/28.
 * @author dell
 */
@Component
public class RabbitConfirmCallback implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private RabbitConnectionFactoryBean rabbitConnectionFactoryBean;

    @PostConstruct
    public void init(){
//        rabbitConnectionFactoryBean.setRequestedChannelMax();
        rabbitTemplate.setConfirmCallback(this);            //指定 ConfirmCallback
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息唯一标识："+correlationData.getId());
        System.out.println("确认结果："+ack);
        System.out.println("失败原因："+cause);
        // TODO 失败时做出反应 (唯一标识里存消息体？)
    }
}
