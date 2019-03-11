package sy.demo.framework.exporter.web;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sy.demo.framework.common.platform.RP;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dell on 2019/3/7.
 * @author dell
 */
@RestController
public class TestExporterController {
    @Autowired
    private PrometheusMeterRegistry registry;

//    Gauge randomGauge =Gauge.builder("randomMetrics",0,x -> x.doubleValue()).tag("randomTagKey","randomTagValue").register(new SimpleMeterRegistry());
//    Double randomDouble = Metrics.gauge("randomMetrics", Arrays.asList(new ImmutableTag("randomTagKey","randomTagValue")),0D);

    @GetMapping("/test")
    public RP test() throws Exception{
        return RP.buildSuccess("撒旦飞洒发生");
    }

    @GetMapping("/random")
    public RP random() throws Exception{
        AtomicInteger atomicInteger = new AtomicInteger();
        Gauge gauge = Gauge.builder("randomGauge", atomicInteger, x -> x.doubleValue())
                .tag("gauge", "gauge")
                .description("gauge")
                .register(registry);
        atomicInteger.set(new Random().nextInt());
        return RP.buildSuccess("");
    }

    @GetMapping("/random2")
    public RP random2() throws Exception{
        BigDecimal randomNum = new BigDecimal(Math.random());
        Gauge gauge = Gauge.builder("gauge", randomNum, x -> x.doubleValue())
                .tag("gauge", "gauge")
                .description("gauge")
                .register(new SimpleMeterRegistry());
        randomNum.add(BigDecimal.ONE);
        System.out.println(gauge.value());
        System.out.println(gauge.measure());
        randomNum.add(BigDecimal.ONE);
        System.out.println(gauge.value());
        System.out.println(gauge.measure());
        return RP.buildSuccess("");
    }

    @GetMapping("/random3")
    public RP random3() throws Exception{
        MyRandom myRandom = new MyRandom();
        Gauge gauge = Gauge.builder("gauge", myRandom, x -> x.getRandomNum())
                .tag("gauge", "gauge")
                .description("gauge")
                .register(new SimpleMeterRegistry());
        myRandom.setRandomNum(11D);
        System.out.println(gauge.value());
        System.out.println(gauge.measure());
        myRandom.setRandomNum(22D);
        System.out.println(gauge.value());
        System.out.println(gauge.measure());
        myRandom.setRandomNum(33D);
        System.out.println(gauge.value());
        System.out.println(gauge.measure());
        return RP.buildSuccess("");
    }
    private  MyRandom myRandom = new MyRandom(0D);
    private  Gauge gauge = Gauge.builder("randomMetrics", myRandom, x -> x.getRandomNum())
            .tag("randomTagKey", "randomTagValue")
            .description("randomDescription")
            .register(Metrics.globalRegistry);

    @GetMapping("random4")
    public RP random4() throws Exception{
        Double randomNum = Math.random();
        myRandom.setRandomNum(randomNum);
        return RP.buildSuccess(randomNum);
    }

    @Data
    class MyRandom{
        public MyRandom(){}
        public MyRandom(Double randomNum){
            this.randomNum = randomNum;
        }
        private Double randomNum;
    }
}
