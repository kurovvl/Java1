package GB.Lesson4;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //
    static char[][] map; // map field
    static Scanner scanner; // console input
    static final int SIZE = 5; // map size
    static final int CELL_TO_WIN = 3;
    static final char EMPTY_CELL = ' ';
    static final char SIGN_X = 'X';
    static final char SIGN_O = 'O';
    static final int ERR_INT_VALUE = -1;
    static char userSign = EMPTY_CELL;
    static char compSign = EMPTY_CELL;
    static boolean isUserTurn = false;
    static int lastRow = ERR_INT_VALUE; // last userTurn row
    static int lastCol = ERR_INT_VALUE; // last userTurn col


    public static void main(String[] args) {
        init();
        while (!checkWin()) {
            makeTurn();
            drawMap();
        }


        if (scanner != null) scanner.close();
    }

    static void makeTurn() {
        if (isUserTurn) {
            turnUser();
        } else
            compTurn();
        isUserTurn = !isUserTurn;
    }

    static void compTurn() {
        System.out.println("Computer's turn.");
        if (blockTurn(lastRow,lastCol))
            return;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == EMPTY_CELL) {
                    map[i][j] = compSign;
                    return;
                }
            }
        }
    }

    enum direct {W, NW, N, NE, E, SE, S, SW} // compass


    static boolean blockTurn(int _row, int _col) {
        boolean isTurned = false;
        int[] weights = new int[direct.values().length];
        int[] emptyCellStep = new int[direct.values().length];
        for (int i = 0; i < direct.values().length; i++) {
            var w = getWeight(_row, _col, direct.values()[i], userSign);
            weights[i] = w[0];
            emptyCellStep[i] = w[1];
        }

        var blockDirection = maxIndex(weights, emptyCellStep);
        if (blockDirection >= 0){
            var blockCoords = getCoords(_row,_col,direct.values()[blockDirection],emptyCellStep[blockDirection]);
            map[blockCoords[0]][blockCoords[1]] = compSign;
            isTurned = true;
        }
        return isTurned;
    }
