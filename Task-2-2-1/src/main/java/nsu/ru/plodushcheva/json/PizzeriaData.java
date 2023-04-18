package nsu.ru.plodushcheva.json;


import java.util.List;

/**
 * Represents the data required for initializing a pizzeria,
 * including the stock size, a list of cook information,
 * and a list of courier information.
 */
public class PizzeriaData {
    private final int stockSize;

    private final List<CookJson> cooks;
    private final List<CourierJson> couriers;

    public int getStockSize() {
        return stockSize;
    }

    /**
     * Constructs a PizzeriaData object with the specified cook and courier information and stock size.
     *
     * @param cooks A list of cook information.
     * @param couriers A list of courier information.
     * @param stockSize The size of the stock.
     */
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