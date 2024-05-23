package com.example.orderUp_api.dto.common;

import com.example.orderUp_api.dto.sql.RatingSummaryQueryDto;
import lombok.Data;

@Data
public class RatingSummaryDto {
    private double star;
    private long quantity;

    public static RatingSummaryDto fromRatingSummaryDto(RatingSummaryQueryDto dto) {
        RatingSummaryDto data = new RatingSummaryDto();
        data.setStar(dto.getStar());
        data.setQuantity(dto.getQuantity());
        return data;
    }
}
