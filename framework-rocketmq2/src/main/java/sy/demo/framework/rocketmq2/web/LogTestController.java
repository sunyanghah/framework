package sy.demo.framework.rocketmq2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2019/1/26.
 * @author dell
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogTestController {

    @GetMapping("/print")
    public RP test() throws Exception{
        log.info("=========================================================rocketmq-2-1");
        return RP.buildSuccess("success");
    }

    @GetMapping("/error")
    public RP error() throws Exception{
        System.out.println(1/0);
        return RP.buildSuccess("error");
    }

}
