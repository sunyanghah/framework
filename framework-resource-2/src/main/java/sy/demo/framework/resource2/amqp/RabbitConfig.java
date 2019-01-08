package sy.demo.framework.resource2.amqp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2018/12/28.
 * @author dell
 */
@Configuration
public class RabbitConfig {

    /**
     * 死信队列 交换机标识符
     */
    private static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    private static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";


    /**
     * 声明一个死信队列.
     * x-dead-letter-exchange   对应  死信交换机
     * x-dead-letter-routing-key  对应 死信队列
     *
     * @return the queue
     */
    @Bean
    public Queue deadLetterQueue1() {
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    声明  死信交换机
        args.put(DEAD_LETTER_QUEUE_KEY, "REDIRECT_EXCHANGE");
//       x-dead-letter-routing-key    声明 死信路由键
        args.put(DEAD_LETTER_ROUTING_KEY, "REDIRECT_KEY.1");
        return QueueBuilder.nonDurable("DL_QUEUE1").withArguments(args).build();
    }

    @Bean
    public Queue deadLetterQueue2() {
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    声明  死信交换机
        args.put(DEAD_LETTER_QUEUE_KEY, "REDIRECT_EXCHANGE");
//       x-dead-letter-routing-key    声明 死信路由键
        args.put(DEAD_LETTER_ROUTING_KEY, "REDIRECT_KEY.2");
        return QueueBuilder.nonDurable("DL_QUEUE2").withArguments(args).build();
    }

    /**
     * 定义死信队列转发队列.
     *
     * @return the queue
     */
    @Bean
    public Queue redirectQueue1() {
        return QueueBuilder.nonDurable("REDIRECT_QUEUE1").build();
    }

    @Bean
    public Queue redirectQueue2() {
        return QueueBuilder.nonDurable("REDIRECT_QUEUE2").build();
    }


    @Bean
    public Queue helloQueue(){
        return QueueBuilder.nonDurable("hello").build();
    }
    @Bean
    public Exchange helloExchange(){
        return ExchangeBuilder.directExchange("hello_exchange").durable(false).build();
    }
    @Bean
    public Binding helloBinding(){
        return new Binding("hello",Binding.DestinationType.QUEUE,"hello_exchange","hello_key",null);
    }


    /**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     *
     * @return the exchange
     */
    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.topicExchange("DL_EXCHANGE").durable(false).build();
    }

    @Bean
    public Exchange redirectExchange(){
        return ExchangeBuilder.topicExchange("REDIRECT_EXCHANGE").durable(false).build();
    }

    /**
     * 死信路由通过 DL_KEY 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding deadLetterBinding1() {
        return new Binding("DL_QUEUE1", Binding.DestinationType.QUEUE, "DL_EXCHANGE", "DL_KEY.1", null);

    }

    @Bean
    public Binding deadLetterBinding2() {
        return new Binding("DL_QUEUE2", Binding.DestinationType.QUEUE, "DL_EXCHANGE", "DL_KEY.2", null);
    }

    /**
     * 死信路由通过 KEY_R 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding1() {
        return new Binding("REDIRECT_QUEUE1", Binding.DestinationType.QUEUE, "REDIRECT_EXCHANGE", "REDIRECT_KEY.1", null);
    }

    @Bean
    public Binding redirectBinding2() {
        return new Binding("REDIRECT_QUEUE2", Binding.DestinationType.QUEUE, "REDIRECT_EXCHANGE", "REDIRECT_KEY.2", null);
    }


}
