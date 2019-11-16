package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author ve
 * @date 2019/7/17 13:59
 */
public class SnakeThread extends Thread {

    boolean live = true;

    public void run() {

        int x;
        int y;
        while (live) {


            // 拿到修改前头部的位置
            x = Game.snake.getFirst().getBounds().x;
            y = Game.snake.getFirst().getBounds().y;
            // 把数组中贪吃蛇的尾巴嫁接到头部成为新的头部
            // 如果吃过食物,则此次前进使用食物前进而不是嫁接尾部
            if (Game.eatFood) {
                Game.eatFood = false;
                Game.snake.addFirst(new SnakeBody(Game.snake.getFirst().getBounds()));
                Game.gamePanel.add(Game.snake.getFirst());
            } else {
                Game.snake.addFirst(Game.snake.removeLast());
            }
            // 调整新头部的位置,为旧头部的位置的前进一格
            switch (Game.direction) {
                case KeyEvent.VK_UP:
                    Game.snake.getFirst().setLocation(x, y - SnakeBody.BODY_SIDE);
                    break;
                case KeyEvent.VK_DOWN:
                    Game.snake.getFirst().setLocation(x, y + SnakeBody.BODY_SIDE);
                    break;
                case KeyEvent.VK_LEFT:
                    Game.snake.getFirst().setLocation(x - SnakeBody.BODY_SIDE, y);
                    break;
                case KeyEvent.VK_RIGHT:
                    Game.snake.getFirst().setLocation(x + SnakeBody.BODY_SIDE, y);
                    break;
            }

            // 吃食物
            if (Game.food != null && Game.snake.getFirst().getBounds().equals(Game.food.getBounds())) {
                Game.gamePanel.remove(Game.food);
                Game.food = null;
                Game.eatFood = true;
                Game.Integral += 1;
                String fenshu = Integer.toString(Game.Integral);
                Game.labelIntegral.setText("分数: "+fenshu);

                synchronized (Game.foodThread) {
                    Game.foodThread.notifyAll();
                }
            }

            for (int i = 0; i < Game.snake.size(); i++) {
                // 若头部撞到身体,u died
                if (i > 1 && Game.snake.getFirst().getBounds().equals(Game.snake.get(i).getBounds())) {
                    live = false;
                    JOptionPane.showMessageDialog(null, "you died!", "提示", JOptionPane.ERROR_MESSAGE);
                }
                // 对蛇的头部跟尾部进行染色,方便调试
                if (i == 0) {
                    Game.snake.get(i).setBackground(Color.GREEN);
                } else if (i == Game.snake.size() - 1) {
                    Game.snake.get(i).setBackground(Color.RED);
                } else {
                    Game.snake.get(i).setBackground(Color.BLACK);
                }
            }
            // 不允许撞墙
            if (Game.snake.getFirst().getX() < 0 || Game.snake.getFirst().getY() < 0
                    || Game.snake.getFirst().getX() >= Game.gamePanel.getWidth()
                    || Game.snake.getFirst().getY() >= Game.gamePanel.getHeight()) {
                live = false;
                JOptionPane.showMessageDialog(null, "you died!", "提示", JOptionPane.ERROR_MESSAGE);

            }

            try {
                // 蛇的停顿
                Thread.sleep(200 - Game.Integral * 10);
            } catch (InterruptedException e) {
            }
        }
    }
}