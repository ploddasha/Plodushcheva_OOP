package nsu.ru.plodushcheva.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Objects;
import java.io.Reader;

/**
 * A class for parsing a JSON file and obtaining data about a pizzeria.
 */
public class JsonParser {

    /**
     * Reads a JSON file containing information about a pizzeria
     * and returns an instance of PizzeriaData.
     *
     * @return an instance of PizzeriaData containing information about a pizzeria.
     */
    public PizzeriaData getData() {

        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("info.json")))) {
            Gson gson = new Gson();
            return gson.fromJson(reader,
                    new TypeToken<PizzeriaData>() {}.getType());
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
