package ua.ita.smartcarservice.service.impl.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.sensors.ISensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.sensors.common.AlertSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.BasicSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.ChartSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.SensorRepositoryFactory;
import ua.ita.smartcarservice.service.SensorService;
import ua.ita.smartcarservice.entity.sensors.ISingleValueEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.ita.smartcarservice.service.impl.sensors.DateParser.parseDateToLocal;

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
        BasicSensorRepository rep = repositoryFactory.getRepository(recordDto.getSensorType());
        rep.save(recordDtoToEntity(recordDto));
    }

    private ISensorEntity recordDtoToEntity(RecordDto recordDto) {
        String repositoryType = recordDto.getSensorType();

        ISensorEntity entity = entityFactory.getEntity(repositoryType);
        entity.setCar(carRepository.findByVin(recordDto.getCarVin()));
        entity.setValues(entity, recordDto.getValues());

        if (recordDto.getDate() != null) {
            entity.setDate(parseDateToLocal(recordDto.getDate()));
        }

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

    private BasicSensorRepository getRepository(DateForChartDto dateForChartDto) {
        return repositoryFactory.getRepository(dateForChartDto.getSensorType());
    }

    private ChartSensorRepository getChartRepository(DateForChartDto dateForChartDto) {
        return (ChartSensorRepository) getRepository(dateForChartDto);
    }

    private AlertSensorRepository getAlertRepository(String sensorType) {
        return (AlertSensorRepository)repositoryFactory.getRepository(sensorType);
    }

    @Override
    public ChartDto findAllByDay(DateForChartDto dateForChartDto) {
        ParamsProvider params = new ParamsProvider(dateForChartDto);
        return getChartDtoFromObjArray(
                getChartRepository(dateForChartDto)
                        .getAllByDay(params.getDate(), params.getCarId()));
    }

    @Override
    public ChartDto findAvgByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = new ParamsProvider(dateForChartDto);
        return getChartDtoFromObjArray(
                getChartRepository(dateForChartDto)
                        .getAvgByMonth(params.getDate(), params.getCarId()));
    }

    @Override
    public ChartDto findMaxByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = new ParamsProvider(dateForChartDto);
        return getChartDtoFromObjArray(
                getChartRepository(dateForChartDto)
                        .getMaxByMonth(params.getDate(), params.getCarId()));
    }

    @Override
    public ChartDto findMinByMonth(DateForChartDto dateForChartDto) {
        ParamsProvider params = new ParamsProvider(dateForChartDto);
        return getChartDtoFromObjArray(
                getChartRepository(dateForChartDto)
                        .getMinByMonth(params.getDate(), params.getCarId()));
    }

    @Override
    public ChartDto findAvgByYear(DateForChartDto dateForChartDto) {
        ParamsProvider params = new ParamsProvider(dateForChartDto);
        return getChartDtoFromObjArray(
                getChartRepository(dateForChartDto)
                        .getAvgByYear(params.getDate(), params.getCarId()));
    }

    @Override
    public ChartDto findMaxByYear(DateForChartDto dateForChartDto) {
        ParamsProvider params = new ParamsProvider(dateForChartDto);
        return getChartDtoFromObjArray(
                getChartRepository(dateForChartDto)
                        .getMaxByYear(params.getDate(), params.getCarId()));
    }

    @Override
    public ChartDto findMinByYear(DateForChartDto dateForChartDto) {
        ParamsProvider params = new ParamsProvider(dateForChartDto);
        return getChartDtoFromObjArray(
                getChartRepository(dateForChartDto)
                        .getMinByYear(params.getDate(), params.getCarId()));
    }


    @Override
    public ChartDto findLastValue(DateForChartDto dateForChartDto) {
        return new ChartDto(repositoryFactory.getBatteryRepository()
                .getLastValue(dateForChartDto.getCarId()));
    }

    @Override
    public RecordDto findRecordBeforeDate(RecordDto recordDto) {
        ISingleValueEntity sensor = (ISingleValueEntity) getAlertRepository(recordDto.getSensorType())
                .findRecordBeforeDate(
                        parseDateToLocal(recordDto.getDate()),
                        carRepository.findByVin(recordDto.getCarVin()).getId());
        if(sensor != null) {
            return new RecordDto(sensor.getDate().toString(), sensor.getValue());
        }
        return null;
    }


}
