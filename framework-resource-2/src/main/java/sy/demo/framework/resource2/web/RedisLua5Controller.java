package sy.demo.framework.resource2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import sy.demo.framework.common.platform.IdGenerator;
import sy.demo.framework.common.platform.RP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/12/20.
 * @author dell
 */
@RestController
@RequestMapping("/lua5")
public class RedisLua5Controller {

    private static double seed = 3;

    @Autowired
    private IdGenerator idGenerator;
//    @Autowired
    private Jedis jedis;

    private static String hb_info = "hbInfo";
    private static String hb_type = "type";
    private static String hb_size = "size";
    private static String hb_money = "money";
    private static String hb_maxPrice = "maxPrice";
    private static String hb_minPrice = "minPrice";
    private static String hb_deadline = "deadline";


    private static String unpackHongBaoScript = "math.randomseed(KEYS[6]:reverse():sub(1,6))\n" +
            "local function getRandomMoney( remainSize, remainMoney, minPrice, maxPrice)\n" +
            "\tif (remainSize <=0 or remainMoney <=0 ) then\n" +
            "\t\treturn nil\n" +
            "\tend\n" +
            "\tif (minPrice == 0 or minPrice == nil) then\n" +
            "\t\tminPrice = 1\n" +
            "\tend\n" +
            "\tif (maxPrice == 0 or maxPrice == nil) then\n" +
            "\t\tmaxPrice = remainMoney\n" +
            "\tend\n" +
            "\tlocal minTemp = remainMoney - (maxPrice * (remainSize-1))\n" +
            "\tlocal randomMin = minTemp\n" +
            "\tif (minTemp < minPrice) then\n" +
            "\t\trandomMin = minPrice\n" +
            "\tend\n" +
            "\tlocal maxTemp = remainMoney - (minPrice * (remainSize-1))\n" +
            "    local randomMax = maxTemp \n" +
            "\tif (maxTemp > maxPrice) then\n" +
            "\t\trandomMax = maxPrice\n" +
            "\tend\n" +
            "\tlocal beatMax = remainMoney / remainSize * 2\n" +
            "\tif (randomMax > beatMax) then\n" +
            "\t\trandomMax = beatMax\n" +
            "\tend\n" +
            "\treturn math.floor((math.random()*(randomMax-randomMin) + randomMin)+0.5);\n" +
            "end\n" +
            "local size = tonumber(redis.call('hget', KEYS[1], KEYS[2]))\n" +
            "local money = tonumber(redis.call('hget', KEYS[1], KEYS[3]))\n" +
            "local maxPrice = tonumber(redis.call('hget', KEYS[1], KEYS[4]))\n" +
            "local minPrice = tonumber(redis.call('hget', KEYS[1], KEYS[5]))\n" +
            "if (size <= 0 or money <= 0)then\n" +
            "return nil\n" +
            "else\n" +
            "local randomMoney = getRandomMoney( size, money,minPrice,maxPrice)\n" +
            "redis.call('hincrby', KEYS[1], KEYS[2], -1)\n" +
            "redis.call('hincrbyfloat', KEYS[1], KEYS[3], -randomMoney)\n" +
            "return randomMoney\n" +
            "end\n";


    @GetMapping("/pack")
    public RP packTest(@RequestParam("size")Integer size, @RequestParam("money")Integer money,
                       @RequestParam("type")String type,
                       @RequestParam(value = "maxPrice",required = false) Integer maxPrice,
                       @RequestParam(value = "minPrice",required = false) Integer minPrice) throws Exception {

        //TODO 入库，调用支付的预下单，返回prePayId
        String redId = String.valueOf(idGenerator.next());

        Map<String,String> hbMap = new HashMap<>();
        hbMap.put(hb_size,String.valueOf(size));
        hbMap.put(hb_money,String.valueOf(money));
        // 红包类型，0 手气 1 普通
        hbMap.put(hb_type,type);
        hbMap.put(hb_maxPrice,String.valueOf(maxPrice==null?0:maxPrice));
        hbMap.put(hb_minPrice,String.valueOf(minPrice==null?0:minPrice));
        hbMap.put(hb_deadline,String.valueOf(System.currentTimeMillis()+(1000*60*5)));
        jedis.hmset(hb_info+redId,hbMap);
        return RP.buildSuccess("包红包成功","测试返回红包ID:"+redId);
    }


