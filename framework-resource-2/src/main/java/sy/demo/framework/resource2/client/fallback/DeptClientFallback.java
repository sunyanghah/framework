package sy.demo.framework.resource2.client.fallback;

import org.springframework.stereotype.Component;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.resource2.client.DeptClient;

/**
 * Created by dell on 2018/11/23.
 * @author dell
 */
@Component
public class DeptClientFallback implements DeptClient {
    @Override
    public RP getTest() throws Exception {
        return null;
    }
}
