package com.smartcarservice.ua.SmartCarService.dto.sensors;

import lombok.Data;

import java.util.List;

@Data
public class ChartDto {

    private List<Double> data;

    private List<String> labels;

}
