package sy.demo.framework.jenkinsK8s.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2019/3/11.
 * @author dell
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public RP test() throws Exception{
        return RP.buildSuccess("ssdfsdfsfs");
    }
}
