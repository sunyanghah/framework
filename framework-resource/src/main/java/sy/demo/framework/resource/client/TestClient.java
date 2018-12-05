package sy.demo.framework.resource.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2018/12/5.
 * @author dell
 */
@FeignClient(value = "framework-resource2")
public interface TestClient {

    @PostMapping("/test/save2")
    RP testSave2() throws Exception;
}
