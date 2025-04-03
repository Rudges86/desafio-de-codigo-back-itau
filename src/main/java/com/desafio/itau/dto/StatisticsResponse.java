package com.desafio.itau.dto;

import lombok.Getter;

import java.util.DoubleSummaryStatistics;
@Getter
public class StatisticsResponse {
    private long count;
    private double sum;
    private double min;
    private double max;

    public StatisticsResponse(DoubleSummaryStatistics statistics) {
        this.count = statistics.getCount();
        this.sum = statistics.getSum();
        this.min = statistics.getMin();
        this.max = statistics.getMax();
    }


}
