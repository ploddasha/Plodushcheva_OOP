package nsu.ru.plodushcheva.json;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonParserTest {

    @Test
    public void testNumberOfCooks() {
        JsonParser jsonParser = new JsonParser();
        PizzeriaData pizzeriaData = jsonParser.getData();
        int numCooks = pizzeriaData.getNumCooks();
        Assertions.assertEquals(3, numCooks);
    }

    @Test
    public void testNumberOfCouriers() {
        JsonParser jsonParser = new JsonParser();
        PizzeriaData pizzeriaData = jsonParser.getData();
        int numCouriers = pizzeriaData.getNumCouriers();
        Assertions.assertEquals(2, numCouriers);
    }

    @Test
    public void testCooksNames() {
        JsonParser jsonParser = new JsonParser();
        PizzeriaData pizzeriaData = jsonParser.getData();
        List<CookJson> cooks = pizzeriaData.getCooks();
        Assertions.assertEquals("Name 1", cooks.get(0).getName());
        Assertions.assertEquals("Name 2", cooks.get(1).getName());
        Assertions.assertEquals("Name 3", cooks.get(2).getName());
    }

    @Test
    public void testCooksStrength() {
        JsonParser jsonParser = new JsonParser();
        PizzeriaData pizzeriaData = jsonParser.getData();
        List<CookJson> cooks = pizzeriaData.getCooks();
        Assertions.assertEquals(1, cooks.get(0).getStrength());
        Assertions.assertEquals(2, cooks.get(1).getStrength());
        Assertions.assertEquals(3, cooks.get(2).getStrength());
    }

    @Test
    public void testCouriersNames() {
        JsonParser jsonParser = new JsonParser();
        PizzeriaData pizzeriaData = jsonParser.getData();
        List<CourierJson> couriers = pizzeriaData.getCouriers();
        Assertions.assertEquals("Name 1", couriers.get(0).getName());
        Assertions.assertEquals("Name 2", couriers.get(1).getName());
    }

    @Test
    public void testCouriersBags() {
        JsonParser jsonParser = new JsonParser();
        PizzeriaData pizzeriaData = jsonParser.getData();
        List<CourierJson> couriers = pizzeriaData.getCouriers();
        Assertions.assertEquals(2, couriers.get(0).getMaxTrunkSize());
        Assertions.assertEquals(1, couriers.get(1).getMaxTrunkSize());
    }

    @Test
    public void testStockSize() {
        JsonParser jsonParser = new JsonParser();
        PizzeriaData pizzeriaData = jsonParser.getData();
        int stockSize = pizzeriaData.getStockSize();
        Assertions.assertEquals(5, stockSize);
    }

}