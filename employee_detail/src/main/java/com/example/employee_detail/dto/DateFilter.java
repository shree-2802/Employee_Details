package com.example.employee_detail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DateFilter {
    private String startDate;
    private String endDate;
    private int page;
}
