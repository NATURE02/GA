package sodukopenandpaperklar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Sort {

    static Object[][] possibleValues = new Object[9][9]; //[y][x]
    static int values[][] = new int[9][9];  //[y][x]
    static boolean changed = true;

    static ArrayList<Object[][]> possibleValuesList = new ArrayList();
    static ArrayList<int[][]> valuesList = new ArrayList();
    static ArrayList<Integer> xValues = new ArrayList();
    static ArrayList<Integer> yValues = new ArrayList();
    static ArrayList<Integer> tryNum = new ArrayList();
    static ArrayList<int[]> tryArr = new ArrayList();

    static ArrayList<int[][]> test = new ArrayList();

    static int numbersDeep = 0;
    static long endTime;

    public Sort(Object[][] possibleValues, int values[][]) {
        this.possibleValues = possibleValues;
        this.values = values;
        //printAll();
        skapa();
        xValues.add(null);
        yValues.add(null);
        tryNum.add(null);
        tryArr.add(null);
        numbersDeep++;
    }

    public static void startSort() {
        while (changed) {
            changed = false;
            sort();
            SodukoPenAndPaperKlar.tries++;
            //printAll();
        }
        
        System.out.println(Arrays.deepToString(SodukoPenAndPaperKlar.startValues)
                .replace("],", "\n")
                .replaceAll("[\\[\\]]", " "));
        System.out.println("");
        System.out.println("");
        System.out.println("");

        if (Arrays.deepEquals(SodukoPenAndPaperKlar.startValues, values)) {
            System.out.println("JA SAMMA");
        } else {
            System.out.println("NEJ OLIKA");
        }
        if (correct()) {
            System.out.println("RÄTT");
        } else {
            System.out.println("FEL");
        }
        
        endTime = System.nanoTime();
        
        System.out.println("TID:  " + (endTime - SodukoPenAndPaperKlar.startTime) / 1e+9);
         
    }

    private static void sort() {

        //System.out.println("sortLoneSingle");
        sortLoneSingle();
        //System.out.println("sortSingle");
        sortSingle();
        if (changed == false) {
            //System.out.println("NAKED");
            sortNakedPairs();
        }
        if (changed == false) {
            //System.out.println("HIDDEN");
            sortHiddenPairs();
        }
        if (changed == false) {
            if (finished()) {
                //System.out.println("klar");
            } else {
                //System.out.println("inte klar");

                if (solvable()) {
                    //System.out.println("DEN ÄR LÖSBAR");
                    findShortest();
                    changed = true;
                } else {
                    //System.out.println("DEN ÄR INTE LÖSBAR");
                    newRoute();
                }

            }
        }
    }

    //SINGEL SAKER
    private static void sortSingle() {
        sortSingleBox();
        sortSingleRow();
        sortSingelColomn();
    }

    private static void sortSingleBox() {
        int a = 0;
        int num = 0;
        int xPos = 0;
        int yPos = 0;

        for (int x = 0; x < 7; x += 3) {  // Singel BOX
            for (int y = 0; y < 7; y += 3) {
                for (int i = 1; i < 10; i++) {  //nummer
                    num = 0;
                    for (int j = 0; j < 9; j++) { //possision
                        Set temp = (Set) possibleValues[y + j / 3][x + j % 3];
                        if (temp != null) {
                            if (temp.contains(i)) {
                                num++;
                                xPos = x + j % 3;
                                yPos = y + j / 3;
                            }
                        }
                    }
                    if (num == 1) {
                        values[yPos][xPos] = i;
                        possibleValues[yPos][xPos] = null;
                        //System.out.println(" X = " + xPos + "   Y = " + yPos + "   " + i);
                        remove(xPos, yPos, i);
                        a++;
                        changed = true;
                    }
                }
            }
        }
        //System.out.println("sortSingleBox är = " + a);
    }

    private static void sortSingleRow() {
        int a = 0;
        int num = 0;
        int xPos = 0;
        int yPos = 0;

        for (int y = 0; y < 9; y++) {
            for (int i = 1; i < 10; i++) {  //nummer
                num = 0;
                for (int j = 0; j < 9; j++) { //possision
                    Set temp = (Set) possibleValues[y][j];
                    if (temp != null) {
                        if (temp.contains(i)) {
                            num++;
                            xPos = j;
                            yPos = y;
                        }
                    }
                }
                if (num == 1) {
                    values[yPos][xPos] = i;
                    possibleValues[yPos][xPos] = null;
                    //System.out.println(" X = " + xPos + "   Y = " + yPos + "   " + i + "  ÖÖ");
                    remove(xPos, yPos, i);
                    a++;
                    changed = true;
                }
            }
        }
        //System.out.println("sortSingleRow är = " + a);
    }

    private static void sortSingelColomn() {
        int a = 0;
        int num = 0;
        int xPos = 0;
        int yPos = 0;

        for (int x = 0; x < 9; x++) {
            for (int i = 1; i < 10; i++) {  //nummer
                num = 0;
                for (int j = 0; j < 9; j++) { //possision
                    Set temp = (Set) possibleValues[j][x];
                    if (temp != null) {
                        if (temp.contains(i)) {
                            num++;
                            xPos = x;
                            yPos = j;
                        }
                    }
                }
                if (num == 1) {
                    values[yPos][xPos] = i;
                    possibleValues[yPos][xPos] = null;
                    //System.out.println(" X = " + xPos + "   Y = " + yPos + "   " + i);
                    remove(xPos, yPos, i);
                    a++;
                    changed = true;
                }
            }
        }
        //System.out.println("sortSingleColomn är = " + a);
    }

    //PAAAR
    private static void sortHiddenPairs() {
        sortHiddenPairsBox();
        sortHiddenPairsRow();
        sortHiddenPairsColomn();
    }

    private static void sortHiddenPairsBox() {
        int a = 0;
        for (int x = 0; x < 7; x += 3) {
            for (int y = 0; y < 7; y += 3) {
                Set union = new java.util.HashSet();
                for (int i = 0; i < 9; i++) {
                    if (possibleValues[y + i / 3][x + i % 3] != null) {
                        union.addAll((Set) possibleValues[y + i / 3][x + i % 3]);

                    }
                }
                if (union != null) {
                    for (int i = 1; i < 9; i++) {
                        if (union.contains(i)) {
                            for (int j = i + 1; j < 10; j++) {
                                if (union.contains(j)) {
                                    int ant = 0;
                                    int x1 = 0;
                                    int y1 = 0;
                                    int x2 = 0;
                                    int y2 = 0;
                                    for (int k = 0; k < 9; k++) {
                                        Set temp = (Set) possibleValues[y + k / 3][x + k % 3];
                                        if (temp != null) {
                                            if (temp.contains(i) || temp.contains(j)) {
                                                ant++;
                                                if (ant == 1) {
                                                    x1 = x + k % 3;
                                                    y1 = y + k / 3;
                                                } else if (ant == 2) {
                                                    x2 = x + k % 3;
                                                    y2 = y + k / 3;
                                                } else if (ant > 2) {
                                                    k = 10;
                                                }
                                            }
                                        }
                                    }
                                    if (ant == 2) {
                                        a++;
                                        Set group = new java.util.HashSet();
                                        group.add(i);
                                        group.add(j);
                                        //System.out.println("OOOOOO " + " x1 = " + x1 + " y1 = " + y1 + "   x2 = " + x2 + " y2 = " + y2 + " group " + group);
                                        removeHiddenPairs(x1, y1, x2, y2, group);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("sortHiddenPairsBox är = " + a);
    }

    private static void sortHiddenPairsRow() {
        int a = 0;
        for (int y = 0; y < 9; y++) {
            Set union = new java.util.HashSet();
            for (int x = 0; x < 9; x++) {
                if (possibleValues[y][x] != null) {
                    union.addAll((Set) possibleValues[y][x]);
                }
            }
            if (union != null) {
                for (int i = 1; i < 9; i++) {
                    if (union.contains(i)) {
                        for (int j = i + 1; j < 10; j++) {
                            if (union.contains(j)) {
                                int ant = 0;
                                int x1 = 0;
                                int y1 = 0;
                                int x2 = 0;
                                int y2 = 0;
                                for (int x = 0; x < 9; x++) {
                                    Set temp = (Set) possibleValues[y][x];
                                    if (temp != null) {
                                        if (temp.contains(i) || temp.contains(j)) {
                                            ant++;
                                            if (ant == 1) {
                                                x1 = x;
                                                y1 = y;
                                            } else if (ant == 2) {
                                                x2 = x;
                                                y2 = y;
                                            } else if (ant > 2) {
                                                x = 10;
                                            }
                                        }
                                    }
                                }

                                if (ant == 2) {
                                    a++;
                                    Set group = new java.util.HashSet();
                                    group.add(i);
                                    group.add(j);
                                    //System.out.println("OOOOOO " + " x1 = " + x1 + " y1 = " + y1 + "   x2 = " + x2 + " y2 = " + y2 + " group " + group);
                                    removeHiddenPairs(x1, y1, x2, y2, group);
                                }

                            }
                        }
                    }
                }
            }
        }
        //System.out.println("sortHiddenPairsRow är = " + a);
    }

    private static void sortHiddenPairsColomn() {

        int a = 0;
        for (int x = 0; x < 9; x++) {
            Set union = new java.util.HashSet();
            for (int y = 0; y < 9; y++) {
                if (possibleValues[y][x] != null) {
                    union.addAll((Set) possibleValues[y][x]);
                }
            }
            if (union != null) {
                for (int i = 1; i < 9; i++) {
                    if (union.contains(i)) {
                        for (int j = i + 1; j < 10; j++) {
                            if (union.contains(j)) {
                                int ant = 0;
                                int x1 = 0;
                                int y1 = 0;
                                int x2 = 0;
                                int y2 = 0;
                                for (int y = 0; y < 9; y++) {
                                    Set temp = (Set) possibleValues[y][x];
                                    if (temp != null) {
                                        if (temp.contains(i) || temp.contains(j)) {
                                            ant++;
                                            if (ant == 1) {
                                                x1 = x;
                                                y1 = y;
                                            } else if (ant == 2) {
                                                x2 = x;
                                                y2 = y;
                                            } else if (ant > 2) {
                                                y = 10;
                                            }
                                        }

                                    }
                                }

                                if (ant == 2) {
                                    a++;
                                    Set group = new java.util.HashSet();
                                    group.add(i);
                                    group.add(j);
                                    //System.out.println("OOOOOO " + " x1 = " + x1 + " y1 = " + y1 + "   x2 = " + x2 + " y2 = " + y2 + " group " + group);
                                    removeHiddenPairs(x1, y1, x2, y2, group);
                                }

                            }
                        }
                    }
                }
            }
        }
        //System.out.println("sortHiddenPairsColomn är = " + a);
    }

    //PARIS SAKER
    private static void sortNakedPairs() {
        sortNakedPairsBox();
        sortNakedPairsRow();
        sortNakedPairsColomn();

    }

    private static void sortNakedPairsBox() {
        int a = 0;
        for (int x = 0; x < 7; x += 3) {  // Naked BOX
            for (int y = 0; y < 7; y += 3) {
                for (int i = 0; i < 8; i++) {
                    Set temp1 = (Set) possibleValues[y + i / 3][x + i % 3];
                    if (temp1 != null) {
                        if (temp1.size() <= 2) {
                            for (int j = i + 1; j < 9; j++) {
                                Set temp2 = (Set) possibleValues[y + j / 3][x + j % 3];
                                if (temp2 != null) {
                                    Set union = new java.util.HashSet();
                                    union.addAll(temp1);
                                    union.addAll(temp2);
                                    if (union.size() == 2) {
                                        //System.out.println(union);
                                        //System.out.println("x 1 = " + (x + i % 3) + " " + "y 1 = " + (y + i / 3));
                                        //System.out.println("x 2 = " + (x + j % 3) + " " + "y 2 = " + (y + j / 3));
                                        removeNakedPairsBox(x + i % 3, y + i / 3, x + j % 3, y + j / 3, union);
                                        a++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("sortNakedPairsBox är = " + a);
    }

    private static void sortNakedPairsRow() {
        int a = 0;
        for (int y = 0; y < 9; y++) {
            for (int i = 0; i < 8; i++) {
                Set temp1 = (Set) possibleValues[y][i];
                if (temp1 != null) {
                    for (int j = i + 1; j < 9; j++) {
                        Set temp2 = (Set) possibleValues[y][j];
                        if (temp2 != null) {
                            Set union = new java.util.HashSet();
                            union.addAll(temp1);
                            union.addAll(temp2);
                            if (union.size() == 2) {
                                //System.out.println(union);
                                //System.out.println("x 1 = " + i + " " + "y 1 = " + y);
                                //System.out.println("x 2 = " + j + " " + "y 2 = " + y);
                                removeNakedPairsRow(i, y, j, y, union);
                                a++;
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("sortNakedPairsRow är = " + a);
    }

    private static void sortNakedPairsColomn() {
        int a = 0;
        for (int x = 0; x < 9; x++) {
            for (int i = 0; i < 8; i++) {
                Set temp1 = (Set) possibleValues[i][x];
                if (temp1 != null) {
                    for (int j = i + 1; j < 9; j++) {
                        Set temp2 = (Set) possibleValues[j][x];
                        if (temp2 != null) {
                            Set union = new java.util.HashSet();
                            union.addAll(temp1);
                            union.addAll(temp2);
                            if (union.size() == 2) {
                                //System.out.println(union);
                                //System.out.println("x 1 = " + x + " " + "y 1 = " + i);
                                //System.out.println("x 2 = " + x + " " + "y 2 = " + j);
                                removeNakedPairsColomn(x, i, x, j, union);
                                a++;
                            }
                        }
                    }
                }
            }
        }
        //System.out.println("sortNakedPairsColomn är = " + a);
    }

    //REMOVE SAKER
    private static void remove(int x, int y, int k) {
        //System.out.println("ÖÖÖÖ" + "  REM");
        int xOffser = (x / 3) * 3;
        int yOffser = (y / 3) * 3;

        for (int i = 0; i < 9; i++) { //removes ROW
            Set temp = (Set) possibleValues[y][i];
            if (temp != null && temp.contains(k)) {
                //System.out.println(" X = " + (i) + "   Y = " + (y) + "   " + k);
                temp.remove(k);
            }

        }

        for (int i = 0; i < 9; i++) { //removes COLOMN
            Set temp = (Set) possibleValues[i][x];
            if (temp != null && temp.contains(k)) {
                //System.out.println(" X = " + (x) + "   Y = " + (i) + "   " + k);
                temp.remove(k);
            }

        }

        for (int i = 0; i < 3; i++) {    //removes BOX
            for (int j = 0; j < 3; j++) {
                Set temp = (Set) possibleValues[yOffser + j][xOffser + i];
                if (temp != null && temp.contains(k)) {
                    //System.out.println(" X = " + (xOffser + i) + "   Y = " + (yOffser + j) + "   " + k);
                    temp.remove(k);
                }
            }
        }
        //System.out.println("ÖÖÖÖ" + "  REM");
        //printAll();
    }

    private static void removeNakedPairsBox(int x1, int y1, int x2, int y2, Set union) {
        int xOffser = (x1 / 3) * 3;
        int yOffser = (y1 / 3) * 3;

        for (int i = 0; i < 3; i++) {    //removes BOX
            for (int j = 0; j < 3; j++) {
                Set temp = (Set) possibleValues[yOffser + j][xOffser + i];
                if (temp != null) {
                    if ((xOffser + i == x1 && yOffser + j == y1) || (xOffser + i == x2 && yOffser + j == y2)) {

                    } else {
                        if (temp.removeAll(union)) {
                            changed = true;
                        }
                    }
                }
            }
        }
    }  //FIXA

    private static void removeNakedPairsRow(int x1, int y1, int x2, int y2, Set union) {
        for (int i = 0; i < 9; i++) {
            Set temp = (Set) possibleValues[y1][i];
            if (temp != null) {
                if (x1 == i || x2 == i) {

                } else {
                    if (temp.removeAll(union)) {
                        changed = true;
                    }
                }
            }
        }
    }

    private static void removeNakedPairsColomn(int x1, int y1, int x2, int y2, Set union) {
        for (int i = 0; i < 9; i++) {
            Set temp = (Set) possibleValues[i][x1];
            if (temp != null) {
                if (y1 == i || y2 == i) {

                } else {
                    if (temp.removeAll(union)) {
                        changed = true;
                    }
                }
            }
        }
    }

    private static void removeHiddenPairs(int x1, int y1, int x2, int y2, Set group) {
        Set toRemove = new java.util.HashSet();
        for (int k = 1; k < 10; k++) {
            toRemove.add(k);
        }
        toRemove.removeAll(group);
        if (group.containsAll((Set) possibleValues[y1][x1]) && group.containsAll((Set) possibleValues[y2][x2])) {
            //System.out.println("Klar");
            //System.out.println("");
        } else {

            Set temp1 = (Set) possibleValues[y1][x1];
            temp1.removeAll(toRemove);
            Set temp2 = (Set) possibleValues[y2][x2];
            temp2.removeAll(toRemove);
            changed = true;
            //System.out.println("Ändrad");
            //System.out.println("");
        }
    }

    //ÖVRIGT
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
        
        System.out.println("Försök " + SodukoPenAndPaperKlar.tries + " " + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
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

    private static void sortLoneSingle() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Set temp = (Set) possibleValues[j][i];
                if (temp != null) {
                    if (temp.size() == 1) {
                        //System.out.println("x = " + i + "  y = " + j); 
                        for (int k = 1; k < 10; k++) {
                            if (temp.contains(k)) {
                                //printAll();
                                values[j][i] = k;
                                possibleValues[j][i] = null;
                                //System.out.println(" X = " + i + "   Y = " + j + "   " + k);
                                remove(i, j, k);
                                changed = true;
                                //printAll();
                            }
                        }
                    }
                }
            }
        }
    }  //ONÖDIG???

    public static boolean finished() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (values[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean correct() {
        for (int i = 0; i < 9; i++) {
            int[] arr = new int[10];
            arr[0] = 1;
            for (int j = 0; j < 9; j++) {
                arr[values[i][j]]++;
            }
            for (int j = 0; j < 9; j++) {
                if (arr[i] != 1) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            int[] arr = new int[10];
            arr[0] = 1;
            for (int j = 0; j < 9; j++) {
                arr[values[j][i]]++;
            }
            for (int j = 0; j < 9; j++) {
                if (arr[i] != 1) {
                    return false;
                }
            }
        }

        for (int x = 0; x < 7; x += 3) {  // Singel BOX
            for (int y = 0; y < 7; y += 3) {
                for (int i = 1; i < 10; i++) {  //nummer
                    int num = 0;
                    for (int j = 0; j < 9; j++) { //possision
                        if (values[y + j / 3][x + j % 3] == i) {
                            num++;
                        }
                    }
                    if (num != 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean solvable() {
        Set all = new java.util.HashSet();
        for (int i = 1; i < 10; i++) {
            all.add(i);
        }

        for (int i = 0; i < 9; i++) {
            Set group = new java.util.HashSet();
            for (int j = 0; j < 9; j++) {
                if (values[i][j] != 0) {
                    group.add(values[i][j]);
                }
            }
            for (int j = 0; j < 9; j++) {
                if (possibleValues[i][j] != null) {
                    group.addAll((Set) possibleValues[i][j]);
                }
            }
            if (!group.containsAll(all)) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            Set group = new java.util.HashSet();
            for (int j = 0; j < 9; j++) {
                if (values[j][i] != 0) {
                    group.add(values[j][i]);
                }
            }
            for (int j = 0; j < 9; j++) {
                if (possibleValues[j][i] != null) {
                    group.addAll((Set) possibleValues[j][i]);
                }
            }
            if (!group.containsAll(all)) {
                return false;
            }
        }

        for (int x = 0; x < 7; x += 3) {  // Singel BOX
            for (int y = 0; y < 7; y += 3) {
                Set group = new java.util.HashSet();
                for (int j = 0; j < 9; j++) { //possision
                    if (values[y + j / 3][x + j % 3] != 0) {
                        group.add(values[y + j / 3][x + j % 3]);
                    }
                }
                for (int j = 0; j < 9; j++) {
                    if (possibleValues[y + j / 3][x + j % 3] != null) {
                        group.addAll((Set) possibleValues[y + j / 3][x + j % 3]);
                    }
                }
                if (!group.containsAll(all)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void findShortest() {
        int length = 10;
        int xPos = 0;
        int yPos = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Set temp = (Set) possibleValues[i][j];
                if (temp != null) {
                    if (temp.size() < length) {
                        length = temp.size();
                        xPos = j;
                        yPos = i;
                    }
                }
            }
        }
        //System.out.println(" x = " + xPos + " y = " + yPos + " group " + (Set) possibleValues[yPos][xPos]);

        skapa();

        int size = ((Set) possibleValues[yPos][xPos]).size();
        int[] tempVal = new int[size];
        int order = 0;
        for (int i = 1; i < 10; i++) {
            if (((Set) possibleValues[yPos][xPos]).contains(i)) {
                tempVal[order] = i;
                order++;
            }
        }
        //System.out.println("tempVal = " + Arrays.toString(tempVal));
        int noll = 0;

        values[yPos][xPos] = tempVal[0];
        possibleValues[yPos][xPos] = null;
        remove(xPos, yPos, tempVal[0]);
        xValues.add(xPos);
        yValues.add(yPos);
        tryNum.add(0);
        tryArr.add(tempVal);
        numbersDeep++;

        //System.out.println("ÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅd");
        //printAll();
        //System.out.println("ÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅ");
    }

    private static void skapa() {
        //System.out.println("SKAPAR  " + numbersDeep);
        Object[][] tempPossibleValues = new Object[9][9];
        int tempValues[][] = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Set temp1 = new java.util.HashSet();
                Set temp2 = (Set) possibleValues[i][j];
                if (temp2 != null) {
                    temp1.addAll(temp2);
                }

                tempPossibleValues[i][j] = temp1;
                tempValues[i][j] = values[i][j];
            }
        }

        possibleValuesList.add(tempPossibleValues);
        valuesList.add(tempValues);
    }

    private static void newRoute() {
        //System.out.println(" FIXXAR  NUM DEAP =  " + numbersDeep);

        Object[][] tempPossibleValues = new Object[9][9];
        int tempValues[][] = new int[9][9];

        tempPossibleValues = possibleValuesList.get(numbersDeep - 1);
        tempValues = valuesList.get(numbersDeep - 1);
        /*
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Set temp1 = new java.util.HashSet();
                Set temp2 = (Set) tempPossibleValues[i][j];
                if (temp2 != null) {
                    temp1.addAll(temp2);
                }
                possibleValues[i][j] = temp1;
                values[i][j] = tempValues[i][j];
            }
        }
         */
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                values[i][j] = tempValues[i][j];
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (values[j][i] == 0) {
                    Set temp = new java.util.HashSet();
                    for (int k = 1; k < 10; k++) {
                        temp.add(k);
                    }
                    possibleValues[j][i] = temp;
                }
            }
        }

        startRow();
        startColomn();
        startBox();

        /*
        
        printAll();

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

*/
        
        int tempVal[] = tryArr.get(numbersDeep - 1);
        int length = tempVal.length;
        int xPos = xValues.get(numbersDeep - 1);
        int yPos = yValues.get(numbersDeep - 1);
        int tryNumer = tryNum.get(numbersDeep - 1);

        System.out.println(length + " " + Arrays.toString(tempVal) + "  x = " + xPos + " y = " + yPos);

        if (tryNumer < length - 1) {
            tryNumer++;
            System.out.println("JA " + tryNumer);
            values[yPos][xPos] = tempVal[tryNumer];

            tryNum.remove(numbersDeep - 1);
            tryNum.add(tryNumer);
            possibleValues[yPos][xPos] = null;
            //System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
            remove(xPos, yPos, tempVal[tryNumer]);
            changed = true;

            //System.out.println("ÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖÖ");
            //printAll();
            if (solvable()) {
                //System.out.println("LÖSBAR");
            } else {
                //System.out.println("INTE LÖSBAR");
            }
          // System.out.println("ÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅ");

        } else {
            //System.out.println("Nej " + tryNumer);
            possibleValuesList.remove(numbersDeep - 1);
            valuesList.remove(numbersDeep - 1);
            xValues.remove(numbersDeep - 1);
            yValues.remove(numbersDeep - 1);
            tryNum.remove(numbersDeep - 1);
            tryArr.remove(numbersDeep - 1);
            numbersDeep--;
            changed = true;
/*
            System.out.println("ÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅ");
            printAll();
            System.out.println("ÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅÅ");
*/
            newRoute();
        }

    }

    private static void startRow() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (values[j][i] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int l = 0; l < 9; l++) {
                            if (values[j][l] == k) {
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
                if (values[j][i] == 0) {
                    for (int k = 1; k < 10; k++) {
                        for (int l = 0; l < 9; l++) {
                            if (values[l][i] == k) {
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
                if (values[j][i] == 0) {
                    int offsetX = (i / 3) * 3;
                    int offsetY = (j / 3) * 3;
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            for (int k = 1; k < 10; k++) {
                                if (values[y + offsetY][x + offsetX] == k) {
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

}
