import java.util.*;

public class DataFilter {

    // Метод для фильтрации некорректных строк
    public List<String[]> filterInvalidRows(List<String[]> rows) {
        List<String[]> validRows = new ArrayList<>();
        for (String[] row : rows) {
            if (isValidRow(row)) {
                validRows.add(row);
            }
        }
        return validRows;
    }

    // Проверка валидности строки
    private boolean isValidRow(String[] row) {
        for (String value : row) {
            if (!isValidValue(value)) {
                return false;
            }
        }
        return true;
    }

    // Проверка валидности значения
    private boolean isValidValue(String value) {
        if (!value.contains("\"")) {
            return true;
        }
        return value.startsWith("\"") && value.endsWith("\"") && value.length() > 1;
    }
}
