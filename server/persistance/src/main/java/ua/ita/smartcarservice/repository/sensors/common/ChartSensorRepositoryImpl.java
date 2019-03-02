package ua.ita.smartcarservice.repository.sensors.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.sensors.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorElements;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;
import ua.ita.smartcarservice.entity.sensors.common.SensorTypes;
import ua.ita.smartcarservice.entity.sensors.common.Tires;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.*;

public class ChartSensorRepositoryImpl<T extends BaseSensorEntity> implements ChartSensorRepository<T> {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    SensorEntityFactory entityFactory;

    private CriteriaBuilder builder;

    private Path<String> datePath;
    private Map<String, Path<Double>> valuePath;
    private Path<Car> carIdPath;

    private String selection;

    @Override
    public List<Object[]> findAllDataByPeriod(@Param("date") LocalDateTime date,
                                              @Param("carId") long carId,
                                              @Param("sensorType") String sensorType,
                                              @Param("selection") String selection) {

        this.selection = selection;

        builder = entityManager.getCriteriaBuilder();
        Class entityClass = entityFactory.getEntity(sensorType).getClass();
        CriteriaQuery criteria = builder.createQuery(entityClass);
        initPaths(criteria.from(entityClass), sensorType);


        Expression selectedPeriod = getSelectedPeriod();
        Predicate[] predicates = getPredicates(carId, date);
        Expression[] selectionValues = getSelection(selectedPeriod, valuePath);

        criteria.select(builder.array(selectionValues));
        criteria.where(predicates);
        criteria.groupBy(selectedPeriod);
        criteria.orderBy(builder.asc(selectedPeriod));

        return entityManager.createQuery(criteria).getResultList();
    }

    private void initPaths(Root root, String sensorType) {
        datePath = root.get(SensorElements.DATE.toString());
        carIdPath = root.get(SensorElements.CAR.toString()).get(SensorElements.ID.toString());
        initValuePath(root, sensorType);
    }

    private void initValuePath(Root root, String sensorType) {
        valuePath = new HashMap<>();
        Arrays.stream(getValuesNames(sensorType)).forEach((value) -> {
            String key = value.toString();
            valuePath.put(key, root.get(key));
        });
    }

    private Object[] getValuesNames(String sensorType) {
        if (sensorType.equals(SensorTypes.TIRE_PRESSURE.toString())) {
            return Tires.values();
        } else {
            return new Object[]{SensorElements.VALUE.toString()};
        }
    }

    private Expression getSelectedPeriod() {
        if (selection.contains(SensorElements.DAY.toString())) {
            return getPartOfDate(SensorElements.TIME.toString());
        } else if (selection.contains(SensorElements.MONTH.toString())) {
            return getPartOfDate(SensorElements.DAY.toString());
        } else {
            return getPartOfDate(SensorElements.MONTH.toString());
        }
    }

    private Expression getPartOfDate(String part) {
        return builder.function(part, Integer.class, datePath);
    }

    private Expression getAggregatedValue(Path<Double> value) {
        if (selection.contains(SensorElements.MIN.toString())) {
            return builder.min(value);
        } else if (selection.contains(SensorElements.MAX.toString())) {
            return builder.max(value);
        } else {
            return builder.avg(value);
        }
    }

    private Expression[] getSelection(Expression selectedPeriod, Map<String, Path<Double>> values) {
        List<Expression> list = new ArrayList<>();
        list.add(selectedPeriod);
        values.values().forEach((value) -> list.add(getAggregatedValue(value)));
        return list.toArray(new Expression[]{});
    }


    private Predicate[] getPredicates(long carId, LocalDateTime date) {
        Predicate equalCar = builder.equal(carIdPath, carId);
        Predicate equalYear = builder.equal(getPartOfDate(SensorElements.YEAR.toString()), date.getYear());
        Predicate equalMonth = builder.equal(getPartOfDate(SensorElements.MONTH.toString()), date.getMonthValue());
        Predicate equalDay = builder.equal(getPartOfDate(SensorElements.DAY.toString()), date.getDayOfMonth());

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(equalCar);
        predicates.add(equalYear);

        if (selection.contains(SensorElements.DAY.toString())) {
            predicates.addAll(Arrays.asList(equalMonth, equalDay));
        } else if (selection.contains(SensorElements.MONTH.toString())) {
            predicates.add(equalMonth);
        }

        return predicates.toArray(new Predicate[]{});
    }
}
