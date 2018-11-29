package sy.demo.framework.common.mutual.resource.fallback;

import org.springframework.stereotype.Component;
import sy.demo.framework.common.mutual.resource.DeptClient;
import sy.demo.framework.common.platform.RP;

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
