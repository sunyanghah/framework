package sy.demo.framework.auth.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sy.demo.framework.auth.entity.SysUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@Data
public class CustomUserDetails implements UserDetails {

    private SysUser sysUser;

    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return getPrincipal();
    }

    private String getPrincipal(){
        Map<String,Object> principalMap = new HashMap();
        principalMap.put("userId",sysUser.getId());
        principalMap.put("username",sysUser.getUsername());
        return JSON.toJSONString(principalMap);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
