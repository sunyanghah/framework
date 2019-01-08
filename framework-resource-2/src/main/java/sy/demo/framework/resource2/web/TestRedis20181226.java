package sy.demo.framework.resource2.web;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import sy.demo.framework.common.platform.RP;
import sy.demo.framework.resource2.config.RedConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by dell on 2018/12/26.
 * @author dell
 */
@RestController
@RequestMapping("/testredis")
public class TestRedis20181226 {

    /**
     * 需要手动管理连接池，不然有问题
     */
//    @Autowired
    private Jedis jedis;

    /**
     * spring boot 大法好
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/1")
    public RP test1(@RequestParam("redId")Long redId) throws Exception {
        List<String> list = jedis.hmget(RedConstants.HB_INFO+redId,RedConstants.HB_DEADLINE,RedConstants.HB_TYPE);
        return RP.buildSuccess(list);
    }

    @GetMapping("/2")
    public RP test2(@RequestParam("redId")Long redId) throws Exception {
        List<String> list = redisTemplate.opsForHash().multiGet(RedConstants.HB_INFO+redId, Arrays.asList(RedConstants.HB_DEADLINE,RedConstants.HB_TYPE));
        return RP.buildSuccess(list);
    }

    @GetMapping("/3")
    public RP test3(@RequestParam("redId")Long redId) throws Exception {
        String userAccount = RandomStringUtils.randomAlphanumeric(5);
        redisTemplate.execute(new RedScript<String>(), new StringRedisSerializer(),new StringRedisSerializer(),Arrays.asList(RedConstants.HB_INFO+redId, RedConstants.HB_SIZE,
                RedConstants.HB_MONEY, RedConstants.HB_MAX_PRICE, RedConstants.HB_MIN_PRICE,
                String.valueOf(System.currentTimeMillis()),RedConstants.HB_USER+redId,userAccount),RedConstants.HB_INFO+redId,RedConstants.HB_SIZE,RedConstants.HB_MONEY);
        List<String> list = redisTemplate.opsForHash().multiGet(RedConstants.HB_INFO+redId, Arrays.asList(RedConstants.HB_DEADLINE,RedConstants.HB_TYPE));
        return RP.buildSuccess(list);
    }

    @GetMapping("/4")
    public RP test4(@RequestParam("redId")Long redId) throws Exception {
        String userAccount = RandomStringUtils.randomAlphanumeric(5);
        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setScriptText(
                "local size = tonumber(redis.call('hget', KEYS[1], ARGV[1])) \n" +
                "local money = tonumber(redis.call('hget', KEYS[1], ARGV[1])) \n" +
                "if (size <= 0 or money <= 0)then \n" +
                "return 0 \n" +
                "else \n" +
                "return nil \n" +
                "end");
        defaultRedisScript.setResultType(String.class);


        DefaultRedisScript<String> defaultRedisScript2 = new DefaultRedisScript<>();
        defaultRedisScript2.setScriptText("redis.call('hset',KEYS[1],ARGV[1],ARGV[2])");
        defaultRedisScript.setResultType(String.class);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.execute(defaultRedisScript2, new StringRedisSerializer(),new StringRedisSerializer(),Collections.singletonList("TEST3"+redId),RedConstants.HB_SIZE,"123");
        return RP.buildSuccess("");
    }

    public class RedScript<T> implements RedisScript<T>{

        @Override
        public String getSha1() {
            return "4534a286f70087ff4fd8b4e9ede993e87dd53bc2";
        }

        @Override
        public Class getResultType() {
            return String.class;
        }

        @Override
        public String getScriptAsString() {
            return "math.randomseed(KEYS[6]:reverse():sub(1,6)) \n" +
                    "local function getRandomMoney( remainSize, remainMoney, minPrice, maxPrice) \n" +
                    "if (remainSize <=0 or remainMoney <=0 ) then \n" +
                    "return nil \n" +
                    "end \n" +
                    "if (minPrice == 0 or minPrice == nil) then \n" +
                    "minPrice = 1 \n" +
                    "end \n" +
                    "if (maxPrice == 0 or maxPrice == nil) then \n" +
                    "maxPrice = remainMoney \n" +
                    "end \n" +
                    "local minTemp = remainMoney - (maxPrice * (remainSize-1)) \n" +
                    "local randomMin = minTemp \n" +
                    "if (minTemp < minPrice) then \n" +
                    "randomMin = minPrice \n" +
                    "end \n" +
                    "local maxTemp = remainMoney - (minPrice * (remainSize-1)) \n" +
                    "local randomMax = maxTemp  \n" +
                    "if (maxTemp > maxPrice) then \n" +
                    "randomMax = maxPrice \n" +
                    "end \n" +
                    "local beatMax = remainMoney / remainSize * 2 \n" +
                    "if (randomMax > beatMax) then \n" +
                    "randomMax = beatMax \n" +
                    "end \n" +
                    "return math.floor((math.random()*(randomMax-randomMin) + randomMin)+0.5); \n" +
                    "end \n" +
                    "if redis.call('hexists', KEYS[7], KEYS[8]) ~= 0 then \n" +
                    "return nil\n" +
                    "else\n" +
                    "local size = tonumber(redis.call('hget', ARGV[1], ARGV[2])) \n" +
                    "local money = tonumber(redis.call('hget', ARGV[1], ARGV[3])) \n" +
                    "local maxPrice = tonumber(redis.call('hget', KEYS[1], KEYS[4])) \n" +
                    "local minPrice = tonumber(redis.call('hget', KEYS[1], KEYS[5])) \n" +
                    "if (size <= 0 or money <= 0)then \n" +
                    "return 0 \n" +
                    "else \n" +
                    "local randomMoney = getRandomMoney( size, money,minPrice,maxPrice) \n" +
                    "redis.call('hincrby', KEYS[1], KEYS[2], -1) \n" +
                    "redis.call('hincrbyfloat', KEYS[1], KEYS[3], -randomMoney) \n" +
                    "redis.call('hmset', KEYS[7],KEYS[8],randomMoney) \n" +
                    "return randomMoney \n" +
                    "end\n" +
                    "end";
        }
    }
}
