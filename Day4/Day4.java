import java.util.List;

public class Day4  extends Day{

  public static String[][] getPuzzle(){
    
    List<String> puzzleList = readFiledata("./input2.txt");
    String[][] wordPuzzle = new String[puzzleList.size()][];

    for(int i = 0; i < puzzleList.size(); i++){
      wordPuzzle[i] = puzzleList.get(i).split("");
    }

    return wordPuzzle;
  }

  public static boolean findHorizontalXmasBack(int row, int col, String[][] puzzle){
    if(((col+1)-4) >= 0){
      String xmas = puzzle[row][col] + puzzle[row][col-1] + puzzle[row][col-2] + puzzle[row][col-3];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }

  public static boolean findDiagonalXmasback(int row, int col, String[][] puzzle){
    if((((col+1)-4) >= 0) && (((row+1)-4) >= 0)){
      String xmas = puzzle[row][col] + puzzle[row-1][col-1] + puzzle[row-2][col-2] + puzzle[row-3][col-3];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }
  
  public static boolean findVerticalXmasBack(int row, int col, String[][] puzzle){
    if(((row+1)-4) >= 0){
      String xmas = puzzle[row][col] + puzzle[row-1][col] + puzzle[row-2][col] + puzzle[row-3][col];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }

  public static boolean findHorizontalXmas(int row, int col, String[][] puzzle){

    if(puzzle.length - col >= 4){
      String xmas = puzzle[row][col] + puzzle[row][col+1] + puzzle[row][col+2] + puzzle[row][col+3];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }

  public static boolean findVerticalXmas(int row, int col, String[][] puzzle){

    if(puzzle.length - row >= 4){

      String xmas = puzzle[row][col] + puzzle[row+1][col] + puzzle[row+2][col] + puzzle[row+3][col];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }

  public static boolean findDiagonalXmas(int row, int col, String[][] puzzle){
    if(puzzle.length - row >= 4 && puzzle.length - col >= 4){
      String xmas = puzzle[row][col] + puzzle[row+1][col+1] + puzzle[row+2][col+2] + puzzle[row+3][col+3];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }
  public static boolean findDiagonalXmasX(int row, int col, String[][] puzzle){
    if(puzzle.length - row >= 4 && (col+1)-4 >= 0){
      String xmas = puzzle[row][col] + puzzle[row+1][col-1] + puzzle[row+2][col-2] + puzzle[row+3][col-3];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }

  public static boolean findDiagonalXmasY(int row, int col, String[][] puzzle){
    if(puzzle.length - col >= 4 && (row+1)-4 >= 0){
      String xmas = puzzle[row][col] + puzzle[row-1][col+1] + puzzle[row-2][col+2] + puzzle[row-3][col+3];
      return xmas.equals("XMAS") | xmas.equals("SAMX");
    }
    return false;
  }

  public static void main(String[] args) {
    String[][] puzzle = getPuzzle();
    int xmas = 0;

    for(int i =0; i < puzzle.length; i++){
      for(int j=0; j < puzzle.length; j++){
        if(findHorizontalXmas(i, j, puzzle)) xmas++;
        if(findVerticalXmasBack(i, j, puzzle)) xmas++;
        if(findHorizontalXmas(i, j, puzzle)) xmas++;
        if(findVerticalXmasBack(i, j, puzzle)) xmas++;
        if(findDiagonalXmas(i, j, puzzle)) xmas++;
        if(findDiagonalXmasback(i, j, puzzle)) xmas++;
        if(findDiagonalXmasX(i, j, puzzle)) xmas++;
        if(findDiagonalXmasY(i, j, puzzle)) xmas++;

      }
    }
    System.out.println(xmas/2);
  }
  
}
