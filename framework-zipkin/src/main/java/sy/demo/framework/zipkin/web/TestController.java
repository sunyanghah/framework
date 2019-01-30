package sy.demo.framework.zipkin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.zipkin.client.Zipkin2Client;

/**
 * Created by dell on 2019/1/28.
 * @author dell
 */
@RestController
@RequestMapping("/zipkin")
public class TestController {

    @Autowired
    private Zipkin2Client zipkin2Client;

    @GetMapping("/test")
    public RP test() throws Exception{
        System.out.println("--------------------");
        return RP.buildSuccess("");
    }

    @GetMapping("/test2")
    public RP test2() throws Exception{
        zipkin2Client.test();
        return RP.buildSuccess("");
    }
}
