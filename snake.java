
import java.util.*;
import javax.swing.*;

public class snake extends JFrame
{
    snake()
    {
        //setLocation(700,300);
        super("Snake Game");
        add(new board()); //anything that needs to be added on the frame has to be taken from this board class
        pack(); //similar functions like the set visible it shows all the changes that are done in the frame

        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        new snake();
    }
}