package sy.demo.framework.resource.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dell on 2018/11/28.
 * @author dell
 */
@Configuration
public class AmqpConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
    
    @Bean
    public Queue userQueue() {
        return new Queue("user");
    }
}
