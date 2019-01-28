package sy.demo.framework.logcollect.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 2019/1/26.
 * @author dell
 */
@Slf4j
public class LogUtil {

    public static String getUser(String format) {
        StringBuilder formater = new StringBuilder();
        Integer userId = 123123;
        if(null != userId) {
            formater.append("[用户").append("[").append(userId).append("]]");
        }

        String ip = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null != attributes && null != attributes.getRequest()) {
            HttpServletRequest request = attributes.getRequest();
            ip = HttpUtil.getClientIP(request);
        }

        if(null != ip && !"".equals(ip)) {
            formater.append("[requestIp").append("[").append(ip).append("]]");
        }

        formater.append(format);
        return formater.toString();
    }

    public static void info(String format, Object... arguments){
        log.info(getUser(format),arguments);
    }
}
