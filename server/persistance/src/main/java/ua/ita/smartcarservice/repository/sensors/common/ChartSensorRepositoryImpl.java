package ua.ita.smartcarservice.repository.sensors.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.sensors.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartSensorRepositoryImpl<T extends BaseSensorEntity> implements ChartSensorRepository<T> {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    SensorEntityFactory entityFactory;

    private CriteriaBuilder builder;

    private Path<String> datePath;
    private Path<Double> valuePath;
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
        initPaths(criteria.from(entityClass));


        Expression selectedPeriod = getSelectedPeriod();
        Expression selectedValue = getSelectedValue();
        Predicate[] predicates = getPredicates(carId, date);

        criteria.select(builder.array(selectedPeriod, selectedValue));
        criteria.where(predicates);
        criteria.groupBy(selectedPeriod);
        criteria.orderBy(builder.asc(datePath));

        return entityManager.createQuery(criteria).getResultList();
    }

    private void initPaths(Root root) {
        datePath = root.get("date");
        valuePath = root.get("value");
        carIdPath = root.get("car").get("id");
    }

    private Expression getSelectedPeriod() {
        if (selection.contains("day")) {
            return getPartOfDate("time");
        } else if (selection.contains("month")) {
            return getPartOfDate("day");
        } else {
            return getPartOfDate("month");
        }
    }

    private Expression getPartOfDate(String part) {
        return builder.function(part, Integer.class, datePath);
    }

    private Expression getSelectedValue() {
        if (selection.contains("min")) {
            return builder.min(valuePath);
        } else if (selection.contains("max")) {
            return builder.max(valuePath);
        } else {
            return valuePath;
        }
    }


    private Predicate[] getPredicates(long carId, LocalDateTime date) {
        Predicate equalCar = builder.equal(carIdPath, carId);
        Predicate equalYear = builder.equal(getPartOfDate("year"), date.getYear());
        Predicate equalMonth = builder.equal(getPartOfDate("month"), date.getMonthValue());
        Predicate equalDay = builder.equal(getPartOfDate("day"), date.getDayOfMonth());

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(equalCar);
        predicates.add(equalYear);

        if (selection.contains("day")) {
            predicates.addAll(Arrays.asList(equalMonth, equalDay));
        } else if (selection.contains("month")) {
            predicates.add(equalMonth);
        }

        return predicates.toArray(new Predicate[]{});
    }
}