    /**
     * 红包只要被打开一次，不管是已经抢到过了，红包过期，还是红包已经被抢完了，app端需要记录并显示红包状态为已经打开。
     * @param redId
     * @return
     * @throws Exception
     */
    @GetMapping("/grab")
    public RP grabTest(@RequestParam("redId")String redId) throws Exception{

        List<String> list = jedis.hmget(hb_info+redId,hb_deadline,hb_size,hb_money);
        if (null != list && list.size() > 0){

            String deadLine = list.get(0);
            int size = Integer.parseInt(list.get(1));
            int money = Integer.parseInt(list.get(2));

            if (System.currentTimeMillis() > Long.parseLong(deadLine)){
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

        List<String> list = jedis.hmget(hb_info+redId,hb_deadline,hb_type);
        if (null != list && list.size() > 0){

            String deadLine = list.get(0);
            if (System.currentTimeMillis() > Long.parseLong(deadLine)){
                return RP.buildSuccess("红包已经过期了");
            }

        }

        //TODO 加上已抢人员判断

        Object object = jedis.eval(unpackHongBaoScript, 6, hb_info+redId, hb_size, hb_money, hb_maxPrice, hb_minPrice,String.valueOf(System.currentTimeMillis()));
        if (null == object){
            return RP.buildSuccess("手慢了，红包被抢完了");
        }else{
            Integer redMoney = Integer.parseInt(object.toString());
            // TODO: 异步入库
            return RP.buildSuccess("牛逼，你抢了"+redMoney);
        }
    }



    public static void main(String[] args) throws Exception{
        List<Integer> red = getRandomRed(3,24,5,0);

        int sum = 0;

        for(Integer d : red){
            sum+=d;
            System.out.println(d);
        }
        System.out.println("----------------------"+sum);
    }

    /**
     * 单位分
     * @param size
     * @param money
     * @param minPrice
     * @param maxPrice
     * @return
     * @throws Exception
     */
    private static List<Integer> getRandomRed(Integer size, Integer money, Integer minPrice, Integer maxPrice) throws Exception{
        List<Integer> redMoneyList = new ArrayList<>();
        for (int i = size;i>0;i--){
            int randomMoney = getRandomMoney(i,money,minPrice,maxPrice);
            redMoneyList.add(randomMoney);
            money -= randomMoney;
        }
        return redMoneyList;
    }

    private static int getRandomMoney(Integer remainSize,Integer remainMoney,Integer minPrice,Integer maxPrice) throws Exception{

        if(remainSize <=0 || remainMoney <=0){
            throw new Exception();
        }

        /**
         * 默认每个红包最小一分
         */
        if(minPrice == 0 || minPrice == null){
            minPrice = 1;
        }
        /**
         * 默认红包最大为全部金额
         * 并不会出现全部金额的情况，下面做了处理
         */
        if(maxPrice == 0 || maxPrice == null){
            maxPrice = remainMoney;
        }

        /**
         * 计算当剩余红包全部装满时，当前红包最少可接受的金额
         */
        Integer minTemp = remainMoney - (maxPrice * (remainSize-1));
        Integer randomMin = minTemp < minPrice ? minPrice : minTemp;

        /**
         * 计算当剩余红包全部塞入最少金额时，当前红包最多可装多少金额
         */
        Integer maxTemp = remainMoney - (minPrice * (remainSize-1));
        Integer randomMax = maxTemp > maxPrice ? maxPrice : maxTemp;

        /**
         * 以下两句代码是为了使每个红包的金额尽量均匀，
         * 不然会出现总是第一个红包特别大的情况
         * 想进一步干预的话 可以改进一下: 做出一个种子 可以人工配置，或动态计算出这个种子值
         * 种子用来控制红包金额的波动情况。
         */
        Integer beatMax = remainMoney / remainSize * 2;
        randomMax = randomMax > beatMax ? beatMax : randomMax;

        return (int)Math.round((Math.random()*(randomMax-randomMin) + randomMin));

    }


}
