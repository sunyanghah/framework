package sy.demo.framework.resource2.service;

import com.baomidou.mybatisplus.service.IService;
import sy.demo.framework.resource2.entity.Test;

/**
 * Created by dell on 2018/12/4.
 * @author dell
 */
public interface TestService extends IService<Test>{
    void saveTest() throws Exception;

    void saveTest2() throws Exception;
}
