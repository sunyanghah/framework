//package sy.demo.framework.config.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
///**
// * Created by dell on 2018/11/9.
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * spring5.0以后，以及把spring.security.basic.enabled给去掉了，
//     所以我们推荐我们去创建一个自己的WebSecurityConfig类去实现WebSecurityConfigAdapter。
//     高版本的丢弃了
//     *
//     * security:
//     *   basic:
//     *    enabled: true
//     *
//     * 配置，应该使用以下方式开启
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Configure HttpSecurity as needed (e.g. enable http basic).
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//        http.csrf().disable();
//        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,
//        // 如果是form方式,不能使用url格式登录
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//
//    }
//
//}
//
