package sy.demo.framework.gateway.web;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.imageio.plugins.common.ReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.gateway.client.ConfigClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2018/11/29.
 * @author dell
 */
@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigActuatorController {

    @Autowired
    private ConfigClient configClient;

    @PostMapping("/refresh")
    public RP configRefresh(@RequestBody Map<String,Object> requestMap, HttpServletRequest request) throws Exception{
        String jackSon = new ObjectMapper().writeValueAsString(requestMap);
        String valiSign = "sha1="+getSignature(jackSon,"webhookssecret");
        String sign = request.getHeader("X-Hub-Signature");

        if (sign != null && sign.equals(valiSign)){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        configClient.busRefresh();
                    } catch (Exception e) {
                        log.error("{}",e);
                    }
                }
            });
            thread.start();
        }else{
            return RP.buildFailure("签名验证失败");
        }

        return RP.buildSuccess("已请求刷新");
    }


    public String getSignature(String data, String key) throws Exception {

        byte[] keyBytes = key.getBytes();
        // 根据给定的字节数组构造一个密钥。
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(data.getBytes());

        String hexBytes = byte2hex(rawHmac);
        return hexBytes;
    }

    private String byte2hex(final byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
            stmp = (java.lang.Integer.toHexString(b[n] & 0xFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

}
