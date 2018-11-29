package sy.demo.framework.resource.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2018/11/28.
 * @author dell
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${test.testMessage}")
    private String message;

    @GetMapping("/message")
    public RP getMessage() throws Exception{
        return RP.buildSuccess(message);
    }
}
