import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Please provide the input file path.");
            System.exit(1);
        }

        String inputFilePath = args[0];

        long startTime = System.nanoTime();

        // Инициализация считывателя
        Reader reader = new Reader();
        DataGrouper dataGrouper = new DataGrouper();

        // Используем поток для обработки данных
        try (Stream<String> lines = Files.lines(Paths.get(inputFilePath))) {
            lines.forEach(line -> {
                String[] row = reader.parseLine(line);
                dataGrouper.groupRow(row);
            });
        }

        int groupCount = dataGrouper.printGroupedData();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Number of groups with more than one element: " + groupCount);
        System.out.println("Execution time: " + duration + " ms");
    }
}
