package Tilt;

import java.awt.Color;
import java.awt.Graphics2D;


public class Player extends Dot {
	
	int speed = 1;
	public Player(int x, int y){
		super(x,y);
	}
	
	public void draw (Graphics2D g2d){
		g2d.setColor(Color.RED);
		super.draw(g2d);
	}
	
	public void moveVert(int dir) {
		if (dir == 1) y+=speed;
		else y-=speed;
	}
	
	public void moveHoriz(int dir) {
		if (dir == 1) x+=speed;
		else x-=speed;
	}
}
