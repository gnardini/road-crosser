package enemies;

import java.awt.Graphics;
import java.awt.Image;

public class Hiena extends Space {

	public Hiena(int lane,int dir,int pos, int num) {
		super(lane, dir, pos, 10, num);
	}
	
	public void draw(Graphics g, Image[] img){
		g.drawImage(img[7],10+getPos(),10+(getLane()+1)*ESCALA,null);
	}
	
	@Override
	public String getName() {
		return "Hiena";
	}

}
