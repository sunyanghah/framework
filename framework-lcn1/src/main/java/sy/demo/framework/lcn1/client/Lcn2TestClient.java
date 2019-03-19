package sy.demo.framework.lcn1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.lcn1.client.fallback.Lcn2TestClientFallback;

/**
 * Created by dell on 2019/3/19.
 * @author dell
 */
@FeignClient(value = "framework-lcn2",fallback = Lcn2TestClientFallback.class)
public interface Lcn2TestClient {

    /**
     * 测试
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/lcn2/test",method = RequestMethod.GET)
    RP testAdd() throws Exception;

}
