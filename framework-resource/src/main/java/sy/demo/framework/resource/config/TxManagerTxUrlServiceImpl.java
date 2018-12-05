package sy.demo.framework.resource.config;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * create by lorne on 2017/11/18
 */
@Component
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {


    @Value("${tm.manager.url}")
    private String url;

    @Override
    public String getTxUrl() {
        System.out.println("load tm.manager.url ");
        return url;
    }
}
