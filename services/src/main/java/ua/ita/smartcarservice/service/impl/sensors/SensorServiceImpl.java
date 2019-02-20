package ua.ita.smartcarservice.service.impl.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.sensors.ISensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.sensors.SensorRepository;
import ua.ita.smartcarservice.repository.sensors.SensorRepositoryFactory;
import ua.ita.smartcarservice.service.sensors.SensorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepositoryFactory repositoryFactory;

    @Autowired
    SensorEntityFactory entityFactory;

    @Autowired
    CarRepository carRepository;


    /* CREATE */

    @Override
    public void addRecord(RecordDto recordDto) {
        SensorRepository rep = repositoryFactory.getRepository(recordDto.getSensorType());
        rep.save(recordDtoToEntity(recordDto));
    }

    private ISensorEntity recordDtoToEntity(RecordDto recordDto) {
        String repositoryType = recordDto.getSensorType();

        ISensorEntity entity = entityFactory.getEntity(repositoryType);
        entity.setCar(carRepository.findByVin(recordDto.getCarVin()));
        entity.setValues(entity, recordDto.getValues());

        if (recordDto.getDate() != null)
            entity.setDate(parseDateToLocal(recordDto.getDate()));

        return entity;
    }


    /* READ */

    private ChartDto getChartDtoFromObjArray(List<Object[]> records) {
        Map<String, List<Double>> dataMap = new HashMap<>();
        records.forEach((record) -> {
            String label = record[0].toString();
            List<Double> data = new ArrayList<>();
            for (int i = 1; i < record.length; i++) {
                data.add((double) record[i]);
            }
            dataMap.put(label, (data));
        });
        return new ChartDto(dataMap);
    }

    private SensorRepository getRepository(DateForChartDto dateForChartDto) {
        return repositoryFactory.getRepository(dateForChartDto.getSensorType());
    }

    @Override
    public ChartDto getAllByDay(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getAllByDay(params.getDate(), params.getCarId());

        return getChartDtoFromObjArray(records);
    }

    @Override
    public ChartDto getAvgByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getAvgByMonth(params.getDate(), params.getCarId());

        return getChartDtoFromObjArray(records);
    }

    @Override
    public ChartDto getMaxByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMaxByMonth(params.getDate(), params.getCarId());

        return getChartDtoFromObjArray(records);
    }

    @Override
    public ChartDto getMinByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMinByMonth(params.getDate(), params.getCarId());

        return getChartDtoFromObjArray(records);
    }

    @Override
    public ChartDto getAvgByYear(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getAvgByYear(params.getDate(), params.getCarId());

        return getChartDtoFromObjArray(records);
    }

    @Override
    public ChartDto getMaxByYear(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMaxByYear(params.getDate(), params.getCarId());

        return getChartDtoFromObjArray(records);
    }

    @Override
    public ChartDto getMinByYear(DateForChartDto dateForChartDto) {
        ParamsProvider params = getParams(dateForChartDto);
        List<Object[]> records = getRepository(dateForChartDto).getMinByYear(params.getDate(), params.getCarId());

        return getChartDtoFromObjArray(records);
    }

}
