import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day {
  
  public static List<String> readFiledata(String fileName){

    try {
      final Path filePath = Paths.get(fileName);
      final List<String> file = Files.readAllLines(filePath);
      return file;  

    } catch (Exception e) {
      return null;
    }
  }
}
