package sy.demo.framework.resource2.web;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import sy.demo.framework.common.platform.IdGenerator;
import sy.demo.framework.common.platform.RP;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/12/19.
 * @author dell
 */
@RestController
@RequestMapping("/when")
public class RedisLua2Controller {


    @Autowired
    private IdGenerator idGenerator;
//    @Autowired
    private Jedis jedis;

    private static String hongbaoInfo = "hbInfo";


    private static String unpackHongBaoScript =
                     "math.randomseed(tostring(os.time()):reverse():sub(1,6))\n" +
                     "local function getRandomMoney( size, money)\n" +
                     "if (size == 1) then\n" +
                     "return math.floor(money * 100 + 0.5) / 100\n" +
                     "end\n" +
                     "local min = 0.01\n" +
                     "local max = money / size * 2\n" +
                     "local randomMoney = math.random() * max;\n" +
                     "if (randomMoney < min) then\n" +
                     "randomMoney = min\n" +
                     "end\n" +
                     "randomMoney = math.floor(randomMoney * 100) / 100\n" +
                     "return randomMoney\n" +
                     "end\n" +
                     "\n"+
                     "local size = redis.call('hget', KEYS[1], KEYS[2])\n" +
                     "local money = redis.call('hget', KEYS[1], KEYS[3])\n" +
                     "if (size <= 0 or money <= 0)then\n" +
                     "return nil\n" +
                     "else\n" +
                     "local randomMoney = getRandomMoney( size, money)\n" +
                     "redis.call('hincrby', KEYS[1], KEYS[2], -1)\n" +
                     "redis.call('hincrbyfloat', KEYS[1], KEYS[2], -randomMoney)\n" +
                     "return randomMoney\n" +
                     "end";

    @GetMapping("/send")
    public RP whenTest(@RequestParam("size")int size, @RequestParam("money")double money) throws Exception{

        //TODO 入库，返回红包id
        String redId = String.valueOf(idGenerator.next());

        Map<String,String> hbMap = new HashMap<>();
        hbMap.put("size",String.valueOf(size));
        hbMap.put("money",String.valueOf(money));
        hbMap.put("deadline",String.valueOf(System.currentTimeMillis()+(1000*60*5)));

        jedis.hmset(hongbaoInfo+redId,hbMap);
        return RP.buildSuccess("");
    }


    /**
     * 红包只要被打开一次，不管是已经抢到过了，红包过期，还是红包已经被抢完了，app端需要记录并显示红包状态为已经打开。
     * @param redId
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/grab")
    public RP grabTest(@RequestParam("redId")String redId,@RequestParam("userId")String userId) throws Exception{

        List<String> list = jedis.hmget(hongbaoInfo+redId,"deadline","size","money");
        if (null != list && list.size() > 0){

            String deadLine = list.get(0);
            int size = Integer.parseInt(list.get(1));
            double money = Double.parseDouble(list.get(2));

            if (System.currentTimeMillis() > Integer.parseInt(deadLine)){
                return RP.buildSuccess("红包已经过期了");
            }

            if (size <= 0 || money <= 0){
                return RP.buildSuccess("手慢了。红包已经被抢完了");
            }

        }

        return RP.buildSuccess("小伙子，赶紧拆");
    }

    @GetMapping("/unpack")
    public RP unpack(@RequestParam("redId")String redId,@RequestParam("userId")String userId) throws Exception{

        List<String> list = jedis.hmget(hongbaoInfo+redId,"deadline");
        if (null != list && list.size() > 0){

            String deadLine = list.get(0);
            if (System.currentTimeMillis() > Integer.parseInt(deadLine)){
                return RP.buildSuccess("红包已经过期了");
            }

        }

        Object object = jedis.eval(unpackHongBaoScript, 3, hongbaoInfo+redId, "size", "money");
        if (null == object){
            return RP.buildSuccess("手慢了，红包被抢完了");
        }else{
            double redMoney = Double.parseDouble(object.toString());
            return RP.buildSuccess("牛逼，你抢了"+redMoney);
        }
    }



}
