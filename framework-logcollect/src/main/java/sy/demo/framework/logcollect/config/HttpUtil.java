package sy.demo.framework.logcollect.config;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 2019/1/26.
 * @author dell
 */
public class HttpUtil {

    public static String getClientIP(HttpServletRequest request) {
        String[] headers = new String[]{"X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};
        String[] arr$ = headers;
        int len$ = headers.length;

        String ip;
        for(int i$ = 0; i$ < len$; ++i$) {
            String header = arr$[i$];
            ip = request.getHeader(header);
            if(!isUnknow(ip)) {
                return getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        return getMultistageReverseProxyIp(ip);
    }

    public static String getMultistageReverseProxyIp(String ip) {
        if(ip != null && ip.indexOf(",") > 0) {
            String[] ips = ip.trim().split(",");
            String[] arr$ = ips;
            int len$ = ips.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String subIp = arr$[i$];
                if(!isUnknow(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }

        return ip;
    }
    public static boolean isUnknow(String checkString) {
        return (null == checkString || "".equals(checkString)) || "unknown".equalsIgnoreCase(checkString);
    }
}
