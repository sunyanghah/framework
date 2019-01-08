package sy.demo.framework.resource2.web;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.resource2.service.TestService;

import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 2018/12/5.
 * @author dell
 */
@RestController
@RequestMapping("/limiter")
public class RateLimiterTestController {

    @Autowired
    private TestService testService;

    /**
     * 每秒往桶中放10个令牌
     * guava 的 RateLimiter 只适用于 单实例应用。分布式 用redis + lua
     */
    private RateLimiter rateLimiter = RateLimiter.create(10);
    @GetMapping("/one")
    public RP one() throws Exception{
        return RP.buildSuccess("hahhahahhahah");
    }

    @PostMapping("/save2")
    public RP save2() throws Exception{

        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)){
            return RP.buildSuccess("没有抢到，滚蛋吧");
        }
        /**
         * acquire 请求获取到令牌之间等待的时间
         */
        System.out.println("等待时间" + rateLimiter.acquire());
        testService.saveTest2();
        return RP.buildSuccess("插入成功");
    }

}
