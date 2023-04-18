package nsu.ru.plodushcheva.json;


import java.util.List;

public class PizzeriaData {
    private final int stockSize;

    private final List<CookJson> cooks;
    private final List<CourierJson> couriers;

    public int getStockSize() {
        return stockSize;
    }

    public PizzeriaData(List<CookJson> cooks, List<CourierJson> couriers,
                        int stockSize) {
        this.cooks = cooks;
        this.couriers = couriers;
        this.stockSize = stockSize;
    }

    public List<CookJson> getCooks() {
        return cooks;
    }

    public List<CourierJson> getCouriers() {
        return couriers;
    }

    public int getNumCooks() {
        return cooks.size();
    }


    public int getNumCouriers() {
        return couriers.size();
    }
}