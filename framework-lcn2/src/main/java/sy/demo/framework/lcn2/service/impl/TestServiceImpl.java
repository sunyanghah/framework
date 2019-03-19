package sy.demo.framework.lcn2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sy.demo.framework.lcn2.entity.Test;
import sy.demo.framework.lcn2.mapper.TestMapper;
import sy.demo.framework.lcn2.service.TestService;

/**
 * Created by dell on 2019/3/19.
 * @author dell
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    //@TxTransaction
    public void addTest() throws Exception {
        Test test = new Test();
        test.setName("孙绿齐2222");
        test.setAge(30);
        testMapper.insert(test);
    }
}
