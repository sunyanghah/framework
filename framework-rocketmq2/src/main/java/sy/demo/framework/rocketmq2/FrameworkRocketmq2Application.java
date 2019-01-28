package sy.demo.framework.rocketmq2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2019/1/24.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
public class FrameworkRocketmq2Application {

        public static void main(String[] args){
            SpringApplication.run(FrameworkRocketmq2Application.class,args);
        }

}
