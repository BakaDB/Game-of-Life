import java.awt.Graphics;
import java.awt.Color;

public class Cell {
    private boolean alive, nextState;
    private int x, y;
    
    public Cell(boolean alive, int x, int y) {
        this.alive = alive;
        this.x = x;
        this.y = y;
    }
    
    public boolean getAlive() {return alive;}    
    public void setAlive(boolean alive) {this.alive = alive;} 
    
    public boolean getNextState() {return nextState;}
    public void setNextState(boolean nextState) {this.nextState = nextState;}
    
    public void changeState(boolean cellState) {
        if (cellState) alive = false;
        else alive = true;
    }
    
    public void paintCell(Graphics g) {
        if (alive) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(x, y, MainPanel.B, MainPanel.B);
    }
}
