import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilePath = args[0];

        List<String[]> rows = readCsvFile(inputFilePath);

        List<String[]> validRows = filterInvalidRows(rows);

        Map<Integer, Map<String, List<String[]>>> groupedData = groupByColumns(validRows);

        printGroupedData(groupedData);
    }

    private static List<String[]> readCsvFile(String filePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(line.split(";"));
            }
        }
        return rows;
    }

    private static List<String[]> filterInvalidRows(List<String[]> rows) {
        List<String[]> validRows = new ArrayList<>();
        for (String[] row : rows) {
            boolean isValid = true;
            for (String value : row) {
                if (!isValidValue(value)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                validRows.add(row);
            }
        }
        return validRows;
    }

    private static boolean isValidValue(String value) {
        if (!value.contains("\"")) {
            return true;
        }
        return value.startsWith("\"") && value.endsWith("\"") && value.length() > 1;
    }

    private static Map<Integer, Map<String, List<String[]>>> groupByColumns(List<String[]> rows) {
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

    private static void printGroupedData(Map<Integer, Map<String, List<String[]>>> groupedData) {
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
