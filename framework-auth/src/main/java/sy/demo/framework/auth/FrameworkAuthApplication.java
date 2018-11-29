package sy.demo.framework.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2018/11/21.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
public class FrameworkAuthApplication {
    public static void main(String[] args){
        SpringApplication.run(FrameworkAuthApplication.class,args);
    }
}
