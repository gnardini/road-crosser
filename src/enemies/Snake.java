package enemies;

import java.awt.Graphics;
import java.awt.Image;

public class Snake extends Space {

	public Snake(int lane,int dir,int pos, int num) {
		super(lane, dir, pos, 5, num);
	}
	
	public void draw(Graphics g, Image[] img){
		g.drawImage(img[11],10+getPos(),10+(getLane()+1)*ESCALA,null);
	}
	
	@Override
	public String getName() {
		return "Snake";
	}
}
