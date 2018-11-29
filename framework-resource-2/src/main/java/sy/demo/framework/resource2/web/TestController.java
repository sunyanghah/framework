package sy.demo.framework.resource2.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sy.demo.framework.common.dto.resource2.InTestDto;
import sy.demo.framework.common.mutual.resource.DeptClient;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2018/11/22.
 * @author dell
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

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
}
