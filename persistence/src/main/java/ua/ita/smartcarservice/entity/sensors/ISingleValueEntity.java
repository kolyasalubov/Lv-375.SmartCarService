package ua.ita.smartcarservice.entity.sensors;


import java.util.Map;

public interface ISingleValueEntity extends ISensorEntity {

    void setValue(double value);

    double getValue();

    default ISensorEntity setValues(ISensorEntity entity, Map<String, Double> values) {
        ISingleValueEntity iEntity = (ISingleValueEntity) entity;
        iEntity.setValue(values.get("value"));

        return iEntity;
    }

}
