package ua.ita.smartcarservice.service.impl;

import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sensors.data.ISensorEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.SensorRepository;
import ua.ita.smartcarservice.repository.SensorRepositoryFactory;
import ua.ita.smartcarservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepositoryFactory factory;

    @Autowired
    CarRepository carRepository;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);

        ISensorEntity entity = factory.getEntity(repositoryType);
        entity.setDate(LocalDateTime.parse(recordDto.getDate(), formatter));
        entity.setValue(recordDto.getValue());

        Car car = carRepository.findByVin(recordDto.getCarVin());
        entity.setCar(car);

        SensorRepository rep = factory.getRepository(repositoryType);
        rep.save(entity);

    }

}
