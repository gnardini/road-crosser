package enemies;

import java.awt.Graphics;
import java.awt.Image;

public class Life extends Bonus {

	public Life(int lane,int dir,int pos, int num) {
		super(lane, dir, pos, num);
	}
	
	@Override
	public void draw(Graphics g, Image[] img) {
		g.drawImage(img[18],10+getPos(),10+(getLane()+1)*ESCALA,null);
	}

}
