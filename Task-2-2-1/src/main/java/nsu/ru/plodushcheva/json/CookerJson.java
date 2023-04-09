package nsu.ru.plodushcheva.json;

public class CookerJson {
    private final String name;
    private final int strength;

    public CookerJson(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }
}
