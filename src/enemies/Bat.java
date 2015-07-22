package enemies;

import java.awt.Graphics;
import java.awt.Image;

public class Bat extends Space {

	public Bat(int lane,int dir,int pos, int num) {
		super(lane, dir, pos, 8, num);
	}
	
	public void draw(Graphics g, Image[] img){
		g.drawImage(img[5],10+getPos(),10+(getLane()+1)*ESCALA,null);
	}
	
	public void move(){
		super.move();
		int n = (int)(Math.random()*100);
		if(n<3) changeLane(-1); 
		else if(n<6) changeLane(1);
	}
	
	@Override
	public String getName() {
		return "Bat";
	}

}
