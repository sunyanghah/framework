//package sy.demo.framework.resource2.amqp;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.listener.Topic;
//
///**
// * Created by dell on 2018/11/28.
// * @author dell
// */
//@Configuration
//public class AmqpConfig {
//
//    @Bean
//    public Queue helloQueue() {
//        return new Queue("hello");
//    }
//
//    @Bean
//    public Queue userQueue() {
//        return new Queue("user");
//    }
//
//
//    //===============以下是验证topic Exchange的队列==========
//    @Bean
//    public Queue queueMessage1() {
//        return new Queue("topic.message");
//    }
//
//    @Bean
//    public Queue queueMessage2() {
//        return new Queue("topic.messages");
//    }
//    //===============以上是验证topic Exchange的队列==========
//
//    @Bean
//    TopicExchange exchange() {
//        //交换机 交换机名称
//        return new TopicExchange("exchange");
//    }
//
//    @Bean
//    TopicExchange helloExchange(){
//        return new TopicExchange("helloExchange");
//    }
//
//    @Bean
//    Binding bindingHelloExchange(){
//        return BindingBuilder.bind(helloQueue()).to(helloExchange()).with("hello");
//    }
//
//    /**
//     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
//     * @param queueMessage1
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessage(Queue queueMessage1, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage1).to(exchange).with("topic.message");
//    }
//
//    /**
//     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
//     * @param queueMessage2
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessages(Queue queueMessage2, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage2).to(exchange).with("topic.#");
//    }
//
//
//
//}
