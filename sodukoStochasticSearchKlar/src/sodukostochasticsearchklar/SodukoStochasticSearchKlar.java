package sodukostochasticsearchklar;

import java.util.Arrays;
import java.util.Random;

public class SodukoStochasticSearchKlar {

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
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}};
     */
    static int[][] values = new int[9][9];

    static int[][] startValues2 = new int[9][9];

    static int[] numbers = new int[10];
    static long startTime;
    static long endTime;

    public static void main(String[] args) {
        start();
        sort();
    }

    private static void start() {
        startTime = System.nanoTime();
        
        values = startValues;
        System.out.println(Arrays.deepToString(startValues)
                .replace("],", "\n").replaceAll("[\\[\\]]", " "));
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println(Arrays.deepToString(values)
                .replace("],", "\n").replaceAll("[\\[\\]]", " "));
        System.out.println("");
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers[startValues[i][j]]++;
            }
        }
        //System.out.println(Arrays.toString(numbers));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (startValues[i][j] != 0) {
                    startValues2[i][j] = 1;
                }
            }
        }

    }

    private static void sort() {
        Random ran = new Random();
        int a = 0;
        fill();

        while (test()) {
            int temp;
            int tempX = 0;
            int tempY = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (startValues2[i][j] == 0) {
                        tempX = ran.nextInt(9);
                        tempY = ran.nextInt(9);
                        while (startValues2[tempX][tempY] != 0) {
                            tempX = ran.nextInt(9);
                            tempY = ran.nextInt(9);
                        }
                        temp = values[i][j];
                        values[i][j] = values[tempX][tempY];
                        values[tempX][tempY] = temp;
                    }
                }
            }
            a++;
            if (a % 10000 == 0) {
                System.out.println(a);
                System.out.println(Arrays.deepToString(values)
                        .replace("],", "\n").replaceAll("[\\[\\]]", " "));
                System.out.println("");
                System.out.println("");
                System.out.println("");
            }
        }
        System.out.println("KLAR");
        System.out.println(Arrays.deepToString(values)
                .replace("],", "\n").replaceAll("[\\[\\]]", " "));
        System.out.println("");
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < 10; i++) {
            numbers[i] = 0;
        }
        System.out.println(Arrays.toString(numbers));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numbers[values[i][j]]++;
            }
        }
        System.out.println(Arrays.toString(numbers));

        if (test()) {
            System.out.println("fel");
        } else {
            System.out.println("ja");
        }
        System.out.println(a);

        
        endTime = System.nanoTime();
        
        System.out.println("TID:  " + (endTime - startTime) / 1e+9);
    }

    private static void fill() {
        int dsds[] = new int[10];
        int aaa[] = new int[10];
        int tempArr[] = new int[10];
        for (int i = 1; i < 10; i++) {
            tempArr[i] = 9 - numbers[i];
        }

        for (int i = 0; i < 10; i++) {
            dsds[i] = tempArr[i] + numbers[i];
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (values[i][j] == 0) {
                    for (int k = 1; k < 10; k++) {
                        if (tempArr[k] != 0) {
                            aaa[tempArr[k]]++;
                            values[i][j] = k;
                            tempArr[k]--;
                            k = 21;
                        }
                    }
                }
            }
        }
        System.out.println("OOO");
        System.out.println(Arrays.deepToString(values)
                .replace("],", "\n").replaceAll("[\\[\\]]", " "));
        System.out.println("");
        System.out.println("");
        System.out.println("");
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
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if ((values[index / 9][index % 9] == values[i][j]) && (i != index / 9) && (j != index % 9)) {
                    if (values[x][y] != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean test() {
        for (int i = 0; i < 81; i++) {
            if (checkRow(i) || checkColomn(i) || checkBox(i)) {
                return true;
            }
        }
        return false;
    }
}
