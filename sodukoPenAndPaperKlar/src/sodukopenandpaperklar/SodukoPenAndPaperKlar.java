package sodukopenandpaperklar;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Set;

public class SodukoPenAndPaperKlar {

//SVÅRASTE StochasticSearch klarat av....  
    
    static int[][] startValues = {
        {8, 3, 0, 1, 6, 2, 4, 7, 5},
        {2, 4, 0, 9, 8, 5, 6, 1, 3},
        {0, 5, 0, 3, 7, 0, 2, 8, 9},
        {6, 9, 1, 8, 5, 3, 7, 0, 4},
        {3, 7, 8, 4, 2, 0, 1, 5, 6},
        {4, 2, 0, 7, 1, 6, 9, 3, 8},
        {9, 1, 0, 5, 3, 0, 8, 0, 2},
        {7, 6, 0, 2, 9, 8, 5, 4, 1},
        {5, 0, 2, 6, 4, 1, 3, 0, 7}};
     
//https://www.printablesudoku99.com/ , Easy Sudoku Puzzles 1, NR. 1
    /*
    static int[][] startValues = {
        {1, 4, 2, 0, 9, 0, 0, 0, 5},
        {7, 0, 0, 4, 0, 0, 0, 8, 9},
        {8, 0, 5, 0, 0, 0, 0, 2, 4},
        {2, 0, 0, 0, 0, 4, 8, 0, 0},
        {0, 3, 0, 0, 0, 1, 2, 6, 0},
        {0, 8, 0, 0, 7, 2, 9, 4, 1},
        {0, 5, 0, 2, 0, 6, 0, 0, 0},
        {0, 2, 8, 0, 0, 9, 4, 1, 0},
        {0, 7, 9, 1, 0, 8, 5, 3, 0}};
     */
//https://www.printablesudoku99.com/ , Easy Sudoku Puzzles 1, NR. 2
    /*
    static int[][] startValues = {
        {1, 0, 0, 0, 9, 4, 7, 0, 5},
        {5, 7, 3, 1, 0, 2, 0, 0, 0},
        {0, 4, 0, 0, 5, 3, 1, 0, 8},
        {0, 8, 1, 5, 6, 7, 3, 4, 0},
        {0, 0, 0, 8, 0, 1, 0, 0, 7},
        {0, 5, 6, 4, 0, 9, 0, 0, 2},
        {4, 6, 0, 0, 0, 0, 0, 9, 0},
        {0, 3, 0, 9, 1, 0, 0, 7, 6},
        {9, 0, 0, 0, 4, 0, 0, 0, 0}};
*/
//https://www.printablesudoku99.com/ , Medium Sudoku Puzzles 1, NR. 1
    /*
    static int[][] startValues = {
        {0, 0, 1, 0, 0, 0, 9, 4, 0},
        {4, 0, 7, 8, 3, 0, 2, 1, 0},
        {9, 0, 6, 5, 0, 0, 8, 0, 3},
        {8, 0, 0, 6, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 2, 0, 1, 3, 0},
        {0, 0, 0, 0, 0, 3, 5, 0, 0},
        {5, 7, 0, 0, 0, 2, 4, 8, 0},
        {1, 6, 0, 0, 9, 0, 0, 5, 0},
        {0, 0, 0, 4, 1, 0, 0, 0, 7}};
     */
//https://www.printablesudoku99.com/ , Medium Sudoku Puzzles 1, NR. 2
    /*
    static int[][] startValues = {
        {0, 7, 4, 0, 0, 9, 5, 0, 0},
        {1, 0, 0, 0, 7, 0, 8, 3, 4},
        {3, 0, 2, 0, 0, 4, 0, 0, 1},
        {0, 0, 1, 9, 4, 0, 6, 7, 5},
        {0, 8, 6, 3, 1, 0, 0, 0, 0},
        {0, 0, 7, 5, 0, 0, 0, 0, 3},
        {0, 2, 0, 0, 6, 3, 0, 0, 0},
        {0, 0, 0, 2, 0, 5, 0, 0, 0},
        {0, 9, 0, 0, 0, 0, 4, 0, 0}};
     */
//https://www.printablesudoku99.com/ , Medium Sudoku Puzzles 1, NR. 3
     /*
    static int[][] startValues = {
        {0, 7, 0, 4, 0, 8, 9, 3, 2},
        {0, 0, 0, 0, 0, 3, 0, 0, 0},
        {8, 9, 0, 2, 0, 0, 7, 4, 0},
        {9, 6, 0, 0, 0, 0, 2, 0, 0},
        {0, 1, 4, 0, 0, 5, 0, 0, 0},
        {0, 0, 7, 0, 0, 2, 8, 9, 0},
        {0, 0, 0, 0, 8, 0, 5, 2, 1},
        {0, 5, 0, 0, 0, 9, 0, 8, 3},
        {0, 8, 0, 0, 2, 0, 0, 7, 0}};
     */
//https://www.printablesudoku99.com/ , Hard Sudoku Puzzles 1, NR. 1
    /*
    static int[][] startValues = {
        {2, 0, 8, 0, 0, 7, 3, 0, 0},
        {0, 4, 0, 8, 0, 0, 0, 0, 0},
        {0, 7, 0, 0, 0, 0, 9, 6, 0},
        {0, 6, 5, 0, 0, 0, 0, 0, 0},
        {0, 3, 1, 0, 0, 0, 6, 0, 5},
        {0, 2, 9, 6, 0, 0, 0, 7, 0},
        {6, 9, 0, 0, 0, 0, 0, 2, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 5, 1, 0, 0, 6}};
     */
//https://www.printablesudoku99.com/ , Hard Sudoku Puzzles 1, NR. 2
    /*
    static int[][] startValues = {
        {0, 0, 0, 0, 0, 9, 0, 0, 0},
        {1, 5, 0, 0, 3, 0, 7, 0, 4},
        {0, 9, 0, 1, 0, 8, 0, 0, 0},
        {9, 0, 0, 0, 0, 0, 0, 0, 5},
        {0, 0, 5, 0, 1, 3, 0, 0, 7},
        {8, 0, 6, 0, 0, 0, 0, 0, 0},
        {4, 2, 3, 0, 0, 0, 0, 8, 0},
        {0, 1, 7, 0, 5, 0, 0, 0, 0},
        {5, 0, 0, 0, 0, 0, 1, 0, 0}};
     */
//https://www.ams.org/journals/notices/200904/rtx090400460p.pdf, Will Shortz’s puzzle 301    
    /*
    static int[][] startValues = {
        {0, 3, 9, 5, 0, 0, 0, 0, 0},
        {0, 0, 0, 8, 0, 0, 0, 7, 0},
        {0, 0, 0, 0, 1, 0, 9, 0, 4},
        {1, 0, 0, 4, 0, 0, 0, 0, 3},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 0, 0, 8, 6, 0},
        {0, 0, 6, 7, 0, 8, 2, 0, 0},
        {0, 1, 0, 0, 9, 0, 0, 0, 5},
        {0, 0, 0, 0, 0, 1, 0, 0, 8}};
     */
//https://www.ams.org/journals/notices/200904/rtx090400460p.pdf, The Mepham diabolical Sudoku puzzle
    /*
    static int[][] startValues = {
        {0, 9, 0, 7, 0, 0, 8, 6, 0},
        {0, 3, 1, 0, 0, 5, 0, 2, 0},
        {8, 0, 6, 0, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 5, 0, 0, 0, 6},
        {0, 0, 0, 3, 0, 7, 0, 0, 0},
        {5, 0, 0, 0, 1, 0, 7, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 9},
        {0, 2, 0, 6, 0, 0, 3, 5, 0},
        {0, 5, 4, 0, 0, 8, 0, 7, 0}};
     */
    public static void main(String[] args) {
        start();
    }
    static Object[][] possibleValues = new Object[9][9]; //[y][x]
    static int values[][] = new int[9][9];  //[y][x]
    static int tries = 0;
    static long startTime;

