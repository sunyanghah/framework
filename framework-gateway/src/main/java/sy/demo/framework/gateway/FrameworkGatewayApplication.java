package sy.demo.framework.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by dell on 2018/11/21.
 * @author dell
 */
@SpringBootApplication(scanBasePackages = {"sy.demo.framework"})
@EnableZuulProxy
@EnableOAuth2Sso
@EnableSwagger2
@EnableFeignClients(basePackages = {"sy.demo.framework.gateway.client"})
public class FrameworkGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(FrameworkGatewayApplication.class,args);
    }
}
