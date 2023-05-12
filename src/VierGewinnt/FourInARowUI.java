package VierGewinnt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FourInARowUI extends JFrame{
    private JPanel mainFrame;
    private JButton btReset;
    private JTextArea tAr5c1;
    private JTextArea tAr5c7;
    private JTextArea tAr5c6;
    private JTextArea tAr5c2;
    private JTextArea tAr5c3;
    private JTextArea tAr5c4;
    private JTextArea tAr5c5;
    private JTextArea tAr4c6;
    private JTextArea tAr3c6;
    private JTextArea tAr0c6;
    private JTextArea tAr2c6;
    private JTextArea tAr1c6;
    private JTextArea tAr4c7;
    private JButton btC1;
    private JButton btC2;
    private JButton btC3;
    private JButton btC4;
    private JButton btC5;
    private JButton btC6;
    private JButton btC7;
    private JTextArea tAr4c1;
    private JTextArea tAr4c2;
    private JTextArea tAr4c3;
    private JTextArea tAr4c4;
    private JTextArea tAr4c5;
    private JTextArea tAr3c1;
    private JTextArea tAr3c2;
    private JTextArea tAr3c3;
    private JTextArea tAr3c4;
    private JTextArea tAr3c5;
    private JTextArea tAr3c7;
    private JTextArea tAr2c1;
    private JTextArea tAr2c2;
    private JTextArea tAr2c3;
    private JTextArea tAr2c4;
    private JTextArea tAr2c5;
    private JTextArea tAr2c7;
    private JTextArea tAr1c1;
    private JTextArea tAr1c2;
    private JTextArea tAr1c3;
    private JTextArea tAr1c4;
    private JTextArea tAr1c5;
    private JTextArea tAr1c7;
    private JTextArea tAr0c1;
    private JTextArea tAr0c2;
    private JTextArea tAr0c3;
    private JTextArea tAr0c4;
    private JTextArea tAr0c5;
    private JTextArea tAr0c7;
    private JLabel winner;
    private JRadioButton rbComPlayer;
    private JComboBox cbLevel;
    FourInARowLogic logic;
    GameKI ki;
    boolean useKi = false;
    public FourInARowUI(){
        logic = new FourInARowLogic();
        ki = new GameKI();

        setContentPane(mainFrame);
        setSize(getPreferredSize());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialize();
    }
    private void initialize() {
        rbComPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useKi = rbComPlayer.isSelected();
                cbLevel.setEnabled(useKi);
            }
        });
        cbLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KiLevel level = KiLevel.EASY;
                switch((String)cbLevel.getSelectedItem()){
                    case "MEDIUM":
                        level = KiLevel.MEDIUM;
                        break;
                    case "HARD":
                        level = KiLevel.HARD;
                        break;
                    case "VARYHARD":
                        level = KiLevel.VERYHARD;
                        break;
                    default:
                        level = KiLevel.EASY;
                }
                ki.setLevel(level);
            }
        });
        btC1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTurn(0);
            }
        });
        btC2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTurn(1);
            }
        });
        btC3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTurn(2);
            }
        });
        btC4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTurn(3);
            }
        });
        btC5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTurn(4);
            }
        });
        btC6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTurn(5);
            }
        });
        btC7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTurn(6);
            }
        });

        btReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }
    private void makeTurn(int col) {
        if(logic.getState() == WinState.RUN) {
            Tuple3<Integer, Integer, FourInARowPlayer> coordinates = logic.makeTurn(col);
            setPlayerColor(coordinates.getVal3(), coordinates.getVal1(), coordinates.getVal2());
        }
        if(useKi)
        {
            int kiTurn = ki.makeTurn(logic);
            Tuple3<Integer, Integer, FourInARowPlayer> coordinates = logic.makeTurn(kiTurn);
            setPlayerColor(coordinates.getVal3(), coordinates.getVal1(), coordinates.getVal2());
        }
    }
    private void reset() {
        for(int i = 0 ; i < 7; i++)
            for(int j = 0; j < 6; j++)
                SetCellColor(Color.WHITE, i, j);
        winner.setText("");
        logic.reset();
    }
    private void setPlayerColor(FourInARowPlayer player, int col, int row) {
        if(row != -1) {
            if (player == FourInARowPlayer.REDPLAYER)
                SetCellColor(Color.RED, col, row);
            else
                SetCellColor(Color.BLUE, col, row);

            switch (logic.getState()) {
                case RUN:
                    break;
                case RED:
                    winner.setText("RED WINNS");
                    break;
                case BLUE:
                    winner.setText("BLUE WINNS");
                    break;
                case DRAW:
                    winner.setText("DRAW");
                    break;
            }
        }
    }
    private void SetCellColor(Color color, int col, int row) {
        switch (col)
        {
            case 0:
                SetRowCol1(color, row);
                break;
            case 1:
                SetRowCol2(color, row);
                break;
            case 2:
                SetRowCol3(color, row);
                break;
            case 3:
                SetRowCol4(color, row);
                break;
            case 4:
                SetRowCol5(color, row);
                break;
            case 5:
                SetRowCol6(color, row);
                break;
            case 6:
                SetRowCol7(color, row);
                break;
        }
    }
    private void SetRowCol1(Color color, int row) {
        switch (row)
        {
            case 0:
                tAr5c1.setBackground(color);
                break;
            case 1:
                tAr4c1.setBackground(color);
                break;
            case 2:
                tAr3c1.setBackground(color);
                break;
            case 3:
                tAr2c1.setBackground(color);
                break;
            case 4:
                tAr1c1.setBackground(color);
                break;
            case 5:
                tAr0c1.setBackground(color);
                break;
        }
    }
    private void SetRowCol2(Color color, int row) {
        switch (row) {
            case 0:
                tAr5c2.setBackground(color);
                break;
            case 1:
                tAr4c2.setBackground(color);
                break;
            case 2:
                tAr3c2.setBackground(color);
                break;
            case 3:
                tAr2c2.setBackground(color);
                break;
            case 4:
                tAr1c2.setBackground(color);
                break;
            case 5:
                tAr0c2.setBackground(color);
                break;
        }
    }
    private void SetRowCol3(Color color, int row) {
        switch (row) {
            case 0:
                tAr5c3.setBackground(color);
                break;
            case 1:
                tAr4c3.setBackground(color);
                break;
            case 2:
                tAr3c3.setBackground(color);
                break;
            case 3:
                tAr2c3.setBackground(color);
                break;
            case 4:
                tAr1c3.setBackground(color);
                break;
            case 5:
                tAr0c3.setBackground(color);
                break;
        }
    }
    private void SetRowCol4(Color color, int row) {
        switch (row) {
            case 0:
                tAr5c4.setBackground(color);
                break;
            case 1:
                tAr4c4.setBackground(color);
                break;
            case 2:
                tAr3c4.setBackground(color);
                break;
            case 3:
                tAr2c4.setBackground(color);
                break;
            case 4:
                tAr1c4.setBackground(color);
                break;
            case 5:
                tAr0c4.setBackground(color);
                break;
        }
    }
    private void SetRowCol5(Color color, int row) {
        switch (row) {
            case 0:
                tAr5c5.setBackground(color);
                break;
            case 1:
                tAr4c5.setBackground(color);
                break;
            case 2:
                tAr3c5.setBackground(color);
                break;
            case 3:
                tAr2c5.setBackground(color);
                break;
            case 4:
                tAr1c5.setBackground(color);
                break;
            case 5:
                tAr0c5.setBackground(color);
                break;
        }
    }
    private void SetRowCol6(Color color, int row) {
        switch (row) {
            case 0:
                tAr5c6.setBackground(color);
                break;
            case 1:
                tAr4c6.setBackground(color);
                break;
            case 2:
                tAr3c6.setBackground(color);
                break;
            case 3:
                tAr2c6.setBackground(color);
                break;
            case 4:
                tAr1c6.setBackground(color);
                break;
            case 5:
                tAr0c6.setBackground(color);
                break;
        }
    }
    private void SetRowCol7(Color color, int row) {
        switch (row) {
            case 0:
                tAr5c7.setBackground(color);
                break;
            case 1:
                tAr4c7.setBackground(color);
                break;
            case 2:
                tAr3c7.setBackground(color);
                break;
            case 3:
                tAr2c7.setBackground(color);
                break;
            case 4:
                tAr1c7.setBackground(color);
                break;
            case 5:
                tAr0c7.setBackground(color);
                break;
        }
    }
}
