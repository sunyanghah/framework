package sy.demo.framework.lcn2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sy.demo.framework.lcn2.entity.Test;

/**
 * Created by dell on 2019/3/19.
 * @author dell
 */
public interface TestService extends IService<Test> {

    /**
     * test
     * @throws Exception
     */
    void addTest() throws Exception;
}
