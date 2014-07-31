package Tilt;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game extends JPanel{
	public final static int WIDTH = 700;
	public final static int HEIGHT = 700;
	Player p;
	static boolean  down = false;
	static boolean right = false;
	static boolean up = false;
	static boolean left = false; 
	static long begin;
	static long begin1;
	static long timer;
	Enemy[] enemies = new Enemy[1000];
	Powerup[] powerups = new Powerup[1000];
	static int ecount = 0;
	
	public Game() {
		p = new Player((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("~~~~");
		final Game game = new Game();
		frame.add(game);
		//JLabel timeLabel = new JLabel("TIME");
		//frame.add(timeLabel, BorderLayout.CENTER);
		Container c = frame.getContentPane();
		Dimension d = new Dimension(WIDTH, HEIGHT);
		c.setPreferredSize(d); 
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		begin = System.currentTimeMillis();
		begin1 = begin;
		timer = begin;
		
		frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int ID = e.getKeyCode();    
                if (ID == KeyEvent.VK_ESCAPE) System.exit(0);
                if (ID == KeyEvent.VK_S || ID == KeyEvent.VK_DOWN) down = true; 
                if (ID == KeyEvent.VK_D || ID == KeyEvent.VK_RIGHT)  right = true; 
                if (ID == KeyEvent.VK_W || ID == KeyEvent.VK_UP)  up = true; 
                if (ID == KeyEvent.VK_A || ID == KeyEvent.VK_LEFT)  left = true; 
            }
            public void keyReleased(KeyEvent e){
            	int ID = e.getKeyCode();
            	if (ID == KeyEvent.VK_S || ID == KeyEvent.VK_DOWN) down = false;   
                if (ID == KeyEvent.VK_D || ID == KeyEvent.VK_RIGHT)  right = false; 
                if (ID == KeyEvent.VK_W || ID == KeyEvent.VK_UP)  up = false; 
                if (ID == KeyEvent.VK_A || ID == KeyEvent.VK_LEFT)  left = false; 
            }
        });
		while (true) {
			game.update();
			Thread.sleep(40);
		}
	}
	private void update() throws InterruptedException {
		if (down) p.moveVert(1);
		if (right) p.moveHoriz(1);
		if (up) p.moveVert(-1);
		if (left)  p.moveHoriz(-1);	
		
		if (down) {
			if (((double) System.currentTimeMillis() - begin) > 500) {
				begin = System.currentTimeMillis(); 
				p.speed++;
				if (p.speed > 10) p.speed = 9;
			}
		} 
		
		if (right) {
			if (((double) System.currentTimeMillis() - begin) > 500) {
				begin = System.currentTimeMillis(); 
				p.speed++;
				if (p.speed > 10) p.speed = 9;
			}
		}
		
		if (up) {
			if (((double) System.currentTimeMillis() - begin) > 500) {
				begin = System.currentTimeMillis(); 
				p.speed++;
				if (p.speed > 10) p.speed = 9;
			}
		}
		
		if (left) {
			if (((double) System.currentTimeMillis() - begin) > 500) {  
				begin = System.currentTimeMillis(); 
				p.speed++;
				if (p.speed > 10) p.speed = 9;
			}
		}
		
	
		if (right == false && up == false) {
			if (left == false &&  down == false) { 
				p.speed = 1;
			}
		}
		
		//spawn
		if (((double) System.currentTimeMillis() - begin1) > 1000) {
			begin1 = System.currentTimeMillis();
			if (ecount % 5 == 0) powerups[ecount] =  new Powerup((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
			enemies[ecount] = new Enemy((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT), (int) (Math.random() * 4) + 1);
			ecount++;
		}
			repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		p.draw(g2d);
		for (Enemy e : enemies) {
			for (Powerup r : powerups){
				if (e != null && r!= null) {
					if (r.used && !r.exploded){
						if (Math.sqrt((e.x - r.x) * (e.x - r.x) + (e.y - r.y) * (e.y - r.y)) < r.blastRadius/2 + e.radius/2) {
							e.cyanerds = true;
							e = null;
						}
					}
				}	
			}
			if (e != null) e.draw(g2d, p.x, p.y);
		}
		for (Powerup r : powerups) {
			if (r != null)
				try {
					r.draw(g2d, p.x, p.y);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		}
		Font font = new Font("Serif", Font.PLAIN, 32);
		g.setFont(font); 
		g.drawString("TIME : " + (double) (System.currentTimeMillis() - timer)/1000, HEIGHT - 250, 50);
	}
}
