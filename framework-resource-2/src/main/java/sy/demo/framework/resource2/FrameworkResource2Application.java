package sy.demo.framework.resource2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by dell on 2018/11/22.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
@EnableFeignClients(basePackages = {"sy.demo.framework.common.mutual","sy.demo.framework.resource2.client"})
public class FrameworkResource2Application {
    public static void main(String[] args){
        SpringApplication.run(FrameworkResource2Application.class,args);
    }
}
