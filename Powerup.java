package Tilt;

import java.awt.Color;
import java.awt.Graphics2D;

public class Powerup extends Dot {
	boolean used = false;
	boolean exploded = false;
	int blastRadius = 100;
	
	
	
	public Powerup(int x, int y){
		super(x,y);
		this.radius = 20.0;
	}
	
	public void draw (Graphics2D g2d, int px, int py) throws InterruptedException{
		g2d.setColor(Color.GREEN);
		if (Math.abs(px-x) < 20 && Math.abs(py-y) < 20) used = true; 
		if (!used) super.draw(g2d);
		if(used && !exploded) {
			if (this.radius < blastRadius) {
				this.radius += 1;
				g2d.setColor(Color.GREEN);
				g2d.fillOval(x - (int) this.radius/2, y - (int) this.radius/2, (int) radius, (int) radius);
			}
			else exploded = true;
		}
	}
}
