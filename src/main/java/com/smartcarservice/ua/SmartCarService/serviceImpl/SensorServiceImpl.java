package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.dto.sensors.ChartDto;
import com.smartcarservice.ua.SmartCarService.dto.sensors.DateForChartDto;
import com.smartcarservice.ua.SmartCarService.dto.sensors.RecordDto;
import com.smartcarservice.ua.SmartCarService.entity.sensors.data.ISensorEntity;
import com.smartcarservice.ua.SmartCarService.repository.SensorRepository;
import com.smartcarservice.ua.SmartCarService.repository.SensorRepositoryFactory;
import com.smartcarservice.ua.SmartCarService.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepositoryFactory factory;

    @Override
    public ChartDto getAllByDay(DateForChartDto dateForChartDto) {
//        SensorRepository repository = getRepository(dateForChartDto);
//        LocalDateTime date = dateForChartDto.getDate();
//        List<? extends BaseSensorEntity> records = repository.getAllByDay(date, dateForChartDto.getCarId());
//
//        List<Double> data = getData(records);
//        List<String> lables = new LabelsProvider().getDays(date);
//        return new ChartDto(data, lables);
        return null;
    }

    @Override
    public ChartDto getAvgByMonth(DateForChartDto dateForChartDto) {
//        SensorRepository repository = getRepository(dateForChartDto);
//        LocalDateTime date = dateForChartDto.getDate();
//        List<? extends BaseSensorEntity> records = repository.getAvgByMonth(date, dateForChartDto.getCarId());
//
//        List<Double> data = getData(records);
//        List<String> lables = new LabelsProvider().getDays(date);
//        return new ChartDto(data, lables);
        return null;
    }

    @Override
    public ChartDto getMaxByMonth(DateForChartDto dateForChartDto) {
        return null;
    }

    @Override
    public ChartDto getMinByMonth(DateForChartDto dateForChartDto) {
        return null;
    }

    @Override
    public ChartDto getAvgByYear(DateForChartDto dateForChartDto) {
        return null;
    }

    @Override
    public ChartDto getMaxByYear(DateForChartDto dateForChartDto) {
        return null;
    }

    @Override
    public ChartDto getMinByYear(DateForChartDto dateForChartDto) {
        return null;
    }

    @Override
    public void addRecord(RecordDto recordDto) {

        String repositoryType = recordDto.getSensorType();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        ISensorEntity entity = factory.getEntity(repositoryType);
        entity.setDate(LocalDateTime.parse(recordDto.getDate(), formatter));
        entity.setValue(recordDto.getValue());

        SensorRepository rep = factory.getRepository(repositoryType);
        rep.save(entity);

    }

}
