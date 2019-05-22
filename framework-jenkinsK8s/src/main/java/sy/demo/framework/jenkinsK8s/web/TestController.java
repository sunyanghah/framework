package sy.demo.framework.jenkinsK8s.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.jenkinsK8s.dto.TestDto;
import sy.demo.framework.jenkinsK8s.mapper.TestMapper;

import javax.validation.Valid;

/**
 * Created by dell on 2019/3/11.
 * @author dell
 */
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @PostMapping("/testPage")
    public RP test(@Valid @RequestBody TestDto testDto) throws Exception{
        System.out.println(111);
        return RP.buildSuccess("ssdfsdfsfs");
    }


}
