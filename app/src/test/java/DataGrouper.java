import java.util.*;

public class DataGrouper {

    // Поле для хранения сгруппированных данных
    private Map<Integer, Map<String, List<String[]>>> groupedData = new HashMap<>();

    // Метод для обработки строки и группировки данных
    public void groupRow(String[] row) {
        for (int col = 0; col < row.length; col++) {
            String value = row[col];
            groupedData
                    .computeIfAbsent(col, k -> new HashMap<>())
                    .computeIfAbsent(value, k -> new ArrayList<>())
                    .add(row);
        }
    }

    // Метод для получения сгруппированных данных
    public Map<Integer, Map<String, List<String[]>>> getGroupedData() {
        return groupedData;
    }
}
