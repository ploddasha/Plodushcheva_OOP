package nsu.ru.plodushcheva.json;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Objects;

public class JsonParser {
    public PizzeriaData getData() {

        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().
                        getResourceAsStream("info.json")))) {
            Gson gson = new Gson();
            return gson.fromJson(reader,
                    new TypeToken<PizzeriaData>() {}.getType());
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
