package sodukobacktrackingklar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author karls
 */
public class SodukoBacktrackingKlar {

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
    static int[][] values = new int[9][9];
    static int[][] fel = new int[9][9];
    static ArrayList prevY = new ArrayList();
    static ArrayList prevX = new ArrayList();
    static long startTime;
    static long endTime;

    public static void main(String[] args) {
        start();
        sort();
    }

    private static void start() {
        startTime = System.nanoTime();
        
        System.out.println(Arrays.deepToString(startValues)
                .replace("],", "\n").replaceAll("[\\[\\]]", " "));
        values = startValues;
        System.out.println("");
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < 81; i++) {
            if (values[i / 9][i % 9] == 0) {
                prevX.add((i / 9));
                prevY.add((i % 9));
                fel[i / 9][i % 9] = prevX.size();
            }
        }

        System.out.println(Arrays.deepToString(fel)
                .replace("],", "\n").replace(",", "\t| ")
                .replaceAll("[\\[\\]]", " "));
        System.out.println("");
        System.out.println("");
        System.out.println("");

    }

    public static void sort() {
        int index = 0;
        int d = 0;

        while (index != 81) {
            if (values[index / 9][index % 9] == 0) {
                //System.out.println(index);
                values[index / 9][index % 9] = 1;
            } else if (values[index / 9][index % 9] == 10) {
                values[index / 9][index % 9] = 0;
                //System.out.println("ÖÖÖÖ");
                int temp = (int) fel[index / 9][index % 9];
                int x = (int) prevX.get(temp - 2);
                int y = (int) prevY.get(temp - 2);
                //System.out.println("x = " + x + "  y = " + y);
                values[x][y] = values[x][y] + 1;
                //System.out.println(values[x][y]);
                index = x * 9 + y;
                //System.out.println(index + " s");

            } else if (checkRow(index) || checkColomn(index) || checkBox(index)) {
                // System.out.println(index + "  FEL" + " value  =" + values[index / 9][index % 9]);
                index = correct(index);
            } else {
                index++;
            }
            //d++;
        }
        
        endTime = System.nanoTime();
        
        System.out.println("TID:  " + (endTime - startTime) / 1e+9);
        
        System.out.println(Arrays.deepToString(values)
                .replace("],", "\n").replaceAll("[\\[\\]]", " "));
    }

    private static boolean checkRow(int index) {

        for (int i = 0; i < 9; i++) {
            if ((values[index / 9][i] == values[index / 9][index % 9]) && (i != index % 9)) {
                if (values[index / 9][index % 9] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkColomn(int index) {

        for (int i = 0; i < 9; i++) {
            if ((values[i][index % 9] == values[index / 9][index % 9]) && (i != index / 9)) {
                if (values[index / 9][index % 9] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkBox(int index) {
        int x = index / 9 - (index / 9) % 3;
        int y = index % 9 - (index % 9) % 3;
        // System.out.println("x = " + x +"  y = " + y);
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                //System.out.println("i = " + i +"  j = " + j);                
                if ((values[index / 9][index % 9] == values[i][j]) && (i != index / 9) && (j != index % 9)) {
                    //System.out.println("JJJJJJJ");
                    if (values[x][y] != 0) {
                        /*
                        System.out.println(values[x][y] + "  Acc");
                        System.out.println(values[i][j] + "  Acc");
                        System.out.println("i = " + i + "  j = " + j);
                         */
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int correct(int index) {
        int temp;
        int x;
        int y;
        values[index / 9][index % 9]++;
        //System.out.println("RR " + index + "  " + values[index / 9][index % 9]);
        if (values[index / 9][index % 9] == 10) {
            values[index / 9][index % 9] = 0;
            //System.out.println("QQQQ");
            temp = (int) fel[index / 9][index % 9];
            x = (int) prevX.get(temp - 2);
            y = (int) prevY.get(temp - 2);
            //System.out.println("x = " + x + "  y = " + y);
            values[x][y] = values[x][y] + 1;
            //System.out.println(values[x][y]);
            //System.out.println(index - 1);
            return x * 9 + y;
        }
        return index;
    }

}
