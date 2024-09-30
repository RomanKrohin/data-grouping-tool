import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataGrouperTest {

    @Test
    public void testGroupByColumns() {
        List<String[]> rows = Arrays.asList(
            new String[]{"\"79336638333\"", "\"79018904323\""},
            new String[]{"\"79336638333\"", "\"79011904323\""}
        );

        DataGrouper dataGrouper = new DataGrouper();
        Map<Integer, Map<String, List<String[]>>> groupedData = dataGrouper.groupByColumns(rows);

        assertEquals(2, groupedData.size());
        assertEquals(2, groupedData.get(0).get("\"79336638333\"").size());  // Две строки с этим значением в столбце 0
    }
}