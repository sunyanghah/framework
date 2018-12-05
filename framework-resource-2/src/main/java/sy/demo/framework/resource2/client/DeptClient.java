package sy.demo.framework.resource2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2018/11/23.
 * @author dell
 */
@FeignClient(value = "framework-resource")
public interface DeptClient {

    /**
     * test
     * @return
     * @throws Exception
     */
    @GetMapping("/dept")
    RP getTest() throws Exception;
}
