package sy.demo.framework.resource2.web;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import sy.demo.framework.common.platform.IdGenerator;
import sy.demo.framework.common.platform.RP;

import java.util.*;

/**
 * Created by dell on 2018/12/5.
 * @author dell
 */
@RestController
@RequestMapping("/pre")
@Slf4j
public class RedisLuaController {

//    @Autowired
    private Jedis jedis;

    @Autowired
    private IdGenerator idGenerator;

    static String tryGetHongBaoScript =
                      "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"
                    + "return nil\n"
                    + "else\n"
                    + "local hongBao = redis.call('rpop', KEYS[1]);\n"
                    + "if (not hongBao) then\n"
                    + "return 0;"
                    + "end\n"
                    + "end\n"
                    + "return 1";

    static String unpackHongBaoScript =
                     "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"
                    + "return nil\n"
                    + "else\n"
                    + "local hongBao = redis.call('rpop', KEYS[1]);\n"
                    + "if hongBao then\n"
                    + "local x = cjson.decode(hongBao);\n"
                    + "x['userId'] = KEYS[4];\n"
                    + "local re = cjson.encode(x);\n"
                    + "redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"
                    + "redis.call('lpush', KEYS[2], re);\n"
                    + "return re;\n"
                    + "end\n"
                    + "end\n"
                    + "return 0";

    static String hongBaoList = "hbList";
    static String hongBaoConsumedList = "hbConsumedList";
    static String hongBaoConsumedMap = "hbConsumedMap";
    static String hongBaoDeadline = "hbDeadline";

    @GetMapping("/grab")
    public RP grabTest(@RequestParam("userId")String userId,@RequestParam("redId") String redId) throws Exception {

        String deadLine = jedis.get(hongBaoDeadline+redId);
        if (System.currentTimeMillis() > Integer.parseInt(deadLine)){
            return RP.buildSuccess("红包已经过期了");
        }

        Object object = jedis.eval(tryGetHongBaoScript, 4, hongBaoList+redId, hongBaoConsumedList+redId, hongBaoConsumedMap+redId, userId);
        if (null == object) {
            return RP.buildSuccess("你已经抢过红包，不要贪");
        }else{
            try {
                Integer zero = Integer.parseInt(object.toString());
                if (zero.compareTo(0) == 0){
                    return RP.buildSuccess("红包已经被抢光了，手慢了");
                }
            }catch (Exception e){

            }
        }
        log.info("userId:{}--------{}",userId,object);
        return RP.buildSuccess("快去拆吧，小伙子");
    }

    @GetMapping("/unpack")
    public RP unpackTest(@RequestParam("userId")String userId,@RequestParam("redId")String redId) throws Exception{
        String deadLine = jedis.get(hongBaoDeadline+redId);
        if (System.currentTimeMillis() > Integer.parseInt(deadLine)){
            return RP.buildSuccess("红包已经过期了");
        }
        Object object = jedis.eval(unpackHongBaoScript, 4, hongBaoList+redId, hongBaoConsumedList+redId, hongBaoConsumedMap+redId, userId);
        if (null == object) {
            return RP.buildSuccess("你已经抢过红包，不要贪");
        }else{
            try {
                Integer zero = Integer.parseInt(object.toString());
                if (zero.compareTo(0) == 0){
                    return RP.buildSuccess("红包已经被抢光了，手慢了");
                }
            }catch (Exception e){

            }
        }
        //TODO 异步入库
        return RP.buildSuccess("");
    }


    @GetMapping("/send")
    public RP sendTest(@RequestParam("size") int size,@RequestParam("money")double money) throws Exception {

        String redId = String.valueOf(idGenerator.next());
        // 模拟：一个红包预分配成10个后，扔入redis
        List<Double> redMoneyList = getRandomRed(size,money);
        for (Double redMoney : redMoneyList){
            Map<String,Object> object = new HashMap<>();
            object.put("id",idGenerator.next());
            object.put("money", redMoney);
            jedis.lpush(hongBaoList+redId, JSON.toJSONString(object));
        }
        jedis.set(hongBaoDeadline+redId,String.valueOf(System.currentTimeMillis()+(1000*60*5)));
        return RP.buildSuccess("发送成功:"+redId);
    }


    public static void main(String[] args) throws Exception{
        List<Double> reds = getRandomRed(10,10);
        double sum = 0;
        for (Double d : reds){
            sum += d;
            System.out.println(d);
        }
        System.out.println("------------------"+sum);
    }

    private static List<Double> getRandomRed(int size,double money){
        List<Double> redMoneyList = new ArrayList<>();
        for (int i = size;i>0;i--){
            double randomMoney = getRandomMoney(i,money);
            redMoneyList.add(randomMoney);
            money -= randomMoney;
        }
        return redMoneyList;
    }

    private static double getRandomMoney(int size,double remainMoney) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (size == 1) {
            return (double) Math.round(remainMoney * 100) / 100;
        }
        Random r     = new Random();
        double min   = 0.01; //
        double max   = remainMoney / size * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        return money;
    }


}
