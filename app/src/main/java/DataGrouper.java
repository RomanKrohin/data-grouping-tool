import java.util.*;

public class DataGrouper {

    // Группировка данных по значениям столбцов
    public Map<Integer, Map<String, List<String[]>>> groupByColumns(List<String[]> rows) {
        Map<Integer, Map<String, List<String[]>>> groupedData = new HashMap<>();

        for (String[] row : rows) {
            for (int col = 0; col < row.length; col++) {
                String value = row[col];
                groupedData
                        .computeIfAbsent(col, k -> new HashMap<>())
                        .computeIfAbsent(value, k -> new ArrayList<>())
                        .add(row);
            }
        }
        return groupedData;
    }

    // Печать сгруппированных данных
    public void printGroupedData(Map<Integer, Map<String, List<String[]>>> groupedData) {
        for (Map.Entry<Integer, Map<String, List<String[]>>> columnGroup : groupedData.entrySet()) {
            int colIndex = columnGroup.getKey();
            System.out.println("Column " + colIndex + ":");
            for (Map.Entry<String, List<String[]>> valueGroup : columnGroup.getValue().entrySet()) {
                List<String[]> group = valueGroup.getValue();
                if (group.size() > 1) {
                    String value = valueGroup.getKey();
                    System.out.println("  Value: " + (value.isEmpty() ? "\"\"" : value));
                    for (String[] row : group) {
                        System.out.println("    " + Arrays.toString(row));
                    }
                }
            }
        }
    }
}
