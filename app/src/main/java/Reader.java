import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Reader {

    private String filePath;

    public Reader(String filePath) {
        this.filePath = filePath;
    }

    public Reader() {
    }

    // Возвращает BufferedReader для построчного чтения файла
    public BufferedReader getBufferedReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(filePath));
    }

    // Парсинг строки с учетом кавычек
    public String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                insideQuotes = !insideQuotes; // Меняем состояние
            } else if (c == ';' && !insideQuotes) {
                result.add(currentValue.toString());
                currentValue.setLength(0); // Очищаем текущий результат
            } else {
                currentValue.append(c); // Добавляем символ к текущему значению
            }
        }
        // Добавляем последнее значение
        result.add(currentValue.toString());

        return result.toArray(new String[0]); // Конвертируем список обратно в массив
    }
}