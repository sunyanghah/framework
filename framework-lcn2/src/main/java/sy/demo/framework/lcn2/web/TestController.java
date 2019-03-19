package sy.demo.framework.lcn2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.lcn2.service.TestService;

/**
 * Created by dell on 2019/3/19.
 * @author dell
 */
@RestController
@RequestMapping("/lcn2")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public RP testAdd() throws Exception{
        testService.addTest();
        return RP.buildSuccess("");
    }
}
