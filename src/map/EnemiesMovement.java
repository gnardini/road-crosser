package map;

import enemies.Space;
import player.Player;
import setup.Queue;
import setup.myConstants;

public class EnemiesMovement implements Runnable, myConstants {

	private Thread t;
	private Maps map;
	private Player p;
	
	public EnemiesMovement(Maps map, Player p){
		this.map=map;
		this.p=p;
		t= new Thread(this,"Movement");
		t.start();
	}
	
	@Override
	public void run() {
		try{
			while(true){
				for(Queue<Space> q: map.enemies())
				for(Space sp: q){
					sp.move();
					checkTouch(sp);
					if(sp.getPos()<-23 || sp.getPos()>20+10*ESCALA) map.remove(sp.getName());
				}
				Thread.sleep(100);
			}
		}catch(Exception e){}

	}
	
	public void checkTouch(Space s){
		if(s.getLane()!=p.getY()) return;
		if(s.getDir()==1){
			if(s.getPos()+ESCALA>p.getX() && s.getPos()<p.getX()) map.touched(s);
		}else{
			if(s.getPos()<p.getX()+ESCALA && s.getPos()+ESCALA>p.getX()+ESCALA) map.touched(s);
		}
	}
	
}
