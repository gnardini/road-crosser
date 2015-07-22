package enemies;

import java.awt.Graphics;
import java.awt.Image;

import setup.myConstants;

public abstract class Space implements myConstants{

	private int lane, dir, pos, speed, num;
	
	public Space(int l, int d, int p, int s, int n){
		lane=l;dir=d;pos=p;speed=s;num=n;
	}
	
	public void move(){
		pos=pos+dir*speed;
	}
	public abstract void draw(Graphics g, Image[] img);
	public abstract String getName();
	
	public void changeLane(int n){
		lane += n;
		if(lane<1) lane=1;
		else if(lane>10) lane=10;
	}
	
	public int getLane() {
		return lane;
	}

	public int getPos() {
		return pos;
	}
	
	public int getDir(){
		return dir;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Space other = (Space) obj;
		if (num != other.num)
			return false;
		return true;
	}

	
	
}
