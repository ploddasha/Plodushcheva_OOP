package nsu.ru.plodushcheva.json;


import java.util.List;

public record PizzeriaData(List<CookerJson> cookers, List<CourierJson> couriers, int stockSize) {

    public int getNumCouriers() {
        return couriers.size();
    }

    public int getNumCookers() {
        return cookers.size();
    }

}
