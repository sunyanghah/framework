package sy.demo.framework.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sy.demo.framework.auth.entity.SysUser;
import sy.demo.framework.auth.entity.SysRole;
import sy.demo.framework.auth.user.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
public class CustomUserService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        SysUser user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        customUserDetails.setSysUser(user);
        customUserDetails.setAuthorities(getAuthority(user.getId()));
        return customUserDetails;
    }

    public Collection<GrantedAuthority> getAuthority(Integer userId){
        final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<SysRole> roleList = userMapper.getRolesByUserId(userId);
        if (null != roleList && roleList.size() > 0){
            roleList.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
        }
        return grantedAuthorities;
    }
}
