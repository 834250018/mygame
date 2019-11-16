package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    static LinkedList<SnakeBody> snake;
    public static int direction;
    public static boolean begin;
    // 食物
    public static Food food;
    public static boolean eatFood;
    public static FoodThread foodThread;
    public static SnakeThread snakeThread;

    /**
     * 整体页面渲染
     */
    public static void init() {
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
        btnClear.addActionListener(new BtnListener(BtnListener.RESET_TYPE));
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
        if (main != null) {
            main.dispose();
        }
        main = new JFrame();
        main.setResizable(false);
        main.setDefaultCloseOperation(main.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setBounds(500, 250, 600, 560);
        main.setContentPane(contentPane);
        // 监听键盘,需要先获取焦点,否则关联不上监听器
        main.setFocusable(true);
        main.addKeyListener(new MyKeyAdapter());

        Integral = 0;
        direction = KeyEvent.VK_RIGHT;
        begin = false;

        // 初始化蛇的身体
        Game.snake = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            Game.snake.push(new SnakeBody(250 + i * SnakeBody.BODY_SIDE, 200));
        }
        for (SnakeBody snakeBody : Game.snake) {
            Game.gamePanel.add(snakeBody);
        }
//        Game.main.repaint();
//        System.out.println("ok");
        // 投入食物
        Game.food = null;
        Game.foodThread = new FoodThread("FoodThread");
        Game.foodThread.start();
    }
}

