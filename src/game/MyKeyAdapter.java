package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author ve
 * @date 2019/11/16 16:02
 */
public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyReleased(KeyEvent e) {
        if (!Game.begin) {
            Game.snakeThread =   new SnakeThread("SnakeThread");
            Game.snakeThread.start();
            Game.begin = true;
        }

        if (Game.direction == e.getKeyCode()) {
            return;
        }

        int x = Game.snake.get(0).getBounds().x;
        int y = Game.snake.get(0).getBounds().y;
        int x1 = Game.snake.get(1).getBounds().x;
        int y1 = Game.snake.get(1).getBounds().y;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (x == x1 && y - SnakeBody.BODY_SIDE == y1) {
                    return;
                }
                Game.direction = e.getKeyCode();
                break;
            case KeyEvent.VK_DOWN:
                if (x == x1 && y + SnakeBody.BODY_SIDE == y1) {
                    return;
                }
                Game.direction = e.getKeyCode();
                break;
            case KeyEvent.VK_LEFT:
                if (x - SnakeBody.BODY_SIDE == x1 && y == y1) {
                    return;
                }
                Game.direction = e.getKeyCode();
                break;
            case KeyEvent.VK_RIGHT:
                if (x + SnakeBody.BODY_SIDE == x1 && y == y1) {
                    return;
                }
                Game.direction = e.getKeyCode();
                break;
        }
    }
}
