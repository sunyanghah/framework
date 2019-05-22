package sy.demo.framework.rocketmq2.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell on 2019/1/24.
 * @author dell
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public RP test() throws Exception{
        log.info("this is my log hahahh");
        return RP.buildSuccess("");
    }

    public static void main(String[] args) throws Exception{
//        // 按指定模式在字符串查找
//        String line = "2019-01-25 17:33:06.726  INFO 6308 --- [nio-8016-exec-1] s.d.f.rocketmq2.web.TestController       : this is my log hahahh";
//        String pattern = "^*---*";
//
//        // 创建 Pattern 对象
//        Pattern r = Pattern.compile(pattern);
//
//        // 现在创建 matcher 对象
//        Matcher m = r.matcher(line);
//        if (m.find( )) {
//            System.out.println("Found value: " + m.group(0) );
//            System.out.println("Found value: " + m.group(1) );
//            System.out.println("Found value: " + m.group(2) );
//            System.out.println("Found value: " + m.group(3) );
//        } else {
//            System.out.println("NO MATCH");
//
        System.out.println(new Integer("-2"));
    }
}
