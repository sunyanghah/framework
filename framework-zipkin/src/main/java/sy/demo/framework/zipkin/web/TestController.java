package sy.demo.framework.zipkin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2019/1/28.
 * @author dell
 */
@RestController
@RequestMapping("/zipkin")
public class TestController {

    @GetMapping("/test")
    public RP test() throws Exception{
        System.out.println("--------------------");
        return RP.buildSuccess("");
    }
}
