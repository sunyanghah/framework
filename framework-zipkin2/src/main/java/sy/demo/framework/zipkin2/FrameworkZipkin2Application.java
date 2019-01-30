package sy.demo.framework.zipkin2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by dell on 2019/1/28.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
@EnableFeignClients(basePackages = {"sy.demo.framework.zipkin2.client"})
public class FrameworkZipkin2Application {
    public static void main(String[] args){
        SpringApplication.run(FrameworkZipkin2Application.class,args);
    }
}
