package enemies;

import java.awt.Graphics;
import java.awt.Image;

import player.Player;

public abstract class Bonus extends Space {

	public Bonus(int lane,int dir,int pos, int num) {
		super(lane, dir, pos, 20, num);
	}
	
	public void benefit(Player p){
		p.addLife();
	}
	
	@Override
	public abstract void draw(Graphics g, Image[] img);

	@Override
	public String getName(){
		return "Bonus";
	}

}
