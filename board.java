package snakegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class board extends JPanel implements ActionListener {
    private Image apple;
    private Image dot;
    private Image head;
    
    private boolean ingame = true;
    
    private int dots;

    private final int alldots = 900;
    private final int dotsize = 10;
    private final int randompos=29;
    int r;
    
    private int apple_x;
    private int apple_y;
 
    private final int x[]=new int[alldots];
    private final int y[]=new int[alldots];
    
    private Timer timer;
    
    private boolean leftdirection = false;
    private boolean rightdirection = true;
    private boolean updirection = false;
    private boolean downdirection = false;
   
    board()
    {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);

        loadImages();
        initGame();

    }
    public void loadImages()
    { 
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        apple=i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot=i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
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
        
        locateApple();
        
        timer = new Timer(140,this);
        
        timer.start();
    }

    public void locateApple()
    {
       r = ((int) (Math.random()*randompos));
       apple_x = r*dotsize;
       r = ((int) (Math.random()*randompos));
       apple_y = r*dotsize; 

    }
    
    
    public void paintComponent(Graphics g)  
    {
        super.paintComponent(g);        
        draw(g);
    }
    
    public void draw(Graphics g)        
    {   
        if(ingame)
        {
        g.drawImage(apple,apple_x,apple_y,this);
        
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
        else
        {
            gameover(g);
        }
    }
    
    public void gameover(Graphics g)
    {
        String msg = "FUCK YOU!!";
        int sizesnake = (dots-1);
        String score = "Score:" + String.valueOf(sizesnake);
        Font font = new Font("SAN_SERIF",Font.BOLD,20);
        Font fontn = new Font("SERIF",Font.PLAIN,15);

        FontMetrics metrices = getFontMetrics(font);
        FontMetrics metricesn = getFontMetrics(font);
       
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg,(300 - metrices.stringWidth(msg))/2, 300/2);
        g.drawString(score,(300 - metricesn.stringWidth(msg))/2, 500/2);
    }
    
    public void move()
    {
        for(int i=dots;i>0;i--)
        {
            x[i] = x[i-1];      
            y[i] = y[i-1];
        }
        
        if(leftdirection)
        {
            x[0]-=dotsize;
        }
        
        if(rightdirection)
        {
            x[0]+=dotsize;
        }
        
        if(updirection)
        {
            y[0]-=dotsize;
        }
        
        if(downdirection)
        {
            y[0]+=dotsize;
        }
        
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ingame)
        {
        checkapple();
        checkcollision();
        move();
        repaint();
        }
    }
    
    public void checkcollision()
    {
        for(int i=dots;i>0;i--)
        {
            if((i>4) && (x[0]==x[i]) && (y[0]==y[i]))
            {
                ingame = false;
            }
        }
        
        if(y[0]>=300)
        {
            ingame=false;
        }
        if(x[0]>=300)
        {
            ingame=false;
        }
        if(y[0]<0)
        {
            ingame=false;
        }
        if(x[0]<0)
        {
            ingame=false;
        }
        
        if(!ingame)
        {
            timer.stop();
        }
    }
    public void checkapple()
    {
        if((x[0] == apple_x) && (y[0] == apple_y))
        {
            dots++; 
            locateApple();
        }
    }
    
    public class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key = e.getKeyCode();
            
            if(key == KeyEvent.VK_LEFT && (!rightdirection))
            {
                leftdirection = true;
                updirection = false;
                downdirection = false;
            }
            if(key == KeyEvent.VK_RIGHT && (!leftdirection))
            {
                rightdirection = true;
                updirection = false;
                downdirection = false;
            }
            if(key == KeyEvent.VK_DOWN && (!updirection))
            {
                leftdirection = false;
                rightdirection = false;
                downdirection = true;
            }
            if(key == KeyEvent.VK_UP && (!downdirection))
            {
                leftdirection = false;
                rightdirection = false;
                updirection = true;
            }
        }
    }
}
 
