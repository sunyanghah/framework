package sy.demo.framework.logcollect.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.logcollect.config.LogUtil;

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
        log.info("=========================================================log-collect-1");
        return RP.buildSuccess("success");
    }

    @GetMapping("/error")
    public RP error() throws Exception{
        System.out.println(1/0);
        return RP.buildSuccess("error");
    }

    @GetMapping("/logutil")
    public RP logutil() throws Exception{
        LogUtil.info("-----------------this is logUtil--{}--------","诗圣杜甫");
        return RP.buildSuccess("");
    }
}
