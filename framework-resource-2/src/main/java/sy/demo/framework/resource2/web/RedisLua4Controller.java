package sy.demo.framework.resource2.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2018/12/20.
 */
public class RedisLua4Controller {


    public static void main(String[] args) throws Exception{
        List<Double> red = getRandomRed(3,10,0,0);

        double sum = 0;

        for(Double d : red){
            sum+=d;
            System.out.println(d);
        }
        System.out.println("----------------------"+sum);
    }

    private static List<Double> getRandomRed(int size, double money, double minPrice, double maxPrice) throws Exception{
        List<Double> redMoneyList = new ArrayList<>();
        for (int i = size;i>0;i--){
            double randomMoney = getRandomMoney(i,money,minPrice,maxPrice);
            redMoneyList.add(randomMoney);
            money -= randomMoney;
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

        double minTemp = remainMoney - (maxPrice * (remainSize-1));
        double randomMin = minTemp < minPrice ? minPrice : minTemp;

        double maxTemp = remainMoney - (minPrice * (remainSize-1));
        double randomMax = maxTemp > maxPrice ? maxPrice : maxTemp;

        return (double)Math.round((Math.random()*(randomMax-randomMin) + randomMin) * 100) / 100;

    }

}
