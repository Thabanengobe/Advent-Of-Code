import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Corrupt {

  public static List<String> readFiledata(String fileName){

    try {
      final Path filePath = Paths.get(fileName);
      final List<String> file = Files.readAllLines(filePath);
      return file;  

    } catch (Exception e) {
      return null;
    }
  }

  public static int parseMulFunction(String mulFunctionFound){

    Pattern pattern = setPattern("\\d+");
    Matcher match = pattern.matcher(mulFunctionFound);

    final ArrayList<String> matchStrings = getMatchResults(match);
    
    return mul(Integer.parseInt(matchStrings.get(0)), Integer.parseInt(matchStrings.get(1)));

  }


  public static Pattern setPattern(String pattern){
    return Pattern.compile(pattern);
  }

  public static ArrayList<String> getMatchResults(Matcher match){

    final ArrayList<String> matchStrings = new ArrayList<>();

    while(match.find()){
      matchStrings.add(match.group());
    }

    return matchStrings;
  }

  public static int mul(int x, int y){
    return x * y;
  }

  public static void main(String[] args) throws Exception {
    Pattern pattern = setPattern("(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\))|(don\'t\\(\\))");
    List<String> fileData = readFiledata("./corrupt.txt");

    int sum = 0;
    Matcher match = pattern.matcher(fileData.toString());
    ArrayList<String> matchResulst = getMatchResults(match);

    boolean isEnabled = true;

    for(String mulFunction : matchResulst){

      if(mulFunction.equals("do()")) isEnabled = true;
      else if(mulFunction.equals("don't()")) isEnabled = false;
      else if(isEnabled){
        sum += parseMulFunction(mulFunction);
      }
    }



    System.out.println(sum);
  }
}
