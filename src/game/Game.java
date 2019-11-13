package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author ve
 * @date 2019/7/17 13:58
 */

public class Game {
    // 整体框架,包含主面板
    JFrame main;
    // 主面板,包含游戏面板跟菜单面板
    JPanel contentPane;
    // 游戏面板,包含蛇、食物还有边界
    JPanel gamePanel;
    // 菜单面板,包含帮助按钮、排行榜按钮、重置按钮、统计积分
    JPanel menuPanel;
    // 统计积分
    int Integral;
    // 积分文本
    JLabel labelIntegral;

    static void begin() {
        // 启动蛇的移动线程
        new LiveThread().start();
    }

    public Game() {
        // 初始化页面数据
        initContainer();
    }

    /**
     * 整体页面渲染
     */
    public void initContainer() {
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
        labelIntegral = new JLabel("积分");
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
        main.setDefaultCloseOperation(main.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setBounds(500, 250, 600, 560);
        // 监听键盘
        main.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (LiveThread.direction == e.getKeyCode()) {
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    LiveThread.direction = KeyEvent.VK_UP;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    LiveThread.direction = KeyEvent.VK_DOWN;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    LiveThread.direction = KeyEvent.VK_LEFT;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    LiveThread.direction = KeyEvent.VK_RIGHT;
                }
            }
        });
        main.setContentPane(contentPane);

        Integral = 0;
    }
}

