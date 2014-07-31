package Tilt;

import java.awt.Color;
import java.awt.Graphics2D;


public class Dot {
	public double radius = 10.0;
	int x;
	int y;
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2d) {
		if (x > 700) x = Game.WIDTH - 15;
		if (y > 700) y = Game.HEIGHT - 15;
		if (x < 0) x = 0;
		if (y < 0) y = 0;
		g2d.fillOval(x, y, (int) radius, (int) radius);
	}
}
