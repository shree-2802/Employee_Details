package com.example.employee_detail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateFilterWithEmployee {
    private int id;
    private DateFilter dateFilter;
}
