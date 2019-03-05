package ua.ita.smartcarservice.repository.sensors.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.sensors.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorElements;
import ua.ita.smartcarservice.entity.sensors.common.SensorEntityFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartSensorRepositoryImpl<T extends BaseSensorEntity> implements ChartSensorRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SensorEntityFactory entityFactory;

    private CriteriaBuilder builder;

    // TODO rename
    private final int DEFAULT_VALUE = 0;

    // TODO change max to having
    @Override
    public Integer findLastRecordValue(@Param("carId") long carId,
                                       @Param("sensorType") String sensorType) {

        builder = entityManager.getCriteriaBuilder();
        Class entityClass = entityFactory.getEntity(sensorType).getClass();
        CriteriaQuery criteria = builder.createQuery(entityClass);
        Root root = criteria.from(entityClass);

        Subquery subquery = criteria.subquery(entityClass);
        Root fromSubquery = subquery.from(entityClass);
        subquery.select(builder.max(fromSubquery.get(SensorElements.ID.toString())));
        subquery.where(builder.equal(
                fromSubquery.get(SensorElements.CAR.toString()).get(SensorElements.ID.toString()), carId));

        Path value = root.get(SensorElements.VALUE.toString());
        criteria.select(value);
        criteria.where(builder.in(root.get(SensorElements.ID.toString())).value(subquery));

        List records = entityManager.createQuery(criteria).getResultList();
        return records.size() == 0 ? DEFAULT_VALUE : (int)Math.round((double)records.get(0));
    }

    @Override
    public List<Object[]> findAllDataByPeriod(@Param("date") LocalDateTime date,
                                              @Param("carId") long carId,
                                              @Param("sensorType") String sensorType,
                                              @Param("selection") String selection) {

        builder = entityManager.getCriteriaBuilder();
        Class entityClass = entityFactory.getEntity(sensorType).getClass();
        CriteriaQuery criteria = builder.createQuery(entityClass);
        Root root = criteria.from(entityClass);

        ChartCriteriaElementsProvider elementsProvider =
                new ChartCriteriaElementsProvider(carId, date, selection, sensorType, root);

        Expression selectedPeriod = getSelectedPeriod(elementsProvider);
        Predicate[] predicates = getPredicates(elementsProvider);
        Expression[] selectionValues = getSelection(selectedPeriod, elementsProvider);

        criteria.select(builder.array(selectionValues));
        criteria.where(predicates);
        criteria.groupBy(selectedPeriod);
        criteria.orderBy(builder.asc(selectedPeriod));

        return entityManager.createQuery(criteria).getResultList();
    }

    private Expression getSelectedPeriod(ChartCriteriaElementsProvider elementsProvider) {
        String selection = elementsProvider.getSelection();
        Path<String> datePath = elementsProvider.getDatePath();

        if (selection.contains(SensorElements.DAY.toString())) {
            return getPartOfDate(SensorElements.TIME.toString(), datePath);
        } else if (selection.contains(SensorElements.MONTH.toString())) {
            return getPartOfDate(SensorElements.DAY.toString(), datePath);
        } else {
            return getPartOfDate(SensorElements.MONTH.toString(), datePath);
        }
    }
    private Predicate[] getPredicates(ChartCriteriaElementsProvider elementsProvider) {
        Path<String> datePath = elementsProvider.getDatePath();
        LocalDateTime date = elementsProvider.getDate();

        Predicate equalCar = builder.equal(elementsProvider.getCarIdPath(), elementsProvider.getCarId());
        Predicate equalYear = builder.equal(getPartOfDate(SensorElements.YEAR.toString(), datePath), date.getYear());
        Predicate equalMonth = builder.equal(getPartOfDate(SensorElements.MONTH.toString(), datePath), date.getMonthValue());
        Predicate equalDay = builder.equal(getPartOfDate(SensorElements.DAY.toString(), datePath), date.getDayOfMonth());

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(equalCar);
        predicates.add(equalYear);

        String selection = elementsProvider.getSelection();
        if (selection.contains(SensorElements.DAY.toString())) {
            predicates.addAll(Arrays.asList(equalMonth, equalDay));
        } else if (selection.contains(SensorElements.MONTH.toString())) {
            predicates.add(equalMonth);
        }

        return predicates.toArray(new Predicate[]{});
    }

    private Expression getPartOfDate(String part, Path<String> datePath) {
        return builder.function(part, Integer.class, datePath);
    }

    private Expression[] getSelection(Expression selectedPeriod, ChartCriteriaElementsProvider elementsProvider) {
        Map<String, Path<Double>> mapOfValues = elementsProvider.getValuePath();
        String selection = elementsProvider.getSelection();

        List<Expression> list = new ArrayList<>();
        list.add(selectedPeriod);
        list.addAll(mapOfValues.values().stream()
                .map(value -> getAggregatedValue(value, selection)).collect(Collectors.toList()));
        return list.toArray(new Expression[]{});
    }

    private Expression getAggregatedValue(Path<Double> value, String selection) {
        if (selection.contains(SensorElements.MIN.toString())) {
            return builder.min(value);
        } else if (selection.contains(SensorElements.MAX.toString())) {
            return builder.max(value);
        } else {
            return builder.avg(value);
        }
    }
}