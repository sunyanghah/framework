package sy.demo.framework.jenkinsK8s.mapper;

import org.apache.ibatis.annotations.Mapper;
import sy.demo.framework.jenkinsK8s.dto.TestDto;

/**
 * Created by dell on 2019/3/20.
 * @author dell
 */
@Mapper
public interface TestMapper {

    Integer test(TestDto testDto);
}
