package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * @author ve
 * @date 2019/7/17 13:58
 */

public class Game {
    // 整体框架,包含主面板
    static JFrame main;
    // 主面板,包含游戏面板跟菜单面板
    static JPanel contentPane;
    // 游戏面板,包含蛇、食物还有边界
    static JPanel gamePanel;
    // 菜单面板,包含帮助按钮、排行榜按钮、重置按钮、统计积分
    static JPanel menuPanel;
    // 统计积分
    static int Integral;
    // 积分文本
    static JLabel labelIntegral;

    // 整条蛇
    static LinkedList<SnakeBody> snake = new LinkedList<>();
    public static int direction = KeyEvent.VK_RIGHT;
    public static int not_direction = 0;
    public static boolean begin = false;
    // 食物
    public static Food food;
    public static boolean eatFood;
    public static FoodThread foodThread;

    /**
     * 整体页面渲染
     */
    public static void initSnake() {
        // 初始化蛇的身体
        for (int i = 0; i < 8; i++) {
            Game.snake.push(new SnakeBody(250 + i * SnakeBody.BODY_SIDE, 200));
        }
        for (SnakeBody snakeBody : Game.snake) {
            Game.gamePanel.add(snakeBody);
            Game.gamePanel.updateUI();
        }
    }

    /**
     * 整体页面渲染
     */
    public static void initContainer() {
        // 初始化按钮
        int BUTTON_HEIGHT = 23;
        int BUTTON_WIDTH = 93;
        JButton btnClear = new JButton("重置");
        btnClear.setBounds(258, 21, BUTTON_WIDTH, BUTTON_HEIGHT);
        JButton btnRank = new JButton("排行报");
        btnRank.setBounds(361, 21, BUTTON_WIDTH, BUTTON_HEIGHT);
        JButton btnHelp = new JButton("帮助");
        btnHelp.setBounds(464, 21, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnHelp.addActionListener(new BtnListener(BtnListener.HELP_TYPE));
        btnRank.addActionListener(new BtnListener(BtnListener.RANK_TYPE));
        btnClear.addActionListener(new BtnListener(BtnListener.CLEAR_TYPE));
        // 积分文本框
        labelIntegral = new JLabel("分数");
        labelIntegral.setBounds(120, 12, 100, 40);

        // 初始化菜单面板,加入按钮
        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setBounds(6, 460, 570, 55);
        menuPanel.add(labelIntegral);
        menuPanel.add(btnClear);
        menuPanel.add(btnRank);
        menuPanel.add(btnHelp);

        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        gamePanel.setBorder(BorderFactory.createEtchedBorder());
        gamePanel.setBounds(6, 4, 570, 450);
        gamePanel.setVisible(true);
        gamePanel.setFocusable(true);

        // 初始化主面板,加入菜单面板跟游戏面板
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setLayout(null);
        contentPane.add(gamePanel, BorderLayout.NORTH);
        contentPane.add(gamePanel, BorderLayout.CENTER);
        contentPane.add(menuPanel, BorderLayout.SOUTH);

        // app界面
        main = new JFrame();
        main.setResizable(false);
        main.setDefaultCloseOperation(main.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setBounds(500, 250, 600, 560);
        // 监听键盘
        main.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (!begin) {
                    SnakeThread snakeThread = new SnakeThread();
                    snakeThread.start();
                    begin = true;
                }

                if (direction == e.getKeyCode()) {
                    return;
                }

                int x = snake.get(0).getBounds().x;
                int y = snake.get(0).getBounds().y;
                int x1 = snake.get(1).getBounds().x;
                int y1 = snake.get(1).getBounds().y;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (x == x1 && y - SnakeBody.BODY_SIDE == y1) {
                            return;
                        }
                        direction = e.getKeyCode();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (x == x1 && y + SnakeBody.BODY_SIDE == y1) {
                            return;
                        }
                        direction = e.getKeyCode();
                        break;
                    case KeyEvent.VK_LEFT:
                        if (x - SnakeBody.BODY_SIDE == x1 && y == y1) {
                            return;
                        }
                        direction = e.getKeyCode();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (x + SnakeBody.BODY_SIDE == x1 && y == y1) {
                            return;
                        }
                        direction = e.getKeyCode();
                        break;
                }
            }
        });
        main.setContentPane(contentPane);

        Integral = 0;
    }
}

