package sy.demo.framework.lcn1.client.fallback;

import org.springframework.stereotype.Component;
import sy.demo.framework.common.exception.BusinessException;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.lcn1.client.Lcn2TestClient;

/**
 * Created by dell on 2019/3/19.
 * @author dell
 */
@Component
public class Lcn2TestClientFallback implements Lcn2TestClient{
    @Override
    public RP testAdd() throws Exception {
        throw new BusinessException("test");
    }
}
