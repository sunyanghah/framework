package sy.demo.framework.jenkinsK8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2018/11/21.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
public class FrameworkJenkinsK8sApplication {
    public static void main(String[] args){
        SpringApplication.run(FrameworkJenkinsK8sApplication.class,args);
    }
}
