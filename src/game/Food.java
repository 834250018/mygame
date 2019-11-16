package game;

import javax.swing.*;
import java.awt.*;

/**
 * @author ve
 * @date 2019/11/14 1:00
 */
public class Food extends JPanel {

    public Food() {
        int foodX = 0, foodY = 0;
        // 寻找可投放食物的位置
        for (boolean again = true; again; ) {
            again = false;
            foodX = ((int) (Math.random() * 56)) * 10;
            foodY = ((int) (Math.random() * 45)) * 10;
            for (SnakeBody snakeBody : Game.snake) {
                if (foodX == snakeBody.getX() && foodY == snakeBody.getY()) {
                    again = true;
                }
            }
        }
        setBounds(foodX, foodY, SnakeBody.BODY_SIDE, SnakeBody.BODY_SIDE);
        setBackground(Color.red);
    }
}
