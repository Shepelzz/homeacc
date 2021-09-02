package com.homeacc.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CounterData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTER_ID", nullable = false)
    private Counter counter;

    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MONTH_ID", nullable = false)
    private Month month;

    private float value;

    public int getYearMonthId() {
        return year*100+month.getId();
    }
}
