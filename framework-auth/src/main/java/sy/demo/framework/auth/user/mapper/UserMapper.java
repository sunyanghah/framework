package sy.demo.framework.auth.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sy.demo.framework.auth.entity.SysRole;
import sy.demo.framework.auth.entity.SysUser;

import java.util.List;


/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户账号获取用户信息
     * @param username
     * @return
     */
    SysUser getUserByUsername(String username);

    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
    List<SysRole> getRolesByUserId(Integer userId);
}
