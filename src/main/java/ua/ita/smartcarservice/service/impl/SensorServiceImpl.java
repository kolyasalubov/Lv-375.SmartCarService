package ua.ita.smartcarservice.service.impl;

import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sensors.data.ISensorEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.SensorRepository;
import ua.ita.smartcarservice.repository.sensorsFactory.SensorRepositoryFactory;
import ua.ita.smartcarservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        SensorRepository rep = factory.getRepository(recordDto.getSensorType());
        rep.save(recordDtoToEntity(recordDto));
    }

    private ISensorEntity recordDtoToEntity(RecordDto recordDto){
        String repositoryType = recordDto.getSensorType();
        Car car = carRepository.findByVin(recordDto.getCarVin());

        ISensorEntity entity = factory.getEntity(repositoryType);
        entity.setDate(parseDate(recordDto.getDate()));
        entity.setValue(recordDto.getValue());
        entity.setCar(car);

        return entity;
    }

    private LocalDateTime parseDate(String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(strDate, formatter);
    }

}
