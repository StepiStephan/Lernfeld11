package VierGewinnt;

import java.util.List;
import java.util.Random;

public class GameKI {
    private FourInARowLogic kiLogic = new FourInARowLogic();
    private FourInARowPlayer player;
    private KiLevel level = KiLevel.EASY;
    private int turn;

    public int makeTurn(FourInARowLogic logic) {
        turn = -1;
        SetAktuTurn(logic);
        player = kiLogic.aktuPlayer;

        max(level.value);
        if(turn == -1)
        {
            var rand = new Random();
            turn = rand.nextInt(7);
        }
        return turn;
    }
    private int min(int depth) {
        if(depth == 0 || kiLogic.getState() == WinState.RED || kiLogic.getState() == WinState.BLUE)
        {
            return Calculate();
        }
        int bestval = 100000;
        int[] turns = GenerateTurns();

        for(int i = 0; i < turns.length; i++)
        {
            kiLogic.makeTurn(turns[i]);
            var max = max(depth-1);
            kiLogic.undoTurn(turns[i]);
            if(max < bestval) {
                bestval = max;
                if(depth == level.value)
                {
                    turn = i;
                }
            }
        }
        return bestval;
    }
    private int Calculate() {
        int result = 0;

        if(kiLogic.getState() == WinState.BLUE && FourInARowPlayer.BLUEPLAYER == player || kiLogic.getState() == WinState.RED && FourInARowPlayer.REDPLAYER == player)
        {
            return 1000;
        }
        if(kiLogic.getState() == WinState.BLUE && FourInARowPlayer.REDPLAYER == player || kiLogic.getState() == WinState.RED && FourInARowPlayer.BLUEPLAYER == player)
        {
            return -1000;
        }

        result += Calculate3();
        result += Calculate2();

        return result;
    }
    private int Calculate2() {
        int resultCounter = 0;

        for(int i = 0; i < kiLogic.field.length; i++) {
            for(int j = 0; j < kiLogic.field[i].length; j++) {
                int counter = 1;
                if(kiLogic.field[i][j] != FourInARowPlayer.EMPTY) {
                    //Horrizontal
                    if (i + 1 < kiLogic.field.length-1 && kiLogic.field[i][j] == kiLogic.field[i + 1][j]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i + 1][j] != FourInARowPlayer.EMPTY)
                            counter--;
                    }
                    //Vertical
                    if (j + 1 < kiLogic.field[i].length-1 && kiLogic.field[i][j] == kiLogic.field[i][j + 1]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i][j + 1] != FourInARowPlayer.EMPTY)
                            counter--;
                    }
                    //Diagonal Rechts
                    if (j + 1 < kiLogic.field[i].length-1 && i + 1 < kiLogic.field.length-1 && kiLogic.field[i][j] == kiLogic.field[i + 1][j + 1]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i + 1][j + 1] != FourInARowPlayer.EMPTY)
                            counter--;
                    }
                    //Diagonal Links
                    if (j + 1 < kiLogic.field[i].length-1 && i - 1 >= 0 && kiLogic.field[i][j] == kiLogic.field[i - 1][j + 1]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i - 1][j + 1] != FourInARowPlayer.EMPTY)
                            counter--;
                    }

                    resultCounter += counter;
                }
            }
        }

        return resultCounter;
    }
    private int Calculate3() {
        int resultCounter = 0;

        for(int i = 0; i < kiLogic.field.length; i++) {
            for(int j = 0; j < kiLogic.field[i].length; j++) {
                int counter = 1;
                if(kiLogic.field[i][j] != FourInARowPlayer.EMPTY) {
                    //Horrizontal
                    if (i + 2 < kiLogic.field.length-1 && kiLogic.field[i][j] == kiLogic.field[i + 1][j] && kiLogic.field[i][j] == kiLogic.field[i + 2][j]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i + 1][j] != FourInARowPlayer.EMPTY && kiLogic.field[i + 2][j] != FourInARowPlayer.EMPTY)
                            counter--;
                    }
                    //Vertical
                    if (j + 2 < kiLogic.field[i].length-1 && kiLogic.field[i][j] == kiLogic.field[i][j + 1] && kiLogic.field[i][j] == kiLogic.field[i][j + 2]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i][j + 1] != FourInARowPlayer.EMPTY && kiLogic.field[i][j + 2] != FourInARowPlayer.EMPTY)
                            counter--;
                    }
                    //Diagonal Rechts
                    if (j + 2 < kiLogic.field[i].length-1 && i + 2 < kiLogic.field.length-1 && kiLogic.field[i][j] == kiLogic.field[i + 1][j + 1] && kiLogic.field[i][j] == kiLogic.field[i + 2][j + 2]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i + 1][j + 1] != FourInARowPlayer.EMPTY && kiLogic.field[i + 2][j + 2] != FourInARowPlayer.EMPTY)
                            counter--;
                    }
                    //Diagonal Links
                    if (j + 2 < kiLogic.field[i].length-1 && i - 2 >= 0 && kiLogic.field[i][j] == kiLogic.field[i - 1][j + 1] && kiLogic.field[i][j] == kiLogic.field[i + 2][j + 2]) {
                        if (kiLogic.aktuPlayer == player)
                            counter++;
                        else if (kiLogic.field[i - 1][j + 1] != FourInARowPlayer.EMPTY && kiLogic.field[i - 2][j + 2] != FourInARowPlayer.EMPTY)
                            counter -= 3;
                    }

                    resultCounter += counter;
                }
            }
        }

        return resultCounter*3;
    }
    private int[] GenerateTurns() {
        int count = 0;
        int length = kiLogic.field[0].length-1;
        for(int i = 0; i < kiLogic.field.length; i++)
            if(kiLogic.field[i][length] == FourInARowPlayer.EMPTY)
                count++;

        int[] result = new int[count];
        for(int i = 0; i < count; i++)
            if(kiLogic.field[i][length] == FourInARowPlayer.EMPTY)
                result[i] = i;

        return result;
    }
    private int max(int depth) {
        if(depth == 0 || kiLogic.getState() == WinState.RED || kiLogic.getState() == WinState.BLUE)
        {
            return Calculate();
        }
        int bestval = -100000;

        int[] turns = GenerateTurns();

        for(int i = 0; i < turns.length; i++)
        {
            kiLogic.makeTurn(turns[i]);
            var min = min(depth-1);
            kiLogic.undoTurn(turns[i]);
            if(min > bestval) {
                bestval = min;
                if(depth == level.value)
                {
                    turn = i;
                }
            }
        }
        return bestval;
    }
    private void SetAktuTurn(FourInARowLogic logic) {
        for(int i = 0; i < kiLogic.field.length; i++)
            for(int j = 0; j < kiLogic.field[i].length; j++)
                kiLogic.field[i][j] = logic.field[i][j];
        kiLogic.aktuPlayer = logic.aktuPlayer;
    }
    public void setLevel(KiLevel level) {
        this.level = level;
    }
}
