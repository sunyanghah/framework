package sy.demo.framework.rocketmq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

/**
 * Created by dell on 2019/1/7.
 * @author dell
 */
@RequestMapping("/redis")
@RestController
public class RedisClusterController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/add")
    public RP add() throws Exception{
        redisTemplate.opsForValue().set("syTest","sddfsdffsdsdfs");
        return RP.buildSuccess("");
    }

    @GetMapping("/get")
    public RP get() throws Exception{
        Object obj = redisTemplate.opsForValue().get("syTest");
        return RP.buildSuccess("",obj);
    }

}