//static void printArr(int[] arr){
//    for (int i : arr) {
//        System.out.print(i+" ");
//    }
//}

    static int[] getCoords(int row, int col, direct dir, int step){
        for (int i = 1; i <= step ; i++) {
            switch (dir) {
                case W: col--; break;
                case NW: row--; col--; break;
                case N: row--; break;
                case NE: row--; col++; break;
                case E: col++; break;
                case SE: row++; col++; break;
                case S: row++; break;
                case SW: row++; col--; break;
            }
        }

        return  new int[]{row,col};
    }

    static int[] getWeight(int row, int col, direct dir, char sign) {
        int weight = 2; // start weight
        int emptyCellStep = 0;
        int step = 0;
        while (row < map.length && row >= 0 && col < map.length && col >= 0) {
            step++;
            var coords = getCoords(row, col, dir, 1); // step = 1 default
            row = coords[0];
            col = coords[1];
            if (col<0 || row <0 || col>=map.length || row>=map.length) break;
                if (map[row][col] == sign)
                    weight *= 2;
                else if (map[row][col] == EMPTY_CELL){
                    //weight++;
                    emptyCellStep = (emptyCellStep > 0 ? emptyCellStep : step);
                }
                else weight--;
        }

        return new int[]{weight,emptyCellStep};
    }


    static int maxIndex(int[] weights, int[] freeCells) {
        int max = 0; // maximum of weight
        int idx = -1;
        for (int i = 0; i < weights.length; i++) {
            var altIndex = (i+4) >= weights.length ? (i+4) % 4 : i+4;
            if (weights[i] > max && freeCells[i]>0) {
                idx = i;
                max= weights[i];
            }else if (weights[i] > max && freeCells[altIndex]>0){
                    idx = altIndex;
                    max= weights[i];
            }


        }
        return idx;
    }

    static boolean checkWin() {
        if (map == null) return false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                var sign = map[i][j];
                switch (sign) {
                    case SIGN_O:
                    case SIGN_X:
                        if (commonCheck(i, j, sign))
                            return whoWins(sign);
                }
            }
        }
        return false;
    }

    static boolean commonCheck(int row, int col, char sign) {
        return (
                sign == checkRows(row, col, sign)
                        || sign == checkColumns(row, col, sign)
                        || sign == checkDiagonalL2R(row, col, sign)
                        || sign == checkDiagonalR2L(sign, col, sign)
        );
    }

    static boolean whoWins(char sign) {
        if (sign == userSign) {
            System.out.println("Congrats! You win!");
            return true;
        }

        if (sign == compSign) {
            System.out.println("We are so sorry... You LOOSE!");
            return true;
        }

        return false;
    }

    static char checkRows(int row, int col, char what) {
        var score = 0;
        for (int c = col; c < map.length; c++) {
            if (map[row][c] == what) score++;
            else break;
        }
        return checkScore(score, what);
    }

    static char checkColumns(int row, int col, char what) {
        var score = 0;
        for (int r = row; r < map.length; r++) {
            if (map[r][col] == what) score++;
            else break;
        }

        return checkScore(score, what);
    }

    //check left to right
    static char checkDiagonalL2R(int row, int col, char what) {
        var score = 0;
        while (row < map.length && col < map.length)
            if (map[row++][col++] == what) score++;
            else break;

        return checkScore(score, what);
    }

    //check right to left
    static char checkDiagonalR2L(int row, int col, char what) {
        var score = 0;
        while (row < map.length && col >= 0)
            if (map[row++][col--] == what) score++;
            else break;

        return checkScore(score, what);
    }

    static char checkScore(int score, char what) {
        if (score >= map.length || score >= CELL_TO_WIN) return what;
        return EMPTY_CELL;
    }

    static void turnUser() {
        if (map == null) return;
        System.out.println("User! It's your turn.");
        while (true) {
            var row = getUserEnter("Type # of row: ");
            var col = getUserEnter("Type # of column: ");
            if (map[row][col] == EMPTY_CELL) {
                map[row][col] = userSign;
                lastRow = row;
                lastCol = col;
                break;
            } else System.out.println("That cell is filled!\nTry another cell!");
        }
    }

    static int getUserEnter(String header) {
        if (scanner == null) return ERR_INT_VALUE;
        while (true) {
            System.out.print(header);
            if (scanner.hasNextInt()) {
                var tmp = scanner.nextInt();
                if (tmp >= 0 && tmp <= map.length - 1) {
                    return tmp;
                }
            }
            scanner.next();
            System.out.println("You may type only digits from 0 to " + (map.length - 1));
        }
    }


    static void init() {
        scanner = new Scanner(System.in);
        fillMap();
        whoHaveFirstTurn(); // 0-49 user | 50-99 comp
    }

    static void drawMap() {
        if (map == null) return;
        System.out.print("   "); // 3 spaces == 3 signs print row #
        for (int i = 0; i < map.length; i++) {
            System.out.print(" " + i + "  "); // print column ... 1 space for '[' and 2 spaces for '] '
        }
        System.out.println(); // \n
        for (int i = 0; i < map.length; i++) {
            System.out.print(" " + (i) + " "); // print row #
            for (var a : map[i])
                System.out.print("[" + a + "] "); // print cell
            System.out.println();  // \n
        }
    }

    static void fillMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = EMPTY_CELL;
            }
        }
        drawMap();
    }

    static void whoHaveFirstTurn() {
        isUserTurn =  (new Random().nextInt(100) < 50) ;
        //isUserTurn = true; // for debug
        if (isUserTurn) initUserTurn();
        else initCompTurn();
    }
    static void initUserTurn(){
        userSign = SIGN_X;
        compSign = SIGN_O;
        System.out.println("User! Your turn is first... ");
    }
    static void initCompTurn(){
        userSign = SIGN_O;
        compSign = SIGN_X;
        System.out.println("Computer turns first...");
    }
}