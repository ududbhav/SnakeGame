//we use the JPanel component to divide the frame created into multiple smaller smaller parts
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class board extends JPanel 
{   
    private Image apple;
    private Image dot;
    private Image head;
    
    private int dots;

    private final int alldots = 900;
    private final int dotsize = 10;

    private final int x[]=new int[900];
    private final int y[]=new int[900];
    

   
    board()
    {
        setBackground(Color.BLACK);
        setFocusable(true);

        loadImages();
        initGame();

    }
    public void loadImages()
    {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("gamesnake/icons/apple.png"));
        apple=i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("gamesnake/icons/dot.png"));
        dot=i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("gamesnake/icons/head.png"));
        head=i3.getImage();
    }
    public void initGame()
    {
        dots=3;
        for(int i=0;i<dots;i++)
        {
            y[i] = 50;
            x[i] = 50 - i*dotsize;
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        for(int i=0;i<dots;i++)
        {
            if(i==0)
            {
                g.drawImage(head,x[i],y[i],this);
            }
            else
            {
                g.drawImage(dot,x[i],y[i],this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }
}