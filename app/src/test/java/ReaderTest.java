import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    public void testReadCsvFile() throws IOException {
        String content = "\"79336638333\";\"79018904323\";\"79837242463\";\"79509669547\";\"\";\"79874934090\";\"79842224430\"\n"
                       + "\"79720567031\";\"79196918770\";\"79310426628\";\"79715285038\"\n"
                       + "\"79336638333\";\"79011904323\";\"79837242463\";\"79509669547\";\"\";\"79874934090\";\"79842224430\"";

        File tempFile = createTempFile(content);
        Reader csvReader = new Reader(tempFile.getAbsolutePath());
        
        try (BufferedReader br = csvReader.getBufferedReader()) {
            String line;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                String[] parsedLine = csvReader.parseLine(line);
                if (lineCount == 0) {
                    assertArrayEquals(new String[]{"\"79336638333\"", "\"79018904323\"", "\"79837242463\"", "\"79509669547\"", "\"\"", "\"79874934090\"", "\"79842224430\""}, parsedLine);
                }
                lineCount++;
            }

            assertEquals(3, lineCount);  // Проверка, что прочитаны все строки
        }
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
