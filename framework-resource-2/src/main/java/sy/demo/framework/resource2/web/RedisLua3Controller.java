package sy.demo.framework.resource2.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018/12/20.
 */
public class RedisLua3Controller {


    public static void main(String[] args) throws Exception{
        List<Double> red = getRandomRed(3,10,0,0);
        for(Double d : red){
            System.out.println(d);
        }
    }

    private static List<Double> getRandomRed(int size, double money, double minPrice, double maxPrice) throws Exception{
        List<Double> redMoneyList = new ArrayList<>();
        for (int i = size;i>0;i--){
            double randomMoney = getRandomMoney(i,money,minPrice,maxPrice);
            redMoneyList.add(randomMoney);
            money = new BigDecimal(money).subtract(new BigDecimal(randomMoney)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return redMoneyList;
    }

    private static double getRandomMoney(int remainSize,double remainMoney,double minPrice,double maxPrice) throws Exception{

        if(remainSize <=0 || remainMoney <=0){
            throw new Exception();
        }
        if(minPrice == 0){
            minPrice = 0.01;
        }
        if(maxPrice == 0){
            maxPrice = remainMoney;
        }

        BigDecimal remainMoneyBd = new BigDecimal(remainMoney);
        BigDecimal minPriceBd = new BigDecimal(minPrice);
        BigDecimal maxPriceBd = new BigDecimal(maxPrice);

        BigDecimal minTemp = remainMoneyBd.subtract(maxPriceBd.multiply(new BigDecimal(remainSize-1)));
        BigDecimal randomMin = minTemp.compareTo(BigDecimal.ZERO) == -1||minTemp.compareTo(BigDecimal.ZERO) == 0 ? minPriceBd : minTemp;

        BigDecimal maxTemp = remainMoneyBd.subtract(minPriceBd.multiply(new BigDecimal(remainSize-1)));
        BigDecimal randomMax = maxTemp.compareTo(maxPriceBd) == 1 ? maxPriceBd : maxTemp;

        return Math.floor((new BigDecimal(Math.random()).multiply(randomMax.subtract(randomMin)).add(randomMin)).multiply(new BigDecimal(100)).doubleValue()) / 100;

    }

}
