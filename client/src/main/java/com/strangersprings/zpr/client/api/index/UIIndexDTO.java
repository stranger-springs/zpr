package com.strangersprings.zpr.client.api.index;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UIIndexDTO {

    private Long id;
    private LocalDateTime timestamp;
    private double value;

}
