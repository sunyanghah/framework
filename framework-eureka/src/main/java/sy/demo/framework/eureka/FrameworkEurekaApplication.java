package sy.demo.framework.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by dell on 2018/11/27.
 * @author dell
 */
@SpringBootApplication
@EnableEurekaServer
public class FrameworkEurekaApplication {

    public static void main(String[] args){
        SpringApplication.run(FrameworkEurekaApplication.class, args);
    }

}
