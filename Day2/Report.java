import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Report{

  public static boolean isSafe( int level1, int level2){
    final int patternDiff = level1 - level2;
    return (Math.abs(patternDiff) <= 3 && Math.abs(patternDiff) >= 1);
  }

  public static boolean  isIncreasing(int[] report){
    for(int i = 0; i < (report.length-1); i++ ){
      if(!(report[i] < report[i+1])) return false;
    }
    return true;
  }

  public static boolean isDecreasing(int[] report){
    for(int i = 0; i < (report.length-1); i++ ){
      if(!(report[i] > report[i+1])) return false;
    }
    return true;
  }

  public static boolean findsafeReports(int[] report){

    final boolean  isIncrease = isIncreasing(report);
    final boolean isDecrease = isDecreasing(report);

    if( !isIncrease && !isDecrease) return false;

    for(int i = 0; i < (report.length-1); i++){

      final int level1 = report[i];
      final int level2 = report[i+1];
      if(!(isSafe(level1, level2))) return false;
    }
    return true;
  }


  public static boolean problemDumpener(int[] report){

    ArrayList<Integer> tempArray = new ArrayList<>();

    for( int level : report){ tempArray.add(level); }

    for(int level : report){

      int levelIndex = tempArray.indexOf(level);
      tempArray.remove(levelIndex);

      int[] newReport = convertToArray(tempArray);
      if(findsafeReports(newReport)) return true;

      tempArray.add(levelIndex, level);

    }
    return false;
  }

  public static int[] convertToArray(ArrayList<Integer> tempReport){
    int[] tempArray = new int[tempReport.size()];
    for(int i = 0; i < tempReport.size(); i++){
      tempArray[i] = tempReport.get(i).intValue();
    }
    return tempArray;
  }

  public static void main(String[] args) {
    try {

      final Path filePath = Paths.get("./reports.txt");
      final Object[] file = Files.readAllLines(filePath).toArray();
      int safe = 0;
      
      for (int i = 0; i < file.length; i++){
          int[] report = Arrays.stream((String[])((String)file[i]).split(" ")).mapToInt(Integer::parseInt).toArray();
          if(findsafeReports(report)) safe++;
          else if(problemDumpener(report)) safe++;
      }

      System.out.println(safe);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}