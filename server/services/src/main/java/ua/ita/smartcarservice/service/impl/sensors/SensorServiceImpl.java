package ua.ita.smartcarservice.service.impl.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sensors.ChartDto;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.entity.sensors.ISensorEntity;
import ua.ita.smartcarservice.entity.sensors.ISingleValueEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.sensors.common.AlertSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.BasicSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.ChartSensorRepository;
import ua.ita.smartcarservice.repository.sensors.common.SensorRepositoryFactory;
import ua.ita.smartcarservice.service.SensorService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    public ChartDto findDataByPeriod(DateForChartDto dto) {
        return getChartDtoFromObjArray(getChartRepository(dto)
                .findAllDataByPeriod(parseDateToLocal(dto.getDate()), dto.getCarId(),
                        dto.getSensorType(), dto.getSelection()));
    }

    private ChartDto getChartDtoFromObjArray(List<Object[]> records) {
        Map<String, List<Double>> dataMap = new HashMap<>();
        if (records != null && records.size() > 0 && records.get(0)[0] != null) {
            records.forEach((record) -> {
                String label = record[0].toString();
                List<Double> data = new ArrayList<>();
                for (int i = 1; i < record.length; i++) {
                    data.add((double) record[i]);
                }
                dataMap.put(label, (data));
            });
        }
        return new ChartDto(dataMap);
    }

    private BasicSensorRepository getRepository(DateForChartDto dateForChartDto) {
        return repositoryFactory.getRepository(dateForChartDto.getSensorType());
    }

    private ChartSensorRepository getChartRepository(DateForChartDto dateForChartDto) {
        return (ChartSensorRepository) getRepository(dateForChartDto);
    }

    private AlertSensorRepository getAlertRepository(String sensorType) {
        return (AlertSensorRepository) repositoryFactory.getRepository(sensorType);
    }

    private LocalDateTime parseDateToLocal(String strDate) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public ChartDto findCurrentLevel(DateForChartDto dateForChartDto){
        return new ChartDto(repositoryFactory.getBatteryRepository()
                .findCurrentLevel(dateForChartDto.getCarId()));
    }


    @Override
    public RecordDto findRecordBeforeDate(RecordDto recordDto) {
        ISingleValueEntity sensor = (ISingleValueEntity) getAlertRepository(recordDto.getSensorType())
                .findRecordBeforeDate(
                        parseDateToLocal(recordDto.getDate()),
                        carRepository.findByVin(recordDto.getCarVin()).getId());
        if (sensor != null) {
            return new RecordDto(sensor.getDate().toString(), sensor.getValue());
        }
        return null;
    }

}
