import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

    @Test
    public void testReadCsvFile() throws IOException {
        String content = "\"79336638333\";\"79018904323\";\"79837242463\";\"79509669547\";\"\";\"79874934090\";\"79842224430\"\n"
                       + "\"79720567031\";\"79196918770\";\"79310426628\";\"79715285038\"\n"
                       + "\"79336638333\";\"79011904323\";\"79837242463\";\"79509669547\";\"\";\"79874934090\";\"79842224430\"";

        File tempFile = createTempFile(content);
        Reader csvReader = new Reader(tempFile.getAbsolutePath());
        List<String[]> rows = csvReader.readCsvFile();

        assertEquals(3, rows.size());
        assertArrayEquals(new String[]{"\"79336638333\"", "\"79018904323\"", "\"79837242463\"", "\"79509669547\"", "\"\"", "\"79874934090\"", "\"79842224430\""}, rows.get(0));
    }

    // Вспомогательный метод для создания временного файла
    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("test", ".csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write(content);
        }
        return tempFile;
    }
}
