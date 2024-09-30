import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Please provide the input file path.");
            System.exit(1);
        }

        String inputFilePath = args[0];

        // Чтение CSV файла
        Reader csvReader = new Reader(inputFilePath);
        List<String[]> rows = csvReader.readCsvFile();

        // Фильтрация некорректных строк
        DataFilter dataFilter = new DataFilter();
        List<String[]> validRows = dataFilter.filterInvalidRows(rows);

        // Группировка данных по значениям столбцов
        DataGrouper dataGrouper = new DataGrouper();
        Map<Integer, Map<String, List<String[]>>> groupedData = dataGrouper.groupByColumns(validRows);

        // Печать сгруппированных данных
        dataGrouper.printGroupedData(groupedData);
    }
}
