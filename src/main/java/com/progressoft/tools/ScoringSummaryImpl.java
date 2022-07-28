package com.progressoft.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;


public class ScoringSummaryImpl implements ScoringSummary{
    BigDecimal sum =  new BigDecimal("0.00");
    ArrayList<BigDecimal> integerData;
    ArrayList<BigDecimal> sortedData = new ArrayList<>() ;


    public ScoringSummaryImpl(ArrayList<BigDecimal> integerData) {

        for (int i = 0; i < integerData.size(); i++) {
            sortedData.add(integerData.get(i));
        }
        Collections.sort(sortedData);
        this.integerData = integerData;
        sum();
    }

    @Override
    public BigDecimal mean() {
        BigDecimal size = BigDecimal.valueOf(integerData.size()).setScale(2 , RoundingMode.HALF_EVEN );
        return sum.divide(size , 2, RoundingMode.HALF_EVEN ).setScale(0 , RoundingMode.HALF_EVEN ).setScale(2,RoundingMode.HALF_EVEN) ;
    }

    @Override
    public BigDecimal standardDeviation() {
        return BigDecimal.valueOf(Math.sqrt(variance().doubleValue())).setScale(2 , RoundingMode.HALF_EVEN );
    }

    @Override
    public BigDecimal variance() {
        BigDecimal standardDeviation = new BigDecimal("0.00") ;;
        int length = integerData.size();

        BigDecimal mean = mean();

        for(BigDecimal num: integerData) {
            standardDeviation  = standardDeviation.add(num.subtract(mean).multiply(num.subtract(mean)));
        }
        return  standardDeviation.divide(BigDecimal.valueOf(length) , 2 , RoundingMode.HALF_EVEN).setScale(0 , RoundingMode.HALF_EVEN ).setScale(2,RoundingMode.HALF_EVEN );
    }

    @Override
    public BigDecimal median() {
        BigDecimal median;

        if (sortedData.size() % 2 == 0){
            median = (sortedData.get(sortedData.size()/2).add(sortedData.get(sortedData.size()/2 - 1)).divide(new BigDecimal(2))).setScale(2 , RoundingMode.CEILING) ;
        }
        else
            median = sortedData.get(sortedData.size()/2).setScale(2 , RoundingMode.CEILING);
        return median;
    }

    @Override
    public BigDecimal min() {
        return sortedData.get(0);
    }

    @Override
    public BigDecimal max() {
        return sortedData.get(sortedData.size()-1);
    }

    public void sum() {
        integerData.stream().forEach(index -> sum = sum.add(index));
    }
}
