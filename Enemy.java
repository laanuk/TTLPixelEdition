package Tilt;

import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Dot {
int espeed;
public boolean cyanerds = false;
	public Enemy(int x, int y, int s) {
		super(x, y);
		espeed = s;
	}
	
	public void draw (Graphics2D g2d, int px, int py){
		if (!cyanerds) {
			g2d.setColor(Color.BLACK);
		
//		if (Math.abs(px-x) < 5 && Math.abs(py-y) < 5) System.exit(0);
		
			if (px > x) x+= espeed; 
			else x-= espeed;
			if (py > y) y+= espeed;
			else y-= espeed;
		
			if (x > 700) x = Game.WIDTH - 15;
			if (y > 700) y = Game.HEIGHT - 15;
			if (x < 0) x = 0;
			if (y < 0) y = 0;
		
			g2d.fillOval(x, y, (int) radius, (int) radius);
		}
		if (cyanerds) {
			x = -100; y = -100;
		}
	}

}
