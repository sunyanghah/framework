package sy.demo.framework.auth.client.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sy.demo.framework.auth.entity.SysOauthClientDetails;

/**
 * Created by dell on 2018/11/12.
 * @author dell
 */
@Mapper
public interface ClientMapper extends BaseMapper<SysOauthClientDetails> {
}
