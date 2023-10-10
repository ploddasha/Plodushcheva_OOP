package nsu.ru.plodushcheva.json;

/**
 * This class represents the JSON object for a cook with their name and strength.
 */
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
