package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author ve
 * @date 2019/7/17 13:59
 */
public class LiveThread extends Thread {
    public LiveThread() {
        // 初始化游戏面板,加入蛇的身体跟食物
        for (int i = 0; i < max; i++) {
            snake[i] = new JPanel();
            snake[i].setBackground(Color.black);
            container.gamePanel.add(snake[i]);
            snake[i].setBounds(300 - i * 10, 200, 10, 10);
        }
        container.gamePanel.add(food);
    }

    // 食物
    Food food = new Food();
    // 整条蛇
    JPanel[] snake = new JPanel[100];
    // 蛇的身体长度
    int max = 4;
    // 蛇的每个节点的大小
    static int side = 10;
    Game container = new Game();
    int a = 0;
    int b = max - 1;
    boolean eat = false;
    boolean live = true;
    static volatile int direction = 0;
    int x = 0;
    int y = 0;

    public void run() {
        while (live) {
            try {
                Thread.sleep(500 - container.Integral * 10);
            } catch (InterruptedException e) {
            }
            if (snake[a].getX() == food.getX() && snake[a].getY() == food.getY()) {
                container.Integral += 1;
                String fenshu = Integer.toString(container.Integral);
                container.labelIntegral.setText(fenshu);
                int foodX = (int) (Math.random() * 56);
                while (foodX == snake[max - 1].getX()) {
                    foodX = (int) (Math.random() * 56);
                }
                int foodY = (int) (Math.random() * 45);
                while (foodY == snake[max - 1].getY()) {
                    foodY = (int) (Math.random() * 45);
                }
                food.setBounds(foodX * 10, foodY * 10, side, side);
                // 身体变长
                snake[max] = new JPanel();
                snake[max].setBounds(x, y, side, side);
                container.gamePanel.add(snake[max]);
                snake[max].setBackground(Color.black);
                max += 1;
            }
            if (direction == KeyEvent.VK_UP) {
                snake[b].setBounds(snake[a].getX(), snake[a].getY() - 10, side, side);
            } else if (direction == KeyEvent.VK_DOWN) {
                snake[b].setBounds(snake[a].getX(), snake[a].getY() + 10, side, side);
            } else if (direction == KeyEvent.VK_LEFT) {
                snake[b].setBounds(snake[a].getX() - 10, snake[a].getY(), side, side);
            } else if (direction == KeyEvent.VK_RIGHT) {
                snake[b].setBounds(snake[a].getX() + 10, snake[a].getY(), side, side);
            }
            b -= 1;
            a -= 1;
            if (a == -1) {
                a = max - 1;
            }
            if (b == -1) {
                b = max - 1;
            }
        }
    }
}