package ua.ita.smartcarservice.service.impl;

import javafx.util.Pair;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sensors.data.ISensorEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.sensors.factory.SensorRepository;
import ua.ita.smartcarservice.repository.sensors.factory.SensorRepositoryFactory;
import ua.ita.smartcarservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepositoryFactory factory;

    @Autowired
    CarRepository carRepository;


    /* CREATE */

    @Override
    public void addRecord(RecordDto recordDto) {
        SensorRepository rep = factory.getRepository(recordDto.getSensorType());
        rep.save(recordDtoToEntity(recordDto));
    }

    private ISensorEntity recordDtoToEntity(RecordDto recordDto){
        String repositoryType = recordDto.getSensorType();
        Car car = carRepository.findByVin(recordDto.getCarVin());

        ISensorEntity entity = factory.getEntity(repositoryType);
        entity.setDate(parseDateToLocal(recordDto.getDate()));
        entity.setValue(recordDto.getValue());
        entity.setCar(car);

        return entity;
    }

    private LocalDateTime parseDateToLocal (String strDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(strDate, formatter);
    }


    /* READ */

    private List<Double> getData(List<ISensorEntity> records){
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            data.add(records.get(i).getValue());
        }
        return data;
    }

    private List<Double> getDataFromObjArray(List<Object[]> records){
        List<Double> data = new ArrayList<>();
        for(Object[] el : records){
            data.add((double) el[1]);
        }
        return data;
    }

    private SensorRepository getRepository(DateForChartDto dateForChartDto){
        return factory.getRepository(dateForChartDto.getSensorType());
    }

    private Pair<LocalDateTime, Long> getParams(DateForChartDto dateForChartDto){
        LocalDateTime date = parseDateToLocal(dateForChartDto.getDate());
        Long carId = dateForChartDto.getCarId();
        return new Pair<>(date, carId);
    }

    @Override
    public ChartDto getAllByDay(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<ISensorEntity> records = getRepository(dateForChartDto).getAllByDay(params.getKey(), params.getValue());

        List<Double> data = getData(records);
        List<String> lables = new LabelsProvider().getHours(records);
        return new ChartDto(data, lables);
    }

    @Override
    public ChartDto getAvgByMonth(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getAvgByMonth(params.getKey(), params.getValue());

        return chartDtoForMonths(records);
    }

    @Override
    public ChartDto getMaxByMonth(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMaxByMonth(params.getKey(), params.getValue());

        return chartDtoForMonths(records);
    }

    @Override
    public ChartDto getMinByMonth(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMinByMonth(params.getKey(), params.getValue());

        return chartDtoForMonths(records);
    }

    private ChartDto chartDtoForMonths(List<Object[]> records){
        List<Double> data = getDataFromObjArray(records);
        List<String> lables = new LabelsProvider().getDays(records);
        return new ChartDto(data, lables);
    }

    @Override
    public ChartDto getAvgByYear(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getAvgByYear(params.getKey(), params.getValue());

        return chartDtoForYears(records);
    }

    @Override
    public ChartDto getMaxByYear(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMaxByYear(params.getKey(), params.getValue());

        return chartDtoForYears(records);
    }

    @Override
    public ChartDto getMinByYear(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMinByYear(params.getKey(), params.getValue());

        return chartDtoForYears(records);
    }

    private ChartDto chartDtoForYears(List<Object[]> records){
        List<Double> data = getDataFromObjArray(records);
        List<String> lables = new LabelsProvider().getMonths(records);
        return new ChartDto(data, lables);
    }
}
