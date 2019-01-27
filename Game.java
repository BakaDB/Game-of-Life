import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {    
    private static final int N = 800; //game size window
    
    private JPanel blockPanel, functionsPanel;
    private static MainPanel mainPanel;
    private JButton startButton;
    
    public Game() {
        //Init Frame
        super("Game of Life");
        
        setFocusable(true);        
        setVisible(true);
        setSize(N, N);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initGUI();        
    }
    
    public void initGUI() {
        //Init Main Panel
        mainPanel = new MainPanel();
        add(mainPanel);
        
        //Init Functions Panel
        functionsPanel = new JPanel();
        functionsPanel.setBackground(Color.DARK_GRAY);
        startButton = new JButton("START");
        startButton.addActionListener(this);
        functionsPanel.add(startButton);
        add(functionsPanel, BorderLayout.SOUTH);
        
        //Init Block Panel
        blockPanel = new JPanel();
        blockPanel.setBackground(Color.DARK_GRAY);    
        add(blockPanel, BorderLayout.NORTH);
    }
    
    public void actionPerformed(ActionEvent e) {
        //When StartButton is clicked
        if (mainPanel.getRun()) {
            mainPanel.setRun(false);
            startButton.setText("START");
        } else {
            mainPanel.setRun(true);
            startButton.setText("STOP");
        }
        //System.out.println(mainPanel.getRun());
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        Thread t = new Thread(mainPanel);
        t.start();
    }
}
