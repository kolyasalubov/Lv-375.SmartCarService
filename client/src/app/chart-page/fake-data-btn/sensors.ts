import { Sensor } from './sensor';

export const SENSORS: Sensor[] = [
    {type: 'fuel', minValue: 0, maxValue: 40},
    {type: 'speed', minValue: 0, maxValue: 100},
    {type: 'mileage', minValue: 0, maxValue: 100_000},
    {type: 'battery', minValue: 0, maxValue: 100},
    {type: 'oil level', minValue: 0, maxValue: 20}, // TODO maxValue
    {type: 'oil pressure', minValue: 0, maxValue: 10}, // TODO maxValue
    {type: 'tire pressure', minValue: 2, maxValue: 5}
];
