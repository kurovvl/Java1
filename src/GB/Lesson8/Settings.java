package GB.Lesson8;

import java.util.Scanner;

public class Settings {
    static enum SignType {EMPTY_CELL, X, O}


    static SignType[][] map; // map field
    static int SizeMap = 5; // map size
    static int CELL_TO_WIN = 3;

    static final int ERR_INT_VALUE = -1;

    public static void setUserSign(SignType userSign) {
        Settings.userSign = userSign;
        if (userSign == SignType.X)
            compSign = SignType.O;
        else
            compSign = SignType.X;

    }

    static SignType userSign = SignType.X;
    static SignType compSign = SignType.O;
    static boolean isUserTurn = false;
    static int lastRow = ERR_INT_VALUE; // last userTurn row
    static int lastCol = ERR_INT_VALUE; // last userTurn col
}
