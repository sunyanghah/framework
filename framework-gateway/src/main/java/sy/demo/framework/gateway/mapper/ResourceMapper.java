package sy.demo.framework.gateway.mapper;

import org.apache.ibatis.annotations.Mapper;
import sy.demo.framework.common.dto.gateway.OutResourcesDto;

import java.util.List;

/**
 * Created by dell on 2018/11/26.
 * @author dell
 */
@Mapper
public interface ResourceMapper {

    /**
     * 根据角色集合查询可访问的资源列表
     * @param roles
     * @return
     */
    List<OutResourcesDto> getResourcesByRoles(List<String> roles);
}
