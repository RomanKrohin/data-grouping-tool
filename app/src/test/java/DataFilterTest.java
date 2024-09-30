import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataFilterTest {

    @Test
    public void testFilterInvalidRows() {
        List<String[]> rows = Arrays.asList(
            new String[]{"\"79336638333\"", "\"79018904323\"", "\"79837242463\"", "\"79509669547\"", "\"\"", "\"79874934090\"", "\"79842224430\""},
            new String[]{"\"8383\"200000741652251", "\"79855053897\"", "\"83100000580443402\"", "\"200000133000191\""}
        );

        DataFilter dataFilter = new DataFilter();
        List<String[]> validRows = dataFilter.filterInvalidRows(rows);

        assertEquals(1, validRows.size());  // Только первая строка валидна
        assertArrayEquals(new String[]{"\"79336638333\"", "\"79018904323\"", "\"79837242463\"", "\"79509669547\"", "\"\"", "\"79874934090\"", "\"79842224430\""}, validRows.get(0));
    }
}
