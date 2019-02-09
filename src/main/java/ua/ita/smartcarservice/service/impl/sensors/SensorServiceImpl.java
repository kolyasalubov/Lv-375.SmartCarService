package ua.ita.smartcarservice.service.impl.sensors;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sensors.ARecordDto;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.sensors.data.ISensorEntity;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.sensors.factory.SensorFactory;
import ua.ita.smartcarservice.repository.sensors.factory.SensorRepository;
import ua.ita.smartcarservice.service.sensors.SensorService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service(value="basic")
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorFactory factory;

    @Autowired
    CarRepository carRepository;


    /* CREATE */

    @Override
    public void addRecord(ARecordDto recordDto) {
        SensorRepository rep = factory.getRepository(recordDto.getSensorType());
        rep.save(recordDtoToEntity((RecordDto)recordDto));
    }

    private ISensorEntity recordDtoToEntity(RecordDto recordDto){
        String repositoryType = recordDto.getSensorType();

        ISensorEntity entity = factory.getEntity(repositoryType);
        entity.setCar(carRepository.findByVin(recordDto.getCarVin()));
        entity.setValue(recordDto.getValue());
        if(recordDto.getDate() != null)
            entity.setDate(parseDateToLocal(recordDto.getDate()));

        return entity;
    }


    /* READ */

    private List<Double> getData(List<ISensorEntity> records){
        List<Double> data = new ArrayList<>();
        for (ISensorEntity record : records) {
            data.add(record.getValue());
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

    @Override
    public ChartDto getAllByDay(DateForChartDto dateForChartDto) {
        Pair<LocalDateTime, Long> params = getParams(dateForChartDto);
        List<ISensorEntity> records = getRepository(dateForChartDto).getAllByDay(params.getKey(), params.getValue());

        List<Double> data = getData(records);
        List<String> labels = new LabelsProvider().getHours(records);
        return new ChartDto(data, labels);
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
        List<String> labels = new LabelsProvider().getDays(records);
        return new ChartDto(data, labels);
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
        List<String> labels = new LabelsProvider().getMonths(records);
        return new ChartDto(data, labels);
    }
}
