package sy.demo.framework.resource.service;

import com.baomidou.mybatisplus.service.IService;
import sy.demo.framework.resource.entity.Test;

/**
 * Created by dell on 2018/12/4.
 * @author dell
 */
public interface TestService extends IService<Test>{
    void saveTest();
}