    private static void start() {
        startTime = System.nanoTime();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (startValues[j][i] == 0) {
                    Set temp = new java.util.HashSet();
                    for (int k = 1; k < 10; k++) {
                        temp.add(k);
                    }
                    possibleValues[j][i] = temp;
                } else {
                    values[j][i] = startValues[j][i];
                }
            }
        }

        startRow();
        startColomn();
        startBox();
        //printAll();

        Sort sort = new Sort(possibleValues, values);
        sort.startSort();
    }

    private static void startRow() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (startValues[j][i] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int l = 0; l < 9; l++) {
                            if (startValues[j][l] == k) {
                                Set temp = (Set) possibleValues[j][i];
                                temp.remove(k);
                                possibleValues[j][i] = temp;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void startColomn() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (startValues[j][i] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int l = 0; l < 9; l++) {
                            if (startValues[l][i] == k) {
                                Set temp = (Set) possibleValues[j][i];
                                temp.remove(k);
                                possibleValues[j][i] = temp;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void startBox() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (startValues[j][i] == 0) {
                    int offsetX = (i / 3) * 3;
                    int offsetY = (j / 3) * 3;
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            for (int k = 1; k < 10; k++) {
                                if (startValues[y + offsetY][x + offsetX] == k) {
                                    Set temp = (Set) possibleValues[j][i];
                                    temp.remove(k);
                                    possibleValues[j][i] = temp;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void printAll() {
        int[][][] temp = new int[9][9][9];  //[y][x][nums]

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Set temp2 = (Set) possibleValues[j][i];
                if (temp2 != null) {
                    for (int k = 1; k < 10; k++) {

                        if (temp2.contains(k)) {
                            temp[j][i][k - 1] = k;
                        }

                    }
                }
            }
        }
        
        System.out.println("Försök " + tries + " " + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + new DecimalFormat("00").format((i * 9 + j + 1)));
                System.out.print(Arrays.toString(temp[i][j]));
            }
            System.out.println("");
        }
         
        System.out.println("");
        System.out.println("");
        System.out.println("");
         
        System.out.println(Arrays.deepToString(values)
                .replace("],", "\n")
                .replaceAll("[\\[\\]]", " "));
        System.out.println("");
        System.out.println("");
        System.out.println("");

    }
}
