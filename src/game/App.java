package game;

/**
 * @author ve
 * @date 2019/7/17 13:58
 */

public class App {

    public static void main(String[] args) throws InterruptedException {
        Game.initContainer();
        Game.initSnake();
        // 投入食物
        Game.foodThread = new FoodThread();
        Game.foodThread.start();

    }

}