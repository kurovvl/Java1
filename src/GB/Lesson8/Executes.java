package GB.Lesson8;

public class Executes {


    static void compTurn() {
        if (checkWin()) return;
        System.out.println("Computer's turn.");

        if (blockTurn(Settings.lastRow, Settings.lastCol))
            return;
        for (int i = 0; i < Settings.map.length; i++) {
            for (int j = 0; j < Settings.map.length; j++) {
                if (Settings.map[i][j] == Settings.SignType.EMPTY_CELL) {
                    Settings.map[i][j] = Settings.compSign;
                    removeListenerByName(i + "_" + j);
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
            var w = getWeight(_row, _col, direct.values()[i], Settings.userSign);
            weights[i] = w[0];
            emptyCellStep[i] = w[1];
        }

        var blockDirection = maxIndex(weights, emptyCellStep);
        if (blockDirection >= 0) {
            var blockCoords = getCoords(_row, _col, direct.values()[blockDirection], emptyCellStep[blockDirection]);
            Settings.map[blockCoords[0]][blockCoords[1]] = Settings.compSign;
            removeListenerByName(blockCoords[0] + "_" + blockCoords[1]);
            isTurned = true;
        }
        return isTurned;
    }

    private static void removeListenerByName(String name) {
        var btn = FrameWindow.btnMap.get(name);
        btn.setText(Settings.compSign.toString());
        FrameWindow.removeListener(btn);

    }
    private static void removeAllListeners(){
        FrameWindow.btnMap.forEach((key,val)->{
            FrameWindow.removeListener(val);
        });
    }

    static int[] getCoords(int row, int col, direct dir, int step) {
        for (int i = 1; i <= step; i++) {
            switch (dir) {
                case W:
                    col--;
                    break;
                case NW:
                    row--;
                    col--;
                    break;
                case N:
                    row--;
                    break;
                case NE:
                    row--;
                    col++;
                    break;
                case E:
                    col++;
                    break;
                case SE:
                    row++;
                    col++;
                    break;
                case S:
                    row++;
                    break;
                case SW:
                    row++;
                    col--;
                    break;
            }
        }

        return new int[]{row, col};
    }

    static int[] getWeight(int row, int col, direct dir, Settings.SignType sign) {
        int weight = 2; // start weight
        int emptyCellStep = 0;
        int step = 0;
        while (row < Settings.map.length && row >= 0 && col < Settings.map.length && col >= 0) {
            step++;
            var coords = getCoords(row, col, dir, 1); // step = 1 default
            row = coords[0];
            col = coords[1];
            if (col < 0 || row < 0 || col >= Settings.map.length || row >= Settings.map.length) break;
            if (Settings.map[row][col] == sign)
                weight *= 2;
            else if (Settings.map[row][col] == Settings.SignType.EMPTY_CELL) {
                //weight++;
                emptyCellStep = (emptyCellStep > 0 ? emptyCellStep : step);
            } else weight--;
        }

        return new int[]{weight, emptyCellStep};
    }


    static int maxIndex(int[] weights, int[] freeCells) {
        int max = 0; // maximum of weight
        int idx = -1;
        for (int i = 0; i < weights.length; i++) {
            var altIndex = (i + 4) >= weights.length ? (i + 4) % 4 : i + 4;
            if (weights[i] > max && freeCells[i] > 0) {
                idx = i;
                max = weights[i];
            } else if (weights[i] > max && freeCells[altIndex] > 0) {
                idx = altIndex;
                max = weights[i];
            }


        }
        return idx;
    }

    static boolean checkWin() {
        if (Settings.map == null) return false;
        for (int i = 0; i < Settings.map.length; i++) {
            for (int j = 0; j < Settings.map.length; j++) {
                var sign = Settings.map[i][j];
                switch (sign) {
                    case O:
                    case X:
                        if (commonCheck(i, j, sign))
                            return whoWins(sign);
                }
            }
        }
        return false;
    }

    static boolean commonCheck(int row, int col, Settings.SignType sign) {
        return (
                sign == checkRows(row, col, sign)
                        || sign == checkColumns(row, col, sign)
                        || sign == checkDiagonalL2R(row, col, sign)
                        || sign == checkDiagonalR2L(row, col, sign)
        );
    }

    static boolean whoWins(Settings.SignType sign) {
        if (sign == Settings.userSign) {
            System.out.println("Congrats! You win!");
            removeAllListeners();
            return true;
        }

        if (sign == Settings.compSign) {
            System.out.println("We are so sorry... You LOOSE!");
            removeAllListeners();
            return true;
        }

        return false;
    }

    static Settings.SignType checkRows(int row, int col, Settings.SignType what) {
        var score = 0;
        for (int c = col; c < Settings.map.length; c++) {
            if (Settings.map[row][c] == what) score++;
            else break;
        }
        return checkScore(score, what);
    }

    static Settings.SignType checkColumns(int row, int col, Settings.SignType what) {
        var score = 0;
        for (int r = row; r < Settings.map.length; r++) {
            if (Settings.map[r][col] == what) score++;
            else break;
        }

        return checkScore(score, what);
    }

    //check left to right
    static Settings.SignType checkDiagonalL2R(int row, int col, Settings.SignType what) {
        var score = 0;
        while (row < Settings.map.length && col < Settings.map.length)
            if (Settings.map[row++][col++] == what) score++;
            else break;

        return checkScore(score, what);
    }

    //check right to left
    static Settings.SignType checkDiagonalR2L(int row, int col, Settings.SignType what) {
        var score = 0;
        while (row < Settings.map.length && col >= 0)
            if (Settings.map[row++][col--] == what) score++;
            else break;

        return checkScore(score, what);
    }

    static Settings.SignType checkScore(int score, Settings.SignType what) {
        if (score >= Settings.map.length || score >= Settings.CELL_TO_WIN) return what;
        return Settings.SignType.EMPTY_CELL;
    }

    static void turnUser(String name) {
        if (Settings.map == null) return;
        if (checkWin()) return;
        var rc = name.split("_");
        var row = Integer.parseInt(rc[0]);
        var col = Integer.parseInt(rc[1]);
        if (Settings.map[row][col] == Settings.SignType.EMPTY_CELL) {
            Settings.map[row][col] = Settings.userSign;
            Settings.lastRow = row;
            Settings.lastCol = col;
        } //else compTurn();
        compTurn();
    }

    static void fillMap() {
        Settings.map = new Settings.SignType[Settings.SizeMap][Settings.SizeMap];
        for (int i = 0; i < Settings.map.length; i++) {
            for (int j = 0; j < Settings.map.length; j++) {
                Settings.map[i][j] = Settings.SignType.EMPTY_CELL;
            }
        }

    }

}
