package nsu.ru.plodushcheva;

import nsu.ru.plodushcheva.Threads.CookerJson;
import nsu.ru.plodushcheva.Threads.CourierJson;

import java.util.List;

public class PizzeriaData {
    private final int stockSize;

    private final List<CookerJson> cookers;
    private final List<CourierJson> couriers;

    public int getStockSize() {
        return stockSize;
    }

    public int getNumCouriers() {
        return couriers.size();
    }

    public int getNumCookers() {
        return cookers.size();
    }

    public PizzeriaData(List<CookerJson> cookers, List<CourierJson> couriers,
                      int stockSize) {
        this.cookers = cookers;
        this.couriers = couriers;
        this.stockSize = stockSize;
    }

    public List<CookerJson> getCookers() {
        return cookers;
    }

    public List<CourierJson> getCouriers() {
        return couriers;
    }
}
