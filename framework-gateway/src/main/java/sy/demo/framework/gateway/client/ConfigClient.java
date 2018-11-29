package sy.demo.framework.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * Created by dell on 2018/11/29.
 * @author dell
 */
@FeignClient(value = "framework-config")
public interface ConfigClient {

    @PostMapping(value = "/actuator/bus-refresh")
    Map<String,Object> busRefresh() throws Exception;
}
