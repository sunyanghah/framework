package sy.demo.framework.resource.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sy.demo.framework.resource.entity.Test;
import sy.demo.framework.resource.mapper.TestMapper;
import sy.demo.framework.resource.service.TestService;

/**
 * Created by dell on 2018/12/4.
 * @author dell
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService {

    @Autowired
    private TestMapper testMapper;


    @TxTransaction(isStart = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTest() {
        Test test = new Test();
        test.setName("resource-李四");
        testMapper.insert(test);
//        int i = 1/0;
    }
}
