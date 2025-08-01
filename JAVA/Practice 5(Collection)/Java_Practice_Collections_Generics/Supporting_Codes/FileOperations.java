import java.io.BufferedReader;
import java.io.FileReader;

public class FileOperations {

    private static final String INPUT_FILE_NAME = "movies.txt";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split(",");
            for (String value : values) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        br.close();
    }
}