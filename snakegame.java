package snakegame;
import java.util.*;
import javax.swing.*;

public class SnakeGame extends JFrame {

    SnakeGame()
    {
        
        super("Snake Game");
        add(new board()); 
        pack(); 

        
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SnakeGame();
    }
    
}
