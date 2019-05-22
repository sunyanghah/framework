package sy.demo.framework.lcn1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by dell on 2019/1/28.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
@EnableFeignClients(basePackages = {"sy.demo.framework.lcn1.client"})
@EnableEurekaClient
public class FrameworkLcn1Application {
    public static void main(String[] args){
        SpringApplication.run(FrameworkLcn1Application.class,args);
    }
}
