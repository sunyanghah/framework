package sy.demo.framework.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by dell on 2018/11/27.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = "sy.demo.framework")
@EnableConfigServer
public class FrameworkConfigApplication {

    public static void main(String[] args){
        SpringApplication.run(FrameworkConfigApplication.class, args);
    }

}
