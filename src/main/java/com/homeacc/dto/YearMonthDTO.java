package com.homeacc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class YearMonthDTO {
    private int yearId;
    private int monthId;
}
