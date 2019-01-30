package sy.demo.framework.zipkin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2019/1/29.
 * @author dell
 */
@FeignClient(value = "framework-zipkin2")
public interface Zipkin2Client {

    @GetMapping("/zk2/test")
    RP test() throws Exception;
}
