package sy.demo.framework.resource.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sy.demo.framework.resource.entity.Test;

/**
 * Created by dell on 2018/12/4.
 * @author dell
 */
@Mapper
public interface TestMapper extends BaseMapper<Test> {
}
