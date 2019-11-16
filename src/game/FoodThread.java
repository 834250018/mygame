package game;

/**
 * @author ve
 * @date 2019/11/16 7:43
 */
public class FoodThread extends Thread {
    public FoodThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            // 如果不加打印,就不会再次创建食物
            System.out.println();
            if (Game.food == null) {
                Game.food = new Food();
                Game.gamePanel.add(Game.food);
                Game.gamePanel.updateUI();
            }
            try {
                synchronized (Game.foodThread) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
