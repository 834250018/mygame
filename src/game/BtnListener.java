package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ve
 * @date 2019/11/14 1:19
 */
public class BtnListener implements ActionListener {

    private int type;

    static final int RESET_TYPE = 1;
    static final int HELP_TYPE = 2;
    static final int RANK_TYPE = 3;

    public BtnListener(int type) {
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (type) {
            case RESET_TYPE:
                Game.foodThread.stop();
                Game.foodThread = null;
                if (Game.snakeThread != null) {
                    Game.snakeThread.stop();
                    Game.snakeThread = null;
                }
                Game.init();
//                Game.foodThread = new FoodThread("FoodThread");
//                Game.main.setFocusable(true);
//                Game.main.repaint();
                break;
            case HELP_TYPE:
                JOptionPane.showMessageDialog(null, "待开发", "提示ʾ", JOptionPane.INFORMATION_MESSAGE);
                break;
            case RANK_TYPE:
                break;
        }
    }
}
