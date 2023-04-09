package nsu.ru.plodushcheva.json;

public class CourierJson {
    private final String name;
    private final int maxTrunkSize;

    public CourierJson(String name, int maxTrunkSize) {
        this.maxTrunkSize = maxTrunkSize;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMaxTrunkSize() {
        return maxTrunkSize;
    }
}
