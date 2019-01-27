import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MainPanel extends JPanel implements MouseListener, Runnable {
    private static final int N = 800; //frame size window
    private static final int D = 50;
    public static final int B = N/D;
    
    private Cell[][] cells = new Cell[D][D]; 
    
    private boolean run;
    
    public MainPanel () {
        super.setPreferredSize(new Dimension(N,N));
        run = false;
        addMouseListener(this);
        initCells();
    }
    
    public boolean getRun() {return run;}
    public void setRun(boolean run) {this.run = run;}
    
    public void initCells() {
        for (int x = 0; x < D; x++) {
            for (int y = 0; y < D; y++) {
                cells[x][y] = new Cell(false, x*B, y*B);
            }
        }
    }
    
    public void paint(Graphics g) {
        paintComponents(g);
        
        //Background
        g.setColor(Color.WHITE);
        g.fillRect(0,0,N,N);
        
        //Cell
        for (int x = 0; x < D; x++) {
            for (int y = 0; y < D; y++) {
                cells[x][y].paintCell(g);
            }
        }
        
        //Grid
        g.setColor(Color.BLACK);
        for (int x = 0; x < D; x++) {
            g.drawLine(B*x, 0, B*x, N);
        }
        for (int y = 0; y < D; y++) {
            g.drawLine(0, B*y, N, B*y);            
        }
    }
        
    public void mousePressed(MouseEvent e) {
        int x = e.getX()/B;
        int y = e.getY()/B;
        cells[x][y].changeState(cells[x][y].getAlive());
    }
    
    /*Un-used methods of implemented Objects*/
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
    /** 
     * GAME OF LIFE ALGORITHM
     */
    public void getNeighbours() {
        for (int x = 1; x < D-1; x++) {
            for (int y = 1; y < D-1; y++) {
                if (cells[x][y].getAlive()){                    
                    int count = 0;
                    if (cells[x-1][y-1].getAlive()) count++;
                    if (cells[x][y-1].getAlive()) count++;
                    if (cells[x+1][y-1].getAlive()) count++;
                    if (cells[x-1][y].getAlive()) count++;
                    if (cells[x+1][y].getAlive()) count++;
                    if (cells[x-1][y+1].getAlive()) count++;
                    if (cells[x][y+1].getAlive()) count++;
                    if (cells[x+1][y+1].getAlive()) count++;
                    
                    if (count < 2) cells[x][y].setNextState(false);
                    else if (count == 2 || count == 3) cells[x][y].setNextState(true);
                    else if (count > 3) cells[x][y].setNextState(false);
                    
                } else {
                    int count = 0;
                    if (cells[x-1][y-1].getAlive()) count++;
                    if (cells[x][y-1].getAlive()) count++;
                    if (cells[x+1][y-1].getAlive()) count++;
                    if (cells[x-1][y].getAlive()) count++;
                    if (cells[x+1][y].getAlive()) count++;
                    if (cells[x-1][y+1].getAlive()) count++;
                    if (cells[x][y+1].getAlive()) count++;
                    if (cells[x+1][y+1].getAlive()) count++;
                    
                    if (count == 3) cells[x][y].setNextState(true);
                }
            }
        }
    }
    
    public void run() {
        while(true) {
            if (run) {
                getNeighbours();
                for (int x = 0; x < D; x++) {
                    for (int y = 0; y < D; y++) {
                        cells[x][y].setAlive(cells[x][y].getNextState());
                        System.out.print("h");
                    }
                    System.out.println();
                }
            }            
            repaint();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("ANT");
        frame.setVisible(true);
        frame.setSize(N+10, N+30);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel main = new MainPanel();
        
        frame.add(main);
        
        Thread t = new Thread(main);
        t.start();
    }
}