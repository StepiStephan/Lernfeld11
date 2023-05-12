package VierGewinnt;

public class FourInARowLogic {
    public FourInARowPlayer[][] field = new FourInARowPlayer[7][6];
    public FourInARowPlayer aktuPlayer;
    private WinState state;

    public FourInARowLogic(){
        reset();
    }

    public void reset() {
        for(int i = 0; i < 7; i++)
            for(int j = 0; j< 6; j++)
                field[i][j] = FourInARowPlayer.EMPTY;
        aktuPlayer = FourInARowPlayer.REDPLAYER;
        state = WinState.RUN;
    }

    public Tuple3<Integer, Integer, FourInARowPlayer> makeTurn(int col) {
        int y = 0;
        
        while(y < field[col].length) {
            if (field[col][y] == FourInARowPlayer.EMPTY) {
                field[col][y] = aktuPlayer;
                break;
            }
            y++;
        }

        var trunTuple = new Tuple3<Integer, Integer, FourInARowPlayer>(col, y, aktuPlayer);
        won();

        if(y != field[col].length) {
            if (aktuPlayer == FourInARowPlayer.REDPLAYER)
                aktuPlayer = FourInARowPlayer.BLUEPLAYER;
            else
                aktuPlayer = FourInARowPlayer.REDPLAYER;
        }

        return trunTuple;
    }

    private void won() {
        boolean hasEmptyFields = false;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++) {
                if(field[i][j] != FourInARowPlayer.EMPTY)
                {
                    int count = checkNextFields(field[i][j], i, j);
                    if(count >= 4)
                    {
                        if(field[i][j] == FourInARowPlayer.BLUEPLAYER)
                            state = WinState.BLUE;
                        else
                            state = WinState.RED;
                        aktuPlayer = FourInARowPlayer.EMPTY;
                    }
                }
                else
                {
                    hasEmptyFields = true;
                }
            }
        }
        if(!hasEmptyFields && state == WinState.RUN)
            state = WinState.DRAW;

    }

    private int checkNextFields(FourInARowPlayer fourInARowPlayer, int row, int col) {
        int count = 0;

        count = horrizontalCheck(fourInARowPlayer, row, col, count);
        if(count >= 4)
            return count;
        else
            count = 0;

        count = verticalCheck(fourInARowPlayer, row, col, count);
        if(count >= 4)
            return count;
        else
            count = 0;

        count = topLeftCheck(fourInARowPlayer, row, col, count);
        if(count >= 4)
            return count;
        else
            count = 0;

        count = topRightCheck(fourInARowPlayer, row, col, count);
        if(count >= 4)
            return count;
        else
            count = 0;
        return count;
    }

    private int topRightCheck(FourInARowPlayer fourInARowPlayer, int row, int col, int count) {
        for(int i = 0; i < field.length; i++) {
            if(row +i >= field.length-1 || col +i >= field[row].length-1) {
                count = 0;
                break;
            }
            if (fourInARowPlayer == field[row +i][col +i])
                count++;
            else
                count = 0;

            if(count == 4)
                break;
        }
        return count;
    }

    private int topLeftCheck(FourInARowPlayer fourInARowPlayer, int row, int col, int count) {
        for(int i = 0; i < field.length; i++) {
            if(row -i < 0 || col +i >= field[row].length-1) {
                count = 0;
                break;
            }
            if (fourInARowPlayer == field[row -i][col +i])
                count++;
            else
                count = 0;

            if(count == 4)
                break;
        }
        return count;
    }

    private int verticalCheck(FourInARowPlayer fourInARowPlayer, int row, int col, int count) {
        for(int i = col; i < field[row].length; i++) {
            if (fourInARowPlayer == field[row][i])
                count++;
            else
                count = 0;

            if(count == 4)
                break;
        }
        return count;
    }

    private int horrizontalCheck(FourInARowPlayer fourInARowPlayer, int row, int col, int count) {
        for(int i = row; i < field.length; i++) {
            if (fourInARowPlayer == field[i][col])
                count++;
            else
                count = 0;

            if(count == 4)
                break;
        }
        return count;
    }

    public WinState getState() {
        return state;
    }

    public void undoTurn(int col) {
        for(int i = 0; i < field[col].length; i++)
            if(field[col][i] != FourInARowPlayer.EMPTY)
                field[col][i] = FourInARowPlayer.EMPTY;

        if (aktuPlayer == FourInARowPlayer.REDPLAYER)
            aktuPlayer = FourInARowPlayer.BLUEPLAYER;
        else
            aktuPlayer = FourInARowPlayer.REDPLAYER;
    }
}
