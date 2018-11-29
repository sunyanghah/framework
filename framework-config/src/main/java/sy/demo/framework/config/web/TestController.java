package sy.demo.framework.config.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dell on 2018/11/28.
 * @author dell
 */
@RestController
@RequestMapping("/ttt")
public class TestController {

    @GetMapping
    public String test() throws Exception{
        return "dfsfdsd";
    }
}
