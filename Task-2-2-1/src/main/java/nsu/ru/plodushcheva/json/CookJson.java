package nsu.ru.plodushcheva.json;

public class CookJson {
    private final String name;
    private final int strength;

    public CookJson(String name, int strength) {
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
