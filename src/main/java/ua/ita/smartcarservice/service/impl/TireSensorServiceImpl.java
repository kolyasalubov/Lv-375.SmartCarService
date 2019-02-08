package ua.ita.smartcarservice.service.impl;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sensors.*;

import ua.ita.smartcarservice.entity.sensors.data.TirePressureEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.sensors.TirePressureRepository;

import ua.ita.smartcarservice.service.SensorService;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="tire")
public class TireSensorServiceImpl implements SensorService {

    @Autowired
    TirePressureRepository repository;

    @Autowired
    CarRepository carRepository;


    /* CREATE */

    @Override
    public void addRecord(ARecordDto recordDto) {
        repository.save(recordDtoToEntity((TireRecordDto)recordDto));
    }

    private TirePressureEntity recordDtoToEntity(TireRecordDto recordDto){
        TirePressureEntity entity = new TirePressureEntity();
        entity.setCar(carRepository.findByVin(recordDto.getCarVin()));

        Map<String, Double> data = recordDto.getValue();
        entity.setValueBackRight(data.get(Tires.BACK_RIGHT.toString()));
        entity.setValueBackLeft(data.get(Tires.BACK_LEFT.toString()));
        entity.setValueFrontRight(data.get(Tires.FRONT_RIGHT.toString()));
        entity.setValueFrontLeft(data.get(Tires.FRONT_LEFT.toString()));

        if(recordDto.getDate() != null)
            entity.setDate(parseDateToLocal(recordDto.getDate()));

        return entity;
    }


    /* READ */

    private Map<String, List<Double>> fillMap(){

        Map<String, List<Double>> map = new HashMap<>();
        for(Tires tire : Tires.values())
            map.put(tire.toString(), new ArrayList<>());
        return map;
    }

    private Map<String, List<Double>> getData(List<TirePressureEntity> records){
        Map<String, List<Double>> map = fillMap();

        for (TirePressureEntity tire : records) {
            map.get(Tires.BACK_LEFT.toString()).add(tire.getValueBackLeft());
            map.get(Tires.BACK_RIGHT.toString()).add(tire.getValueBackRight());
            map.get(Tires.FRONT_LEFT.toString()).add(tire.getValueFrontLeft());
            map.get(Tires.FRONT_RIGHT.toString()).add(tire.getValueFrontRight());
        }

        return map;
    }

    private Map<String, List<Double>> getDataFromObjArray(List<Object[]> records){
        Map<String, List<Double>> map = fillMap();

        for (Object[] arr : records) {
            map.get(Tires.FRONT_LEFT.toString()).add((Double)arr[1]);
            map.get(Tires.FRONT_RIGHT.toString()).add((Double)arr[2]);
            map.get(Tires.BACK_LEFT.toString()).add((Double)arr[3]);
            map.get(Tires.BACK_RIGHT.toString()).add((Double)arr[4]);
        }

        return map;
    }


    @Override
    public TireChartDto getAllByDay(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<TirePressureEntity> records = repository.getAllByDay(params.getKey(), params.getValue());

        Map<String, List<Double>> data = getData(records);
        List<String> labels = new LabelsProvider().getTireHours(records);
        return new TireChartDto(data, labels);
    }

    @Override
    public TireChartDto getAvgByMonth(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = repository.getAvgByMonth(params.getKey(), params.getValue());

        return chartDtoForMonths(records);
    }

    @Override
    public TireChartDto getMaxByMonth(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = repository.getMaxByMonth(params.getKey(), params.getValue());

        return chartDtoForMonths(records);
    }

    @Override
    public TireChartDto getMinByMonth(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = repository.getMinByMonth(params.getKey(), params.getValue());

        return chartDtoForMonths(records);
    }

    private TireChartDto chartDtoForMonths(List<Object[]> records){
        Map< String, List<Double>> data = getDataFromObjArray(records);
        List<String> labels = new LabelsProvider().getDays(records);
        return new TireChartDto(data, labels);
    }

    @Override
    public TireChartDto getAvgByYear(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = repository.getAvgByYear(params.getKey(), params.getValue());

        return chartDtoForYears(records);
    }

    @Override
    public TireChartDto getMaxByYear(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = repository.getMaxByYear(params.getKey(), params.getValue());

        return chartDtoForYears(records);
    }

    @Override
    public TireChartDto getMinByYear(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<Object[]> records = repository.getMinByYear(params.getKey(), params.getValue());

        return chartDtoForYears(records);
    }

    private TireChartDto chartDtoForYears(List<Object[]> records){
        Map<String, List<Double>> data = getDataFromObjArray(records);
        List<String> labels = new LabelsProvider().getMonths(records);
        return new TireChartDto(data, labels);
    }
}
