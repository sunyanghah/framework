package sy.demo.framework.lcn1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sy.demo.framework.lcn1.client.Lcn2TestClient;
import sy.demo.framework.lcn1.entity.Test;
import sy.demo.framework.lcn1.mapper.TestMapper;
import sy.demo.framework.lcn1.service.TestService;

/**
 * Created by dell on 2019/3/19.
 * @author dell
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService{

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private Lcn2TestClient lcn2TestClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TxTransaction(isStart = true)
    public void addTest() throws Exception {
        Test test = new Test();
        test.setName("孙绿齐1111");
        test.setAge(29);
        testMapper.insert(test);

        lcn2TestClient.testAdd();

        System.out.println(1/0);
    }
}
