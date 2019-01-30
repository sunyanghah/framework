package sy.demo.framework.zipkin2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2019/1/29.
 * @author dell
 */
@RestController
@RequestMapping("/zk2")
@Slf4j
public class TestController {

    @GetMapping("/test")
    public RP test() throws Exception{
        log.info("this is zk2 test------------------------");
        return RP.buildSuccess("");
    }
}
