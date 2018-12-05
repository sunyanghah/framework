package sy.demo.framework.resource2.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sy.demo.framework.common.exception.BusinessException;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.resource2.client.TestClient;
import sy.demo.framework.resource2.entity.Test;
import sy.demo.framework.resource2.entity.Test2;
import sy.demo.framework.resource2.mapper.Test2Mapper;
import sy.demo.framework.resource2.mapper.TestMapper;
import sy.demo.framework.resource2.service.TestService;

/**
 * Created by dell on 2018/12/4.
 * @author dell
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private Test2Mapper test2Mapper;

    @Autowired
    private TestClient testClient;


    @TxTransaction(isStart = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTest() throws Exception{
        Test test = new Test();
        test.setName("resource2-张三");
        testMapper.insert(test);

        RP rp = testClient.testSave();
//        if (!rp.isSuccess()){  如果testClient 服务捕获一切异常。则这里需要手动抛出，让这里的事务回滚
//            throw new BusinessException("resource 事务失败");
//        }
//
        int i = 1/0;

    }


    @Transactional(rollbackFor = Exception.class)
    @TxTransaction
    @Override
    public void saveTest2() throws Exception {
        Test2 test2 = new Test2();
        test2.setName("resource2-王五");
        test2Mapper.insert(test2);
    }
}
