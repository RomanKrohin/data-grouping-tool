import java.util.*;

public class DataGrouper {

    // Храним только количество появлений значений для каждой колонки
    private final Map<Integer, Map<String, Integer>> groupedData = new HashMap<>();

    // Группировка одной строки по значениям столбцов
    public void groupRow(String[] row) {
        for (int col = 0; col < row.length; col++) {
            String value = row[col];
            // Используем computeIfAbsent для создания структуры данных только при необходимости
            groupedData.computeIfAbsent(col, k -> new HashMap<>())
                .merge(value, 1, Integer::sum); // Увеличиваем счетчик для текущего значения
        }
    }

    // Печать сгруппированных данных и возврат количества групп с более чем одним элементом
    public int printGroupedData() {
        int groupCount = 0;

        for (Map.Entry<Integer, Map<String, Integer>> columnGroup : groupedData.entrySet()) {
            int colIndex = columnGroup.getKey();
            System.out.println("Column " + colIndex + ":");
            for (Map.Entry<String, Integer> valueGroup : columnGroup.getValue().entrySet()) {
                int count = valueGroup.getValue();
                if (count > 1) {
                    groupCount++;
                    String value = valueGroup.getKey();
                    System.out.println("  Value: " + (value.isEmpty() ? "\"\"" : value) + " (Count: " + count + ")");
                }
            }
        }

        return groupCount; // Возвращаем количество групп с более чем одним элементом
    }
}
