package sy.demo.framework.exporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by dell on 2019/1/28.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
public class FrameworkExporterApplication {
    public static void main(String[] args){
        SpringApplication.run(FrameworkExporterApplication.class,args);
    }
}
