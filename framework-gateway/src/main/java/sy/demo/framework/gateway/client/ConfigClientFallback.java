package sy.demo.framework.gateway.client;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by dell on 2018/11/29.
 * @author dell
 */
@Component
public class ConfigClientFallback implements ConfigClient {
    @Override
    public Map<String, Object> busRefresh() throws Exception {
        return null;
    }
}
