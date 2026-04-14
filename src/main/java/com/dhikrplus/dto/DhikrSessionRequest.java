package com.dhikrplus.dto;

import lombok.Data;

@Data
public class DhikrSessionRequest {
    private Long dhikrId;
    private Integer count;
    private Integer target;
    private Boolean completed;
}
