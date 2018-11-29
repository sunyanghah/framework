package sy.demo.framework.resource.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2018/11/22.
 * @author dell
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public RP getTest() throws Exception{
        return RP.buildSuccess("resource2 message","resource2 data");
    }
}
