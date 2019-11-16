package game;

import javax.swing.*;
import java.awt.*;

/**
 * @author ve
 * @date 2019/11/16 3:01
 */
public class SnakeBody extends JPanel {
    // 蛇的每个节点的大小
    public final static int BODY_SIDE = 10;

    public SnakeBody(int x, int y) {
        setBackground(Color.black);
        setBounds(x, y, BODY_SIDE, BODY_SIDE);
    }

    public SnakeBody(Rectangle rectangle) {
        setBackground(Color.black);
        setBounds(rectangle);
    }
}
