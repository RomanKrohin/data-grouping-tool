import java.io.*;
import java.util.*;

public class Reader {

    private final String filePath;

    public Reader(String filePath) {
        this.filePath = filePath;
    }

    // Метод для чтения CSV файла
    public List<String[]> readCsvFile() throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows.add(parseCsvLine(line));
            }
        }
        return rows;
    }

    // Парсинг строки CSV с учетом кавычек
    private String[] parseCsvLine(String line) {
        return line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }
}
