package sy.demo.framework.resource2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.resource2.client.DeptClient;
import sy.demo.framework.resource2.dto.InTestDto;
import sy.demo.framework.resource2.service.TestService;

/**
 * Created by dell on 2018/11/22.
 * @author dell
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private DeptClient deptClient;

    @GetMapping("/getInfo")
    public RP test() throws Exception{
        RP rp = deptClient.getTest();
        return rp;
    }

    @PostMapping
    public RP postTest(@RequestBody InTestDto inTestDto) throws Exception{
        log.info("--------------------------{}",inTestDto.getName());
        return RP.buildSuccess("success");
    }

    @PostMapping("/save")
    public RP save() throws Exception{
        testService.saveTest();
        return RP.buildSuccess("插入成功");
    }

    @PostMapping("/save2")
    public RP save2() throws Exception{
        testService.saveTest2();
        return RP.buildSuccess("test2 插入成功");
    }
}
