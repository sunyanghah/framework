package sy.demo.framework.resource.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.resource.service.TestService;

/**
 * Created by dell on 2018/12/5.
 * @author dell
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/save")
    public RP testSave() throws Exception{
        try {
            testService.saveTest();
            return RP.buildSuccess("resource2 新增成功");
        }catch (Exception e){
            e.printStackTrace();
            return RP.buildFailure("resource2 新增失败");
        }
    }
}
